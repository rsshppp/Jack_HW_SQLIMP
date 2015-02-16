package sqlhomework;

public class PlaylistBean {
	private java.sql.Timestamp ptime;
	private int movie;
	private String roomid;
	
	
	@Override
	public String toString() {
		return "PlaylistBean [ptime=" + ptime + ", movie=" + movie
				+ ", roomid=" + roomid + "]";
	}
	
	public java.sql.Timestamp getPtime() {
		return ptime;
	}
	public void setPtime(java.sql.Timestamp ptime) {
		this.ptime = ptime;
	}
	public int getMovie() {
		return movie;
	}
	public void setMovie(int movie) {
		this.movie = movie;
	}
	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}       
	         
	 
}
