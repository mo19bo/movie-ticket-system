//冯亮  2015-3-19
//团购类
package DuangClass;

import java.sql.Date;

public class GroupBuy {
	//团购码
	private String serial_code;
	//票ID
	private String tt_id;
	//到期时间
	private Date date_t;
	//票时间
	private Date tt_date;
		
	public String getSerial_code() {
		return serial_code;
	}
	public void setSerial_code(String serial_code) {
		this.serial_code = serial_code;
	}
	public String getTt_id() {
		return tt_id;
	}
	public void setTt_id(String tt_id) {
		this.tt_id = tt_id;
	}
	public Date getDate_t() {
		return date_t;
	}
	public void setDate_t(Date date_t) {
		this.date_t = date_t;
	}
	public Date getTt_date() {
		return tt_date;
	}
	public void setTt_date(Date tt_date) {
		this.tt_date = tt_date;
	}
	
}
