//冯亮 2015-3-17
//电影的返回器和修改器类
package DuangClass;

// 电影的返回器和修改器类
public class Movie {
	//// 属性
	// 电影ID
	private String movieId;
	// 电影名称
	private String movieName;
	// 电影上映日期
	private String movieDate;
	// 电影类型
	private String movieStyle;
	// 导演
	private String movieDirector;
	// 演员
	private String movieActor;
	// 电影描述
	private String moivieDescription;
	// 电影评分
	private Float movieScore;
	// 电影（2D/3D）
	private String movieD;
	//电影海报
	private String movieImage;
	
	public Float getMovieLong() {
		return movieLong;
	}
	public void setMovieLong(Float movieLong) {
		this.movieLong = movieLong;
	}
	private Float movieLong;
	
	public String getMovie_Image() {
		return movieImage;
	}
	public void setMovie_Image(String movieImage) {
		this.movieImage = movieImage;
	}
	//// 方法
	// get & set
	public String getMovie_Id() {
		return movieId;
	}
	public void setMovie_Id(String string) {
		this.movieId = string;
	}
	public String getMovie_Name() {
		return movieName;
	}
	public void setMovie_Name(String movie_Name) {
		this.movieName = movie_Name;
	}
	public String getMovie_Date() {
		return movieDate;
	}
	

	public void setMovie_Date(String movie_date) {
		this.movieDate = movie_date;
	}
	public String getMovie_Style() {
		return movieStyle;
	}
	public void setMovie_Style(String movie_Style) {
		this.movieStyle = movie_Style;
	}
	public String getMovie_Director() {
		return movieDirector;
	}
	public void setMovie_Director(String movie_Director) {
		this.movieDirector = movie_Director;
	}
	public String getMovie_Actor() {
		return movieActor;
	}
	public void setMovie_Actor(String movie_Actor) {
		this.movieActor = movie_Actor;
	}
	public String getMoivie_Description() {
		return moivieDescription;
	}
	public void setMoivie_Description(String moivie_Description) {
		this.moivieDescription = moivie_Description;
	}
	public Float getMovie_Score() {
		return movieScore;
	}
	public void setMovie_Score(Float movie_Score) {
		this.movieScore = movie_Score;
	}
	public String getMovie_D() {
		return movieD;
	}
	public void setMovie_D(String string) {
		this.movieD = string;
	}
}
