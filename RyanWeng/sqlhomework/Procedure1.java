package sqlhomework;

import java.util.Calendar;

public class Procedure1 {

	public static void main(String[] args) {
		Procedure1DAO dao=new Procedure1DAO();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2009,Calendar.DECEMBER,25,13,0,0);   //check APT calendar method "set"
        java.sql.Timestamp ptime=new java.sql.Timestamp(calendar.getTimeInMillis());
        PlaylistBean pbean=dao.insert(ptime, 1, "A廳");
        System.out.println(pbean);
        
        M_roomBean mbean=dao.select("A廳");
        int row=mbean.getSeat_row();
        int col=mbean.getSeat_col();
        int count=0;
        
        for(int i=1;i<=row;i++){
			for(int j=1;j<=col;j++){
                SeatBean seat=new SeatBean();
                calendar.set(2009,Calendar.DECEMBER,25,13,0,0);   //check APT calendar method "set"
                ptime=new java.sql.Timestamp(calendar.getTimeInMillis());
                seat.setPtime(ptime);
                seat.setMovie(1);
                String fori = String.format("%02d", i);  //需顯示二位數的字串數字，前方補零
                String forj = String.format("%02d", j);
                seat.setSeat_num(fori+"-"+forj);
                seat.setSold("0");

                count+=dao.insert2(seat);
			}
		}
        System.out.println("insert "+count+" rows");
	}

}
