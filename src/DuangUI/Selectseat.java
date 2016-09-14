package DuangUI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Label;

import databaseO.DBFactory;

import DuangClass.Movie;
import DuangFunc.Caculate_seat;

public class Selectseat extends Composite {

	protected Object result;
	protected Shell shell;
	private int colonm;
	private int num_seat;
	private String a;
	public String movieName="这里是电影";
	private String date;	//	日期
	private int temp = 0;
	public String seat_info="";//座号
	private int index_1=0;
	private String No_hall="1号厅";//厅号
	private int cont=0;//总数
	private String sence_time;
	private Movie movie_ssMovie;
	private String movie_id;
	private String sessionidd;
	private String sencetime;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Selectseat(final Composite parent, int style, Movie movieS, int cont2,
			int row, String full_seat,String sessionx ) {
		
		super(parent, style);
		//super(parent, SWT.DIALOG_TRIM | SWT.RESIZE);
		// 传值并赋值
		movie_ssMovie=movieS;
		num_seat=cont2;
		colonm=row;
		a=full_seat;
		movieName=movieS.getMovie_Name();
		movie_id =movieS.getMovie_Id();
	//	sence_time=sencetime;
		 sessionidd=sessionx;
		 
		setLayout(new BorderLayout(0, 0));
		
		final Composite main = new Composite(this, SWT.NONE);
		main.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		main.setLayoutData(BorderLayout.CENTER);
		main.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(main, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new BorderLayout(0, 0));
		
		Label lblNewLabel = new Label(composite, SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		lblNewLabel.setText("请选择您想要购买的座位：");
		
		Composite composite_1 = new Composite(main, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.SOUTH);
		composite_1.setLayout(new BorderLayout(0, 0));
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setImage(SWTResourceManager.getImage(Selectseat.class, "/pictureUI/exit.png"));
		
		btnNewButton.setText("下一步");
		
		Composite composite_2 = new Composite(main, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.CENTER);
		composite_2.setLayout(new BorderLayout(0, 0));
		
		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		composite_3.setLayoutData(BorderLayout.WEST);
		
		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		composite_4.setLayoutData(BorderLayout.EAST);
		
		Composite seatA = new Composite(composite_2, SWT.NONE);
		seatA.setLayoutData(BorderLayout.CENTER);
		seatA.setLayout(new GridLayout(colonm, false));
		// 生成座位信息
		GridData gd_ais = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_ais.heightHint = 35;
		gd_ais.widthHint = 10;
		GridData gd_seat = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1,
				1);
		gd_seat.widthHint = 35;
		gd_seat.heightHint = 35;

		GridData gd_b = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_b.widthHint = 35;
		gd_b.heightHint = 35;

		final char flag[] = a.toCharArray();
		int x=0;
		final int y=0;
		for ( x = 0; x < num_seat; x++) {
                temp=x;
                
			switch (flag[x]) {
			case '1':
				final Button seat = new Button(seatA, SWT.NONE);
            
				seat.setLayoutData(gd_seat);

				
				
				///*****************这是算法生成每个的坐标  可以直接getdata************////
				seat.setImage(SWTResourceManager.getImage(Session.class,
						"/pictureUI/seat.png"));
				seat.setData("index",x);
				/////***********************************/////
			     seat.setData("seatname", new Caculate_seat().caculate_seat(a, temp+1, colonm));
			     
				
				seat.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {

						temp=Integer.parseInt(seat.getData("index").toString());
						flag[temp] = 'x';
						seat.setImage(SWTResourceManager.getImage(Session.class,
								"/pictureUI/seatPng.png"));
						
                        seat_info=  seat.getData("seatname").toString()+"_"+seat_info;
                         cont++;
                         seat.setEnabled(false);
						System.out.println(seat_info);
					}
				});

				break;
			case '0':
				Button ais = new Button(seatA, SWT.NONE);
				ais.setLayoutData(gd_ais);
				ais.setText("0");
				
				ais.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {

					}
				});
				ais.setText("ais");

				break;

			case 'x':
				Button b = new Button(seatA, SWT.NONE);
				b.setLayoutData(gd_b);
				b.setImage(SWTResourceManager.getImage(Session.class,
						"/javax/swing/plaf/metal/icons/"));
				b.setEnabled(false);
				//b.setText("N");
				break;
			}
			
		}
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				a=String.valueOf(flag);
				DBFactory dsDbFactory=new DBFactory();
				String SQL="select * from [sence] where s_num=?";
				Object[] params={sessionidd};
				ResultSet rsx=dsDbFactory.executeSQLWithResult(SQL, params);
				try {
					while( rsx.next()){
						
						SimpleDateFormat format  = new SimpleDateFormat();
						Date date_tDate=rsx.getDate("Date");
						//full_seat=rsx.getString("full_seat");
						String date = format.format(date_tDate);
						sencetime=rsx.getString("start_time")+"--"+rsx.getString("end_time");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				a=String.valueOf(flag);
				System.out.println(a);
				
				Control[] conts = main.getChildren();
				for (Control control : conts) {
					control.dispose();
				}
				System.out.println(sencetime+""+movieName+""+a+""+No_hall+""+seat_info+""+date+""+cont+""+sessionidd+""+movie_id);
				TicketContent hGui = new TicketContent(main, getStyle(), sencetime, movieName, a, No_hall, seat_info, date, cont, 15, sessionidd, movie_id);
				hGui.setLayoutData(BorderLayout.CENTER);
				main.getParent().pack();
				
				
				/*TicketDetail ticketDetail = new TicketDetail(getShell(), getStyle(), sencetime, movieName, a, No_hall, seat_info, date, cont, 15,sessionidd,movie_id);
				ticketDetail.open();*/
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
