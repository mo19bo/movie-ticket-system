//  看注释使用   丁睿  2015-3-16
/*数据库类
 * 
 * 放于po package里面使用， 若自己工程没有po包，则新建一个
 */

package po;



public class Staff {

   public Staff() {
		// TODO Auto-generated constructor stub
	}
	private String s_id;//
	   private String s_pwd;//
	   private String s_name;//
   public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getS_pwd() {
		return s_pwd;
	}
	public void setS_pwd(String s_pwd) {
		this.s_pwd = s_pwd;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

   
}

