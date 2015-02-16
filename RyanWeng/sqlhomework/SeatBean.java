package sqlhomework;

public class SeatBean {
	private java.sql.Timestamp ptime;
	private int movie;
	private String seat_num;
	private String sold;
	private String custid;
	
	
	
	
	@Override
	public String toString() {
		return "SeatBean [ptime=" + ptime + ", movie=" + movie + ", seat_num="
				+ seat_num + ", sold=" + sold + ", custid=" + custid + "]";
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
	public String getSeat_num() {
		return seat_num;
	}
	public void setSeat_num(String seat_num) {
		this.seat_num = seat_num;
	}
	public String getSold() {
		return sold;
	}
	public void setSold(String sold) {
		this.sold = sold;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	
	

}
