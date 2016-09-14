package DuangClass;

import java.sql.Date;

// 商家
public class Merchant {
	// // 属性
	// 商家名称
	private String merchantName;
	// 数量
	private Integer groupNum;
	// 时间
	private Date groupTime;

	// // 方法
	// set & get
	public String getMerchant_Name() {
		return merchantName;
	}

	public void setMerchant_Name(String merchant_Name) {
		this.merchantName = merchant_Name;
	}

	public Integer getGroup_Num() {
		return groupNum;
	}

	public void setGroup_Num(Integer group_Num) {
		this.groupNum = group_Num;
	}

	public Date getGroup_Time() {
		return groupTime;
	}

	public void setGroup_Time(Date group_Time) {
		this.groupTime = group_Time;
	}
}
