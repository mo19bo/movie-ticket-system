package DuangUI;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.eclipse.swt.widgets.Composite;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import DuangFunc.MessageDialog;

public class setting extends Composite {
	private Text url;
	private Text DB_name;
	private Text DB_username;
	private Text DB_port;
	private Text DB_pwd;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public setting(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(0, 0, 61, 17);
		label.setText("请设置");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new GridLayout(3, false));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.heightHint = 28;
		gd_lblNewLabel.widthHint = 97;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("数据库所在地址：");
		
		url = new Text(composite_1, SWT.BORDER);
		url.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("数据库名称：");
		
		DB_name = new Text(composite_1, SWT.BORDER);
		DB_name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("端口号：");
		
		DB_port = new Text(composite_1, SWT.BORDER);
		DB_port.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("数据库用户名：");
		
		DB_username = new Text(composite_1, SWT.BORDER);
		DB_username.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("数据库登陆密码:");
		
		DB_pwd = new Text(composite_1, SWT.BORDER);
		DB_pwd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				   Properties prop = new Properties();
				     try {
				    	 
//				           InputStream fis = new FileInputStream("bin/config/DBconfig.properties");
//				            //从输入流中读取属性列表（键和元素对）
//				            prop.load(fis);
				            //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
				            //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
				    	 InputStream fis = new FileInputStream("bin/config/DBconfig.properties");
							// //从输入流中读取属性列表（键和元素对）
							prop.load(fis);

							Enumeration item = prop.keys();
							while (item.hasMoreElements()) {
								String a = item.nextElement().toString();
								System.out.println(prop.get(a));
							}
							// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
							// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
							FileOutputStream fos = new FileOutputStream(
									"bin/config/DBconfig.properties");
							// Writer writer = new FileWriter("bin/config/DBconfig.properties");

							
							prop.setProperty("url", url.getText());
							prop.setProperty("db_name", DB_name.getText());
							prop.setProperty("db_port", DB_port.getText());
							prop.setProperty("db_username", DB_username.getText());
							prop.setProperty("db_pwd", DB_pwd.getText());
							

							// 以适合使用 load 方法加载到 Properties 表中的格式，
							// 将此 Properties 表中的属性列表（键和元素对）写入输出流
							prop.store(fos, "");
							fos.flush();
							fos.close();
							System.out.println("ok");
				        } catch (IOException e1) {
				        	MessageDialog msgDialog=new MessageDialog(getShell(), SWT.DIALOG_TRIM, "配置文件出现问题", 1);
				        	msgDialog.open();
				         System.err.println(e1);
				        }
				    }

				 
				
				
				
			
		});
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 114;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("确定");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
