package DuangUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.custom.ScrolledComposite;

import DuangFunc.ConcerttoDate;
import DuangFunc.MessageDialog;
import DuangFunc.RandomID;
import databaseO.DBFactory;
import org.eclipse.swt.widgets.DateTime;

public class Adverisement extends Composite {
	//	定义年月日 
	int year=0;
    int month=0;
    int day=0;
	private String fileName = null;  
	private Text textUri;
	private Image image = null;   
	private String textImage;
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public Adverisement(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.WEST);

		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new GridLayout(3, false));
		new Label(composite_1, SWT.NONE);

		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setText("图片预览：");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		final ScrolledComposite scrolledComposite = new ScrolledComposite(composite_1, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_scrolledComposite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_scrolledComposite.heightHint = 480;
		gd_scrolledComposite.widthHint = 409;
		scrolledComposite.setLayoutData(gd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		final Composite picture = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(picture);
		scrolledComposite.setMinSize(picture.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		picture.setLayout(new BorderLayout());
		picture.setLayoutData(BorderLayout.CENTER);
		final ImageComposite ic = new ImageComposite(picture, SWT.NONE, new Image(null, "src/pictureUI/HomeBg.png"),
				"Scaled");
		ic.setLayoutData(BorderLayout.CENTER);
		picture.layout();
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		Button button = new Button(composite_1, SWT.NONE);
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button.heightHint = 18;
		gd_button.widthHint = 135;
		button.setLayoutData(gd_button);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 try {
					FileDialog dlg =new FileDialog(getShell(), SWT.OPEN);   
					    dlg.setText("Open");   
					    dlg.setFilterNames(new String[]{"图片文件(*.jpg)","图片文件（*.gif）"});   
					    dlg.setFilterExtensions(new String[]{"*.jpg", "*.gif","*.png"});   
					    fileName = dlg.open();//取得打开图片的物理地址 
					    textUri.setText(fileName);
					    textImage = fileName;
					   
				} catch (Exception e1) {
					MessageDialog msDialog = new MessageDialog(getShell(),SWT.DIALOG_TRIM, "文件不能为空！", 1);
					msDialog.open();
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setText("上传图片");
		
		textUri = new Text(composite_1, SWT.BORDER);
		textUri.setText("D:\\post\\image-55CC_54D1F896.jpg");
		GridData gd_textUri = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textUri.widthHint = 158;
		textUri.setLayoutData(gd_textUri);
		new Label(composite_1, SWT.NONE);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!ic.isDisposed()){
				ic.dispose();
				}
				Control[] conts = picture.getChildren();
				for (Control control : conts) {
					control.dispose();
				}
				System.out.println(textUri.getText());
				if(textUri.getText() == null){
					MessageDialog mDialog = new MessageDialog(getShell(), getStyle(), "请添加图片！", 2);
					mDialog.open();
				}
				else {
					ImageComposite ic = new ImageComposite(picture, SWT.NONE, new Image(null, textUri.getText()),
							"Scaled");
					ic.setLayoutData(BorderLayout.CENTER);
					picture.layout();
				}
				}
				
			
		});
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.heightHint = 18;
		gd_btnNewButton.widthHint = 134;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("预览图片");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		Label you = new Label(composite_1, SWT.NONE);
		GridData gd_you = new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1);
		gd_you.widthHint = 84;
		you.setLayoutData(gd_you);
		you.setText("截止日期：");
		
		final DateTime dateTime = new DateTime(composite_1, SWT.BORDER);
		new Label(composite_1, SWT.NONE);
		//	插入数据库
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
					MessageDialog msDialog = new MessageDialog(getShell(), getStyle(), "插入广告成功！", 2);
					msDialog.open();
					
					Calendar c = Calendar.getInstance();
					int year = c.get(Calendar.YEAR); 
					int month = c.get(Calendar.MONTH); 
					int date = c.get(Calendar.DATE); 
					int hour = c.get(Calendar.HOUR_OF_DAY); 
					int minute = c.get(Calendar.MINUTE); 
					int second = c.get(Calendar.SECOND); 
					String timeCurent = year + "-" + month + "-" + date + " " +hour + ":" +minute + ":" + second;
					System.out.println(timeCurent);
					
					//	插入数据库
				DBFactory db = new DBFactory();
				String SQL = "insert into [advertise] values (?,?,?,?,?)";
				//	随机生成广告ID
				RandomID aID = new RandomID();
				ConcerttoDate concerttoDate=new ConcerttoDate();
				Date pdateString = concerttoDate.converttoDate(dateTime.getYear(),dateTime.getMonth(),dateTime.getHours());
				Timestamp timestamp = new Timestamp(pdateString.getTime());
				//	传入参数
				BufferedImage image;
				//要想保存这个对象的话你要把image声明为BufferedImage 类型
				try {
					image = ImageIO.read(new File(textUri.getText()));
					ImageIO.write(image, "png", new File("D:advertisement\\advs.png"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Object[] params = {textUri.getText(), null, timeCurent, timestamp, aID.a_id()};
				db.executeSQLWithoutResult(SQL, params);
				db.close();
			}
		});
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.heightHint = 23;
		gd_button_1.widthHint = 131;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("确认投放");
		new Label(composite_1, SWT.NONE);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
