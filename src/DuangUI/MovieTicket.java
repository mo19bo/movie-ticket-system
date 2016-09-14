//冯亮 2015-3-17
//电影票类 宝库布局和操作
package DuangUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import databaseO.DBFactory;
import databaseO.MovieDAO;
import DuangClass.Movie;
import DuangClass.Session;
import DuangFunc.MessageDialog;
import MD.SelectSence;

import org.eclipse.swt.custom.ScrolledComposite;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
 
public class MovieTicket extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	
	// 三个关于电影票类的对象
	DBFactory dbFactory = new DBFactory();
	MovieDAO movieDAO = new MovieDAO();
	Movie movie = new Movie();
	private Image image = null; 
	//	电影	id
	private String movieIDA;
	private String userID;
	//	电影描述
	private String descri;
	//	电影类型
	private String styleA;
	//	电影导演
	private String dactor;
	//	电影主演
	private String actor;
	//	电影评分
	private Float score;
	//	电影 2D/3D
	private String dNum;
	//	电影上映日期
	private String mDateString="";
	//	电影海报
	private String moImage;
	private String mNameString = "";	//	电影名称
	private String sNum = ""; //	场次id
	private int seatCount = 0;	// 	座位数量 
	private int sStruct = 0;	//	座位结构
	private String hNum = "";	//	厅号
	private ArrayList<Session> session =new ArrayList<Session>() ;	//	场次日期
	private ArrayList<String> sDateString=new ArrayList<String>();
	private String full_seat="";
	//获取数据库中总共的电影部数
	int numOfMovie = movieDAO.numberOfMovie("movie",dbFactory);
	
	//查询数据库中的电影信息并存到list中
	List<Movie> list = movieDAO.searchMovie(dbFactory);
	
	
	//布局代码(传值电影ID，电影名称，上映日期，电影类型，导演，主演，电影评分，2D/3D,电影剧情)
	public MovieTicket(final Composite parent, int style, String mID, String mName, String mDate, String mStyle, String mD, String mActor,
		Float mScore, String m2d, String mDescri,String mimage) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		moImage = mimage;
		//	为电影信息赋值
		movieIDA=mID;
		mNameString = mName;
		mDateString = mDate;
		styleA = mStyle;
		dactor =mD;
		actor = mActor;
		score = mScore;
		dNum = m2d;
		descri = mDescri;
		final String[] movieName = new String[numOfMovie];
		final String[] movieDate = new String[numOfMovie];
		final String[] movieDirector = new String[numOfMovie];
		final String[] movieActor= new String[numOfMovie];
		final String[] movieStyle = new String[numOfMovie];
		final String[] movieD = new String[numOfMovie];
		final String[] movieDescription = new String[numOfMovie];
		final float[] movieScore= new float[numOfMovie];
		final String[] movieImage = new String[numOfMovie];
		final String[] movieID = new String[numOfMovie];
		for (int i = 0; i <numOfMovie ; i++) {
			// 这里为一个电影块
			
			movie = list.get(i);
			
			movieID[i] = movie.getMovie_Id();		
			movieName[i] = movie.getMovie_Name();
			movieActor[i] =movie.getMovie_Actor();
			movieD[i] = movie.getMovie_D();
			movieDate[i] = movie.getMovie_Date();
			movieDescription[i] = movie.getMoivie_Description();
			movieDirector[i] = movie.getMovie_Director();
			movieStyle[i] = movie.getMovie_Style();
			movieScore[i] = movie.getMovie_Score();
			movieImage[i] = movie.getMovie_Image();
			

		

			
		}
		
		Composite high = new Composite(this, SWT.NONE);
		high.setLayoutData(BorderLayout.CENTER);
		high.setLayout(new BorderLayout(0, 0));
		
		Composite down = new Composite(high, SWT.BORDER);
		down.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		down.setLayoutData(BorderLayout.SOUTH);
		down.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite_1 = new Composite(down, SWT.NONE);
		composite_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		composite_1.setLayout(new FillLayout(SWT.VERTICAL));
		//	返回主页
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		btnNewButton.setImage(SWTResourceManager.getImage(MovieTicket.class, "/pictureUI/i.png"));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Control[] conts = parent.getChildren();
				for (Control control : conts) {
					control.dispose();
				}
				DuangMovie hGui = new DuangMovie(parent, SWT.NONE);
				hGui.setLayoutData(BorderLayout.CENTER);
				parent.layout();
			}
		});
		btnNewButton.setText("返回主页");
		
		Composite composite_2 = new Composite(down, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.VERTICAL));
		//	选场次（检测机制，如果是非用户则无法进行下一步，并提醒进行登陆）
		Button btnNewButton_1 = new Button(composite_2, SWT.NONE);
		btnNewButton_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		btnNewButton_1.setImage(SWTResourceManager.getImage(MovieTicket.class, "/pictureUI/m.png"));
	
		btnNewButton_1.setText("选择场次");
		
		final Composite up = new Composite(high, SWT.NONE);
		up.setLayoutData(BorderLayout.CENTER);
		up.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final Composite picture = new Composite(up, SWT.NONE);
		picture.setLayout(new BorderLayout());
		picture.setLayoutData(BorderLayout.CENTER);
		
		final ImageComposite ic = new ImageComposite(picture, SWT.NONE, new Image(null, mimage),
				"Scaled");
		System.out.println("asdasa"+mimage);
		ic.setLayoutData(BorderLayout.CENTER);
		picture.layout();
		picture.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		picture.setLayout(new BorderLayout(0, 0));
		
		ScrolledComposite scrolledComposite_1 = new ScrolledComposite(up, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_1.setExpandHorizontal(true);
		scrolledComposite_1.setExpandVertical(true);
		
		Composite movieDetail = new Composite(scrolledComposite_1, SWT.NONE);
		movieDetail.setLayout(new FillLayout(SWT.VERTICAL));
		
		Composite composite_12 = new Composite(movieDetail, SWT.NONE);
		composite_12.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_12.setLayout(new FormLayout());
		
		Label label = new Label(composite_12, SWT.WRAP | SWT.SHADOW_NONE | SWT.CENTER);
		FormData fd_label = new FormData();
		fd_label.bottom = new FormAttachment(0, 33);
		fd_label.right = new FormAttachment(0, 424);
		fd_label.top = new FormAttachment(0);
		fd_label.left = new FormAttachment(0);
		label.setLayoutData(fd_label);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.BOLD));
		label.setText(" 电影详情：");
		
		Composite composite_13 = new Composite(composite_12, SWT.NONE);
		FormData fd_composite_13 = new FormData();
		fd_composite_13.top = new FormAttachment(label, 1);
		fd_composite_13.left = new FormAttachment(label, 0, SWT.LEFT);
		fd_composite_13.right = new FormAttachment(0, 208);
		composite_13.setLayoutData(fd_composite_13);
		composite_13.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label label_2 = new Label(composite_13, SWT.CENTER);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(0, 0, 232, 24);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		label_2.setBackground(SWTResourceManager.getColor(235,98,0));
		label_2.setText(" 片名：");
		
		final Label lblNewLabel_6 = new Label(composite_13, SWT.WRAP | SWT.CENTER);
		lblNewLabel_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel_6.setBounds(0, 30, 203, 32);
		lblNewLabel_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblNewLabel_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_6.setText(mNameString);
		
		Composite composite_14 = new Composite(composite_12, SWT.NONE);
		FormData fd_composite_14 = new FormData();
		fd_composite_14.top = new FormAttachment(label, 1);
		fd_composite_14.right = new FormAttachment(100, -5);
		fd_composite_14.left = new FormAttachment(0, 209);
		composite_14.setLayoutData(fd_composite_14);
		composite_14.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel_2 = new Label(composite_14, SWT.CENTER);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(0, 0, 217, 24);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(235,98,0));
		lblNewLabel_2.setText(" 上映日期：");
		
		final Label lblNewLabel_7 = new Label(composite_14, SWT.WRAP | SWT.CENTER);
		lblNewLabel_7.setBounds(0, 30, 220, 35);
		lblNewLabel_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		lblNewLabel_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_7.setText(mDateString);
		
		
		
		
		Composite composite_8 = new Composite(composite_12, SWT.NONE);
		FormData fd_composite_8 = new FormData();
		fd_composite_8.right = new FormAttachment(label, 10, SWT.RIGHT);
		fd_composite_8.top = new FormAttachment(0, 298);
		fd_composite_8.bottom = new FormAttachment(100);
		fd_composite_8.left = new FormAttachment(0);
		composite_8.setLayoutData(fd_composite_8);
		composite_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_8.setLayout(new FormLayout());
		
		Label label_12 = new Label(composite_8, SWT.CENTER);
		label_12.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_label_12 = new FormData();
		fd_label_12.left = new FormAttachment(0);
		fd_label_12.right = new FormAttachment(100, -5);
		fd_label_12.bottom = new FormAttachment(0, 24);
		fd_label_12.top = new FormAttachment(0);
		label_12.setLayoutData(fd_label_12);
		label_12.setText("剧情概要：");
		label_12.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		label_12.setBackground(SWTResourceManager.getColor(235,98,0));
		
		Composite composite_9 = new Composite(composite_8, SWT.NONE);
		composite_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_9.setLayout(new GridLayout(1, false));
		FormData fd_composite_9 = new FormData();
		fd_composite_9.top = new FormAttachment(label_12, 15);
		fd_composite_9.bottom = new FormAttachment(100, -10);
		fd_composite_9.left = new FormAttachment(0);
		fd_composite_9.right = new FormAttachment(100);
		composite_9.setLayoutData(fd_composite_9);
		
		final Label lblNewLabel = new Label(composite_9, SWT.WRAP | SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel.setText(mDescri);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.heightHint = 262;
		gd_lblNewLabel.widthHint = 426;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		
		Composite composite_3 = new Composite(composite_12, SWT.NONE);
		fd_composite_14.bottom = new FormAttachment(composite_3, -6);
		fd_composite_13.bottom = new FormAttachment(composite_3, -6);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_composite_3 = new FormData();
		fd_composite_3.top = new FormAttachment(0, 109);
		fd_composite_3.left = new FormAttachment(label, 0, SWT.LEFT);
		composite_3.setLayoutData(fd_composite_3);
		
		Label label_1 = new Label(composite_3, SWT.CENTER);
		label_1.setText("主演：");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		label_1.setBackground(SWTResourceManager.getColor(235,98,0));
		label_1.setBounds(0, 0, 207, 24);
		
		final Label label_3 = new Label(composite_3, SWT.WRAP | SWT.CENTER);
		label_3.setText(mActor);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setBounds(0, 30, 203, 37);
		
		Composite composite_4 = new Composite(composite_3, SWT.NONE);
		composite_4.setBounds(209, 0, 220, 65);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label label_4 = new Label(composite_4, SWT.CENTER);
		label_4.setText("导演：");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		label_4.setBackground(SWTResourceManager.getColor(235,98,0));
		label_4.setBounds(0, 0, 216, 24);
		
		final Label label_5 = new Label(composite_4, SWT.WRAP | SWT.CENTER);
		label_5.setText(dactor);
		label_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setBounds(0, 30, 220, 35);
		
		Composite composite_5 = new Composite(composite_12, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_composite_5 = new FormData();
		fd_composite_5.top = new FormAttachment(composite_13, 77);
		fd_composite_5.right = new FormAttachment(100, -5);
		fd_composite_5.left = new FormAttachment(0);
		composite_5.setLayoutData(fd_composite_5);
		
		Label label_6 = new Label(composite_5, SWT.CENTER);
		label_6.setText("电影类型：");
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		label_6.setBackground(SWTResourceManager.getColor(235,98,0));
		label_6.setBounds(0, 0, 208, 24);
		
		final Label label_7 = new Label(composite_5, SWT.WRAP | SWT.CENTER);
		label_7.setText(mStyle);
		label_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_7.setBounds(0, 30, 214, 35);
		
		Composite composite_6 = new Composite(composite_5, SWT.NONE);
		composite_6.setBounds(210, 0, 214, 65);
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label label_9 = new Label(composite_6, SWT.CENTER);
		label_9.setText("电影评分：");
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_9.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		label_9.setBackground(SWTResourceManager.getColor(235,98,0));
		label_9.setBounds(0, 0, 217, 24);
		
		final Label label_10 = new Label(composite_6, SWT.WRAP | SWT.CENTER);
		label_10.setText(mScore+"");
		label_10.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_10.setBounds(0, 30, 220, 35);
		
		Label lbldd = new Label(composite_12, SWT.CENTER);
		lbldd.setText("3D/2D:");
		lbldd.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbldd.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		lbldd.setBackground(SWTResourceManager.getColor(235,98,0));
		FormData fd_lbldd = new FormData();
		fd_lbldd.right = new FormAttachment(100, -9);
		fd_lbldd.left = new FormAttachment(0);
		fd_lbldd.bottom = new FormAttachment(composite_5, 23, SWT.BOTTOM);
		fd_lbldd.top = new FormAttachment(composite_5);
		lbldd.setLayoutData(fd_lbldd);
		
		final Label lblNewLabel_1 = new Label(composite_12, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setText(m2d);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.top = new FormAttachment(lbldd, 6);
		fd_lblNewLabel_1.left = new FormAttachment(label, 205, SWT.LEFT);
		fd_lblNewLabel_1.right = new FormAttachment(100, -134);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		//lblNewLabel.setText(descri);
		scrolledComposite_1.setContent(movieDetail);
		scrolledComposite_1.setMinSize(movieDetail.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Composite composite = new Composite(up, SWT.NONE);
		composite.setLayout(new BorderLayout(0, 0));
		
		Label label_8 = new Label(composite, SWT.BORDER | SWT.CENTER);
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_8.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.BOLD));
		label_8.setLayoutData(BorderLayout.NORTH);
		label_8.setText("近期热映的电影：");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		scrolledComposite.setLayoutData(BorderLayout.CENTER);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite_25 = new Composite(scrolledComposite, SWT.NONE);
		composite_25.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		for (int i = 0; i < numOfMovie ; i++) {
			// 这里为一个电影块(近期热映)
		
			
			Composite block_product = new Composite(composite_25, SWT.NONE);
			block_product.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			block_product.setLayout(new GridLayout(2, false));
			new Label(block_product, SWT.NONE);
			new Label(block_product, SWT.NONE);

			Composite composite_61 = new Composite(block_product, SWT.NONE);
			composite_61.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			composite_61.setLayout(new BorderLayout());
			composite_61.setLayoutData(BorderLayout.CENTER);
			final ImageComposite ic1 = new ImageComposite(composite_61, SWT.NONE, new Image(null, movieImage[i]),
					"Scaled");
			ic1.setData("index",i);
			ic1.setLayoutData(BorderLayout.CENTER);
			
			ic1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					lblNewLabel_6.setText(movieName[Integer.parseInt(ic1.getData("index").toString())]);
					lblNewLabel_7.setText(movieDate[Integer.parseInt(ic1.getData("index").toString())]);
					label_3.setText(movieStyle[Integer.parseInt(ic1.getData("index").toString())]);
					label_5.setText(movieDirector[Integer.parseInt(ic1.getData("index").toString())]);
					label_3.setText(movieActor[Integer.parseInt(ic1.getData("index").toString())]);
					label_10.setText(movieScore[Integer.parseInt(ic1.getData("index").toString())]+"");
					label_7.setText(movieD[Integer.parseInt(ic1.getData("index").toString())]);
					lblNewLabel.setText(movieDescription[Integer.parseInt(ic1.getData("index").toString())]);
					lblNewLabel_1.setText(movieD[Integer.parseInt(ic1.getData("index").toString())]);
					movieIDA = movieID[Integer.parseInt(ic1.getData("index").toString())];
					
					
					
					
					
					
					Control[] ctrls = picture.getChildren();
					for (Control ctrl : ctrls) {
						ctrl.dispose();
					}
				
					
					ImageComposite ic = new ImageComposite(picture, SWT.NONE, new Image(null, movieImage[Integer.parseInt(ic1.getData("index").toString())]),
							"Scaled");
					ic.setLayoutData(BorderLayout.CENTER);
					picture.layout();
					picture.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
					
					picture.setLayout(new BorderLayout(0, 0));
				}
				});
			composite_61.layout();
			GridData gd_composite_6 = new GridData(SWT.LEFT, SWT.CENTER, false,
					false, 1, 1);
			gd_composite_6.heightHint = 164;
			gd_composite_6.widthHint = 139;
			composite_61.setLayoutData(gd_composite_6);
			
			Composite composite1 = new Composite(block_product, SWT.NONE);
			GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_composite.widthHint = 268;
			gd_composite.heightHint = 197;
			composite1.setLayoutData(gd_composite);
			composite1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			
			Label movieDescri = new Label(composite1, SWT.WRAP | SWT.HORIZONTAL);
			movieDescri.setBounds(0, 17, 258, 159);
			movieDescri.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			movieDescri.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
			movieDescri.setText(movieDescription[i].substring(0, 100)+"......");
			Label label_11 = new Label(composite1, SWT.CENTER);
			label_11.setBounds(0, 0, 258, 17);
			label_11.setText("剧情：");
			label_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			

			Label movieLable = new Label(block_product, SWT.CENTER);
			movieLable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			movieLable.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
			movieLable.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
			movieLable.setText(movieName[i]);
			new Label(block_product, SWT.NONE);

			
		}
		composite_25.setLayout(new FillLayout(SWT.VERTICAL));
		scrolledComposite.setContent(composite_25);
		scrolledComposite.setMinSize(composite_25.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		parent.setLayoutData(BorderLayout.CENTER);
		parent.setLayout(new BorderLayout(0, 0));
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				

				//	从文件读取用户ID
				try {
					FileReader reader = new FileReader("login.txt");
					BufferedReader br = new BufferedReader(reader);
					userID = br.readLine();
					 
					 
                   if(userID==null){
                	   
                		MessageDialog md =new MessageDialog(getShell(), getStyle(), "请先登录在进行购买", 2);
						md.open();
						
                	   
                   }
                 
                   else if(userID.toCharArray()[1]!= '3'){
                	  
                	 
						MessageDialog msDialog = new MessageDialog(getShell(), getStyle(), "管理员不可进行购买操作", 2);
						msDialog.open();
                   }
					 else {
						
						
				//场次ID
					DBFactory db = new DBFactory();
					String SQL = "select * from [sence] where mv_id = ?";
					Object[] params = {movieIDA};
					ResultSet resultSet =  db.executeSQLWithResult(SQL, params);
					int x = 0;
					ArrayList<String> sDateString=new ArrayList<String>();
					try {
						while(resultSet.next())
						{
							// 传场次信息的值
							Session session_x=new Session();
									
							sNum = resultSet.getString("s_num");
							hNum=resultSet.getString("h_num");
							SimpleDateFormat format  = new SimpleDateFormat();
							Date date_tDate=resultSet.getDate("Date");
							full_seat=resultSet.getString("full_seat");
							String date = format.format(date_tDate);
							//System.out.println(x);
							//	生成字符串时间数组(场次具体信息)
							System.out.println(date+resultSet.getString("start_time")+resultSet.getString("end_time"));
							 
							 sDateString.add(date+" "+resultSet.getString("start_time")+"--"+resultSet.getString("end_time"));
							 session_x.setMovieId(movieIDA);
							 session_x.setDatetimeString(date+resultSet.getString("start_time")+resultSet.getString("end_time"));
							 session_x.setSession_Date( date_tDate);
							 session_x.setHallId(hNum);
							 session_x.setSessionId(sNum);
							 session_x.setFullseatSituation(full_seat);
							 session.add(session_x);
							
						   //  x++;
							
						//	seatCount = resultSet.getInt("seat_cont");
						
						//	sStruct = resultSet.getInt("row");
						//full_seat = resultSet.getString("full_seat");
						}
						} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
					}
					db.close();
					Movie movie=new Movie();
					movie.setMovie_Name(lblNewLabel_6.getText());
					movie.setMovie_Id(movieIDA);
					movie.setMovie_Date(lblNewLabel_7.getText());
					movie.setMovie_Director(label_5.getText());
					
					
					/************这是传值句*********************/
				//	电影名称，场次日期，座位数量， 座位结构，厅号，满座情况
					
					SelectSence sence = new SelectSence(getShell(), getStyle(), sDateString,movie,session);
					
					sence.open();
					
					}
					
					
					
			
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					//	未登录用户无法进入下一步，并且提示登陆再购票
					 
            		MessageDialog md =new MessageDialog(getShell(), getStyle(), "请先登录在进行购买", 2);
					md.open();
					}			
				catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}});
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
