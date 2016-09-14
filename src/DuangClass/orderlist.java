package DuangClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import databaseO.DBFactory;
public class orderlist {
	private String o_id;
	private String mv_name;
	private String o_state;
	//private String s_num;
	//private float price;
	//private int[][] seat;
	private String o_date;
	//private String h_num;
	private String p_tickets;
	private String user_id;
	public String getP_tickets() {
		return p_tickets;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setP_tickets(String p_tickets) {
		this.p_tickets = p_tickets;
	}




	private String y_date;
	public String getY_date() {
		return y_date;
	}

	public void setY_date(String y_date) {
		this.y_date = y_date;
	}




	private String o_money;
	
	
	

	


	public String getO_money() {
		return o_money;
	}

	public void setO_money(String o_money) {
		this.o_money = o_money;
	}

	public String getO_id() {
		return o_id;
	}

	public void setO_id(String o_id) {
		this.o_id = o_id;
	}

	public String getMv_name() {
		return mv_name;
	}

	public void setMv_name(String mv_name) {
		this.mv_name = mv_name;
	}
	
	public List<orderlist> getOrderlist(){
		
		DBFactory dbo = new DBFactory();
		//dbo.connector("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/sx", "root", "pianist98032");
		//String sql = "select userorder.o_state,userorder.o_id,mv_name,userorder.p_tickets,userorder.o_date,userorder.y_date, userorder.o_money from userorder,m_tickets,sence,movie where m_tickets.ID = userorder.id and m_tickets.s_num = sence.s_num and sence.mv_id = movie.mv_id and userorder.user_id = ?;";
		
		
		
		//String sql = "select * from [userorder] where user_id = ?";
		File file = new File("login.txt");
        BufferedReader reader = null;
        
        try {
        	if(file.exists())
            	file.createNewFile();
            reader = new BufferedReader(new FileReader(file));
         
            user_id = reader.readLine();
            // 一次读入一行，直到读入null为文件结束
          
            reader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        String sql = "select o_id, p_tickets, o_date, y_date, o_money, o_state from userorder where user_id = "+user_id;
		 ResultSet rs = dbo.search(sql);
		//ResultSet rs = dbo.search(sql);
		
		List<orderlist> orl = new ArrayList<orderlist>();
		try {
			while(rs.next()){
				orderlist ol = new orderlist();
				ol.setO_id(rs.getString("o_id"));
				//ol.setMv_name(rs.getString("mv_name"));
				ol.setP_tickets(rs.getString("p_tickets"));
				ol.setO_date(rs.getString("o_date"));
				ol.setY_date(rs.getString("y_date"));
				ol.setO_money(rs.getString("o_money"));
				ol.setUser_id("user_id");
				ol.setO_state(rs.getString("o_state"));
				//System.out.println(rs.getString("o_state"));
				orl.add(ol);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orl;
	}

	public String getO_state() {
		return o_state;
	}

	public void setO_state(String o_state) {
		this.o_state = o_state;
	}

	public String getO_date() {
		return o_date;
	}

	public void setO_date(String o_date) {
		this.o_date = o_date;
	}
}
