package databaseO;

import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DuangClass.Record;
 
public class recorddao {
	private DBFactory dbf = new DBFactory();

	/*public void insert(Student stu) {
		String sql = "insert into manager values(?,?,?)";
		Object[] params = {stu.getRecord_id(), stu.getMoney(), stu.getAge() };
		dbf.execSqlWithoutResultset(sql, params);
	}*/
	
	public void update(Record re) {
		String sql = "update record set state_re=? where record_id=?";
		Object[] params = {  re.getState_re(),re.getRecord_id()};
		dbf.executeSQLWithoutResult(sql, params);
	}
	
	public void delete(String id) {
		String sql = "delete from manager where m_id=?";
		Object[] params = { id };
		dbf.executeSQLWithoutResult(sql, params);
	}
	
	
	public List<Record> search() {
		List<Record> list = new ArrayList<Record>();
		try {String sql = "select * from manager";
			//String sql = "select * from user";
			Object[] params = { };
			ResultSet rs = dbf.executeSQLWithResult(sql, params);
			//ResultSet rs2 = dbf.execSqlWithResultset(sq12, params);
			while (rs.next()) {
				Record r = new Record();
				r.setMoney(rs.getString("money"));
				r.setRecord_id(rs.getString("record_id"));
				r.setDatetime(rs.getString("datetime"));
				r.setstate_re(rs.getString("state_re"));
				
				list.add(r);
			}
			dbf.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
