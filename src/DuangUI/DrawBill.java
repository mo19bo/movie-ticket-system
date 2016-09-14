package DuangUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;

import databaseO.DBFactory;
import databaseO.GroupBuyCodeDAO;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import DuangClass.GroupBuy;
import DuangFunc.ConcerttoDate;
import org.eclipse.swt.custom.ScrolledComposite;

public class DrawBill extends Composite {
	private Text text;
	private Text text_1;
	private GroupBuyCodeDAO groupBuyCodeDAO = new GroupBuyCodeDAO();
	private RandomID randomID = new RandomID();
	private GroupBuy groupBuy = new GroupBuy();
	private String rId = "";
	//	商家姓名（数组）
	private ArrayList<String> merNameArrayList = new ArrayList<String>();
//	定义年月日 
	int year=0;
    int month=0;
    int day=0;


	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DrawBill(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
	
		
		
		Composite composite = new Composite(this, SWT.BORDER);
		composite.setLayout(new BorderLayout(0, 0));
		
		Label lblNewLabel = new Label(composite, SWT.CENTER);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		lblNewLabel.setLayoutData(BorderLayout.NORTH);
		lblNewLabel.setText("出票：");
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2.setLayoutData(BorderLayout.CENTER);
		composite_2.setLayout(new GridLayout(1, false));
		
		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		composite_4.setLayout(new FillLayout(SWT.VERTICAL));
		
		final Label label_4 = new Label(composite_4, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setText("请选择团购商家：");
		
		//	从数据库里面读取商家名称
		DBFactory db = new DBFactory();
		String SQL = "select distinct mer_name from [merchant]";
		Object[] params = {};
		ResultSet re = db.executeSQLWithResult(SQL, params);
		try {
			int i = 0;
			while(re.next())
			{
				System.out.println(re.getString("mer_name"));
				merNameArrayList.add(re.getString("mer_name"));
			}
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		db.close();
		final Combo combo = new Combo(composite_2, SWT.READ_ONLY);
		combo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		System.out.println(merNameArrayList);
		// 为combo添加商家名称
		for(int i = 0; i<merNameArrayList.size();i++)
		{
			combo.add(merNameArrayList.get(i));
		}Label label = new Label(composite_2, SWT.NONE);
label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setText("确认出票的的数量：");
		
		text = new Text(composite_2, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		final Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_label_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label_1.widthHint = 208;
		label_1.setLayoutData(gd_label_1);
		
		Button button = new Button(composite_2, SWT.CENTER);
		GridData gd_button = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 97;
		button.setLayoutData(gd_button);
		
		button.setText("确认出票");
		
		final ScrolledComposite scrolledComposite = new ScrolledComposite(composite_2, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_scrolledComposite = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_scrolledComposite.heightHint = 382;
		gd_scrolledComposite.widthHint = 228;
		scrolledComposite.setLayoutData(gd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		final Composite composite_5 = new Composite(scrolledComposite, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.VERTICAL));
		//scrolledComposite.setContent(composite_5);
		//scrolledComposite.setMinSize(composite_5.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		//生成团购码按钮的监听事件
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String valueString = text.getText();
				label_1.setText("成功生成"+valueString+"个团购码");
				//得到用户想生成的团购码个数
				int numOfCode = Integer.parseInt(valueString);
				// System.out.println(i);
				//得到生成的团购码
				
				Date[] t_date = new Date[numOfCode];
				Date[] date_tt = new Date[numOfCode];
				//团购码数组
				String[] code = new String[numOfCode];
				String label_4string = "";
				//tt_id 的数组 tt_id 随机生成
				String[] randomTt_id = new String[numOfCode];
				
				Date date = new Date();
				//Date tt_date = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, 30);
				Date tt_date = calendar.getTime();
				for(int i = 0;i<code.length;i++){	
					//赋值
					date_tt[i] = tt_date;
					t_date[i] = date;
					
					code[i] = String.valueOf(date.getTime()+i);
					randomTt_id[i] = (randomID.tt_id()).concat(String.valueOf(i));
					// System.out.println(randomTt_id[i]);
					label_4string = label_4string.concat(code[i]+"\n");
					
				
				}
				Label groupCodeA = new Label(composite_5, SWT.NONE);
				groupCodeA.setText(label_4string);
				scrolledComposite.setContent(composite_5);
				scrolledComposite.setMinSize(composite_5.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				//在label_4中打印出生成的团购码
				//groupCode.setText(label_4string);
				//代码到这里都没问题 记号
				
				DBFactory dbFactory = new DBFactory();
				Connection conn = dbFactory.getConnection();
				for(int i=0;i<code.length;i++){
					String insertCodeSQL = "insert into [t_ticket] values(?,?,?,?,?)";
					System.out.println("f");
					Object[] params = {code[i],new Timestamp(t_date[i].getTime()), 
					new Timestamp(date_tt[i].getTime()),randomTt_id[i], combo.getItem(combo.getSelectionIndex())};
					dbFactory.executeSQLWithoutResult(insertCodeSQL, params);
					/*PreparedStatement sqlPreparedStatement = conn.prepareStatement(insertCodeSQL);		//此处的SQL为SQL查询语句
					for (int j = 0; j < params.length; j++){	
					//循环设置对象	
						//这里length为2	
						sqlPreparedStatement.setObject(j+1, params[j]);
					}
					sqlPreparedStatement.execute()*/;				//执行
				}
				 Calendar c=Calendar.getInstance();//获得系统当前日期
			        year=c.get(Calendar.YEAR);
			        month=c.get(Calendar.MONTH)+1;//系统日期从0开始算起
			        day=c.get(Calendar.DAY_OF_MONTH);
			        ConcerttoDate concerttoDateA = new ConcerttoDate();
					Date pdateStringA = concerttoDateA.converttoDate(year, month, day);
					Timestamp timestampA = new Timestamp(pdateStringA.getTime());
					rId = randomID.m_id();
					String insertCodeSql = "insert into [merchant] values(?,?,?,?)";
					Object[] params = {combo.getItem(combo.getSelectionIndex()),code.length,timestampA,rId};
					dbFactory.executeSQLWithoutResult(insertCodeSql, params);
					dbFactory.close();
				
					//	 System.out.println("ss");
			}
				
			
		});
		
		Composite composite_1 = new Composite(this, SWT.BORDER);
		composite_1.setLayout(new BorderLayout(0, 0));
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.CENTER);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		lblNewLabel_1.setLayoutData(BorderLayout.NORTH);
		lblNewLabel_1.setText("检票：");
		
		Composite composite_3 = new Composite(composite_1, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_3.setLayoutData(BorderLayout.CENTER);
		composite_3.setLayout(new GridLayout(1, false));
		
		Label label_2 = new Label(composite_3, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setText("确认检票的票号：");
		
		text_1 = new Text(composite_3, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		final Label label_3 = new Label(composite_3, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_label_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label_3.widthHint = 210;
		label_3.setLayoutData(gd_label_3);
		new Label(composite_3, SWT.NONE);
		//检票按钮的监听事件
		Button button_1 = new Button(composite_3, SWT.NONE);
		GridData gd_button_1 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 100;
		button_1.setLayoutData(gd_button_1);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DBFactory dbFactory = new DBFactory();
				String  usercodeString = text_1.getText();
				//System.out.println(usercodeString);
				int rowOfTablet_ticket = dbFactory.CountRowNumber("t_ticket");
				String[] searchCode = new String[rowOfTablet_ticket];
				//建立连接
						
				Connection conn2 = dbFactory.getConnection();
				List<GroupBuy>  gblist = new ArrayList<GroupBuy>();
						
				try {
					String searchSql = "select * from [t_ticket] ";
					Object[] params = {};
					PreparedStatement sqlPreparedStatement = conn2.prepareStatement(searchSql);		
					//此处的SQL为SQL查询语句
					for (int j = 0; j < params.length; j++){	
						//循环设置对象	
						sqlPreparedStatement.setObject(j+1, params[j]);
					}
				
					ResultSet rSet = sqlPreparedStatement.executeQuery();		
					//查询结果给ResultSet 类的 rSet对象		//执行
				
					while(rSet.next()){
						GroupBuy gBuy = new GroupBuy();
						gBuy.setSerial_code(rSet.getString("serial_code"));
						gblist.add(gBuy);
						}

				}catch (SQLException e2) {
							// TODO 自动生成的 catch 块
							e2.printStackTrace();
				}
				
				//得到所有的团购码
				for(int i = 0;i<rowOfTablet_ticket;i++){
					groupBuy = gblist.get(i);
					searchCode[i] = groupBuy.getSerial_code();
					//System.out.println(searchCode[i]);
				}
				//匹配用户输入的团购码
				if(groupBuyCodeDAO.isGBCode(usercodeString, searchCode))
				{
					System.out.println("youwenti");
					label_3.setText("成功查询到团购码 "+usercodeString);
							
				}else {
					System.out.println(usercodeString);
					label_3.setText("未能成功查询到团购码 "+usercodeString);
				}
						
			}
			
		});
		button_1.setText("确认检票");
		
		//	清理combo
		while (getShell().isDisposed ()) {          
			 combo.removeAll();
	}
}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
