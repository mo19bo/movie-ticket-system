package DuangUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.eclipse.swt.widgets.Composite;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import databaseO.DBFactory;

import DuangClass.Record;

public class UserAccount extends Composite {
	//private Table accounttable;
	private  Table recordtable;
	static int[] index;
	static int indexCount=0;
	static int i;
	static TableItem[] items;
	private DBFactory dbo = new DBFactory();
	static String record_id;
	static String m_date;
	static String money;
	static String state_re;
	static String s_num;
	private String userid;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public UserAccount(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		recordtable = new Table(this, SWT.BORDER | SWT.FULL_SELECTION|SWT.CHECK| SWT.MULTI );
		//recordtable.setLayoutData(BorderLayout.WEST);
		recordtable.setLinesVisible(true);
		recordtable.setHeaderVisible(true);
		
		TableColumn tableColumn = new TableColumn(recordtable, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("行号");
		
		TableColumn tblclmnUserid = new TableColumn(recordtable, SWT.NONE);
		tblclmnUserid.setWidth(100);
		tblclmnUserid.setText("user_id");
		
		TableColumn tblclmnRecordid = new TableColumn(recordtable, SWT.NONE);
		tblclmnRecordid.setWidth(100);
		tblclmnRecordid.setText("record_id");
		
		TableColumn tblclmnMoney = new TableColumn(recordtable, SWT.NONE);
		tblclmnMoney.setWidth(100);
		tblclmnMoney.setText("money");
		
		TableColumn tableColumn_3 = new TableColumn(recordtable, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("日期");
		
		TableColumn tableColumn_4 = new TableColumn(recordtable, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("是否确认");
             
		
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(BorderLayout.NORTH);
		String s = "select * from [record]";
		Object[] paramsObjects={};
	   ResultSet rs=dbo.executeSQLWithResult(s,  paramsObjects);
		try {
			i = 1;
			while(rs.next()){
				
				String[] vs = {String.valueOf(i),rs.getString("user_id"),rs.getString("record_id"),rs.getString("money").toString(),
						rs.getDate("m_date").toString(),rs.getString("state_re")};
				
				TableItem ti = new TableItem(recordtable,SWT.NONE);
				ti.setText(vs);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		//final Session se = new Session();
		final Record re = new Record();
		//final Movie mv = new Movie();
		//final Session se = new Session();
		final ToolItem toolItem = new ToolItem(toolBar, SWT.RIGHT);
		//final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		toolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				

				
			//	dbo.executeSQLWithoutResult(sql, params);
			
				items = recordtable.getItems();  
                // 循环所有行  
				
					for (i = items.length - 1; i >= 0; i--){
	                  
	                    // 如果该行没有被选中，继续循环  
	                    if (!items[i].getChecked())  
	                        continue;  
	                    // 否则选中，查找该表格中是否有该行  
	                    re.setRecord_id(record_id);
	    				re.setMoney(money);
	    				re.setDatetime(m_date);
	    				re.setstate_re("确认");
	    				re.setUser_id(userid);
	    				items[i].setText(5, "确认");	
	    				String objString = "'"+items[i].getText(5)+"'";
	    				String obj = "'"+items[i].getText(2)+"'";
	    				
	    				String sql = "update record set state_re = "+objString+" where record_id = "+obj;
	    				Object[] params = {state_re,record_id};
	    				//System.out.println(sql);
	                    dbo.update(sql);
	                   // String objaccount = "'"+items[i].getText(3)+"'";
	                    String objUserid = "'"+items[i].getText(1)+"'";
	                    String sqlString = "update [user] set account=account+"+items[i].getText(3)+" where user_id = "+objUserid;
	                  
	                    dbo.update(sqlString);
	                    
	                    recordtable.pack();  
	                    
	                    
	                } 
					MessageBox mb1 = new MessageBox(getShell());
                    mb1.setMessage("OK");
                    mb1.setText("提示");
                    mb1.open();
				
				
			}
		});
		toolItem.setText("完成");
		
		

		
		
		
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new GridLayout(2, false));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		
		
		
			}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
