//注册UI
//孙珊(终版)
package DuangFunc;
 
 import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;


 /****************************************
  * 按照数据库保存的内容添加其他信息及按钮
  * 
  ***************************************/

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
//import DB.dboperator;
//import DuangClass.user;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;

import DuangClass.User;
import DuangFunc.Judge;
import DuangFunc.RandomID;
import DuangUI.HomePage;
//import DuangClass.user;
import databaseO.*;
public class Register extends Dialog {

	protected static final String String = null;
	protected Object result;
	protected Shell shell;
	private Text username_t;
	private Text password_t;
	private Text password_1t;
	private Text tel_t;
	private Text email_t;
	private Label lblNewLabel;
	private Label lblNewLabel_1;
	private Label label;
	private Label label_1;
	private Label label_5;
	private Label label_6;
	private Label lblNewLabel_2;
	private Text id_t;
	private Text address_t;
	private Text postcode_t;
	private String username;
	private String password;
	private String password_1;
	private String tel;
	private String email;
	private String address;
	private String idcard;
	private String postcode;
	//static User user = new User();
	private Group group;
	private Button MButton;
	private Button FButton;
	private String sex;
	private Label label_7;
	private Button button;
	
	private String errorString1;
	private String errorString2;
	private String errorString3;
	private String errorString4;
	private String errorString5;
	private String errorString6;
	private String errorString7;
	private String errorString;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Register(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(310, 325);
		shell.setText(getText());
		shell.setLayout(new GridLayout(9, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		final Label username_l = new Label(shell, SWT.CENTER);
		username_l.setText("用户名:");
		new Label(shell, SWT.NONE);
		
		username_t = new Text(shell, SWT.BORDER);
		//String username1 = text.getText();
		
		username_t.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		new Label(shell, SWT.NONE);
		
		lblNewLabel_1 = new Label(shell, SWT.NONE);
		
		label = new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		final Label password_l = new Label(shell, SWT.NONE);
		password_l.setText("密码:");
		new Label(shell, SWT.NONE);
		
		password_t = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		
		password_t.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("确认密码:");
		new Label(shell, SWT.NONE);
		
		password_1t = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		
		password_1t.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		label_7 = new Label(shell, SWT.NONE);
		label_7.setText("设置头像：");
		new Label(shell, SWT.NONE);
		
		button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button.setText("选择头像");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("手机号:");
		new Label(shell, SWT.NONE);
		
		tel_t = new Text(shell, SWT.BORDER);
		
		tel_t.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("邮箱:");
		new Label(shell, SWT.NONE);
		
		email_t = new Text(shell, SWT.BORDER);
		
		email_t.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText("性别:");
		new Label(shell, SWT.NONE);
		
		
		
		group = new Group(shell, SWT.NONE);
		GridData gd_group = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_group.heightHint = 5;
		group.setLayoutData(gd_group);
		group.setBounds(22, 10, 188, 89);
		
		MButton = new Button(group, SWT.RADIO);
		MButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sex=MButton.getText();
			
			}
		});
		MButton.setBounds(0, 10, 46, 17);
		MButton.setText("男");
		
		FButton = new Button(group, SWT.RADIO);
		FButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sex=FButton.getText();
			}
		});
		FButton.setBounds(52, 10, 45, 17);
		FButton.setText("女");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		label_1 = new Label(shell, SWT.NONE);
		label_1.setText("身份证号:");
		new Label(shell, SWT.NONE);
		
		id_t = new Text(shell, SWT.BORDER);
		
		id_t.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		label_5 = new Label(shell, SWT.NONE);
		label_5.setText("地址:");
		new Label(shell, SWT.NONE);
		
		address_t = new Text(shell, SWT.BORDER);
		
		address_t.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		label_6 = new Label(shell, SWT.NONE);
		label_6.setText("邮编:");
		new Label(shell, SWT.NONE);
		
		postcode_t = new Text(shell, SWT.BORDER);
		
		postcode_t.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button register = new Button(shell, SWT.NONE);
		GridData gd_register = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_register.heightHint = 26;
		gd_register.widthHint = 44;
		register.setLayoutData(gd_register);
		
		
		

		register.setText("注   册 ");
		
		
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Judge jg = new Judge();
			 username = username_t.getText();;
		 password = password_t.getText();
		 password_1 = password_1t.getText();
		    
			 tel = tel_t.getText();
			email = email_t.getText();
			 address = address_t.getText();
		 idcard = id_t.getText();
			postcode = postcode_t.getText();
		
				if(jg.checkUsername(username))
				{
					
					System.out.println("用户名正确");
				}
				if(jg.checkPassword(password))
				{
					
					System.out.println("密码正确");
				}
				if(jg.checkPassword_1(password,password_1))
				{
					
					System.out.println("密码确认正确");
				}
				if(jg.checkMobileNumber(tel))
				{
					
					System.out.println("电话正确");
				}
				if(jg.checkEmail(email))
				{
					
					System.out.println("邮箱正确");
				}
				if(jg.checkID(idcard))
				{
					
					System.out.println("身份证正确");
				}if(jg.checkPostcode(postcode))
				{
					
					System.out.println("邮编正确");
				}
			
			/*	if(!jg.checkUsername(username)|!jg.checkPassword(password)|!jg.checkPassword_1(password,password_1)|
						!jg.checkMobileNumber(tel)|!jg.checkEmail(email)|
						!jg.checkID(idcard)|!jg.checkPostcode(postcode)){
					System.out.println("信息有误");
					
				}*/
				
		if(jg.checkUsername(username)&&jg.checkPassword(password)&&jg.checkPassword_1(password,password_1)
				&&jg.checkMobileNumber(tel)&&jg.checkEmail(email)
							&&jg.checkID(idcard)&&jg.checkPostcode(postcode)){
				
				/*username = username_t.getText();;
				 password = password_t.getText();
				 password_1 = password_1t.getText();
					 tel = tel_t.getText();
					email = email_t.getText();
					 address = address_t.getText();
				 idcard = id_t.getText();
					postcode = postcode_t.getText();*/
						User user = new User();
						RandomID rand = new RandomID();
						user.setUser_id(rand.user_id());
						user.setUser_name(username);
						user.setPwd(password);
						//System.out.println(sex);
						user.setSex(sex);
						user.setAddress(address);
						user.setId_card(idcard);
						user.setLevel("5");
						user.setPostcode(postcode);
						user.setImage_url("anif");
						user.setAccount("100");
						user.setEmail(email);
						user.setTel(tel);
					   DBFactory db = new DBFactory();
					
						String SQL = "insert into [user] values (?,?,?,?,?,?,?,?,?,?,?,?)";
						// 传入参数

						Object[] params = { user.getUser_id(), user.getUser_name(),
								user.getPwd(), user.getSex(), user.getId_card(),
								user.getPostcode(), user.getAddress(), user.getLevel(),
								user.getAccount(), user.getImage_url(), user.getTel(),
								user.getEmail()};
						db.executeSQLWithoutResult(SQL, params);
						HomePage.personEnable(true);
						shell.dispose();
						//关闭注册页面，跳转至主页
					   //System.out.println("连接成功！");
						File file = new File("login.txt");
						try {
							//file.createNewFile();
							if(file.exists())
								file.delete();
							FileWriter fw = new FileWriter(file.getName(),true);
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write(user.getUser_id());
							bw.close();
							fw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					  
		}   
		else {
			if(!(jg.checkUsername(username))){
				errorString1 = "用户名，";
			}
			else{
				errorString1 = "";
			}
			if(!(jg.checkPassword(password))){
				errorString2 = "密码，";
			}
			else{
				errorString2 = "";
			}
			if(!(jg.checkPassword_1(password, password_1))){
				errorString3 = "密码确认，";
			}
			else{
				errorString3 = "";
			}
			if(!(jg.checkMobileNumber(tel))){
				errorString4 = "电话，";
			}
			else{
				errorString4 = "";
			}
			if(!(jg.checkEmail(email))) {
				errorString5 ="邮箱，";
			}
			else{
				errorString5 = "";
			}
			if(!(jg.checkID(idcard))) {
				errorString6 = "身份证，";
			}
			else{
				errorString6 = "";
			}
			if(!(jg.checkPostcode(postcode))){
				errorString7 = "邮编，";
			} 
			else{
				errorString7 = "";
			}
			errorString = errorString1 + errorString2 + errorString3 + errorString4 + errorString5 + errorString6 + errorString7;
			JOptionPane.showMessageDialog(null, errorString+"有误，请重新输入！","错误", JOptionPane.ERROR_MESSAGE);
		}	   
				}
			
		});
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
	}
}
