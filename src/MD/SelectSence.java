package MD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import DuangClass.Movie;
import DuangClass.Session;
import DuangUI.Selectseat;

import databaseO.DBFactory;

public class SelectSence extends Dialog {

	protected Object result;
	protected Shell shell;
	//	电影名称
	private String moName;
	//	电影上映日期
	private String moDate;
	//	场次id
	private String sessionID;
	private static String textString = "";
	// 各种场次的时间
	private String hallIDString;
	private ArrayList<String> dateTime=new ArrayList<String>();
	private Movie movieS;
	private ArrayList<Session> sessionxArrayList=new ArrayList<Session>();
	private int row;
	private int cont;
	private String full_seat;
	private String session_uiString;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 * @param movie 
	 * @param session 
	 */
	public SelectSence(Shell parent, int style,ArrayList<String> sDateString, Movie movie, ArrayList<Session> session) {
		
		super(parent, style);
		dateTime = sDateString;
		 movieS= movie;
		sessionxArrayList=session;
		setText("请选择场次");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.setLocation(450,230);
		shell.setSize(606, 454);
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
		//shell.setSize(469, 350);
		shell.setText("请选座场次");
		shell.setLayout(new BorderLayout(0, 0));
		
		Composite main = new Composite(shell, SWT.NONE);
		main.setLayoutData(BorderLayout.CENTER);
		main.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(main, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new BorderLayout(0, 0));
		
		Composite composite_2 = new Composite(main, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.CENTER);
		composite_2.setLayout(new BorderLayout(0, 0));
		
		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		composite_3.setLayoutData(BorderLayout.WEST);
		composite_3.setLayout(new BorderLayout(0, 0));
		
		Label lblNewLabel_1 = new Label(composite_3, SWT.BORDER | SWT.WRAP | SWT.CENTER);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setLayoutData(BorderLayout.NORTH);
		lblNewLabel_1.setText("请选座场次");
		
		Composite picture = new Composite(composite_3, SWT.NONE);
		picture.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		picture.setLayoutData(BorderLayout.WEST);
		picture.setLayout(new BorderLayout());
		picture.setLayoutData(BorderLayout.CENTER);
		picture.layout();
		
		final Composite composite_7 = new Composite(composite_2, SWT.NONE);
		composite_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_7.setLayoutData(BorderLayout.CENTER);
		composite_7.setLayout(new BorderLayout(0, 0));
		
		Composite composite_8 = new Composite(composite_7, SWT.NONE);
		composite_8.setLayoutData(BorderLayout.NORTH);
		composite_8.setLayout(new BorderLayout(0, 0));
		
		Label lblNewLabel = new Label(composite_8, SWT.BORDER | SWT.CENTER);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel.setText("请选择您想要观看的场次");
		

		ScrolledComposite scrolledComposite = new ScrolledComposite(composite_7, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(BorderLayout.CENTER);
		scrolledComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		final Composite movie = new Composite(scrolledComposite, SWT.BORDER);
		movie.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		movie.setLayout(new FillLayout(SWT.VERTICAL));
		final int[] index = new int[10]; 
	
		for (int i = 0; i < dateTime.size(); i++) {
			 index[0]++;              
			 final Button button1 = new Button(movie, SWT.RADIO);   
			 button1.setImage(SWTResourceManager.getImage(SelectSence.class, "/pictureUI/help.png"));
			 button1.setText("第"+(index[0])+"场电影:"+dateTime.get(i)); 
			 button1.setData(sessionxArrayList.get(i).getSessionId());
			 // reset size of content so children can be seen - method 1              
			 movie.setSize(movie.computeSize(SWT.DEFAULT, SWT.DEFAULT));              
			 movie.layout(); 
			 
			 button1.addSelectionListener(new SelectionAdapter() {
				 	@Override
				 	public void widgetSelected(SelectionEvent e) {
				 	textString = button1.getText();
				 	sessionID=button1.getData().toString();
				 	
				 	System.out.println(sessionID);
				 	}
				 });
		}
		scrolledComposite.setContent(movie);
		scrolledComposite.setMinSize(movie.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		 
		/* final int[] index = new int[10]; 
			for (int i = 0; i < index.length; i++) {
				 index[0]++;              
				 final Button button1 = new Button(c1, SWT.RADIO);   
				 button1.setImage(SWTResourceManager.getImage(SelectSence.class, "/pictureUI/help.png"));
				 button1.setText("第"+(index[0]+1)+"场电影:"+"2012-10-11-15-30"); 
				 // reset size of content so children can be seen - method 1              
				 c1.setSize(c1.computeSize(SWT.DEFAULT, SWT.DEFAULT));              
				 c1.layout(); 
				 button1.addSelectionListener(new SelectionAdapter() {
					 	@Override
					 	public void widgetSelected(SelectionEvent e) {
					 	textString = button1.getText();
					 	
					 	}
					 });
			}*/
		 //c1.setSize(c1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		 
		 
		 
		
		Composite com = new Composite(composite_7, SWT.NONE);
		com.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		com.setLayoutData(BorderLayout.SOUTH);
		com.setLayout(new FillLayout(SWT.VERTICAL));
		
		Button button_1 = new Button(com, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DBFactory dbsDbFactory=new DBFactory();
				String SQL="select * from [sence] where s_num=?";
				Object[] params={sessionID};
				ResultSet rsResultSet=dbsDbFactory.executeSQLWithResult(SQL, params);
				try {
					while(rsResultSet.next()){
						hallIDString=rsResultSet.getString("h_num");
						full_seat=rsResultSet.getString("full_seat");
						System.out.println(hallIDString);
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String SQLx="select * from [ss_struct] where h_num=?";
				Object[] paramsx={hallIDString};
				ResultSet rsResultSetx=dbsDbFactory.executeSQLWithResult(SQLx, paramsx);
				try {
					while(rsResultSetx.next()){
						row=rsResultSetx.getInt("row");
						cont=rsResultSetx.getInt("seat_cont");
						System.out.println(row+"");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dbsDbFactory.close();
				Control[] conts = composite_7.getChildren();
				for (Control control : conts) {
					control.dispose();
				}
				Selectseat hGui = new Selectseat(composite_7, getStyle(),movieS, cont, row,full_seat,sessionID);
				hGui.setLayoutData(BorderLayout.CENTER);
				composite_7.layout();
				/*SelectSeat seat = new SelectSeat(getParent(), getStyle(),movieS, cont, row,full_seat,sessionID);
				seat.open();*/
			}
		});
		button_1.setImage(SWTResourceManager.getImage(SelectSence.class, "/pictureUI/admin.png"));
		button_1.setText("选择您的座位");
		
		Composite composite_4 = new Composite(shell, SWT.NONE);
		composite_4.setLayoutData(BorderLayout.SOUTH);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		

		}}

