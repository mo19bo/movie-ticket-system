package DuangUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;

import databaseO.DBFactory;

import DuangFunc.ConcerttoDate;
import DuangFunc.MessageDialog;
import DuangFunc.RandomID;
import DuangFunc.token_string;
public class TicketContent extends Composite {

//	用户账户
	private float account;
	//	定义年月日 
	int year=0;
    int month=0;
    int day=0;
	protected Object result;
	protected Shell shell;
	public String mInfoString;
	//	满座情况
	public String full_seat;
	private Label movie_time;
	// 	单价
	private  float price;
	//	座位号
	private String no_seat;
	//	厅号
	private String no_hall;
	//	电影时间（相当于场次）
	private String time_cont;
	//	场次时间
	private String scene;
	// 座位数量（票数）
	private int seat_contString;
	//	订单号
	private String order_idString;
	//	用户ID
	private String userId;
	//	订单金额
	private float oMoney;
	//	场次id
	private String sencetime;
	private String senceNum;
	private String moId;
	private String moTi;
	//	座位号
	private String[] seatEvery; 
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TicketContent(final Composite parent, int style, String sNum, String movieName, String a, String No_hall, String No_seat, String Scenedate, int Seatcont, float Price,String sID, String mID) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		mInfoString = movieName;
		senceNum = sID;
		full_seat=a;
		no_hall=No_hall;
		scene=Scenedate;
		seat_contString=Seatcont;
		no_seat=No_seat;
		price=Price;
		sencetime=sNum;
		oMoney = seat_contString*price; 
		order_idString=new RandomID().o_id();
		moId = mID;
		
		//	得到用户ID
		try {
			FileReader reader = new FileReader("login.txt");
			BufferedReader br = new BufferedReader(reader);
			userId = br.readLine();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(null);
		
		Button button = new Button(composite, SWT.NONE);
		button.setBounds(0, 0, 259, 60);
		button.setImage(SWTResourceManager.getImage(TicketContent.class, "/pictureUI/f.png"));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				插入数据库（订单，电影票），更新（场次）,更新（用户账户）
				Calendar c=Calendar.getInstance();//获得系统当前日期
		        year=c.get(Calendar.YEAR);
		        month=c.get(Calendar.MONTH)+1;//系统日期从0开始算起
		        day=c.get(Calendar.DAY_OF_MONTH);
		        ConcerttoDate concerttoDateA = new ConcerttoDate();
				Date pdateStringA = concerttoDateA.converttoDate(year, month, day);
				//	下单时间
				Timestamp timestampA = new Timestamp(pdateStringA.getTime());
				//	到期时间
				Timestamp timestampB = new Timestamp(pdateStringA.getTime()+30);
				DBFactory db = new DBFactory();
			
				//	随机生成用户订单ID,电影票订单ID
				RandomID tID = new RandomID();
				
				
				ArrayList<String> arr=new ArrayList<String>();
				arr=new token_string().token_string_a(no_seat);
				String SQL="";
				SQL = "insert into [userorder] values (?,?,?,?,?,?,?)";
				Object[] params = {order_idString, userId, seat_contString, timestampA, timestampB, oMoney,"0"};
				db.executeSQLWithoutResult(SQL, params);
				System.out.println(order_idString);
				for(int j=0;j<arr.size();j++){
					String No_seat=arr.get(j);
					moTi =  tID.m_id();
					SQL = "insert into [m_tickets] values (?,?,?,?,?)"; 
					Object[] paramsA = { moTi, senceNum, No_seat, price,order_idString};
					db.executeSQLWithoutResult(SQL, paramsA);
				
				}
				db.close();
				MessageDialog mg = new MessageDialog(getShell(), getStyle(), "加入订单成功！", 2);
				mg.open();
				//shell.dispose();
			}
		});
		button.setText("稍后付款");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBounds(258, 0, 269, 60);
		btnNewButton_1.setImage(SWTResourceManager.getImage(TicketContent.class, "/pictureUI/j.png"));
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				插入数据库（订单，电影票），更新（场次）,更新（用户账户）
				Calendar c=Calendar.getInstance();//获得系统当前日期
		        year=c.get(Calendar.YEAR);
		        month=c.get(Calendar.MONTH)+1;//系统日期从0开始算起
		        day=c.get(Calendar.DAY_OF_MONTH);
		        ConcerttoDate concerttoDateA = new ConcerttoDate();
				Date pdateStringA = concerttoDateA.converttoDate(year, month, day);
				//	下单时间
				Timestamp timestampA = new Timestamp(pdateStringA.getTime());
				//	到期时间
				Timestamp timestampB = new Timestamp(pdateStringA.getTime()+30);
				//	打开数据库连接,插入数据库
				DBFactory db = new DBFactory();
				
				//	随机生成用户订单ID,电影票订单ID
			
				RandomID tID = new RandomID();
				
				
				ArrayList<String> arr=new ArrayList<String>();
				arr=new token_string().token_string_a(no_seat);
				String SQL="";
				SQL = "insert into [userorder] values (?,?,?,?,?,?,?)";
				Object[] params = {order_idString, userId, seat_contString, timestampA, timestampB, oMoney,"1"};
				db.executeSQLWithoutResult(SQL, params);
				System.out.println(order_idString);
				for(int j=0;j<arr.size();j++){
					 System.out.println(j);
					 String No_seat=arr.get(j);
					moTi =  tID.m_id();
					SQL = "insert into [m_tickets] values (?,?,?,?,?)"; 
					Object[] paramsA = { moTi, senceNum, No_seat, price,order_idString};
					db.executeSQLWithoutResult(SQL, paramsA);
				
				}
				
				
				
				//	更新场次
				System.out.println(full_seat);
				SQL = "update [sence] set full_seat = ? where s_num = ?";
				Object[] paramsB = { full_seat, senceNum};
				db.executeSQLWithoutResult( SQL, paramsB);
				//	更新用户账户(先查找再计算)
				SQL = "select account from [user] where user_id = ?";
				Object[] paramsD = {userId};
				ResultSet resultSet = db.executeSQLWithResult(SQL, paramsD);
				
				try {
					while(resultSet.next())
					{
						System.out.println("l");
						account = resultSet.getFloat("account");
						if (account >= oMoney) {
							account = account - oMoney;
							//	计算金额(更新账户)
							SQL = "update [user] set account = ? where user_id = ?";
							Object[] paramsC = {account, userId};
							db.executeSQLWithoutResult(SQL, paramsC);
							
							MessageDialog md =new MessageDialog(getShell(), getStyle(), "恭喜您成功购买", 2);
							md.open();
							//shell.dispose();
						}
						else {
							MessageDialog md = new MessageDialog(getShell(), getStyle(), "余额不足，购买失败", 1);
							 md.open();
						}
					}
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				db.close();
			}
		});
		btnNewButton_1.setText("立即付款");
		
		Composite composite_3 = new Composite(this, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_3.setLayoutData(BorderLayout.NORTH);
		composite_3.setLayout(new BorderLayout(0, 0));
		
		Label lblNewLabel_10 = new Label(composite_3, SWT.CENTER);
		lblNewLabel_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_10.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		lblNewLabel_10.setText("请确认您的电影票：");
		//composite_3.setLayout(new GridLayout(1, false));
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setLayout(new GridLayout(6, false));
		
		Composite composite_4 = new Composite(composite_1, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label.setText("订单号：");
		
		Label No_order = new Label(composite_1, SWT.WRAP);
		No_order.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		No_order.setText(order_idString);
		GridData gd_No_order = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_No_order.heightHint = 50;
		gd_No_order.widthHint = 114;
		No_order.setLayoutData(gd_No_order);
		No_order.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		new Label(composite_1, SWT.NONE);
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_2.setText("价格：");
		
		Label price = new Label(composite_1, SWT.WRAP);
		price.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		price.setText(String.valueOf(25*seat_contString));
		GridData gd_price = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_price.heightHint = 27;
		gd_price.widthHint = 103;
		price.setLayoutData(gd_price);
		price.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Composite composite_7 = new Composite(composite_2, SWT.NONE);
		composite_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_7.setBounds(0, 0, 64, 64);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel.setText("电影名：");
		
		Label movie_name = new Label(composite_1, SWT.WRAP);
		movie_name.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		movie_name.setText(mInfoString);//电影名
		GridData gd_movie_name = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_movie_name.heightHint = 59;
		gd_movie_name.widthHint = 135;
		movie_name.setLayoutData(gd_movie_name);
		movie_name.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		new Label(composite_1, SWT.NONE);
		
		Label label_4 = new Label(composite_1, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_4.setText("厅号：");
		
		Label No_hallA = new Label(composite_1, SWT.WRAP);
		No_hallA.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		No_hallA.setText(no_hall);
		GridData gd_No_hall = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_No_hall.widthHint = 103;
		No_hallA.setLayoutData(gd_No_hall);
		No_hallA.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		
		Composite composite_5 = new Composite(composite_1, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Composite composite_8 = new Composite(composite_5, SWT.NONE);
		composite_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_8.setBounds(0, 0, 64, 64);
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel_1.setText("电影时间：");
		
		Label movie_time = new Label(composite_1, SWT.WRAP);
		movie_time.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		movie_time.setText(sencetime);//	电影时间
		GridData gd_movie_time = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_movie_time.heightHint = 59;
		gd_movie_time.widthHint = 133;
		movie_time.setLayoutData(gd_movie_time);
		movie_time.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		new Label(composite_1, SWT.NONE);
		
		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_3.setText("座位号：");
		
		Label No_seatA = new Label(composite_1, SWT.WRAP);
		No_seatA.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		System.out.println(no_seat);
		No_seatA.setText(no_seat);
		GridData gd_No_seat = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_No_seat.widthHint = 101;
		No_seatA.setLayoutData(gd_No_seat);
		No_seatA.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		
		Composite composite_6 = new Composite(composite_1, SWT.NONE);
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Composite composite_9 = new Composite(composite_6, SWT.NONE);
		composite_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_9.setBounds(0, 0, 64, 64);
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setText("场次：");
		
		Label No_sence = new Label(composite_1, SWT.WRAP);
		No_sence.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		No_sence.setText(sencetime);
		GridData gd_No_sence = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_No_sence.heightHint = 65;
		gd_No_sence.widthHint = 131;
		No_sence.setLayoutData(gd_No_sence);
		No_sence.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		new Label(composite_1, SWT.NONE);
		
		Label label_5 = new Label(composite_1, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_5.setText("购买票数：");
		
		Label movieticks = new Label(composite_1, SWT.WRAP);
		movieticks.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		movieticks.setText(String.valueOf(seat_contString));
		GridData gd_movieticks = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_movieticks.widthHint = 106;
		movieticks.setLayoutData(gd_movieticks);
		movieticks.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));

	
	}


	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
