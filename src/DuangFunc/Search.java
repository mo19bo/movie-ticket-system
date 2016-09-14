package DuangFunc;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseO.DBFactory;
import DuangClass.User;
public class Search {

public String searchuser_id(String user_ame) throws SQLException{
	User user1 = new User();
	DBFactory dbF = new DBFactory();
	User user =new User();
	
	String sql = "select * from [user] where user_name = ?";
	
	Object[] params = { user1.getUser_name() };
	ResultSet uString= dbF.executeSQLWithResult(sql, params);
	
	user.setUser_id(uString.getString(0));
		return user.getUser_id();
	
}
public String getId() throws SQLException{
	User user1 = new User();
	Search search = new Search();
	String id = search.searchuser_id(user1.getUser_name());
	return id;
}
protected void ShowViewDialog(String string) {
	// TODO Auto-generated method stub
		return ;
	}
}
