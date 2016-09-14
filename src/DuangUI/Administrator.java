package DuangUI;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;

public class Administrator extends Composite {

	private Tree tree;
	private String user_id;
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public Administrator(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));

		final Composite detail = new Composite(this, SWT.NONE);
		detail.setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.WEST);
		composite.setLayout(new GridLayout(1, false));

		tree = new Tree(composite, SWT.BORDER);
		GridData gd_tree = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1,
				1);
		gd_tree.widthHint = 183;
		gd_tree.heightHint = 531;
		tree.setLayoutData(gd_tree);

		TreeItem stu1 = new TreeItem(tree, SWT.NONE);
		stu1.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/L1.png"));
		stu1.setText("人员管理");

		final TreeItem info1 = new TreeItem(stu1, SWT.NONE);
		info1.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/O1.png"));
		info1.setText("工作人员");

		final TreeItem info2 = new TreeItem(stu1, SWT.NONE);
		info2.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/H1.png"));
		info2.setText("客户");

		TreeItem stu2 = new TreeItem(tree, SWT.NONE);
		stu2.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/J1.png"));
		stu2.setText("电影管理");

		final TreeItem info3 = new TreeItem(stu2, SWT.NONE);
		info3.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/A1.png"));
		info3.setText("影库查看");

		final TreeItem info4 = new TreeItem(stu2, SWT.NONE);
		info4.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/D1.png"));
		info4.setText("电影排片");

		final TreeItem info5 = new TreeItem(stu2, SWT.NONE);
		info5.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/F1.png"));
		info5.setText("广告投放");
		
		TreeItem treeItem = new TreeItem(tree, SWT.NONE);
		treeItem.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/K1.png"));
		treeItem.setText("影厅管理");
		
		final TreeItem info10 = new TreeItem(treeItem, SWT.NONE);
		info10.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/C1.png"));
		info10.setText("查看影厅");
		
		final TreeItem info11 = new TreeItem(treeItem, SWT.NONE);
		info11.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/B1.png"));
		info11.setText("添加影厅");
		treeItem.setExpanded(true);
		
		final TreeItem info6 = new TreeItem(tree, SWT.NONE);
		info6.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/G1.png"));
		info6.setText("团购管理");
		
		final TreeItem info7 = new TreeItem(info6, SWT.NONE);
		info7.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/I1.png"));
		info7.setText("票务管理");
		
		final TreeItem info8 = new TreeItem(info6, SWT.NONE);
		info8.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/M1.png"));
		info8.setText("合作管理");
		info6.setExpanded(true);

		final TreeItem stu3 = new TreeItem(tree, SWT.NONE);
		stu3.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/N1.png"));
		stu3.setText("用户资金管理");
		
		final TreeItem trtmShu = new TreeItem(tree, SWT.NONE);
		trtmShu.setImage(SWTResourceManager.getImage(Administrator.class, "/pictureUI/J1.png"));
		trtmShu.setText("数据库设置");
		File file = new File("login.txt");
        BufferedReader reader = null;
        try {
           if(file.exists())
        	   file.createNewFile();
            reader = new BufferedReader(new FileReader(file));
         
            user_id = reader.readLine();
            // 一次读入一行，直到读入null为文件结束
          
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem item = (TreeItem) e.item;

				Control[] controls = detail.getChildren();
				for (Control con : controls) {
					con.dispose();
				}
				if (item == info1) {
					if(!user_id.startsWith("m")){
						JOptionPane.showMessageDialog(new Frame(), "您的权限不够", "提示:",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						StaffManager uManager = new StaffManager(detail, SWT.NONE);
						uManager.setLayoutData(BorderLayout.CENTER);
						detail.layout();
					}
				} else if (item == info2) {
					if(!user_id.startsWith("m")){
						JOptionPane.showMessageDialog(new Frame(), "您的权限不够", "提示:",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						UserManage staff = new UserManage(detail, SWT.NONE);
						staff.setLayoutData(BorderLayout.CENTER);
						detail.layout();
					}
				} else if (item == info3) {
					MovieStorage movie = new MovieStorage(detail, SWT.NONE);
					movie.setLayoutData(BorderLayout.CENTER);
					detail.layout();
				} else if (item == info4) {
					Arrage arrage = new Arrage(detail, SWT.NONE);
					arrage.setLayoutData(BorderLayout.CENTER);
					detail.layout();
				} else if (item == info5) {
					Adverisement adThrow = new Adverisement(detail, SWT.NONE);
					adThrow.setLayoutData(getLayout());
					detail.layout();
				} else if (item == stu3) {
					UserAccount cash = new UserAccount(detail, SWT.NONE);
					cash.setLayoutData(BorderLayout.CENTER);
					detail.layout();
				}else if(item == info7){
					DrawBill drawBill = new DrawBill(detail, SWT.NONE);
					drawBill.setLayoutData(BorderLayout.CENTER);
					detail.layout();
				}else if(item == info8){
					merMangage mangage = new merMangage(detail, SWT.NONE);
					mangage.setLayoutData(BorderLayout.CENTER);
					detail.layout();
				}else if(item == info10){
					HallCheck hcCheck = new HallCheck(detail, SWT.NONE);
					hcCheck.setLayoutData(BorderLayout.CENTER);
					detail.layout();
				}else if(item == info11){
					HallAdd hallAdd = new HallAdd(detail, SWT.NONE);
					hallAdd.setLayoutData(BorderLayout.CENTER);
					detail.layout();
				}
				else if(item == trtmShu)
				{
					setting set = new setting(detail, SWT.NONE);
					 set.setLayoutData(BorderLayout.CENTER);
					detail.layout();
				}
			}
		});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
