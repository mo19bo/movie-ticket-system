package DuangUI;

import java.awt.Frame;  
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;

import DuangFunc.ConcerttoDate;

import DuangFunc.RandomID;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import MD.MovieInfo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import swing2swt.layout.BorderLayout;
import DuangClass.*;
import databaseO.DBFactory;

public class Arrage extends Composite {
	static ArrayList<Movie> mlist = new ArrayList<Movie>();
	private String tempMvId;
	private String startTime;
	private String endTime;
	private Time temp;
	private Table table;
	private TableItem ti;
	private Date publicTime;
	private String description;
	private String tempStartTime;
	private Date tempPublicTime;
	private String move_idString;
	private String hall_numString="";
	private String tempHallid;
	private DateTime public_date;
	private int num;
	private boolean movieName;
	private boolean hallName;
	private ArrayList<String> sceneNum = new ArrayList<String>();
	private ArrayList<String> hallId = new ArrayList<String>();
	private Date playTime;
//	private ArrayList<String> h_num = new ArrayList<String>();
	private String sNum;
	private TableItem[] item;
	private TableItem[] item2;
	private Combo combo;
	private Combo hallCombo ;
	private DBFactory dbom = new DBFactory();
	private Button confirm;
	private String mv_id="";
	private Float movieLong;
	private Button button_1;
	private Button button_2;
	private Button button_3;
	private ArrayList<Integer> insertRecord = new ArrayList<Integer>();
//	private ArrayList<Integer> updateRecord = new ArrayList<Integer>();
	private int insertTemp=0;
	//private int totalHall;
	private int countHall=0;
	private int countMovie=0;

/*	Group group = new Group(this, SWT.NONE);
	static Button btnRadioButton;
	static Button btnRadioButton_1;
	static Button btnRadioButton_2;*/
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public Arrage(final Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new GridLayout(7, false));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Label lblNewLabel = new Label(this, SWT.WRAP | SWT.CENTER);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblNewLabel.widthHint = 66;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("电影名称：");
		new Label(this, SWT.NONE);
	
		
	
		combo = new Combo(this, SWT.READ_ONLY);
		String sql = "select * from movie";
		
		ResultSet rs = dbom.search(sql);
		try {
			while(rs.next()){
				Movie mv = new Movie();
				combo.add(rs.getString("mv_name"));
				mv.setMovie_Id(rs.getString("mv_id"));
				mv.setMovie_Name(rs.getString("mv_name"));
				mv.setMovie_Actor(rs.getString("actor"));
				mv.setMoivie_Description(rs.getString("describe"));
				mv.setMovie_D(rs.getString("3D/2D"));
				mv.setMovie_Date(rs.getString("pdate"));
				//System.out.println(mv.getMovie_Date());
				mv.setMovie_Director(rs.getString("director"));
				mv.setMovie_Image(rs.getString("img_movie"));
				mv.setMovie_Style(rs.getString("type"));
				mv.setMovieLong(rs.getFloat("m_time"));
				mlist.add(mv);
				countMovie++;
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			
			public void widgetSelected(SelectionEvent e) {
				
				table.removeAll();
				for(num=0;num<countMovie;num++){
					if (combo.getSelectionIndex()==num){
						 movieName = true;
						 if(hallName==true){
							 button_1.setEnabled(true);
						 }
						 movieLong = mlist.get(num).getMovieLong();							
						 mv_id = mlist.get(num).getMovie_Id();
					
						 String obj = "'"+mlist.get(num).getMovie_Id()+"'";
						 String sql = "select s_num, Date, start_time, end_time, h_num from sence where sence.mv_id= "+obj;
						 
						 ResultSet rs = dbom.search(sql);
						 try {
							while(rs.next()){
							
								 endTime = rs.getString("end_time");
								
								 startTime = rs.getString("start_time");
								 endTime = rs.getString("end_time");
								 playTime = rs.getDate("Date");
							
								 sceneNum.add(rs.getString("s_num"));
								 tempHallid = rs.getString("h_num");
								 if(tempHallid.equals(hall_numString)&&playTime.getDate()==public_date.getDay()&&playTime.getYear()+1900==public_date.getYear()&&playTime.getMonth()+1==public_date.getMonth()+1){
									 System.out.println(true);
								 }
								
								
								 if(tempHallid.equals(hall_numString)&&playTime.getDate()==public_date.getDay()&&playTime.getYear()+1900==public_date.getYear()&&playTime.getMonth()+1==public_date.getMonth()+1){
									 ti = new TableItem(table,SWT.NONE);
									 String[] vs = {startTime,endTime};
									 ti.setText(vs);
								 }
							 }
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 
					}
				}
			}
		});
		
	

		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		// 录入电影详细信息
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MovieInfo mInfo = new MovieInfo(getShell(), getStyle());
				mInfo.open();
				//moviename.setText(mInfo.m_nameString);

				move_idString = mInfo.m_id;
			}
		});

		button.setText("录入电影信息");

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_composite.widthHint = 124;
		composite.setLayoutData(gd_composite);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
				Label lblNewLabel_3 = new Label(this, SWT.CENTER);
				GridData gd_lblNewLabel_3 = new GridData(SWT.LEFT, SWT.CENTER, false,
						false, 1, 1);
				gd_lblNewLabel_3.widthHint = 50;
				lblNewLabel_3.setLayoutData(gd_lblNewLabel_3);
				lblNewLabel_3.setText("影厅");
		new Label(this, SWT.NONE);
		
		
		hallCombo = new Combo(this, SWT.READ_ONLY);
		hallCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				table.removeAll();
				for(num=0;num<countHall;num++){
					if (hallCombo.getSelectionIndex()==num){
						hallName = true;
						if(movieName == true){
							button_1.setEnabled(true);
						}
						hall_numString = hallId.get(num);
						String obj = "'"+hall_numString+"'";
						String sql = "select s_num, Date, start_time, end_time, mv_id from sence where h_num= "+obj;
						 ResultSet rs = dbom.search(sql);
						 try {
							while(rs.next()){
							
								 endTime = rs.getString("end_time");
								/* se.setEnd_Time(rs.getTime("end_time"));
								 se.setStart_Time(rs.getTime("start_time"));
								 se.setSession_Date(rs.getDate("Date"));
								 se.setSessionId(rs.getString("s_num"));
								 se.setHallId(rs.getString("h_num"));
								 slist.add(se);*/
								 startTime = rs.getString("start_time");
								 endTime = rs.getString("end_time");
								 playTime = rs.getDate("Date");
								 sceneNum.add(rs.getString("s_num"));
								 tempMvId = rs.getString("mv_id");
								 if(tempMvId.equals(mv_id)&&playTime.getDate()==public_date.getDay()&&playTime.getYear()+1900==public_date.getYear()&&playTime.getMonth()+1==public_date.getMonth()+1){
									 ti = new TableItem(table,SWT.NONE);
									 String[] vs = {startTime,endTime};
									 ti.setText(vs);
								 }
							 }
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 
					}
				}
			}
		});
		hallCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setText("放映时间：");
		new Label(this, SWT.NONE);

		public_date = new DateTime(this, SWT.BORDER);
		
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		button_1 = new Button(this, SWT.NONE);
		button_1.setEnabled(false);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
					
				
					confirm.setEnabled(true);
					//RandomID rand = new RandomID();
					//button_1.setEnabled(false);
					ti = new TableItem(table,SWT.NONE);
					ti.setChecked(true);
					item = table.getItems();
					insertRecord.add(item.length-1);
					final TableEditor edit = new TableEditor(table);
					final Text text = new Text(table, SWT.NONE);
					text.setText(ti.getText(0));
					edit.grabHorizontal = true;
					edit.setEditor(text, ti, 0);
					
					text.addModifyListener(new ModifyListener() {

						public void modifyText(ModifyEvent arg0) {
							// TODO Auto-generated method stub
							edit.getItem().setText(0, text.getText());
						
							//tempStartTime = ti.getText();
							
						}
					});
					
					/*final TableEditor edit2 = new TableEditor(table);
					final Text text2 = new Text(table, SWT.NONE);
					text2.setText(ti.getText(1));
					edit2.grabHorizontal = true;
					edit2.setEditor(text2, ti, 1);
					text2.addModifyListener(new ModifyListener() {

						public void modifyText(ModifyEvent arg0) {
							// TODO Auto-generated method stub
							edit2.getItem().setText(1, text2.getText());
							endTime = ti.getText(1);
							
						}
					});
*/
				
				
			}
		});
		button_1.setText("添加场次");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Label lblNewLabel_2 = new Label(this, SWT.CENTER);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setText("放映场次");

		Label label = new Label(this, SWT.NONE);

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK | SWT.MULTI);

		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table.heightHint = 60;
		table.setLayoutData(gd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn StartTime = new TableColumn(table, SWT.NONE);
		StartTime.setWidth(127);
		StartTime.setText("开始时间");

		TableColumn EndTime = new TableColumn(table, SWT.NONE);
		EndTime.setWidth(127);
		EndTime.setText("结束时间");

	

		

		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_composite_1.heightHint = 115;
		composite_1.setLayoutData(gd_composite_1);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		button_2 = new Button(this, SWT.NONE);
		//button_2.setEnabled(false);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				confirm.setEnabled(true);
	
				item = table.getItems();
				for (int i = 0; i < item.length; i++) {
					if (!item[i].getChecked())  
                        continue;  
					//button_2.setEnabled(true);
					final TableEditor edit = new TableEditor(table);
					final Text text = new Text(table, SWT.NONE);
					text.setText(item[i].getText(0));
					edit.grabHorizontal = true;
					edit.setEditor(text, item[i], 0);
					
					final int count = i;
					sNum = sceneNum.get(count);
					//mv_id = mlist.get(count).getMovie_Id();
					text.addModifyListener(new ModifyListener() {

						public void modifyText(ModifyEvent arg0) {
							// TODO Auto-generated method stub
							edit.getItem().setText(0, text.getText());
							//upsTime.add(item[count].getText(0));
							
							//String sql = "update sence set startTime = "+startTime+" where ";
						}
					});
					//System.out.println(hallId.get(count));
				
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
					
					/*public_date.setYear(playTime.get(count).getYear()+1900);					 
					public_date.setMonth(playTime.get(count).getMonth());
					public_date.setDay(playTime.get(count).getDate());*/
				}
			}
		});
		button_2.setText("修改场次信息");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		button_3 = new Button(this, SWT.NONE);
		//button_3.setEnabled(false);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				item2 = table.getItems();  
                // 循环所有行  
				
					for (int i = 0;i<item2.length;i++){
	                  
	                    // 如果该行没有被选中，继续循环  
	                    if (!item2[i].getChecked())  
	                        continue;  
	                    // 否则选中，查找该表格中是否有该行  
	                    int index = table.indexOf(item2[i]); 
	                    //String mv_id = "'"+item2[index].getText(1)+"'";
	                    
	                    //String mv_idDelete = "delete from movie where mv_id = "+mv_id;
	                    String sceneDelete = "delete from sence where s_num ="+sceneNum.get(index);
	               
	                    dbom.delete(sceneDelete);
	                    table.remove(index);  	                    
	                    table.pack();  
	                    
	                } 
			}
		});
		button_3.setText("删除场次");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		String sql1 = "select h_num from hall";
		ResultSet rs1 = dbom.search(sql1);
		try {
			while(rs1.next()){
				hallCombo.add(rs1.getString("h_num"));
				hallId.add(rs1.getString("h_num"));
				countHall++;
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		new Label(this, SWT.NONE);
		
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		confirm = new Button(this, SWT.NONE);
		confirm.setEnabled(false);
		confirm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
					insertRecord.add(0);
					item2 = table.getItems(); 
				
					
					try{
						
							for (int i = 0;i<item2.length;i++){
								if (!item2[i].getChecked())  
							        continue; 
								
								if(!insertRecord.isEmpty()&&i == insertRecord.get(insertTemp)){
									
									
											RandomID rand = new RandomID();
											sNum = rand.s_num();
											startTime = item2[i].getText(0);

											ConcerttoDate date = new ConcerttoDate();
											publicTime = date.converttoDate(public_date.getYear(),public_date.getMonth()+1, public_date.getDay());
											Timestamp timestamp=new Timestamp(publicTime.getTime());

											
												temp = Time.valueOf(startTime);
											
											
											 
											
												temp.setMinutes((int) (Time.valueOf(startTime).getMinutes()+movieLong));
												endTime = temp.toString();
											
											
											String hallidSearch = "'"+hall_numString+"'";
											String sqlstruct = "select ss_struct from ss_struct where h_num = "+hallidSearch;
											ResultSet rs = dbom.search(sqlstruct);
											String fullseat = null;
											
												try {
													rs.next();
													fullseat = rs.getString("ss_struct");
												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												String sqlscene = "insert into sence values(?,?,?,?,?,?,?)";
												Object[] params = {sNum, hall_numString,mv_id,timestamp,startTime,endTime,fullseat};
												dbom.executeSQLWithoutResult(sqlscene, params);
												insertTemp++;
												
												confirm.setEnabled(false);
										
								}
								

							
							else{
									
									
										ConcerttoDate date1 = new ConcerttoDate();
										startTime = item2[i].getText(0);
										publicTime = date1.converttoDate(public_date.getYear(),public_date.getMonth()+1, public_date.getDay());
										Timestamp timestamp1=new Timestamp(publicTime.getTime());
										temp = Time.valueOf(startTime);
										
										temp.setMinutes((int) (Time.valueOf(startTime).getMinutes()+movieLong));
										endTime = temp.toString();

										
										String sqlupscene = "update sence set h_num=?, mv_id=?, Date=?, start_time=?, end_time=? where s_num = ?";
										Object[] params1 = {hall_numString,mv_id,timestamp1,startTime,endTime,sceneNum.get(i)};
										dbom.executeSQLWithoutResult(sqlupscene, params1);
										
										confirm.setEnabled(false);
									
							}
							}
							JOptionPane.showMessageDialog(new Frame(), "输入成功", "提示:",
									JOptionPane.INFORMATION_MESSAGE);
							Control[] ctrls = parent.getChildren();
							for (Control ctrl : ctrls) {
								ctrl.dispose();
							}
							Arrage mGui = new Arrage(parent, SWT.NONE);
							mGui.setLayoutData(BorderLayout.CENTER);
							parent.layout();
							
						}catch (IllegalArgumentException e2) {
							// TODO Auto-generated catch block
							MessageBox mb = new MessageBox(getShell());
							mb.setMessage("请按格式输入时间xx:xx:xx");
							mb.setText("提示消息");
							mb.open();
							e2.printStackTrace();

					}catch(NullPointerException e2) {
						// TODO Auto-generated catch block
						MessageBox mb = new MessageBox(getShell());
						mb.setMessage("请按格式输入时间xx:xx:xx");
						mb.setText("提示消息");
						mb.open();
						e2.printStackTrace();

				}
							
						
						 
					
					
			}
		});

		GridData gd_confirm = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_confirm.widthHint = 107;
		confirm.setLayoutData(gd_confirm);
		confirm.setText("确定");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		/*confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DBFactory dbff = new DBFactory();
				String s_numString = new RandomID().s_num();

				ConcerttoDate date = new ConcerttoDate();
				Date date_time = date.converttoDate(public_date.getYear(),
						public_date.getMonth(), public_date.getDay());
				Timestamp pp_date = new Timestamp(date_time.getTime());

				String full_seat = null;
				String sql_1 = "select * from [ss_struct] where h_num=?";
				Object[] params_1 = { hall_numString };
				ResultSet rsResultSet = dbff.executeSQLWithResult(sql_1,
						params_1);
				try {
					while (rsResultSet.next()) {

						full_seat = rsResultSet.getString("ss_struct");

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Object[] params = { s_numString, hall_numString, move_idString,
						pp_date, startTime, endTime, full_seat };
				String SQL = "insert into [sence] values (?,?,?,?,?,?,?)";
				dbff.executeSQLWithoutResult(SQL, params);
			
				JOptionPane.showMessageDialog(new Frame(), "输入成功", "提示:",
						JOptionPane.INFORMATION_MESSAGE);
				dbff.close();
			}
		});*/

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
