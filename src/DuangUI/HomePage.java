package DuangUI;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import DuangFunc.MessageDialog;
import DuangFunc.initalize;

public class HomePage {

	public static boolean loginFlag=false;
	protected Shell shlHome;
	private Composite composite_Main;
	private String fileName = null;   
	private static ToolItem person;
	private static ToolItem admin;
	// private Image image = new Image(Display.getCurrent(),
	// "C:\\Users\\jacksoncat\\Desktop\\annoucement.png");
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HomePage window = new HomePage();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		//	清空昨日的记录
		initalize in = new initalize();
		in.initalize();
		shlHome.open();
		Control[] conts = composite_Main.getChildren();
		for (Control control : conts) {
			control.dispose();
		}
		Home hGui = new Home(composite_Main, SWT.NONE);
		hGui.setLayoutData(BorderLayout.CENTER);
		composite_Main.layout();
		shlHome.layout();
		 Rectangle displayBounds = display .getPrimaryMonitor().getBounds(); 
	        Rectangle shellBounds = shlHome.getBounds(); 
	     int x = displayBounds.x + (displayBounds.width - shellBounds.width)>>1; 
	     int y = displayBounds.y + (displayBounds.height - shellBounds.height)>>1; 
	     shlHome.setLocation(x,y);
		while (!shlHome.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		if(shlHome.isDisposed()){
			
			
			FileOutputStream fileOutputStream;
			try {
				fileOutputStream = new FileOutputStream("login.txt");
			
			
		
				fileOutputStream.write(new String("").getBytes());
			
				// TODO Auto-generated catch block
		
			
				fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		
		}
	}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlHome = new Shell(SWT.MAX | SWT.RESIZE | SWT.PRIMARY_MODAL|SWT.MIN);
		shlHome.setImage(SWTResourceManager.getImage(HomePage.class, "/pictureUI/ICON44.png"));
		shlHome.setText("葫芦娃电影售票系统");
		shlHome.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shlHome.setTouchEnabled(true);
		shlHome.setMinimumSize(new Point(100, 100));
		shlHome.setSize(802, 526);
		shlHome.setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(shlHome, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		ToolBar toolBar = new ToolBar(composite, SWT.BORDER | SWT.FLAT);
		toolBar.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		toolBar.setFont(SWTResourceManager.getFont("宋体", 9, SWT.BOLD));
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		ToolItem toolItem_7 = new ToolItem(toolBar, SWT.NONE);
		toolItem_7.setEnabled(false);
		
		ToolItem toolItem_6 = new ToolItem(toolBar, SWT.NONE);
		toolItem_6.setEnabled(false);
		
		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
		toolItem.setEnabled(false);

		ToolItem toolItem_4 = new ToolItem(toolBar, SWT.NONE);
		toolItem_4.setEnabled(false);

		ToolItem toolItem_5 = new ToolItem(toolBar, SWT.NONE);
		toolItem_5.setEnabled(false);

		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.NONE);
		toolItem_1.setEnabled(false);
		
		// 主页
		ToolItem home = new ToolItem(toolBar, SWT.NONE);
		home.setText("Home");
		home.setImage(SWTResourceManager.getImage(HomePage.class, "/pictureUI/home.png"));
		home.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Control[] conts = composite_Main.getChildren();
				for (Control control : conts) {
					control.dispose();
				}
				Home hGui = new Home(composite_Main, SWT.NONE);
				hGui.setLayoutData(BorderLayout.CENTER);
				composite_Main.layout();
			}
		});
		// 电影票
		ToolItem movieticket = new ToolItem(toolBar, SWT.NONE);
		movieticket.setText("Movie");
		movieticket.setImage(SWTResourceManager.getImage(HomePage.class, "/pictureUI/movietick.png"));
		movieticket.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Control[] ctrls = composite_Main.getChildren();
				for (Control ctrl : ctrls) {
					ctrl.dispose();
				}
				DuangMovie mGui = new DuangMovie(composite_Main, SWT.NONE);
				mGui.setLayoutData(BorderLayout.CENTER);
				composite_Main.layout();
			}
		});
		// 个人中心
		person = new ToolItem(toolBar, SWT.NONE);
		person.setText("Person");
		person.setEnabled(false);
		
		person.setImage(SWTResourceManager.getImage(HomePage.class, "/pictureUI/person.png"));
		person.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Control[] ctrls = composite_Main.getChildren();
				for (Control ctrl : ctrls) {
					ctrl.dispose();
				}
				PersonCenter pGui;
				try {
					pGui = new PersonCenter(composite_Main, SWT.NONE);
					pGui.setLayoutData(BorderLayout.CENTER);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				composite_Main.layout();
			}
		});
		// 管理员中心
		admin = new ToolItem(toolBar, SWT.NONE);
		admin.setText("Admin");
		admin.setEnabled(false);
		admin.setImage(SWTResourceManager.getImage(HomePage.class, "/pictureUI/admin.png"));
		admin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Control[] ctrls = composite_Main.getChildren();
				for (Control ctrl : ctrls) {
					ctrl.dispose();
				}
				Administrator aGui = new Administrator(composite_Main, SWT.NONE);
				aGui.setLayoutData(BorderLayout.CENTER);
				composite_Main.layout();
			}
		});
		// 帮助
		ToolItem tltmNewItem_1 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_1.setText("Help");
		tltmNewItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Help help = new Help(shlHome, SWT.Show);
				help.open();
			}
		});
		tltmNewItem_1.setImage(SWTResourceManager.getImage(HomePage.class, "/pictureUI/help.png"));
		// 关于（葫芦娃电影售票系统简介）
		ToolItem systemset = new ToolItem(toolBar, SWT.NONE);
		systemset.setText("About");
		systemset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				About about = new About(shlHome, SWT.Show);
				about.open();

			}
		});
		systemset.setImage(SWTResourceManager.getImage(HomePage.class, "/pictureUI/about.png"));

		ToolItem tltmNewItem_5 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_5.setEnabled(false);

		ToolItem toolItem_2 = new ToolItem(toolBar, SWT.NONE);
		toolItem_2.setEnabled(false);

		ToolItem toolItem_3 = new ToolItem(toolBar, SWT.NONE);
		toolItem_3.setEnabled(false);

		ToolItem tltmNewItem_2 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_2.setEnabled(false);

		ToolItem tltmNewItem_3 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_3.setEnabled(false);
		
		ToolItem tltmNewItem_6 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_6.setEnabled(false);
		// 注销
		ToolItem tltmNewItem_4 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				person.setEnabled(false);
				admin.setEnabled(false);
				HomePage.loginFlag = false;
				
				//File file = new File("login.txt");
				//file.delete();
				try {
					FileOutputStream fileOutputStream = new FileOutputStream("login.txt");
					fileOutputStream.write(new String("").getBytes());
					fileOutputStream.close();
					MessageDialog exitDialog=new MessageDialog(shlHome, SWT.DIALOG_TRIM, "你已经退出登陆",2);
					exitDialog.open();
					Control[] conts = composite_Main.getChildren();
					for (Control control : conts) {
						control.dispose();
					}
					Home hGui = new Home(composite_Main, SWT.NONE);
					hGui.setLayoutData(BorderLayout.CENTER);
					composite_Main.layout();
				} catch (FileNotFoundException e1) {
					MessageDialog Dialog=new MessageDialog(shlHome, SWT.DIALOG_TRIM, e1.toString(),4);
					Dialog.open();
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					MessageDialog Dialog=new MessageDialog(shlHome, SWT.DIALOG_TRIM, e1.toString(),4);
					Dialog.open();
					e1.printStackTrace();
				}
			}
		});
		tltmNewItem_4.setImage(SWTResourceManager.getImage(HomePage.class,
				"/pictureUI/exit.png"));
		tltmNewItem_4.setText("Login Out");

		Composite composite_1 = new Composite(shlHome, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.SOUTH);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		Label label_1 = new Label(composite_1, SWT.CENTER);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10,
				SWT.BOLD));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setText("欢迎您使用葫芦娃电影售票系统");
		label_1.setAlignment(SWT.CENTER);

		composite_Main = new Composite(shlHome, SWT.NONE);
		composite_Main.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		composite_Main.setLayoutData(BorderLayout.CENTER);
		composite_Main.setLayout(new BorderLayout(0, 0));
	}
	public static void personEnable(boolean a){
		person.setEnabled(a);
	}
	public static void adminEnable(boolean b){
		admin.setEnabled(b);
	}
}
