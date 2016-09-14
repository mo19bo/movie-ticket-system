package DuangUI;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DuangFunc.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import databaseO.DBFactory;

import DuangClass.User;
import DuangClass.orderlist;
//import DB.dboperator;
import DuangClass.Userorder;
import DuangFunc.login;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class PersonCenter extends Composite {
	private ToolItem checkDetail; 
	private Table table;
	private login loginInfo;
    private String userid;
    private User user_xUser;
    private TableItem[] items;
    private DBFactory dbo = new DBFactory();
    private ToolItem payNow;
    private Float account;
    private boolean flag=false;
    private String sceneid;
    private String startTime;
    private String endTime;
    private String mvName;
    private Date postDate;
    private String h_num;
    private ArrayList<String> seatNumber = new ArrayList<String>();
    private String seatNumberString="";
    private String startEnd;
    private String mvid;
    private float temp;
	// orderlist ol = new orderlist();
	/**
	 * Create the composite.
	 * 
	 * @param com
	 * @param style
	 * @throws IOException
	 */
	public PersonCenter(Composite com, int style) throws IOException {
		
		
		super(com, style);
		setLayout(new BorderLayout(0, 0));
		//===============================================
		
	       try {
			File file=new File("login.txt");
			   BufferedReader reader=new BufferedReader(new FileReader(file));
			   if(file.exists())
				   file.createNewFile();
			   userid=reader.readLine();
			   user_xUser= inistance(userid);
			   System.out.println(user_xUser.getUser_name()+userid);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			MessageDialog msgDialog=new MessageDialog(getShell(), SWT.DIALOG_TRIM, e2.toString(), 2);
			msgDialog.open();
			
		}
		  
		//===============================================
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.WEST);
		composite.setLayout(new BorderLayout(0, 0));

		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2.setLayout(new GridLayout(1, false));

		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite_3 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 2);
		gd_composite_3.widthHint = 158;
		gd_composite_3.heightHint = 158;
		composite_3.setLayoutData(gd_composite_3);
		
		Label lblNewLabel_4 = new Label(composite_3, SWT.NONE);
		lblNewLabel_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_4.setImage(SWTResourceManager.getImage(PersonCenter.class, user_xUser.getImage_url()));
		
		
		Label lblNewLabel_3 = new Label(composite_2, SWT.CENTER);
		lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_lblNewLabel_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_3.widthHint = 159;
		lblNewLabel_3.setLayoutData(gd_lblNewLabel_3);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI",13, SWT.NORMAL));
		lblNewLabel_3.setText(user_xUser.getUser_name());
		final User user1 = new User();
		user1.setUser_name(lblNewLabel_3.getText());
		new Label(composite_2, SWT.NONE);

		

		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setImage(SWTResourceManager.getImage(PersonCenter.class, "/pictureUI/level.png"));
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.heightHint = 29;
		gd_lblNewLabel.widthHint = 159;
		lblNewLabel.setLayoutData(gd_lblNewLabel);

		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_4.setLayout(new GridLayout(1, false));
		GridData gd_composite_4 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_composite_4.widthHint = 156;
		gd_composite_4.heightHint = 95;
		composite_4.setLayoutData(gd_composite_4);

		Label lblNewLabel_1 = new Label(composite_4, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_lblNewLabel_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_1.heightHint = 27;
		gd_lblNewLabel_1.widthHint = 145;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI",
				15, SWT.NORMAL));
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setText(user_xUser.getLevel());

		Label lblNewLabel_2 = new Label(composite_4, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setImage(SWTResourceManager.getImage(PersonCenter.class, "/pictureUI/acount.png"));
		GridData gd_lblNewLabel_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_2.heightHint = 29;
		gd_lblNewLabel_2.widthHint = 152;
		lblNewLabel_2.setLayoutData(gd_lblNewLabel_2);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI",
				10, SWT.NORMAL));

		Label label = new Label(composite_4, SWT.CENTER);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label.widthHint = 154;
		label.setLayoutData(gd_label);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14,
				SWT.NORMAL));
		String s = "" + Float.parseFloat(user_xUser.getAccount());
		label.setText(s);

		Button btnNewButton = new Button(composite_2, SWT.NONE);
		
		btnNewButton.setImage(SWTResourceManager.getImage(PersonCenter.class, "/pictureUI/charge.png"));
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton.heightHint = 38;
		gd_btnNewButton.widthHint = 153;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Charge charge;
				
				charge = new Charge(getShell(), getStyle(), userid);
				charge.open();

			}
		});
		btnNewButton.setText("去充值");
		Button button = new Button(composite_2, SWT.NONE);
		button.setImage(SWTResourceManager.getImage(PersonCenter.class, "/pictureUI/personDetail.png"));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UserInfo ui = new UserInfo(getShell() ,SWT.DIALOG_TRIM);
				ui.open();
			}
		});
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_button.widthHint = 156;
		button.setLayoutData(gd_button);
		button.setText("详细信息");
		new Label(composite_2, SWT.NONE);

		Composite composite_5 = new Composite(composite_2, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_5.setLayout(new GridLayout(1, false));
		GridData gd_composite_5 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_composite_5.widthHint = 142;
		composite_5.setLayoutData(gd_composite_5);

		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new BorderLayout(0, 0));

		Composite composite_6 = new Composite(composite_1, SWT.NONE);
		composite_6.setLayoutData(BorderLayout.SOUTH);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));

		

		Composite composite_7 = new Composite(composite_1, SWT.NONE);
		composite_7.setLayoutData(BorderLayout.CENTER);
		composite_7.setLayout(new BorderLayout(0, 0));

		Composite composite_8 = new Composite(composite_7, SWT.NONE);
		composite_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_8.setLayoutData(BorderLayout.NORTH);
		composite_8.setLayout(null);

		Label label_1 = new Label(composite_8, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(PersonCenter.class, "/pictureUI/personOrder.png"));
		label_1.setBounds(0, 0, 481, 24);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		// button_1.setEnabled(false);

		Composite composite_9 = new Composite(composite_7, SWT.NONE);
		composite_9.setLayoutData(BorderLayout.CENTER);
		composite_9.setLayout(new BorderLayout(0, 0));

		table = new Table(composite_9, SWT.BORDER | SWT.FULL_SELECTION
				| SWT.CHECK | SWT.MULTI);
		table.setLayoutData(BorderLayout.CENTER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("订单号");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("票数");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("下单时间");

		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("有效期");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("金额");
		
		ToolBar toolBar = new ToolBar(composite_9, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		toolBar.setLayoutData(BorderLayout.NORTH);
		Button btnNewButton_1 = new Button(composite_6, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				orderlist ol = new orderlist();
				List<orderlist> orl = ol.getOrderlist();
				
				for (int i = 0; i < orl.size(); i++) {					
					payNow.setEnabled(false);
					if(!orl.isEmpty()&&orl.get(i).getO_state().equals("1")){
						TableItem item = new TableItem(table, SWT.LEFT);
					String[] vs = { orl.get(i).getO_id(),orl.get(i).getP_tickets(),
							orl.get(i).getO_date(), orl.get(i).getY_date(),
							orl.get(i).getO_money() };
					item.setText(vs);
					}
				}
				
			}
		});
		btnNewButton_1.setText("已付款");

		Button btnNewButton_2 = new Button(composite_6, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				orderlist ol = new orderlist();
				List<orderlist> orl = ol.getOrderlist();
			
				for (int i = 0; i < orl.size(); i++) {
					
					// item.getData("m_id").toString();
					//System.out.println(orl.isEmpty());
					payNow.setEnabled(true);
					
					if(!orl.isEmpty()&&orl.get(i).getO_state().equals("0")){
					TableItem item = new TableItem(table, SWT.LEFT);
					String[] vs = { orl.get(i).getO_id(), orl.get(i).getP_tickets(),
							orl.get(i).getO_date(), orl.get(i).getY_date(),
							orl.get(i).getO_money() };
					//System.out.println(vs);
					item.setText(vs);
					}
				}
				
			}
		});
		btnNewButton_2.setText("未付款");
		final ToolItem deleteItem = new ToolItem(toolBar, SWT.NONE);
		deleteItem.setImage(SWTResourceManager.getImage(PersonCenter.class, "/pictureUI/delete.png"));
		
		deleteItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				items = table.getItems();  
                // 循环所有行  
				
					for (int i = items.length - 1; i >= 0; i--){
	                  
	                    // 如果该行没有被选中，继续循环  
	                    if (!items[i].getChecked())  
	                        continue;  
	                    // 否则选中，查找该表格中是否有该行  
	                    
	                    int index = table.indexOf(items[i]); 
	                    String order_id = "'"+items[index].getText(0)+"'";
	                    
	                    String sql = "delete from userorder where o_id = "+order_id;
	                  //  String sceneDelete = "delete from sence where sence.mv_id ="+mv_id;
	                  
	                   // dbo.delete(mv_idDelete);
	                    dbo.delete(sql);
	                    table.remove(index);  
	                    
	                    table.pack();  
	                    
	                } 
			}
		});
		
		payNow = new ToolItem(toolBar, SWT.NONE);
		payNow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				items = table.getItems();  
                // 循环所有行  
				
					for (int i = items.length - 1; i >= 0; i--){
	                  
	                    // 如果该行没有被选中，继续循环  
	                    if (!items[i].getChecked())  
	                        continue;  
	                    // 否则选中，查找该表格中是否有该行  
	                    
	                    int index = table.indexOf(items[i]); 
                    	String SQL = "select account from [user] where user_id = ?";
	        			Object[] paramsD = {userid};
	        			ResultSet resultSet = dbo.executeSQLWithResult(SQL, paramsD);	        				
	        			try {
	        					
	        						resultSet.next();
	        						account = resultSet.getFloat("account");
	        						System.out.println(account);
	        						if (account >= Float.valueOf(items[index].getText(4))) {
	        							account = account - Float.valueOf(items[index].getText(4));
	        							//	计算金额(更新账户)
	        							SQL = "update [user] set account = ? where user_id = ?";
	        							Object[] paramsC = {account, userid};
	        							dbo.executeSQLWithoutResult(SQL, paramsC);
	        							String sql = "update [userorder] set o_state = ? where o_id=?";
	        							Object[] params = {"1",items[index].getText(0)};
	        							dbo.executeSQLWithoutResult(sql, params);
	        							
	        						}
	        						else {
	        							JOptionPane.showMessageDialog(new Frame(), "余额不足", "提示:",
	        									JOptionPane.INFORMATION_MESSAGE);
	        							flag = true;
	        						}
	        					}
	        				
	        				catch (SQLException e1) {
	        					// TODO Auto-generated catch block
	        					e1.printStackTrace();
	        				}
	        				
	        				dbo.close();
	                    	
	                    	table.remove(index);  
	                    
	                    	table.pack();  
	                    	
	                    	
	                } 
					if(flag == false)
						JOptionPane.showMessageDialog(new Frame(), "付款成功", "提示:",
								JOptionPane.INFORMATION_MESSAGE);
			}
		});
		payNow.setText("现在付款");
		payNow.setEnabled(false);
		
		checkDetail = new ToolItem(toolBar, SWT.NONE);
		
		checkDetail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				items = table.getItems();  
                // 循环所有行  
				
					for (int i = items.length - 1; i >= 0; i--){
	                  
	                    // 如果该行没有被选中，继续循环  
	                    if (!items[i].getChecked())  
	                        continue;  
	                    // 否则选中，查找该表格中是否有该行  
	                    
	                    int index = table.indexOf(items[i]); 
	                    String objString = "'"+items[index].getText(0)+"'";
	                    String sql = "select sence.mv_id,m_tickets.s_num, mv_name, pdate, start_time,end_time, h_num, seat_num from m_tickets, sence, movie where o_id = "+objString+" and m_tickets.s_num = sence.s_num and sence.mv_id = movie.mv_id";
	                    ResultSet rs = dbo.search(sql);
	                    try {
							while(rs.next()){
								sceneid = rs.getString("s_num");
								mvName = rs.getString("mv_name");
								postDate = rs.getDate("pdate");
								startTime = rs.getString("start_time");
								endTime = rs.getString("end_time");
								h_num = rs.getString("h_num");
								seatNumber.add(rs.getString("seat_num"));
								mvid = rs.getString("mv_id");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                    for(int i1=0;i1<seatNumber.size();i1++){
	                    	seatNumberString = seatNumberString+seatNumber.get(i1);
	                    }
	                    startEnd = startTime+"-"+endTime;
	                    TicketDialog td = new TicketDialog(getShell(), SWT.DIALOG_TRIM, startEnd, mvName, h_num, seatNumberString, postDate, Integer.valueOf(items[i].getText(1)), Float.valueOf(items[i].getText(4)), sceneid, mvid);
	                    td.open();
	                    //seatNumber.removeAll(seatNumber);
	                    seatNumber.clear();
	                    seatNumberString = "";
	                } 
			}
		});
		checkDetail.setText("查看详情");
		
		
		

	}

	protected void ShowViewDialog(String string) {
		// TODO Auto-generated method stub

	}
    private User inistance(String userid){
    	 User user=new User();
    	 DBFactory dbFactory=new DBFactory();
    	  String SQL="Select * from [user] where user_id=?";
    	 Object[]  params={userid};
    	ResultSet rs=dbFactory.executeSQLWithResult(SQL, params);
    	try {
			while(rs.next()){
				user.setUser_name(rs.getString("user_name"));
				user.setLevel(rs.getString("level"));
				user.setAccount(rs.getString("account"));
				user.setImage_url(rs.getString("url_img"));

			}
			
			
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	
    	
    	
    }
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
