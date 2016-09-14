package DuangClass;

 
import java.sql.Time;
import java.util.Date;

//场次
public class Session {
	//// 属性
	// 场次号
	private String sessionId;
	// 厅号
	private String hallId;

	// 电影id
	private String movieId;
	// 场次日期
	private Date sessionDate;
	// 场次开始时间
	private Time startTime;
	// 场次结束时间
	private Time endTime;
	// 满座情况
	private String fullseatSituation;
	private String datetimeString;
	//// 方法
	// set & get

	
	public String getDatetimeString() {
		return datetimeString;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Date getSessionDate() {
		return sessionDate;
	}
	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public String getFullseatSituation() {
		return fullseatSituation;
	}
	public void setFullseatSituation(String fullseatSituation) {
		this.fullseatSituation = fullseatSituation;
	}
	public void setDatetimeString(String datetimeString) {
		this.datetimeString = datetimeString;
	}
	public Date getSession_Date() {
		return sessionDate;
	}
	public String getHallId() {
		return hallId;
	}
	public void setHallId(String hallId) {
		this.hallId = hallId;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public void setSession_Date(Date session_Date) {
		this.sessionDate = session_Date;
	}
	public Time getStart_Time() {
		return startTime;
	}
	public void setStart_Time(Time start_Time) {
		this.startTime = start_Time;
	}
	public Time getEnd_Time() {
		return endTime;
	}
	public void setEnd_Time(Time end_Time) {
		this.endTime = end_Time;
	}
	public String getfullSeat_Situation() {
		return fullseatSituation;
	}
	public void setFull_Situation(String full_Situation) {
		this.fullseatSituation = full_Situation;
	}

}
