package databaseO;

import java.io.BufferedWriter; 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import DuangFunc.MessageDialog;

public class logincheck  {
	public boolean userIsLogined = false;
	private String name;
	private String pwd;
   private Shell shell;
	
	public logincheck(Shell shell_x) {
		shell=shell_x;
	}
	public boolean checkUsername(String name_t, String password)
			throws SQLException {
		DBFactory dbFactory = new DBFactory();
		String sql = "select * from [user] where user_name=?";

		Object params[] = { name_t };
		ResultSet rs = dbFactory.executeSQLWithResult(sql, params);
		// 判读用户名是否存在

		if (!rs.next()) {
 
			return false;

		}
		// 再判断用户密码是否匹配
		else if (rs.getString("password").equals(password)) {
			System.out.println(rs.getString("password"));
			String userid = null;
			String s = "select user_id from [user] where user_name=?";
			ResultSet r = dbFactory.searchUserID(s, name_t);
			r.next();
			userid = r.getString("user_id");
			System.out.println(userid);
			File file = new File("login.txt");
			try {
				//file.createNewFile();
				if(file.exists())
					file.delete();
				FileWriter fw = new FileWriter(file.getName(),true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(userid);
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
		} else
			System.err.println("密码错误");
		return false;
		// }
	}
	//判断管理员是否存在
	

	public boolean checkManagername(String name_t, String password)
			throws SQLException {
		DBFactory dbFactory = new DBFactory();
		String sql = "select * from [manager] where m_name=?";

		Object params[] = { name_t };
		ResultSet rs = dbFactory.executeSQLWithResult(sql, params);
		// 判读用户名是否存在

		if (!rs.next()) {
			
			 MessageDialog mdDialog=new MessageDialog(shell,SWT.DIALOG_TRIM,"管理员名称错误", 2);
		   
			//System.err.println();
			return false;

		}
		// 再判断用户密码是否匹配
		else if (rs.getString("m_password").equals(password)) {
			System.out.println(rs.getString("m_password"));
			String managerid = null;
			String s = "select m_id from [manager] where m_name=?";
			ResultSet r = dbFactory.searchUserID(s, name_t);
			r.next();
			managerid = r.getString("m_id");
			System.out.println(managerid);
			File file = new File("login.txt");
			try {
				//file.createNewFile();
				if(file.exists())
					file.delete();
				FileWriter fw = new FileWriter(file.getName(),true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(managerid);
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
		} else
			System.err.println("密码错误");
		return false;
		// }
	}
	
	//staff登陆时
	public boolean checkStaffname(String name_t, String password)
			throws SQLException {
		DBFactory dbFactory = new DBFactory();
		String sql = "select * from [staff] where s_name=?";

		Object params[] = { name_t };
		ResultSet rs = dbFactory.executeSQLWithResult(sql, params);
		// 判读用户名是否存在

		if (!rs.next()) {
			 MessageDialog mdDialog=new MessageDialog(shell,SWT.DIALOG_TRIM,"工作人员错误", 2);
			return false;

		}
		// 再判断用户密码是否匹配
		else if (rs.getString("s_password").equals(password)) {
			System.out.println(rs.getString("s_password"));
			String userid = null;
			String s = "select s_id from [staff] where s_name=?";
			ResultSet r = dbFactory.searchUserID(s, name_t);
			r.next();
			userid = r.getString("s_id");
			System.out.println(userid);
			File file = new File("login.txt");
			try {
				//file.createNewFile();
				if(file.exists())
					file.delete();
				FileWriter fw = new FileWriter(file.getName(),true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(userid);
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
		} else
			System.out.println("密码错误");
		return false;
		// }
	}
	//获取id
	public String getid(String nameString) throws SQLException{
		DBFactory dbFactory = new DBFactory();
		String sql = "select user_id from [user] where user_name=?";
		Object params[] = { nameString };
		ResultSet resultSet = dbFactory.executeSQLWithResult(sql, params);
		if(!resultSet.next()){
			String SQL = "select m_id from [manager] where m_name = ?";
			Object params1[] = {nameString};
			ResultSet rSet = dbFactory.executeSQLWithResult(SQL, params1);
			
				String managerId = null;
				rSet.next();
				managerId = rSet.getString("m_id");
				System.out.println("管理员");
				return managerId;	
		}else{

		
		String useridString = null;
		useridString = resultSet.getString("user_id");
		System.out.println();
		return resultSet.getString("user_id");}
		
	}
	
	//判断用户登录状态
	public boolean loginState(logincheck logincheck1,String namestring) throws SQLException{
		String name_t=null;
		String password = null;
		DBFactory dbFactory = new DBFactory();
		String sql1 = "select * from [user] where user_name = ?";
		Object params1[] = {namestring};
		ResultSet r1 = dbFactory.executeSQLWithResult(sql1, params1);
		if(!r1.next()){
		
			 System.out.println("如果未查到用户名，那么应该为管理员");
				
			String sql2 = "select * from [manager] where m_name = ?";
			Object params2[] = {namestring};
			ResultSet r2 = dbFactory.executeSQLWithResult(sql2, params2);
			r2.next();
			name_t = r2.getString("m_name");
			password = r2.getString("m_password");
		}else{
			System.out.println("登录人员是用户");
			name_t = r1.getString("user_name");
			password = r1.getString("password");
		}
		
		
		if(logincheck1.checkUsername(name_t, password)||
				logincheck1.checkManagername(name_t, password)){
			System.out.println("已登陆");
			return true;
		}else{
			System.err.println("未登录");
			return false;
		}
	}

}
