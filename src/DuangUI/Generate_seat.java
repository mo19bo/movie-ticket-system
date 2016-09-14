package DuangUI;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import DuangUI.Session;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class Generate_seat extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */

	
	private String aString;
	private  int c_seat;
	private int c_row;
	private char[] aString2;
	private int  temp=0;
	public ArrayList<Character> flag;
	public Generate_seat(Composite parent, int style,int total_seat,int row) {
	
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		 c_seat=total_seat;
         c_row=row;
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(c_row, false));
		
		
	
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 40;
		gd_btnNewButton.heightHint = 40;
		
		
		flag = initalize_seat(c_seat);
		int x=0;
		final int y=0;
		
		for ( x = 0; x < total_seat; x++){
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
								"/pictureUI/ais.png"));
						
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
				ais.setText("ais");

				break;

			case 'x':
				Button b = new Button(composite, SWT.NONE);
				b.setLayoutData(gd_btnNewButton );
				b.setImage(SWTResourceManager.getImage(Session.class,
						"/javax/swing/plaf/metal/icons/"));
				
				//b.setText("N");
				break;
			}
			
		}
	}
    private  ArrayList<Character>  initalize_seat(int total){
      ArrayList<Character> rs_a = new ArrayList<Character>();
       
    	for(int i=0;i<total;i++){
    		
    		rs_a.add('1');
    		
    	}
    	return rs_a;
    }
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
