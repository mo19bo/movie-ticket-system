package DuangUI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import databaseO.DBFactory;
public class HallAdd extends Composite {

	private String seatTemp;
	private Generate_seat gs;
	private Text hNumtext;
	private Text hNametext;
	private Text totaltext;
	private Text rowtext;
	private Composite composite_5;
	//private DBFactory dboh;
	private Label hallNum;
	private Label hallName;
	private Label totalSeat;
	private Label rowNum;
	private String ss_struct="";
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public HallAdd(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new GridLayout(5, false));
		
		hallNum = new Label(composite, SWT.NONE);
		hallNum.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		hallNum.setText("影厅号");
		
		hNumtext = new Text(composite, SWT.BORDER);
		hNumtext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		hallName = new Label(composite, SWT.NONE);
		hallName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		hallName.setText("影厅名");
		
		hNametext = new Text(composite, SWT.BORDER);
		hNametext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		totalSeat = new Label(composite, SWT.NONE);
		totalSeat.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		totalSeat.setText("座位总数");
		
		totaltext = new Text(composite, SWT.BORDER);
		totaltext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		rowNum = new Label(composite, SWT.NONE);
		rowNum.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		rowNum.setText("列数");
		
		rowtext = new Text(composite, SWT.BORDER);
		rowtext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				gs = new Generate_seat(composite_5, SWT.NONE, Integer.parseInt(totaltext.getText()), Integer.parseInt(rowtext.getText()));
				gs.setLayoutData(BorderLayout.CENTER);				
				gs.pack();
			}
		});
		btnNewButton.setText("生成座位图");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.SOUTH);
		composite_1.setLayout(new GridLayout(6, false));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Button btnNewButton_1 = new Button(composite_1, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					DBFactory dboh = new DBFactory();
					//String sql = "insert into hall values("+"'"+hNumtext.getText()+"','"+hNametext.getText()+"')";
					
					String sql = "insert into hall values(?,?)";
					Object[] params = {hNumtext.getText(),hNametext.getText()};
					System.out.println(hNumtext.getText()+hNametext.getText());
					dboh.executeSQLWithoutResult(sql, params);
					//dboh.insert(sql);
					
					String sql2 = "insert into ss_struct values(?,?,?,?)";
					for(int i=0;i<gs.flag.size();i++){
						ss_struct = ss_struct+gs.flag.get(i).toString();
					}
					Object[] params2 = {ss_struct,hNumtext.getText(),Integer.valueOf(rowtext.getText()),Integer.valueOf(totaltext.getText())};
					System.out.println(ss_struct+hNumtext.getText()+Integer.valueOf(rowtext.getText())+Integer.valueOf(totaltext.getText()));
					dboh.executeSQLWithoutResult(sql2, params2);
					MessageBox mb = new MessageBox(getShell());
					mb.setText("提示");
					mb.setMessage("添加成功");
					mb.open();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					MessageBox mb = new MessageBox(getShell());
					mb.setText("提示");
					mb.setMessage("添加失败");
					mb.open();
				}
			}
		});
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_1.widthHint = 145;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setText("确认添加");
		new Label(composite_1, SWT.NONE);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setText("默认是座位，点击变成过道");
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.WEST);
		
		Composite composite_3 = new Composite(this, SWT.NONE);
		composite_3.setLayoutData(BorderLayout.EAST);
		
		Composite composite_4 = new Composite(this, SWT.NONE);
		composite_4.setLayoutData(BorderLayout.CENTER);
		composite_4.setLayout(new BorderLayout(0, 0));
		
		composite_5 = new Composite(composite_4, SWT.NONE);
		composite_5.setLayoutData(BorderLayout.CENTER);
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
