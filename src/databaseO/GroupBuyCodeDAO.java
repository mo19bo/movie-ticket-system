//冯亮 2015-3-19
//团购码数据库操作类
package databaseO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DuangClass.GroupBuy;


public class GroupBuyCodeDAO {
	

	//插入数据库表 t_ticket数据操作
	public void insertGBCode(DBFactory dbFactory,GroupBuy groupBuy,String[] string){
		//SQL插入语句
		
		Object[] params = {groupBuy.getSerial_code(),groupBuy.getDate_t(),
				groupBuy.getTt_date(),groupBuy.getTt_id()
		};
		for(int i=0;i<string.length;i++){
			String insertCodeSQL = "insert into t_ticket values('"+string[i]+"',null,null,null)";
			//执行SQL操作
			dbFactory.executeSQLWithoutResult(insertCodeSQL, params);
			
		}
		
		
	}
	
	
	//查询数据库表t_ticket数据 操作
	public String[] searchGBCode(DBFactory dbFactory,GroupBuy groupBuy) {
		
		
		//定义一个数组用来存储团购码
		int numOft_ticket = dbFactory.CountRowNumber("t_ticket");
		String[] gBCode = new String[numOft_ticket];
		List<GroupBuy>  gblist = new ArrayList<GroupBuy>();
		try {
			String sql = "select serial_code from [t_ticket] ";
			Object[] params = {groupBuy.getSerial_code()};
			ResultSet rSet = dbFactory.executeSQLWithResult(sql, params);
			while(rSet.next()){
				GroupBuy gBuy = new GroupBuy();
				gBuy.setSerial_code(rSet.getString("serial_code"));
				gblist.add(gBuy);
			}
			for(int i =0;i<numOft_ticket;i++){
				groupBuy = gblist.get(i);
				gBCode[i] = groupBuy.getSerial_code();
			}
			
			return gBCode;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	//判断用户输入的团购码是否正确
	public boolean isGBCode(String userInputCode,String[] SQLOutputCode){
		for(int i =0;i<SQLOutputCode.length;i++){
			//如果数据库中有一个团购码和用户输入的相匹配 则返回true 跳出
			if (userInputCode.equals(SQLOutputCode[i])) {
				return true;
			}
		}
		//如果没有匹配的则返回false
		return false;
	}
}
