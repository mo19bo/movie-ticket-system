//  看注释使用   丁睿  2015-3-16
/*数据库类
 * 
 * 放于po package里面使用， 若自己工程没有po包，则新建一个
 */

package po;

public class user {

	public user() {
		// TODO Auto-generated constructor stub
	}
	private String user_id;
	   private String user_name;
	   private String pwd;
	   private String sex;
	   private String image_url;
	   private String id_card;
	   private String postcode;
	   private String address;
	   private String level;
	   private String account;
   public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAccount() {
		return account;
	}
	//public void setAccount(String account) {
		//this.account = account;}
	public void setAccount( String account) {
		// TODO 自动生成的方法存根
		this.account = account;
		
	}

}
