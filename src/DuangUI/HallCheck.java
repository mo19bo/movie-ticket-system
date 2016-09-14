package DuangUI;

import java.sql.ResultSet; 
import java.sql.SQLException;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.TableColumn;
import databaseO.DBFactory;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import DuangFunc.SeatCheck;
public class HallCheck extends Composite {
	private Table table;
	private String h_num;
	private String h_name;
	private String ss_struct;
	private String row;
	private String seat_cont;
	private TableItem[] items;
	private DBFactory dboh = new DBFactory();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public HallCheck( Composite parent,  int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK | SWT.MULTI);
		table.setLayoutData(BorderLayout.CENTER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnid = new TableColumn(table, SWT.NONE);
		tblclmnid.setWidth(100);
		tblclmnid.setText("影厅ID");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("影厅名");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("座位总数");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("列数");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("座位分布");
		
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(BorderLayout.NORTH);
		
		ToolBar toolBar_1 = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setLayoutData(BorderLayout.SOUTH);
		
		ToolItem toolItem_2 = new ToolItem(toolBar_1, SWT.NONE);
		toolItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//CheckSeat cs = new CheckSeat(getShell(), SWT.DIALOG_TRIM, ss_struct, total_seat, row)
				items = table.getItems();  
                // 循环所有行  
				
					for (int i = items.length - 1; i >= 0; i--){
	                  
	                   
	                    if (!items[i].getChecked())  
	                        continue;  
	                   
	                    int index = table.indexOf(items[i]); 
	                   // items[index].getText(4), Integer.valueOf(items[index].getText(2)), Integer.valueOf(items[index].getText(4))
	                //    CheckSeat c  = new CheckSeat(parent, style, "1", 2, 2)
	                   // System.out.println(items[index].getText(4)+Integer.valueOf(items[index].getText(2))+ Integer.valueOf(items[index].getText(3)));
	                    SeatCheck sc = new SeatCheck(getShell(), SWT.DIALOG_TRIM, items[index].getText(4), Integer.valueOf(items[index].getText(2)), Integer.valueOf(items[index].getText(3)));
	                    sc.open();
	                    
	                    
	                } 
			}
		});
		toolItem_2.setText("查看座位详情");
		
		ToolItem toolItem_1 = new ToolItem(toolBar_1, SWT.NONE);
		toolItem_1.addSelectionListener(new SelectionAdapter() {
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
	                    String hallid = "'"+items[index].getText(1)+"'";
	                    
	                    String hallidDelete = "delete from hall where h_num = "+hallid;
	                    String ss_struct = "delete from ss_struct where h_num ="+hallid;
	                   
	                    dboh.delete(hallidDelete);
	                    dboh.delete(ss_struct);
	                    table.remove(index);  
	                    
	                    table.pack();  
	                    
	                } 
			}
		});
		toolItem_1.setText("删除");
		
		
		String sql = "select hall.h_num, h_name, ss_struct, seat_cont,row from ss_struct,hall where hall.h_num=ss_struct.h_num";
		ResultSet rs = dboh.search(sql);
		try {
			while(rs.next()){
				h_num = rs.getString("h_num");
				h_name = rs.getString("h_name");
				ss_struct = rs.getString("ss_struct");
				seat_cont = rs.getString("seat_cont");
				row = rs.getString("row");
				String[] vs = {h_num,h_name,seat_cont,row,ss_struct};
				TableItem ti = new TableItem(table, SWT.NONE);
				ti.setText(vs);
			}
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
