package DuangClass;

// 座位信息
public class SeatInformation {
	// // 属性
	// 厅号
	private Integer hallNum;
	// 过道长度
	private Float aisleLongth;
	// 过道宽度
	private Float aisleWideth;
	// 过道个数
	private Integer aisleNum;
	// 过道位置
	private int[][] aislePosition;
	// 座位结构
	private char seatStructure;
	// 座位位置
	private int[][] seatPosition;

	// // 方法
	// set & get
	public Integer getHallNum() {
		return hallNum;
	}

	public void setHallNum(Integer hallNum) {
		this.hallNum = hallNum;
	}

	public Float getAisleLongth() {
		return aisleLongth;
	}

	public void setAisleLongth(Float aisleLongth) {
		this.aisleLongth = aisleLongth;
	}

	public Float getAisleWideth() {
		return aisleWideth;
	}

	public void setAisleWideth(Float aisleWideth) {
		this.aisleWideth = aisleWideth;
	}

	public Integer getAisleNum() {
		return aisleNum;
	}

	public void setAisleNum(Integer aisleNum) {
		this.aisleNum = aisleNum;
	}

	public int[][] getAislePosition() {
		return aislePosition;
	}

	public void setAislePosition(int[][] aislePosition) {
		this.aislePosition = aislePosition;
	}

	public char getSeatStructure() {
		return seatStructure;
	}

	public void setSeatStructure(char seatStructure) {
		this.seatStructure = seatStructure;
	}

	public int[][] getSeatPosition() {
		return seatPosition;
	}

	public void setSeatPosition(int[][] seatPosition) {
		this.seatPosition = seatPosition;
	}
}
