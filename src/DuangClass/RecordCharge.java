package DuangClass;

import java.util.Date;

public class RecordCharge {

	public RecordCharge() {
		// TODO Auto-generated constructor stub
	}

	private String record_id;// ����ID
	private float money;// ��ֵ���
	private Date datetime;// ��ֵ����
	private String state;
	private String user_id;
	
	public String getuser_id(){
		
		
		return this.user_id;
	}
    public String setuser_id( String userid){
		
    	user_id=userid;
    	
		return user_id;
		
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	public String getRecord_id() {
		return record_id;
	}

	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

}
