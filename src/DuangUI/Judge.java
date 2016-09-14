//判断注册信息
//孙珊

package DuangUI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import MD.MessageDialog;

import org.eclipse.swt.widgets.Shell;

public class Judge {

	// 校验登录名：只能输入5-20个以字母开头、可带数字、“_”、“.”的字串
	private Shell getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	Boolean checkUsername(String username) {
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$");
			Matcher matcher = regex.matcher(username);
			flag = matcher.matches();
			/*
			 * if(flag != true){ MessageDialog md = new MessageDialog();
			 * md.informationMD("information"); } else {
			 * System.out.println("用户名正确！"); }
			 */

		} catch (Exception e) {
			flag = false;
			MessageDialog md = new MessageDialog();
			md.errorMD(e.toString());
			// return flag;
		}
		return flag;
	}

	// 校验密码：只能输入6-20个字母、数字、下划线

	Boolean checkPassword(String password) {
		boolean flag = false;
		try {
			if (password != null) {
				Pattern regex = Pattern.compile("^/w{6,20}$");
				Matcher matcher = regex.matcher(password);
				flag = matcher.matches();

			}
		} catch (Exception e) {
			flag = false;
			MessageDialog md = new MessageDialog();
			md.errorMD(e.toString());
		}
		return flag;
	}

	// 确认密码
	public boolean checkPassword_1(java.lang.String password,
			java.lang.String password_1) {
		boolean flag = false;
		try {
			if (password.equals(password_1) && password_1 != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			MessageDialog md = new MessageDialog();
			md.errorMD(e.toString());
			// TODO: handle exception
		}
		return flag;
	}

	// 匹配电话号码
	Boolean checkMobileNumber(String tel) {

		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("^\\d{11}$");
			Matcher matcher = regex.matcher(tel);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
			MessageDialog md = new MessageDialog();
			md.errorMD(e.toString());
		}
		return flag;
	}

	// 校验邮箱
	public boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
			MessageDialog md = new MessageDialog();
			md.errorMD(e.toString());
		}
		return flag;
	}

	// 校验身份证
	public boolean checkID(String idcard) {
		boolean flag = false;
		try {
			// ^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$
			String check = "^\\p{Digit}{6}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(idcard);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
			MessageDialog md = new MessageDialog();
			md.errorMD(e.toString());
		}
		return flag;
	}

	// 校验邮编
	public boolean checkPostcode(String postcode) {
		boolean flag = false;
		try {

			String check = "^\\p{Digit}{6}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(postcode);
			return matcher.matches();

		} catch (Exception e) {
			flag = false;
			MessageDialog md = new MessageDialog();
			md.errorMD(e.toString());
		}
		return flag;
	}

}
