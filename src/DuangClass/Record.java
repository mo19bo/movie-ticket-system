package DuangClass;

public class Record {

	private String record_id;// ����ID
	private String money;// ��ֵ���
	private String datetime;// ��ֵ����
    private String state_re;
    private String user_id;
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getRecord_id() {
		return record_id;
	}

	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getState_re() {
		return state_re;
	}

	public void setstate_re(String state_re) {
		this.state_re = state_re;
	}


}
