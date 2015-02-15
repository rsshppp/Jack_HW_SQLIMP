package sqlhomework;

import java.util.Calendar;

public class Procedure2 {

	public static void main(String[] args) {
		Procedure1DAO dao=new Procedure1DAO();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2009,Calendar.DECEMBER,25,13,0,0);   //check APT calendar method "set"
        java.sql.Timestamp ptime=new java.sql.Timestamp(calendar.getTimeInMillis());
        PlaylistBean pbean=dao.insert(ptime, 1, "A廳");
        System.out.println(pbean);
        
  /*    int i=dao.insert3("2009-12-25 13:00", 1, pbean.getRoomid());
        System.out.println("insert "+i+" rows");   */  ////???只回傳"1"
        boolean b=dao.insert3("2009-12-25 13:00", 1, pbean.getRoomid());
        System.out.println("是否呼叫stored procedure(新增)成功:"+b);

	}

}
