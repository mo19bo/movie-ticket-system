package DuangClass;
// 电影票
public class Movietickets {
	//// 属性
	// 电影票号
	private Integer movieticketId;
	// 场次号
	private Integer sessionId;
	// 订单号
	private Integer orderId;
	// 座位号
	private int[][] seatNum;
	// 单价
	private Float singlePrice;
	//// 方法
	// set & get
	public Integer getMovieTicket_Id() {
		return movieticketId;
	}
	public void setMovieTicket_Id(Integer movieTicket_Id) {
		this.movieticketId = movieTicket_Id;
	}
	public Integer getSession_Id() {
		return sessionId;
	}
	public void setSession_Id(Integer session_Id) {
		this.sessionId = session_Id;
	}
	public Integer getOrder_Id() {
		return orderId;
	}
	public void setOrder_Id(Integer order_Id) {
		this.orderId = order_Id;
	}
	public int[][] getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int[][] seatNum) {
		this.seatNum = seatNum;
	}
	public Float getSinglePrice() {
		return singlePrice;
	}
	public void setSinglePrice(Float singlePrice) {
		this.singlePrice = singlePrice;
	}
}
