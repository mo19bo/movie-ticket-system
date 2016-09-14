//冯亮 2015-3-17 
//电影票 的数据库操作类
package databaseO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DuangClass.Movie;

public class MovieDAO {
	
	
	
    
	//公共 可调用  查找电影部数
	public int numberOfMovie(String tableName,DBFactory db) {
	
		return db.CountRowNumber(tableName);
	}
	
	// 公共 可调用  查找电影
	public 	ArrayList<Movie> searchMovie(DBFactory dbobject){
		
		ArrayList<Movie> list = new ArrayList<Movie>();
		
		try {
			String searchMovieSQL = "select * from [movie]";		
			//SQL语句
			Object[] params = {};
			ResultSet movieResult = dbobject.executeSQLWithResult(searchMovieSQL, params);
			while(movieResult.next()){
				Movie movie = new Movie();
				movie.setMovie_Id(movieResult.getString("mv_id"));
				movie.setMovie_Name(movieResult.getString("mv_name"));
				movie.setMoivie_Description(movieResult.getString("describe"));
				movie.setMovie_Director(movieResult.getString("director"));
				movie.setMovie_Date(String.valueOf(movieResult.getDate("pdate")));
				movie.setMovie_Actor(movieResult.getString("actor"));
				movie.setMovie_D(movieResult.getString("3D/2D"));
				movie.setMovie_Score(movieResult.getFloat("rate"));
				movie.setMovie_Style(movieResult.getString("type"));
				movie.setMovie_Image(movieResult.getString("img_movie"));
				list.add(movie);
			}
			//dbobject.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	
		
	}
}