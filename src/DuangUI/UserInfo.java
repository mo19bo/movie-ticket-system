package DuangUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;

import databaseO.DBFactory;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
//import DB.dboperator;
public class UserInfo extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text password_text;
	private Text postcode_text;
	private Text address_text;
	private String user_name;
	private String password;
	private String sex;
	private String address;
	private String postcode;
	private String account;
	private String level;
	private Text emall_text;
	private Text tel_text;
	private String email;
	private String tel;
	private String img_urlString;
	private String useridString;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public UserInfo(Shell parent, int style) {
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
		  Rectangle displayBounds = display .getPrimaryMonitor().getBounds(); 
	        Rectangle shellBounds = shell.getBounds(); 
	     int x = displayBounds.x + (displayBounds.width - shellBounds.width)>>1; 
	     int y = displayBounds.y + (displayBounds.height - shellBounds.height)>>1; 
	 	shell.setLocation(x,y);
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
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(450, 242);
		shell.setText("葫芦娃电影售票系统");
		
		ArrayList<String> user = new ArrayList<String>();
		DBFactory dbou = new DBFactory();
		String user_id = null;
		try {
			FileReader reader = new FileReader("login.txt");
			BufferedReader br = new BufferedReader(reader);
			useridString = br.readLine();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "select * from [user] where user_id = ?";
		ResultSet rs = dbou.searchUserOrder(sql, useridString);
		try {
			while(rs.next()){
				user_name = rs.getString("user_name");
				//System.out.println(user_name);
				password = rs.getString("password");
				sex = rs.getString("sex");
				address = rs.getString("address");
				postcode = rs.getString("postcode");
				account = rs.getString("account");
				level = rs.getString("level");
				tel = rs.getString("tel");
			
				email = rs.getString("email");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dbou.close();
		shell.setLayout(new GridLayout(4, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label label = new Label(shell, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("用户名：");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setText(user_name);
		
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("等级：");
		
		Label lblLevel = new Label(shell, SWT.NONE);
		lblLevel.setText(level);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("密码：");
		
		password_text = new Text(shell, SWT.BORDER);
		password_text.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
			}
		});
		GridData gd_password_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_password_text.widthHint = 135;
		password_text.setLayoutData(gd_password_text);
		password_text.setText(password);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("余额：");
		
		Label lblAccount = new Label(shell, SWT.NONE);
		lblAccount.setText(account);
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("性别：");
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setText(sex);
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_7.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		label_7.setText("头像");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setphoto set=new setphoto(getParent(), SWT.DIALOG_TRIM);
				set.open();
				img_urlString=set.image_urlString;
				
			}
		});
		button_1.setText("修改头像");
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("邮编：");
		
		postcode_text = new Text(shell, SWT.BORDER);
		GridData gd_postcode_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_postcode_text.widthHint = 123;
		postcode_text.setLayoutData(gd_postcode_text);
		postcode_text.setText(postcode);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("邮箱：");
		
		emall_text = new Text(shell, SWT.BORDER);
		emall_text.setText(email);
		emall_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("地址：");
		
		address_text = new Text(shell, SWT.BORDER);
		GridData gd_address_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_address_text.widthHint = 123;
		address_text.setLayoutData(gd_address_text);
		address_text.setText(address);
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_6.setText("手机号：");
		
		tel_text = new Text(shell, SWT.BORDER);
		tel_text.setText(tel);
		tel_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		
		Button button = new Button(shell, SWT.NONE);
		GridData gd_button = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 135;
		button.setLayoutData(gd_button);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String SQL="update [user] set password=?,email=?,tel=?,postcode=?,address=?,url_img=? where user_id=?";
				DBFactory dbFactory=new DBFactory();
				Object[]  params={password_text.getText(),emall_text.getText(),tel_text.getText(),postcode_text.getText(),address_text.getText(),img_urlString,useridString};
				dbFactory.executeSQLWithoutResult(SQL, params);
				
				System.out.println(emall_text.getText());
				
				
				shell.close();
				
				
				
			//	String sql = "update user set password= "+password
			}
		});
		button.setText("确定");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		

		
		
		
		/*TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("New Column");*/

	}
}
