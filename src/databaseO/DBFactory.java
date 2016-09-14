//  看注释使用   冯亮  2015-3-16
/*数据库操作类
 * 
 * 将 package 后面的包名改写成自己的包名
 * 将 getConnection函数中的URL改成自己的数据库URL 
 */
package databaseO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import DuangClass.Movie;


public class DBFactory {
	
	private Connection conn = null;
	private boolean flag=false;
	private String URL="localhost";
	private String DBname="test3";
	private String dB_usernameString="sa";
	private String DB_password="111";
	private String portString="1443";
	//连接数据库
	public DBFactory(){	
		Properties prop = new Properties();
		try {
			
			InputStream in = new BufferedInputStream(new FileInputStream("bin/config/DBconfig.properties"));   
            prop.load(in);   
            URL= prop.getProperty("url");
			DBname= prop.getProperty("db_name");
			dB_usernameString=prop.getProperty("db_username");
			DB_password=prop.getProperty("db_pwd");
			portString=prop.getProperty("db_port");		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//构造函数
		conn = getConnection();
	}
	 
	//私有 连接数据库函数
	public Connection getConnection(){		
		Connection conn = null;
			try {
					
				Class.forName("net.sourceforge.jtds.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"+URL+"/"+DBname+":"+portString,dB_usernameString,DB_password);
				flag=true;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block_product
				
				e.printStackTrace();

					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		return conn;		
	}
	public ResultSet searchUserID(String sql, String user_name){
		Object[] params1 = {user_name};
		ResultSet rs = executeSQLWithResult(sql, params1);
	
		return rs;
	}

	public ResultSet searchUserOrder(String sql,String user_id){
		Object[] params1 = {user_id};
		ResultSet rs = executeSQLWithResult(sql, params1);
		return rs;
	}
	public ResultSet searchColumn(String sql,Movie mv){	
		Object[] params1 = {mv.getMovie_Name()};
		ResultSet rs = executeSQLWithResult(sql, params1);
		return rs;
	
}
	public boolean delete(String sql) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
public ResultSet search(String sql) {
		
		ResultSet rs = null;
		try {PreparedStatement pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	//公共 可调用
	public void executeSQLWithoutResult(String SQL, Object[] params){
		try {
			PreparedStatement sqlPreparedStatement = conn.prepareStatement(SQL);		//此处的SQL为SQL查询语句
			for (int i = 0; i < params.length; i++){		//循环设置对象		
				sqlPreparedStatement.setObject(i+1, params[i]);
			}
			sqlPreparedStatement.execute();				//执行
		  //  close();		//关闭
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	// 公共  可调用  通过SQL查询语句查询SQL并且显示结果
	public ResultSet executeSQLWithResult(String SQL, Object[] params){
		ResultSet rSet  = null;	
		try {
			PreparedStatement sqlPreparedStatement = conn.prepareStatement(SQL);
			for (int i = 0; i < params.length; i++) {
				sqlPreparedStatement.setObject(i+1, params[i]);
			}
			rSet = sqlPreparedStatement.executeQuery();		//查询结果给ResultSet 类的 rSet对象
		  // close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return rSet;
	}
		

	//创建关闭函数
		public void close() {
			try {
				if (conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
	//	判断用户名和密码是否在数据库中
	
	//数其中一项的行号
	public int CountRowNumber(String tableName){
		ResultSet rs = null;
		String sql = "select * from "+tableName;
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
			rs = stmt.executeQuery(sql);
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	public void update(String sql2) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql2);
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}
