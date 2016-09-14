package DuangUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.widgets.Composite;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;

import databaseO.DBFactory;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class merMangage extends Composite {
	private Table table;
	private Combo combo;
	//	商家姓名（数组）
	private ArrayList<String> merNameArrayList = new ArrayList<String>();
	//	商家姓名（数组）
	private String[] merName;
	private Integer count1=0;
	private Label count;
	int i = 0;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public merMangage(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
//		从数据库里面读取商家名称
		combo = new Combo(composite, SWT.READ_ONLY);
			DBFactory db = new DBFactory();
			String SQL = "select distinct mer_name from [merchant]";
			Object[] params = {};
			// 为combo添加商家名称
			/*for(int i = 0; i<merNameArrayList.size();i++)
			{
				combo.add(merNameArrayList.get(i));
			}*/
			ResultSet re = db.executeSQLWithResult(SQL, params);
			try {
				int i = 0;
				while(re.next())
				{
					combo.add(re.getString("mer_name"));
					/*System.out.println(re.getString("mer_name"));
					merNameArrayList.add(re.getString("mer_name"));*/
				}
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			db.close();
		
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				databaseO.DBFactory dbome = new databaseO.DBFactory();
				String objString = "'"+combo.getItem(combo.getSelectionIndex())+"'";
				String sqlString = "select * from merchant where mer_name = "+objString;
				ResultSet rsResultSet = dbome.search(sqlString);
				Date startTime;
				Date endTime = null;
				try {
					while(rsResultSet.next()){
						TableItem ti = new TableItem(table, SWT.NONE);
						startTime = rsResultSet.getDate("mer_date");
						//System.out.println(startTime.getDate()+20);
						endTime = startTime;
						endTime.setDate(startTime.getDate()+30);
						String vs[] = {rsResultSet.getString("mer_name"),rsResultSet.getString("mer_num"),rsResultSet.getString("mer_date"),endTime.toString()};
						ti.setText(vs);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String sqlString2 = "select sum(mer_num) as Total from merchant where mer_name = "+objString;
				ResultSet rs = dbome.search(sqlString2);
				try {
					while(rs.next()){
						count1 = rs.getInt("Total");
						count.setText(count1.toString());
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
		Label money = new Label(composite, SWT.CENTER);
		money.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		money.setText("购票总数：");
		
		count = new Label(composite, SWT.CENTER);
		count.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.SOUTH);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setText("上一页");
		
		Button button = new Button(composite_1, SWT.NONE);
		button.setText("下一页");
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(BorderLayout.CENTER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setResizable(false);
		tblclmnNewColumn.setWidth(124);
		tblclmnNewColumn.setText("商家名称：");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setResizable(false);
		tblclmnNewColumn_1.setWidth(136);
		tblclmnNewColumn_1.setText("团购数量：");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(171);
		tblclmnNewColumn_2.setText("购买日期：");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setMoveable(true);
		tblclmnNewColumn_3.setResizable(false);
		tblclmnNewColumn_3.setWidth(153);
		tblclmnNewColumn_3.setText("截止日期：");
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
