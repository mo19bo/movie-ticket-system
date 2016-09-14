package MD;


import java.sql.Timestamp;
import java.util.Date;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import databaseO.DBFactory;
import DuangFunc.ConcerttoDate;
import DuangFunc.RandomID;
import org.eclipse.swt.widgets.DateTime;
public class MovieInfo extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text imageUri;
	private Text movieName;
	private Text movieStyle;
	private Text movieDirector;
	private Text movieActor;
	private Text movieTime;
	private Text movieDescri;
    public String m_nameString;
    public String m_id;
    private String fileName = null;  
    private String type;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MovieInfo(Shell parent, int style) {
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shell.setModified(true);
		shell.setSize(450, 300);
		shell.setText("电影信息录入");
		shell.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shell, SWT.DIALOG_TRIM);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new FillLayout(SWT.VERTICAL));
		
		Composite composite_4 = new Composite(composite, SWT.NONE);
		composite_4.setLayout(new BorderLayout(0, 0));
		
		Label lblNewLabel = new Label(composite_4, SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setText("请设置电影的宣传海报");
		
		Composite composite_5 = new Composite(composite, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_5.setLayout(new BorderLayout(0, 0));

		//	打开文件管理器并获得文件地址
		Button btnNewButton = new Button(composite_5, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 FileDialog dlg =new FileDialog(shell, SWT.OPEN);   
				    dlg.setText("Open");   
				    dlg.setFilterNames(new String[]{"图片文件(*.jpg)","图片文件（*.gif）"});   
				    dlg.setFilterExtensions(new String[]{"*.jpg", "*.gif","*.png"});   
				    fileName = dlg.open();//取得打开图片的物理地址 
				   imageUri.setText(fileName);
				 
				    
			}
		});
		btnNewButton.setLayoutData(BorderLayout.WEST);
		btnNewButton.setText("设置");
		
		imageUri = new Text(composite_5, SWT.BORDER);
		imageUri.setLayoutData(BorderLayout.CENTER);
		//	录入电影信息，退出系统
		Button button = new Button(composite, SWT.NONE);
		
		
		button.setText("确认录入");
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2.setLayout(new FillLayout(SWT.VERTICAL));
		
		Label lblNewLabel_2 = new Label(composite_2, SWT.CENTER);
		lblNewLabel_2.setText("电影详情录入：");
		
		Composite composite_6 = new Composite(composite_2, SWT.NONE);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel_3 = new Label(composite_6, SWT.CENTER);
		lblNewLabel_3.setText("片名：");
		
		movieName = new Text(composite_6, SWT.BORDER);
		
		Composite composite_7 = new Composite(composite_2, SWT.NONE);
		composite_7.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label = new Label(composite_7, SWT.CENTER);
		label.setText("上映日期：");
		
		final DateTime dateTime = new DateTime(composite_7, SWT.BORDER);
		
		
		Composite composite_8 = new Composite(composite_2, SWT.NONE);
		composite_8.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label_2 = new Label(composite_8, SWT.CENTER);
		label_2.setText("电影类型：");
		
		movieStyle = new Text(composite_8, SWT.BORDER);
		
		Composite composite_9 = new Composite(composite_2, SWT.NONE);
		composite_9.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label_4 = new Label(composite_9, SWT.CENTER);
		label_4.setText("导演：");
		
		movieDirector = new Text(composite_9, SWT.BORDER);
		
		Composite composite_10 = new Composite(composite_2, SWT.NONE);
		composite_10.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label_6 = new Label(composite_10, SWT.CENTER);
		label_6.setText("主演：");
		
		movieActor = new Text(composite_10, SWT.BORDER);
		
		Composite composite_11 = new Composite(composite_2, SWT.NONE);
		composite_11.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label_1 = new Label(composite_11, SWT.CENTER);
		label_1.setText("电影时长：");
		
		movieTime = new Text(composite_11, SWT.BORDER);
		
		Label lblNewLabel_1 = new Label(composite_11, SWT.CENTER);
		lblNewLabel_1.setText("分钟");
		
		Composite composite_12 = new Composite(composite_2, SWT.NONE);
		composite_12.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lbldd = new Label(composite_12, SWT.CENTER);
		lbldd.setText("2D/3D:");
		
		Composite composite_13 = new Composite(composite_12, SWT.NONE);
		composite_13.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final Button btnRadioButton_1 = new Button(composite_13, SWT.RADIO);
		btnRadioButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			type="2D";
			}
		});
		
		btnRadioButton_1.setText("2D");
		 	
		Button btnRadioButton = new Button(composite_13, SWT.RADIO);
		btnRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				type="3D";
			}
		});
		btnRadioButton.setText("3D");
		
		Composite composite_3 = new Composite(composite_1, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_3.setLayout(new BorderLayout(0, 0));
		
		Label label_3 = new Label(composite_3, SWT.CENTER);
		label_3.setLayoutData(BorderLayout.NORTH);
		label_3.setText("电影剧情录入：");
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//	插入数据库
				DBFactory db = new DBFactory();
				String SQL = "insert into [movie] values (?,?,?,?,?,?,?,?,?,?,?)";
				//	随机生成电影ID
				RandomID mID = new RandomID();
				ConcerttoDate concerttoDate=new ConcerttoDate();
				Date pdateString=concerttoDate.converttoDate(dateTime.getYear(),dateTime.getMonth(),dateTime.getHours());
				Timestamp timestamp=new Timestamp(pdateString.getTime());
				//	传入参数
				m_id=mID.mv_id();
				float m_time=Float.parseFloat(movieTime.getText());
				float rate=0;
				Object[] params = {m_id, movieName.getText(), timestamp,
						movieStyle.getText(), movieDirector.getText(),rate, type, movieActor.getText(),
						 m_time, movieDescri.getText(), imageUri.getText()};
				m_nameString=movieName.getText();
				db.executeSQLWithoutResult(SQL, params);
				db.close();
				//	退出系统
				shell.close();
			}
			
			
		});
		movieDescri = new Text(composite_3, SWT.BORDER | SWT.WRAP);
		//movieDescri.setText("撒打开就阿訇点卡是大大是打法是否将阿里是减肥垃圾费垃圾是浪费");
		movieDescri.setLayoutData(BorderLayout.CENTER);

	}
}
