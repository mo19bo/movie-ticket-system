package DuangFunc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import databaseO.DBFactory;


public class initalize {

	@SuppressWarnings("deprecation")
	public static  void initalize() {
		
		DBFactory db=new DBFactory();
		String SQL="select * from [sence]";
		Object[] params={};
		Date nowDate=new Date();
		ResultSet rsResultSet=db.executeSQLWithResult(SQL, params);
		try {
			while(rsResultSet.next()){
				if(rsResultSet.getDate("Date").getDay()!=nowDate.getDay()){
					 String hallString=rsResultSet.getString("h_num");
					 
					 SQL=" select ss_struct from [ss_struct] where h_num=?";
					 Object[]  paramsa={hallString};
					 ResultSet rsResult=db.executeSQLWithResult(SQL, paramsa);
				     while(rsResult.next()){
				    	 String full_seatString=rsResult.getString("ss_struct");
				    	 SQL=" update [sence] set full_seat=? where h_num=?";
						 Object[]  paramsb={full_seatString,hallString};
				    	 db.executeSQLWithoutResult(SQL, paramsb);
				     }
					
				}
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
      initalize();
	}

}
