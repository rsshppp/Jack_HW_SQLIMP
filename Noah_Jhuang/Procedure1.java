import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Procedure1 {

	public static void main(String[] args) throws ParseException {
	
		Procedure1 pd = new Procedure1();
		pd.insert("2009-12-25 13:00", 1, "A廳");
		pd.select("A廳");
		pd.insertRowCol("2009-12-25 13:00", 1, "25-20", "0", null);
	}
	
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName = northwind";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "passw0rd";
	
	private static final String INSERT_PLAYLIST = "insert into playlist values (?, ?, ?)";
	
	public boolean insert(String ptime, int movie, String roomid) throws ParseException {

		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(INSERT_PLAYLIST);

			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			java.sql.Timestamp tstamp = new Timestamp(sdformat.parse(ptime).getTime());
			pstmt.setTimestamp(1, tstamp);
			pstmt.setInt(2, movie);
			pstmt.setString(3, roomid);
			
			count = pstmt.executeUpdate();
			
			System.out.println("insert count = " + count);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private static final String SELECT_BY_ROOMID = "select roomid, seat_row, seat_col from m_room where roomid = ?";
	
	public ResultSet select(String roomid) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(SELECT_BY_ROOMID);
			pstmt.setString(1, roomid);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				System.out.println("指定廳院為:" + rs.getString("roomid"));
				System.out.println("row = " + rs.getInt("seat_row"));
				System.out.println("col = " + rs.getInt("seat_col"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	private static final String INSERT_BY_ROW_COL = "insert into seats values (?, ?, ?, ?, ?)";
	
	public boolean insertRowCol(String ptime, int movie,
			String seat_num, String sold, String custid) throws ParseException {

		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(INSERT_BY_ROW_COL);

			String[] s = seat_num.split("-");
			// row
			int row = Integer.valueOf(s[0]);
			// col
			int col = Integer.valueOf(s[1]);

			for (int m = 1; m <= row; m++) {
				for (int n = 1; n <= col; n++) {
					SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					java.sql.Timestamp tstamp = new Timestamp(sdformat.parse(ptime).getTime());
					pstmt.setTimestamp(1, tstamp);
					
					pstmt.setInt(2, movie);
					pstmt.setString(3, String.format("%02d-%02d", m, n));
					pstmt.setString(4, sold);
					pstmt.setString(5, custid);
					//pstmt.setNull(5, 4);

					count = count + pstmt.executeUpdate();
				}
			}
			System.out.println("insert count = " + count);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
