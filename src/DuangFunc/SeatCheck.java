package DuangFunc;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog; 
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;

import DuangUI.Generate_seat;
import DuangUI.Session;

public class SeatCheck extends Dialog {

	protected Object result;
	protected Shell shell;

	private String aString;
	private  int c_seat;
	private int c_row;
	private char[] aString2;
	private int  temp=0;
	public ArrayList<Character> flag = new ArrayList<Character>();
	private int totSeat;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public SeatCheck(Shell parent, int style, String ss_struct, int seatTotal, int rowNum) {
		super(parent, style);
		setText("SWT Dialog");
		System.out.println(ss_struct);
		for(int i=0;i<ss_struct.length();i++){
			flag.add(ss_struct.charAt(i));
		}
		totSeat = seatTotal;
		c_row = rowNum;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 300);
		shell.setText("座位详情");
shell.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(c_row, false));
		/*Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(c_row, false));*/
		
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 40;
		gd_btnNewButton.heightHint = 40;
		
		
		
		int x=0;
		final int y=0;
		
		for ( x = 0; x < totSeat; x++){
               temp=x;
               
			switch (flag.get(x)) {
			case '1':
				final Button seat = new Button(composite, SWT.NONE);
           
				seat.setLayoutData(gd_btnNewButton);

				
				
				///*****************这是算法生成每个的坐标  可以直接getdata************////
				seat.setImage(SWTResourceManager.getImage(Generate_seat.class, "/pictureUI/seat.png"));
				seat.setData("index",x);
				/////***********************************/////
			//     seat.setData("seatname", new Caculate_seat().caculate_seat(a, temp+1, colonm));
				//seat.setData("index",x);
				
				seat.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						
						flag.set(Integer.parseInt(seat.getData("index").toString()),'0');
						seat.setImage(SWTResourceManager.getImage(Session.class,
								"/pictureUI/seat.png"));
						
					}
				});

				break;
			case '0':
				Button ais = new Button(composite, SWT.NONE);
				ais.setLayoutData(gd_btnNewButton );
				ais.setText("0");
				
				ais.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						
					}
				});
				ais.setImage(SWTResourceManager.getImage(Session.class,
						"/pictureUI/ais.png"));

				break;

			case 'x':
				Button b = new Button(composite, SWT.NONE);
				b.setLayoutData(gd_btnNewButton );
				b.setImage(SWTResourceManager.getImage(Session.class,
						"/pictureUI/seatPng.png"));
				
				//b.setText("N");
				break;
			}
			
		}
	}

}
