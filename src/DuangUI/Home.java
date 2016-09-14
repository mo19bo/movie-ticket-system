package DuangUI;

// package org.eclipse.wb.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import DuangFunc.MessageDialog;
import DuangFunc.login;
import databaseO.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import DuangClass.*;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.graphics.Image;

public class Home extends Composite {
	// 澹版槑composite瀵硅薄

	private Composite com;
	private Text loginName_text;
	private Text password_text;
	private login loginInfo;
	
	private static String nameString;
	private static String pwdString;
	private Home home;

	/**
	 * Create the composite.
	 * 
	 * @param composite_Main
	 * @param style
	 */
	public Home(final Composite composite_Main, int style) {
		 
		super(composite_Main, SWT.NONE);
		setForeground(SWTResourceManager.getColor(154, 205, 50));
		setLayout(new BorderLayout(0, 0));
		final logincheck lc = new logincheck(getShell());
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new GridLayout(1, false));

		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(255, 255, 0));
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new BorderLayout(0, 0));

		Composite composite_4 = new Composite(composite_1, SWT.NONE);
		composite_4.setLayoutData(BorderLayout.CENTER);
		composite_4.setLayout(new BorderLayout(0, 0));

		Composite composite_6 = new Composite(composite_4, SWT.BORDER);
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_6.setLayoutData(BorderLayout.CENTER);
		composite_6.setLayout(new BorderLayout(0, 0));

		Composite composite_5 = new Composite(composite_6, SWT.BORDER);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_5.setLayoutData(BorderLayout.EAST);
		composite_5.setLayout(new GridLayout(3, false));
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		
				final Label nameSelect = new Label(composite_5, SWT.CENTER);
				GridData gd_nameSelect = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_nameSelect.widthHint = 224;
				nameSelect.setLayoutData(gd_nameSelect);
				nameSelect.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
				nameSelect.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				nameSelect.setText("dsadasdasdadasdasddasd");
				nameSelect.setVisible(false);

		Composite composite_10 = new Composite(composite_5, SWT.NONE);
		composite_10
				.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_composite_10 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_composite_10.heightHint = 29;
		composite_10.setLayoutData(gd_composite_10);
		new Label(composite_5, SWT.NONE);
		
				Composite composite_11 = new Composite(composite_5, SWT.NONE);
				composite_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				composite_11.setLayout(null);
				GridData gd_composite_11 = new GridData(SWT.LEFT, SWT.CENTER, false,
						false, 1, 1);
				gd_composite_11.widthHint = 228;
				gd_composite_11.heightHint = 46;
				composite_11.setLayoutData(gd_composite_11);
				
						final Label welcome_label = new Label(composite_11, SWT.CENTER);
						welcome_label.setBounds(0, 0, 228, 46);
						welcome_label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
						// 隐藏欢迎文本
						welcome_label.setVisible(true);
						welcome_label.setBackground(SWTResourceManager
								.getColor(SWT.COLOR_WHITE));
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		
				final Label touxiang = new Label(composite_5, SWT.CENTER);
				GridData gd_touxiang = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_touxiang.widthHint = 213;
				touxiang.setLayoutData(gd_touxiang);
				
						touxiang.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						touxiang.setImage(SWTResourceManager.getImage(Home.class, "/pictureUI/ICON44.png"));
						touxiang.setVisible(true);
		new Label(composite_5, SWT.NONE);
		
				final Label loginName_label = new Label(composite_5, SWT.NONE);
				loginName_label.setImage(SWTResourceManager.getImage(Home.class, "/pictureUI/signName.png"));
				loginName_label.setBackground(SWTResourceManager
						.getColor(SWT.COLOR_WHITE));
				loginName_label.setFont(SWTResourceManager.getFont(
						"Microsoft YaHei UI", 11, SWT.BOLD));
				GridData gd_loginName_label = new GridData(SWT.CENTER, SWT.BOTTOM,
						false, false, 1, 1);
				gd_loginName_label.heightHint = 30;
				gd_loginName_label.widthHint = 93;
				loginName_label.setLayoutData(gd_loginName_label);
		
				loginName_text = new Text(composite_5, SWT.BORDER);
				loginName_text.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent arg0) {
						nameString = loginName_text.getText();
					}
				});
				loginName_text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI",
						10, SWT.NORMAL));
				GridData gd_loginName_text = new GridData(SWT.FILL, SWT.CENTER, true,
						false, 1, 1);
				gd_loginName_text.widthHint = 181;
				loginName_text.setLayoutData(gd_loginName_text);
		new Label(composite_5, SWT.NONE);
		
				final Label password_label = new Label(composite_5, SWT.NONE);
				password_label.setImage(SWTResourceManager.getImage(Home.class, "/pictureUI/password.png"));
				password_label.setBackground(SWTResourceManager
						.getColor(SWT.COLOR_WHITE));
				password_label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI",
						11, SWT.BOLD));
				GridData gd_password_label = new GridData(SWT.CENTER, SWT.CENTER,
						false, false, 1, 1);
				gd_password_label.heightHint = 28;
				gd_password_label.widthHint = 91;
				password_label.setLayoutData(gd_password_label);
		
				password_text = new Text(composite_5, SWT.BORDER | SWT.PASSWORD);
				password_text.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent arg0) {
						pwdString = password_text.getText();
					}
				});
				password_text.setText("m123456");
				password_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
						false, 1, 1));
		new Label(composite_5, SWT.NONE);

		final Label error = new Label(composite_5, SWT.NONE);
		error.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		error.setText("用户名或密码错误！");
		
				final Button loginbutton = new Button(composite_5, SWT.CENTER);
				
						loginbutton.setImage(SWTResourceManager.getImage(Home.class, "/pictureUI/register_button.png"));
						GridData gd_loginbutton = new GridData(SWT.CENTER, SWT.BOTTOM, false,
								false, 1, 1);
						gd_loginbutton.heightHint = 67;
						gd_loginbutton.widthHint = 151;
						loginbutton.setLayoutData(gd_loginbutton);
						
								
		error.setVisible(false);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		
				final Button login_button = new Button(composite_5, SWT.CENTER);
				GridData gd_login_button = new GridData(SWT.CENTER, SWT.CENTER, false,
						false, 1, 1);
				gd_login_button.heightHint = 66;
				gd_login_button.widthHint = 153;
				login_button.setLayoutData(gd_login_button);
				login_button.setImage(SWTResourceManager.getImage(Home.class, "/pictureUI/login_button.png"));
				login_button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Register rg = new Register(getShell(), SWT.DIALOG_TRIM);
						rg.open();
					}
				});
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		
				final Label forget = new Label(composite_5, SWT.NONE);
				forget.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false,
						1, 1));
				forget.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9,
						SWT.BOLD));
				forget.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				forget.setText("忘记密码");
		new Label(composite_5, SWT.NONE);
		
		Composite picture = new Composite(composite_6, SWT.NONE);
		picture.setLayout(new BorderLayout());
		picture.setLayoutData(BorderLayout.CENTER);
		ImageComposite ic = new ImageComposite(picture, SWT.NONE, new Image(null, "D:advertisement\\advs.png"),
				"Scaled");
		ic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Control[] conts = composite_Main.getChildren();
				for (Control control : conts) {
					control.dispose();
				}
				DuangMovie hGui = new DuangMovie(composite_Main, SWT.NONE);
				hGui.setLayoutData(BorderLayout.CENTER);
				composite_Main.layout();
			}
		});
		
		ic.setLayoutData(BorderLayout.CENTER);
		picture.layout();
		loginbutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<User> list = new ArrayList<User>();

				// 匹配用户名和密码
				try {

					if (lc.checkUsername(nameString, pwdString)
							|| lc.checkManagername(nameString, pwdString)
							|| lc.checkStaffname(nameString, pwdString)) {
						// 如果登陆成功显示欢迎！并将homepage的登录状态改为true
						HomePage.loginFlag=true;
						// 隐藏composite_5中的所有控件
						loginName_label.setVisible(false);
						loginName_text.setVisible(false);
						password_label.setVisible(false);
						password_text.setVisible(false);
						forget.setVisible(false);
						loginbutton.setVisible(false);
						login_button.setVisible(false);
						// 如果登陆成功判断用户身份
						touxiang.setImage(SWTResourceManager.getImage(Home.class,
								"/pictureUI/touxiangA.png"));
						// 从数据库获取用户id

						FileReader file = new FileReader("login.txt");
						BufferedReader br = new BufferedReader(file);
						String userid = br.readLine();

						
						// char name[] = nameString.toCharArray();
						char id[] = userid.toCharArray();
						if (id[1] == '3') {
							// 跳转到用户界面
							// --------------------添加操作----------------------
							System.out.println("用户");
							welcome_label.setVisible(true);
							welcome_label.setText("欢迎您使用葫芦娃电影售票系统");
							touxiang.setVisible(true);
							//nameSelect.setText("欢迎用户！");
							nameSelect.setVisible(true);
							loginbutton.setVisible(false);
							HomePage.personEnable(true);
							nameSelect.setText("用户，您好！");
							//	用户头像更换
							touxiang.setImage(SWTResourceManager.getImage(Home.class,
									"/pictureUI/touxiangA.png"));
						}else if(id[1] == '7'){
							// 跳转到stuff界面
							// --------------------添加操作----------------------
							System.out.println("stuff");
							welcome_label.setVisible(true);
							welcome_label.setText("欢迎您使用葫芦娃电影售票系统");
							touxiang.setVisible(true);
							//nameSelect.setText("欢迎用户！");
							nameSelect.setVisible(true);
							loginbutton.setVisible(false);
							HomePage.adminEnable(true);
							nameSelect.setText("stuff！");
							//	stuff头像更换
							touxiang.setImage(SWTResourceManager.getImage(Home.class,
									"/pictureUI/touxiangA.png"));
						}

						else {

							// 跳转到管理员操作界面
							// --------------------添加操作----------------------
							System.out.println("管理员");
							welcome_label.setVisible(true);
							touxiang.setVisible(true);
							welcome_label.setText("欢迎您使用葫芦娃电影售票系统");
							nameSelect.setText("管理员，您好！");
							nameSelect.setVisible(true);
							loginbutton.setVisible(false);
							HomePage.adminEnable(true);
							touxiang.setImage(SWTResourceManager.getImage(Home.class,
									"/pictureUI/touxiang.png"));
						}
						// touxiang.setText("用户你好！");
					}
					else {
						System.out.println("saddddddd");
						MessageDialog mdDialog=new MessageDialog(getShell(),SWT.DIALOG_TRIM,"用户名或者密码错误", 1);
						mdDialog.open();
					}
					
					

				} catch (Exception e1) {
					e1.printStackTrace();
					MessageDialog mdDialog=new MessageDialog(getShell(),SWT.DIALOG_TRIM,"程序出现问题", 1);
				}
				
					  
					
					
				
			}

			// 匹配不成功显示用户名或密码错误

		});

		/**************************************
		 ************* 登陆后显示欢迎****************
		 **************************************/

		composite_Main.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_Main.setLayoutData(BorderLayout.CENTER);
		composite_Main.setLayout(new BorderLayout(0, 0));

		if(HomePage.loginFlag){
			// 隐藏composite_5中的所有控件
			loginName_label.setVisible(false);
			loginName_text.setVisible(false);
			password_label.setVisible(false);
			password_text.setVisible(false);
			forget.setVisible(false);
			loginbutton.setVisible(false);
			login_button.setVisible(false);
			welcome_label.setVisible(true);
			touxiang.setVisible(true);
			welcome_label.setText("欢迎使用葫芦娃售票系统！");
		}else {
			// 隐藏composite_5中的所有控件
			loginName_label.setVisible(true);
			loginName_text.setVisible(true);
			password_label.setVisible(true);
			password_text.setVisible(true);
			forget.setVisible(true);
			loginbutton.setVisible(true);
			login_button.setVisible(true);
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
}
