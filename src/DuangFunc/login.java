package DuangFunc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Text;

import databaseO.DBFactory;

import DuangClass.User;

public class login {
	static ArrayList<User> list = new ArrayList<User>();
	private DBFactory dbot;

	public void userInfo(String texString, String password_text) {
		// User usert = new User(texString, password_text);
	}

	public void login(String user_name, String password) throws IOException {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("e:\\账号.txt");
		BufferedReader br = new BufferedReader(fr);
		String str = null;
		System.out.println("---------");

		while ((str = br.readLine()) != null) {
			if (str.equals(user_name)) {
				str = br.readLine();
				if (str.equals(password)) {
					System.out.println("欢迎进入！");

				}
				System.out.println("账户或密码 错误，请重新输入！");
			}

		}

	}

	// 当用户试图登录时，match()方法可以将用户输入与保存的信息，进行匹配。
	// 如果用户名和密码正确，登录成功；否则，登录失败。

	// readFromFile()方法用于从文件中读取用户对象信息，并将用户对象添加进线性表（list)
	public void readFromFile() throws IOException, Exception {
		FileInputStream fis = new FileInputStream(new File(
				"d:\\userRegister.dat"));
		ObjectInputStream ois = new ObjectInputStream(fis);
		User read = null;
		try {
			while (true) {
				read = (User) ois.readObject();
				if (read == null) {
					break;
				}
				list.add(read);
			}
		} catch (Exception e) {
		}
		System.out.println(list);
		User temp = new User();
		// match(temp);
		ois.close();
	}

	// writeToFile()方法，实现用户信息保存功能。
	public static void writeToFile(Text text) throws IOException {
		File file = new File("d:\\userRegister.dat");
		file.createNewFile();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				file));
		for (User usr : list) {
			oos.writeObject(usr);
		}
		oos.flush();
		oos.close();
	}

	public void writeToFile() throws IOException {
		File file = new File("d:\\userRegister.dat");
		file.createNewFile();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				file));
		for (User usr : list) {
			oos.writeObject(usr);
		}
		oos.flush();
		oos.close();
	}

	public void Home(String text) {
		text = DuangUI.Home.class.getName();
	}

}
