import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Procedure2 {
	public static void main(String[] args) {
		
		Procedure2 pd = new Procedure2();
		pd.callable_insert("2009-12-25 13:00", 1, "A廳");
	}

	public boolean callable_insert(String ptime, int movie, String roomid) {
		// JAVA程式中 呼叫 gen_seats stored procedure(新增)指定場次的電影座次表 到 seats表格
		final String URL = "jdbc:sqlserver://localhost:1433;databaseName = northwind";
		final String USER = "sa";
		final String PASSWORD = "passw0rd";
		
		String sql = "{call gen_seats(?, ?, ?)}";
		boolean result = true;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				CallableStatement cstmt = conn.prepareCall(sql);) {

			// 呼叫 JDBC 輸入'2009-12-25 13:00', 1, 'A廳' 到 playlists 表格

			cstmt.setString(1, ptime);
			cstmt.setInt(2, movie);
			cstmt.setString(3, roomid);
			result = cstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!result) { // 回傳值為false是成功,true是失敗
			System.out.println("執行gen_seats預存程序成功!");
		} else {
			System.out.println("執行gen_seats預存程序失敗...");
		}
		return result;
	}

}