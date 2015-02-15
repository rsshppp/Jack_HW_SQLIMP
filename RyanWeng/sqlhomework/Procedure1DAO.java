package sqlhomework;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Procedure1DAO {
    private static final String URL="jdbc:sqlserver://localhost:1433;databaseName=northwind";
    private static final String USERNAME="sa";
    private static final String PASSWORD="passw0rd";
    
    private static final String INSERT="insert into playlist values (?, ?, ?)";
    
    public PlaylistBean insert(java.sql.Timestamp ptime,int movie,String roomid){
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;
    	PlaylistBean pbean=null;
    	
    	try {
			conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt=conn.prepareStatement(INSERT);
			pstmt.setTimestamp(1,ptime);
			pstmt.setInt(2,movie);
			pstmt.setString(3,roomid);
			pstmt.executeUpdate();
			
			pstmt=conn.prepareStatement("select*from playlist where roomid=?");
			pstmt.setString(1, roomid);;
			rs=pstmt.executeQuery();
			if(rs.next()){
				pbean=new PlaylistBean();
				pbean.setPtime(rs.getTimestamp("ptime"));
				pbean.setMovie(rs.getInt("movie"));
				pbean.setRoomid(rs.getString("roomid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	
    	return pbean;
    }
    private static final String SELECT="select seat_row,seat_col from m_room where roomid=?";
    public M_roomBean select(String roomid){
    	M_roomBean mbean=null;
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;
    	
    	try {
			conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt=conn.prepareStatement(SELECT);
			pstmt.setString(1, roomid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				mbean=new M_roomBean();
	//			mbean.setRoomid(rs.getString("roomid")); //(important!!!)並沒收搜尋此欄位,此行得消掉
				mbean.setSeat_row(rs.getInt("seat_row"));
				mbean.setSeat_col(rs.getInt("seat_col"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	return mbean;
    }
    
    private static final String INSERT2="insert into seats values (?, ?, ?, ?, ?);";
    
    public int insert2(SeatBean seat){
    	int updateCount=0;
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;
    	
    	try {
			conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt=conn.prepareStatement(INSERT2);
			pstmt.setTimestamp(1, seat.getPtime());
			pstmt.setInt(2, seat.getMovie());
			pstmt.setString(3, seat.getSeat_num());
			pstmt.setString(4, seat.getSold());
			pstmt.setString(5, null);
			updateCount=pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
    	return updateCount;
    }
    
    
    private static final String INSERT_BY_SP="{call gen_seats(?,?,?)}";  //p27,49,53
    
    public boolean insert3(String ptime,int movie,String roomid){  //參數依照stored_procedure之傳出入之參數
        Connection conn=null;
    	CallableStatement cstmt=null;
    	
    	try {
			conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			cstmt=conn.prepareCall(INSERT_BY_SP);
			cstmt.setString(1, ptime);
			cstmt.setInt(2, movie);
			cstmt.setString(3,roomid);
			if(cstmt.execute()){  //回傳值為false表示跟更新(傳回更動筆數),true表示查詢(傳回資料列),p50
				conn.rollback();   //if true
			}else{      //if false
				// n=cstmt.getUpdateCount();  //???只回傳"1"
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (cstmt!=null) {
				try {
					cstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	
    	
    	return false;
    }
}
