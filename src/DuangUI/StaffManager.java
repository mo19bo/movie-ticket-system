//  看注释使用   丁睿  2015-3-16
/*界面操作类
 * 
 * 放于UI包里面使用，
 * 
 */
package DuangUI;
import DuangFunc.RandomID;

import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Composite;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import databaseO.DBFactory;


public class StaffManager extends Composite {
	private Table table;
	private ToolItem addDone;
	private TableItem[] items;
	private ArrayList<Integer> insertRecord = new ArrayList<Integer>();
	private String staffId;
	private String staffName;
	private DBFactory dbf = new DBFactory();
	private RandomID rid = new RandomID();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public StaffManager(final Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK | SWT.MULTI);
		table.setLayoutData(BorderLayout.CENTER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnid = new TableColumn(table, SWT.NONE);
		tblclmnid.setWidth(100);
		tblclmnid.setText("工作人员ID");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("姓名");
		
		/*TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("密码");*/
		
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(BorderLayout.NORTH);
		
		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
		
		
		
		toolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			
			public void widgetSelected(SelectionEvent e) {
				
				 TableItem[] items = table.getItems();  
                 // 循环所有行  
				 
				// Staffdao staff= new Staffdao(dbf);
                 for (int i = items.length - 1; i >= 0; i--)  
                 {  
                     // 如果该行没有被选中，继续循环  
                     if (!items[i].getChecked())  
                         continue;  
                     // 否则选中，查找该表格中是否有该行  
                     int index = table.indexOf(items[i]);  
                     // 如果没有该行，继续循环  
                     if (index < 0)  
                         continue;  
                     
                     //以下这句话原来为Staffdao.delete()报错为不能进行静态应用，在java中药进行引用必须实现进行实例化，在这里的方法
                     //就是将staffdao改为 new staffdao()
                     String SQL="delete from staff where s_id =?";
                     Object[] params={items[index].getText(0)};
                    dbf.executeSQLWithoutResult(SQL, params);
               //      dbf.delete("delete from staff where s_id = "+items[index].getText(0));
                     table.remove(index);  
                    
                     //table.pack();  
                 }  
                 dbf.close();
			}
			
			
		});
		
		toolItem.setText("删除");
		
		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.NONE);
		toolItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addDone.setEnabled(true);
				TableItem ti = new TableItem(table, SWT.None);
				ti.setChecked(true);
				items = table.getItems();
				insertRecord.add(items.length-1);
				ti.setText(0, rid.s_id());
				//int index = table.indexOf(ti);
                // 循环所有行  
				 DBFactory dbf = new DBFactory();
				// Staffdao staff= new Staffdao(dbf);
			
     			final TableEditor edit2 = new TableEditor(table);
     			final Text text2 = new Text(table,SWT.NONE);
     			text2.setText(ti.getText(1));
     			edit2.grabHorizontal = true;
     			edit2.setEditor(text2,ti,1);
     			
     			text2.addModifyListener(new ModifyListener() {
     				
     				@Override
     				public void modifyText(ModifyEvent arg0) {
     					// TODO Auto-generated method stub
     					edit2.getItem().setText(1,text2.getText());
     					
     				}
     			});
                
                
			}
		});
		toolItem_1.setText("添加");
		
		 addDone = new ToolItem(toolBar, SWT.NONE);
		 addDone.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		items = table.getItems();
		 		for(int i=0;i<insertRecord.size();i++){
		 			staffId = items[insertRecord.get(i)].getText(0);
		 			staffName = items[insertRecord.get(i)].getText(1);
		 			String sql = "insert into staff values(?,?,?)";
		 			Object[] obj = {staffId,"11111",staffName};
		 			dbf.executeSQLWithoutResult(sql, obj);
		 		}
		 		JOptionPane.showMessageDialog(new Frame(), "添加成功", "提示:",
						JOptionPane.INFORMATION_MESSAGE);
		 		Control[] conts = parent.getChildren();
		 		for (Control control : conts) {
					control.dispose();
				}
				StaffManager hGui = new StaffManager(parent, SWT.NONE);
				hGui.setLayoutData(BorderLayout.CENTER);
				parent.layout();
		 	}
		 });
		addDone.setText("完成添加");
		addDone.setEnabled(false);
		
		try {
			 DBFactory dbf = new DBFactory();
			String sql = "select * from [staff]";
			Object[] params = { };
			ResultSet rs = dbf.executeSQLWithResult(sql, params);
			while(rs.next()){
				TableItem item = new TableItem(table, SWT.LEFT);
			 
				String[] vs = {rs.getString("s_id"),rs.getString("s_name"),rs.getString("s_password")};
				item.setText(vs);
				
			}
			dbf.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
			
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
