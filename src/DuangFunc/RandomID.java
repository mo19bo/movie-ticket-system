//修改时间   2015-3-12   冯亮
//随机生成ID 的类
package DuangFunc;

import java.util.Calendar;

public class RandomID {
	
	private static final int tt_id = 1;
	private static final int record_id = 2;
	private static final int user_id = 3;
	private static final int o_id = 4;
	private static final int m_id = 5;
	private static final int mv_id = 6;
	private static final int s_id = 7;
	private static final int a_id = 8;
	private static final int ss_id = 9;
	private static final int mer_id = 10;
	private static final int staff_id = 11;//工作人员ID
	//随机生成ID的私有函数
	private String generation(int tableno){			
		
		String tableNo = String.valueOf(tableno);		//表的序号
		Calendar time = Calendar.getInstance();
		long timeOfDate = time.get(Calendar.YEAR)*10000+(time.get(Calendar.MONTH)+1)*100+
				time.get(Calendar.DAY_OF_MONTH);
		long timeofDay = time.get(Calendar.HOUR_OF_DAY)*10000+time.get(Calendar.MINUTE)*100+time.get(Calendar.SECOND);
		long millsecond = time.get(Calendar.MILLISECOND);			//生成时间年月
		
		//时间字符串
		String timeString = (String.valueOf(timeOfDate)).concat((String.valueOf(timeofDay)).concat(String.valueOf(millsecond)));		
		
		
		String zero = "0";			
		String randomID = "";
		if (tableno<10) {
			randomID = (zero.concat(tableNo)).concat(timeString);	
			//当表的序号小于10的时候 第一个字符用0填充
		}else {
			randomID = tableNo.concat(timeString);	
		}
		return randomID;
	}
	
	
	//供其他类调用的方法
	public String tt_id() {
		return generation(tt_id);
	}
	public String record_id(){
		return generation(record_id);
	}
	public String user_id (){
		return generation(user_id);
	}
	public String o_id (){
		return generation(o_id);
	}
	public String m_id (){
		return generation(m_id);
	}
	public String mv_id (){
		return generation(mv_id);
	}
	public String s_id (){
		return generation(s_id);
	}
	public String a_id()
	{
		return generation(a_id);
	}
	public String s_num()
	{
		return generation(ss_id);
	}
	public String mer_id()
	{
		return generation(mer_id);
	}
	public String generateStaffid()
	{
		return generation(staff_id);
	}
}

