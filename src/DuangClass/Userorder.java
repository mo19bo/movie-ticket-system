package DuangClass;

import java.util.Date;

public class Userorder {

	public Userorder() {
		// TODO Auto-generated constructor stub
	}

	private String o_id;
	private String user_id;
	private String p_tickets;
	private Date o_date;
	private Date y_date;
	private float o_money;

	public String getO_id() {
		return o_id;
	}

	public void setO_id(String o_id) {
		this.o_id = o_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getP_tickets() {
		return p_tickets;
	}

	public void setP_tickets(String p_tickets) {
		this.p_tickets = p_tickets;
	}

	public Date getO_date() {
		return o_date;
	}

	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}

	public Date getY_date() {
		return y_date;
	}

	public void setY_date(Date y_date) {
		this.y_date = y_date;
	}

	public float getO_money() {
		return o_money;
	}

	public void setO_money(float o_money) {
		this.o_money = o_money;
	}

}
