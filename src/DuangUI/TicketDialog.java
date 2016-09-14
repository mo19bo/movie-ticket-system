package DuangUI;

import java.io.BufferedReader; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

import databaseO.DBFactory;

import DuangFunc.ConcerttoDate;
import DuangFunc.MessageDialog;
import DuangFunc.RandomID;

public class TicketDialog extends Dialog {

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
	private  float price2;
	//	座位号
	private String no_seat;
	//	厅号
	private String no_hall;
	//	电影时间（相当于场次）
	private String time_cont;
	//	场次时间
	private Date scene;
	// 座位数量（票数）
	private int seat_contString;
	//	订单号
	private String order_idString;
	//	用户ID
	private String userId;
	//	订单金额
	private float oMoney;
	private String postDate;
	//	场次id
	private String sencetime;
	private String senceNum;
	private String moId;
	private String moTi;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	//座号，电影名称，满座情况，厅号，座位号，场次时间，座位数量，单价
	public TicketDialog(Shell parent, int style, String sNum, String movieName, String No_hall, String No_seat, Date Scenedate, int Seatcont, float Price,String sID, String mID) {
		
		super(parent, SWT.DIALOG_TRIM | SWT.RESIZE);
		mInfoString = movieName;
		senceNum = sID;
		scene = Scenedate;
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			scene = sdf.parse(Scenedate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		no_hall=No_hall;
		
		seat_contString=Seatcont;
		no_seat=No_seat;
		price2=Price;
		sencetime=sNum;
		//oMoney = seat_contString*price; 
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

		setText("电影票信息");
	
		
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
		shell.setSize(450, 300);
		shell.setText(getText());
		shell.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		//	返回按钮
		Button back = new Button(composite, SWT.NONE);
		back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		back.setText("返回");
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new GridLayout(8, false));
		new Label(composite_1, SWT.NONE);
		
		Label lblNewLabel_4 = new Label(composite_1, SWT.NONE);
		lblNewLabel_4.setText("订单号");
		new Label(composite_1, SWT.NONE);
		
		Label No_order = new Label(composite_1, SWT.NONE);
		No_order.setText(order_idString);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setText("电影名");
		new Label(composite_1, SWT.NONE);
		
		Label movie_name = new Label(composite_1, SWT.NONE);
		movie_name.setText(mInfoString);//电影名
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		GridData gd_lblNewLabel_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_1.widthHint = 58;
		gd_lblNewLabel_1.heightHint = 21;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		lblNewLabel_1.setText("厅号");
		
		Label No_hall = new Label(composite_1, SWT.NONE);
		No_hall.setText(no_hall);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		GridData gd_lblNewLabel_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_2.heightHint = 28;
		lblNewLabel_2.setLayoutData(gd_lblNewLabel_2);
		lblNewLabel_2.setText("放映时间");
		new Label(composite_1, SWT.NONE);
		
		movie_time = new Label(composite_1, SWT.NONE);
		movie_time.setText(scene.toString());
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setText("座号");
		
		Label No_seat = new Label(composite_1, SWT.WRAP);
		No_seat.setText(no_seat);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label sence = new Label(composite_1, SWT.NONE);
		sence.setText("场次");
		new Label(composite_1, SWT.NONE);
		
		Label No_sence = new Label(composite_1, SWT.NONE);
		No_sence.setText(sencetime);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label lblNewLabel_3 = new Label(composite_1, SWT.NONE);
		lblNewLabel_3.setText("价格");
		new Label(composite_1, SWT.NONE);
		
		Label price = new Label(composite_1, SWT.NONE);
		price.setText(String.valueOf(price2));
		GridData gd_price = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_price.widthHint = 74;
		gd_price.heightHint = 35;
		price.setLayoutData(gd_price);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("票数");
		
		Label movieticks = new Label(composite_1, SWT.NONE);
		movieticks.setText(String.valueOf(seat_contString));

	}
}
