package databaseO;

import DuangClass.Session;
public class sceneDao {
	private DBFactory dbos = new DBFactory();
	public void updateScene(Session se,String s_num){
		String sql = "update sence set h_num=?,mv_id=?,Date=?,start_time=?,end_time=? where s_num=?";
		Object[] params = {se.getHallId(),se.getMovieId(),se.getSession_Date(),"00-00-00 "+se.getStart_Time(),"00-00-00 "+se.getEnd_Time(),s_num};
		dbos.executeSQLWithResult(sql, params);
	}
}
