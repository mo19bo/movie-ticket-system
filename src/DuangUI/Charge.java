package DuangUI;

import java.sql.Timestamp;
import java.util.Date;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import DuangClass.RecordCharge;
import DuangFunc.MessageDialog;
import databaseO.DBFactory;
import databaseO.logincheck;

public class Charge extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private static RandomID randomRecordID;
	private PersonCenter personCenter;
	final  String STATE = "待确认";
	private logincheck logincheck1;
	private String user_idString;
	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Charge(Shell parent, int style, String id) {
		super(parent, style);
		setText("SWT Dialog");
		user_idString=id;
	}

	/**
	 * Open the dialog.
	 * 
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
	
	//User listUserId = personCenter.searchUser_id();
	//String userID = listUserId.getUser_id();
	
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(450, 300);
		shell.setText("葫芦娃电影售票系统");
		shell.setLayout(null);
		
 
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI",
				20, SWT.NORMAL));
		lblNewLabel.setBounds(89, 69, 117, 35);
		lblNewLabel.setText("充值金额");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(212, 69, 107, 35);
		text.setText("0.0");
		Button btnOk = new Button(shell, SWT.NONE);
		
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell parent = this.getParent();
				Shell dialog = new Shell(parent, SWT.DIALOG_TRIM
						| SWT.APPLICATION_MODAL);
				dialog.setSize(100, 50);
				
				float money =0;
				// 将充值金额、充值时间、随机生成的记录ID和充值状态保存在数据库中
				if(text.getText().toString()!=null){
				money = Float.parseFloat(text.getText().toString());// 金额
				}
				Date dateTime = new Date(System.currentTimeMillis());// 时间
				System.out.println(dateTime);
				//Calendar calendar = Calendar.getInstance();
				
				Timestamp timestamp=new Timestamp(dateTime.getTime() );
				RandomID rand = new RandomID();
				String record_id = rand.record_id();// 记录ID
				dialog.setText("充值成功！");
				String state = new String(STATE);//点击确定改变充值状态
				
				RecordCharge record = new RecordCharge();
				record.setMoney(money);
				record.setDatetime(timestamp);
				record.setRecord_id(record_id);
				record.setState(state);
				record.setuser_id(user_idString);
				System.out.println(dateTime);
				System.out.println(timestamp);
				
				if(record.getMoney()>0){
				
				// **********************插入到充值记录表中*************************
				
				DBFactory dbFactory = new DBFactory();
				String sql = "insert into [record] values (?,?,?,?,?)";
				Object[] params = { record.getRecord_id(),user_idString, record.getMoney(),timestamp,
						 record.getState()};
				dbFactory.executeSQLWithoutResult(sql, params);
				dbFactory.close();
				MessageDialog msgDialog=new MessageDialog(shell, SWT.DIALOG_TRIM, "充值成功！", 2);
				msgDialog.open();
				shell.dispose();
				//dialog.open();
				}
				else{
					MessageDialog msgDialog=new MessageDialog(shell, SWT.DIALOG_TRIM, "充值至少1元", 2);
					msgDialog.open();
					shell.dispose();
				}
				
			}

			private Shell getParent() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		btnOk.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 20,
				SWT.NORMAL));
		btnOk.setBounds(223, 226, 87, 35);
		btnOk.setText("确定");

		Button button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				
				shell.close();
			}
		});
		button.setText("退出");
		button.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 20,
				SWT.NORMAL));
		button.setBounds(347, 226, 87, 35);

	}

}
