package DuangClass;

import java.sql.Date;

// 团购票
public class GrouppurchaseTicket {
	// // 属性
	// 团购票ID
	private Integer groupPurchaseTicketId;
	// 电影票ID
	private Integer movieId;
	// 购买时间
	private Date purchaseTime;
	// 有效时间
	private Date dueTime;
	// 识别码
	private String groupCode;

	// // 方法
	// set & get
	public Integer getGroupPurchaseTicket_Id() {
		return groupPurchaseTicketId;
	}

	public void setGroupPurchaseTicket_Id(Integer groupPurchaseTicket_Id) {
		this.groupPurchaseTicketId = groupPurchaseTicket_Id;
	}

	public Integer getMovie_Id() {
		return movieId;
	}

	public void setMovie_Id(Integer movie_Id) {
		this.movieId = movie_Id;
	}

	public Date getPurchase_Time() {
		return purchaseTime;
	}

	public void setPurchase_Time(Date purchase_Time) {
		this.purchaseTime = purchase_Time;
	}

	public Date getDue_Time() {
		return dueTime;
	}

	public void setDue_Time(Date due_Time) {
		this.dueTime = due_Time;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
}
