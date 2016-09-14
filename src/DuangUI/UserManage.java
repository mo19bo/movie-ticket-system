/*从数据库读取用户数据，显示在页面上，可删除，
丁睿  2015.317*/
package DuangUI;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;

import databaseO.DBFactory;

public class UserManage extends Composite {
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public UserManage(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK | SWT.MULTI);
		table.setLayoutData(BorderLayout.CENTER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("行号");
		
		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(100);
		tblclmnId.setText("ID");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("姓名");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("性别");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("身份证");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setText("邮编");
		tableColumn_6.setWidth(100);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("地址");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("等级");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("余额");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("url");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("tel");
		
		TableColumn tblclmnEmail = new TableColumn(table, SWT.NONE);
		tblclmnEmail.setWidth(100);
		tblclmnEmail.setText("email");
		//===================================================================================
		
		
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(BorderLayout.NORTH);
		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
		toolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 TableItem[] items = table.getItems();  
                 // 循环所有行  
				DBFactory dbo = new DBFactory();
					for (int i = items.length - 1; i >= 0; i--){
	                  
	                    // 如果该行没有被选中，继续循环  
	                    if (!items[i].getChecked())  
	                        continue;  
	                    // 否则选中，查找该表格中是否有该行  
	                    int index = table.indexOf(items[i]); 
	                    
	                    // 如果没有该行，继续循环  
	                    if (index < 0)  
	                        continue; 
	                    // 删除绑定的控件  
	                    
	                    // 如果有该行，删除该行  
	                    // items[index].dispose();
	                    //user必须加括号 ，不然会报错，在mysql里面不需要加
	                    
 	                    Object[] paramas={items[index].getText(1)};
	                    
	                    dbo.executeSQLWithoutResult("delete from [user] where user_id =?", paramas);
	                    
	                    table.remove(index);  
	                    
	                   // table.pack();  
	                    
	                } 
			}
		});
		
		toolItem.setText("删除");
		
		DBFactory dbo = new DBFactory();
	     Object[] paramas={};
		
		ResultSet rs = dbo.executeSQLWithResult("select * from [user]",paramas);//加中括号
		try {
			int i = 1;
			while(rs.next()){
				TableItem ti = new TableItem(table,SWT.NONE);
				String[] vs = {String.valueOf(i),rs.getString("user_id"),rs.getString("user_name"),
						rs.getString("sex"),rs.getString("idcard"),rs.getString("postcode"),
						rs.getString("level"),rs.getString("account"),rs.getString("address"),
						rs.getString("url_img"),rs.getString("tel"),rs.getString("email")};
				ti.setText(vs);
				i++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbo.close();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
