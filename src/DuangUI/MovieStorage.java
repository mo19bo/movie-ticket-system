package DuangUI;

import java.sql.ResultSet;  
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import org.eclipse.swt.widgets.Composite;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import DuangClass.Movie;
import databaseO.DBFactory;
public class MovieStorage extends Composite {
	private  Table Movietable;
	private int i;
	private int[] index;
	private int indexCount=0;
	private TableItem[] items;
	private String movieID;
	private String movieName;
	private String pdate;
	//private String startTime;
	private String movieType;
	private String director;
	private String type;
	private String actor;
	private Float movieLong;
	private String path;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MovieStorage(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		
		
		Movietable = new Table(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK | SWT.MULTI);
		Movietable.setHeaderVisible(true);
		Movietable.setLinesVisible(true);
		
		//===================================================================
	
		final DBFactory dbo = new DBFactory();
	
		TableColumn tblclmnNewColumn = new TableColumn(Movietable, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("行号");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(Movietable, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("电影ID");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(Movietable, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("电影名");
		
		TableColumn tableColumn = new TableColumn(Movietable, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("上映时间");
		
		TableColumn tableColumn_1 = new TableColumn(Movietable, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("电影类型");
		
		TableColumn tableColumn_2 = new TableColumn(Movietable, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("导演");
		
		TableColumn tableColumn_3 = new TableColumn(Movietable, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("3D/2D");
		
		TableColumn tableColumn_7 = new TableColumn(Movietable, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("演员");
		
		TableColumn tableColumn_4 = new TableColumn(Movietable, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("电影时长");
		
		TableColumn tableColumn_5 = new TableColumn(Movietable, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("海报路径");
		
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(BorderLayout.NORTH);
		
		final Movie mv = new Movie();
		//final Session se = new Session();
		final ToolItem toolItem = new ToolItem(toolBar, SWT.RIGHT);
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		toolItem.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
					mv.setMovie_Id(movieID);
					mv.setMovie_Name(movieName);
					mv.setMovie_Date(pdate);
					mv.setMovie_Director(director);
					mv.setMovie_Image(path);
					mv.setMovie_Style(movieType);
					mv.setMovie_D(type);
					mv.setMovieLong(movieLong);
					mv.setMovie_Actor(actor);
					/*se.setStartTime(startTime);
					se.setHallId(hallNum);*/
					
					/*Time endtime = Time.valueOf(startTime);
					ResultSet rs = dbo.searchColumn("select mv_id, m_time from movie where mv_name = ?",mv);
					try {
						se.setSession_Date(sdf.parse(date));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						while(rs.next()){
							se.setMovieId(rs.getString("mv_id"));
							endtime.setMinutes(endtime.getMinutes()+rs.getInt("m_time"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
					se.setEndTime(formatter.format(endtime));
			
					try {
						se.setSession_Date(sdf.parse(date));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}*/
					String sql = "update movie set mv_name=?,pdate=?,type=?,director=?,[3D/2D]=?,actor=?,m_time=?,img_movie=? where mv_id=?";
					Object[] params = {movieName,pdate,movieType,director,type,actor,movieLong,path,movieID};
					dbo.executeSQLWithoutResult(sql, params);
					MessageBox mb = new MessageBox(getShell());
					mb.setMessage("修改成功！");
					mb.setText("提示消息");
					mb.open();
					
			}
		});
		toolItem.setText("完成");
		toolItem.setEnabled(false);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new GridLayout(4, false));
		new Label(composite, SWT.NONE);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				toolItem.setEnabled(true);
				  
                // 循环所有行  
				
				items = Movietable.getItems();
				index = new int[items.length];
					for (i = 0;i<items.length; i++){
	                  
	                    // 如果该行没有被选中，继续循环  
	                    if (!items[i].getChecked())  
	                        continue;  
	                    // 否则选中，查找该表格中是否有该行  
	                    /*index[indexCount] = Movietable.indexOf(items[i]); 
	                    indexCount++;*/
	                    
	                    //int index = table.indexOf(items[i]); 
	                    final TableEditor edit = new TableEditor(Movietable);
	        			final Text text = new Text(Movietable,SWT.NONE);
	        			text.setText(items[i].getText(2));
	        			edit.grabHorizontal = true;
	        			edit.setEditor(text,items[i],2);
	        			final int count = i;
	        			movieID = items[count].getText(1);
	        			movieName = items[count].getText(2);
	        			text.addModifyListener(new ModifyListener() {
	        				
	        				@Override
	        				public void modifyText(ModifyEvent arg0) {
	        					// TODO Auto-generated method stub
	        					
	        					edit.getItem().setText(2,text.getText());
	        					//System.out.println(items[i].getText(2));
	        					movieName = items[count].getText(2);
	        					//movieName.add(items[i].getText(2));
	        				}
	        			});
	        			final TableEditor edit2 = new TableEditor(Movietable);
	        			final Text text2 = new Text(Movietable,SWT.NONE);
	        			text2.setText(items[i].getText(3));
	        			edit2.grabHorizontal = true;
	        			edit2.setEditor(text2,items[i],3);
	        			pdate = items[count].getText(3);
	        			text2.addModifyListener(new ModifyListener() {
	        				
	        				@Override
	        				public void modifyText(ModifyEvent arg0) {
	        					// TODO Auto-generated method stub
	        					edit2.getItem().setText(3,text2.getText());
	        					pdate = items[count].getText(3);
	        				}
	        			});
	        			final TableEditor edit3 = new TableEditor(Movietable);
	        			final Text text3 = new Text(Movietable,SWT.NONE);
	        			text3.setText(items[i].getText(4));
	        			edit3.grabHorizontal = true;
	        			edit3.setEditor(text3,items[i],4);
	        			movieType = items[count].getText(4);
	        			text3.addModifyListener(new ModifyListener() {
	        				
	        				@Override
	        				public void modifyText(ModifyEvent arg0) {
	        					// TODO Auto-generated method stub
	        					edit.getItem().setText(4,text3.getText());
	        					movieType = items[count].getText(4);
	        				}
	        			});
	        			final TableEditor edit4 = new TableEditor(Movietable);
	        			final Text text4 = new Text(Movietable,SWT.NONE);
	        			text4.setText(items[i].getText(5));
	        			edit4.grabHorizontal = true;
	        			edit4.setEditor(text4,items[i],5);
	        			director = items[count].getText(5);
	        			text4.addModifyListener(new ModifyListener() {
	        				
	        				@Override
	        				public void modifyText(ModifyEvent arg0) {
	        					// TODO Auto-generated method stub
	        					edit4.getItem().setText(5,text4.getText());
	        					director = items[count].getText(5);
	        				}
	        			});
	        			final TableEditor edit5 = new TableEditor(Movietable);
	        			final Text text5 = new Text(Movietable,SWT.NONE);
	        			text5.setText(items[i].getText(6));
	        			edit5.grabHorizontal = true;
	        			edit5.setEditor(text5,items[i],6);
	        			type = items[count].getText(6);
	        			text5.addModifyListener(new ModifyListener() {
	        				
	        				@Override
	        				public void modifyText(ModifyEvent arg0) {
	        					// TODO Auto-generated method stub
	        					edit5.getItem().setText(6,text5.getText());
	        					movieType = items[count].getText(6);
	        				}
	        			});
	        			final TableEditor edit6 = new TableEditor(Movietable);
	        			final Text text6 = new Text(Movietable,SWT.NONE);
	        			text6.setText(items[i].getText(7));
	        			edit6.grabHorizontal = true;
	        			edit6.setEditor(text6,items[i],7);
	        			actor = items[count].getText(7);
	        			text6.addModifyListener(new ModifyListener() {
	        				
	        				@Override
	        				public void modifyText(ModifyEvent arg0) {
	        					// TODO Auto-generated method stub
	        					edit6.getItem().setText(7,text6.getText());
	        					actor = items[count].getText(7);
	        				}
	        			});
	        			final TableEditor edit7 = new TableEditor(Movietable);
	        			final Text text7 = new Text(Movietable,SWT.NONE);
	        			text7.setText(items[i].getText(8));
	        			edit7.grabHorizontal = true;
	        			edit7.setEditor(text7,items[i],8);
	        			movieLong = Float.parseFloat(items[count].getText(8));
	        			text7.addModifyListener(new ModifyListener() {
	        				
	        				@Override
	        				public void modifyText(ModifyEvent arg0) {
	        					// TODO Auto-generated method stub
	        					edit7.getItem().setText(8,text7.getText());
	        					movieLong = Float.parseFloat(items[count].getText(8));
	        				}
	        			});
	        			final TableEditor edit8 = new TableEditor(Movietable);
	        			final Text text8 = new Text(Movietable,SWT.NONE);
	        			text8.setText(items[i].getText(9));
	        			edit8.grabHorizontal = true;
	        			edit8.setEditor(text8,items[i],9);
	        			path = items[count].getText(9);
	        			text8.addModifyListener(new ModifyListener() {
	        				
	        				@Override
	        				public void modifyText(ModifyEvent arg0) {
	        					// TODO Auto-generated method stub
	        					edit8.getItem().setText(9,text8.getText());
	        					path = items[count].getText(9);
	        				}
	        			});
	        			/*final TableEditor edit9 = new TableEditor(Movietable);
	        			final Text text9 = new Text(Movietable,SWT.NONE);
	        			text9.setText(items[i].getText(10));
	        			edit9.grabHorizontal = true;
	        			edit9.setEditor(text9,items[i],10);
	        			hallNum = items[count].getText(10);
	        			text9.addModifyListener(new ModifyListener() {
	        				
	        				@Override
	        				public void modifyText(ModifyEvent arg0) {
	        					// TODO Auto-generated method stub
	        					edit9.getItem().setText(10,text9.getText());
	        					hallNum = items[count].getText(10);
	        				}
	        			});*/
	        			
	                    }
	                
			}
		});
		btnNewButton.setText("修改电影信息");
		new Label(composite, SWT.NONE);
		
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				items = Movietable.getItems();  
                // 循环所有行  
				
					for (i = items.length - 1; i >= 0; i--){
	                  
	                    // 如果该行没有被选中，继续循环  
	                    if (!items[i].getChecked())  
	                        continue;  
	                    // 否则选中，查找该表格中是否有该行  
	                    int index = Movietable.indexOf(items[i]); 
	                    String mv_id = "'"+items[index].getText(1)+"'";
	                    
	                    String mv_idDelete = "delete from movie where mv_id = "+mv_id;
	                    String sceneDelete = "delete from sence where sence.mv_id ="+mv_id;
	                    System.out.println(sceneDelete);
	                    dbo.delete(mv_idDelete);
	                    dbo.delete(sceneDelete);
	                    Movietable.remove(index);  
	                    
	                    Movietable.pack();  
	                    
	                } 
			}
		});
		btnNewButton_1.setText("删除电影信息");
		
		
		
		//select mv_id, mv_name, pdate, type, director, 3D/2D, actor, m_time, img_movie where sence.mv_id = movie.mv_id
		String s = "select * from movie";
		ResultSet rs = dbo.search(s);
		try {
			i = 1;
			while(rs.next()){
				TableItem ti = new TableItem(Movietable,SWT.NONE);
				String[] vs = {String.valueOf(i),rs.getString("mv_id"),rs.getString("mv_name"), rs.getDate("pdate").toString(),rs.getString("type"),rs.getString("director"),rs.getString("3D/2D"),rs.getString("actor"),rs.getString("m_time"),rs.getString("img_movie")};
				ti.setText(vs);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		/*TableItem [] item = table.getItems();
		for(int i=0;i<item.length;i++){
			for(int j=0;j<2;j++){
			final TableEditor edit = new TableEditor(table);
			final Text text = new Text(table,SWT.NONE);
			text.setText(item[i].getText(j));
			edit.grabHorizontal = true;
			edit.setEditor(text,item[i],j);
			text.addModifyListener(new ModifyListener() {
				
				public void modifyText(ModifyEvent arg0) {
					// TODO Auto-generated method stub
					edit.getItem().setText(1,text.getText());
				}
			});
			}
		}*/
	}
	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
