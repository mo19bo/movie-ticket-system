//  ��ע��ʹ��   ���  2015-3-16
/*��ݿ������
 * 
 * ����dao package����ʹ�ã�
 * �� getConnection�����е�URL�ĳ��Լ�����ݿ�URL 
 */

package databaseO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import po.Staff;
 
public class Staffdao {
	private DBFactory dbf;
     public Staffdao(DBFactory db) {
    	 dbf=db;
    	 
		// TODO Auto-generated constructor stub
	}
	public void insert(Staff sta) {
		String sql = "insert into manager values(?,?,?)";
		Object[] params = {sta.getS_id(), sta.getS_name(), sta.getS_pwd() };
		dbf.executeSQLWithoutResult(sql, params);
	}
	
	/* public void update(Staff sta) {
		String sql = "update student set name=?,age=? where studentid=?";
		Object[] params = { sta.getS_id(), sta.getS_name(), sta.getS_pwd() };
		dbf.execSqlWithoutResultset(sql, params);
	}
	*/
	public void delete(String id) {
		try {
			String sql = "delete from manager where m_id=?";
			Object[] params = { id };
			dbf.executeSQLWithoutResult(sql, params);
		} catch (Exception e) {
		     System.out.println(e.toString());
		}
		dbf.close();
	}
	
	
	public List<Staff> search() {
		List<Staff> list = new ArrayList<Staff>();
		try {String sql = "select * from staff";
			//String sql = "select * from user";
			Object[] params = { };
			ResultSet rs = dbf.executeSQLWithResult(sql, params);
			//ResultSet rs2 = dbf.execSqlWithResultset(sq12, params);
			while (rs.next()) {
				Staff s = new Staff();
				s.setS_id(rs.getString("s_id"));
				s.setS_name(rs.getString("s_name"));
				s.setS_pwd(rs.getString("s_pwd"));
				
				list.add(s);
			}
			dbf.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

