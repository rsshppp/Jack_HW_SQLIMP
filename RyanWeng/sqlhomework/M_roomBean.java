package sqlhomework;

public class M_roomBean {
     String roomid;
     int seat_row;
     int seat_col;
     
	@Override
	public String toString() {
		return "M_roomBean [roomid=" + roomid + ", seat_row=" + seat_row
				+ ", seat_col=" + seat_col + "]";
	}
	
	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	public int getSeat_row() {
		return seat_row;
	}
	public void setSeat_row(int seat_row) {
		this.seat_row = seat_row;
	}
	public int getSeat_col() {
		return seat_col;
	}
	public void setSeat_col(int seat_col) {
		this.seat_col = seat_col;
	}
     
     
	    
}
