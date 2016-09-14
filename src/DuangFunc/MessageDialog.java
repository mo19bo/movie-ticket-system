package DuangFunc;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle; 

public class MessageDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private String messageString;
	private String imagesString;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public MessageDialog(Shell parent, int style,String message,int id) {
	   
		super(parent, style);
		messageString=message;
		switch(id){
		case 1:
			setText("错误消息");
			 imagesString= "/tips/A.png";
			break;
		case 2:
			setText("提示消息");
			 imagesString= "/tips/B.png";
			break;
		case 3:
			setText("警告消息");
			 imagesString= "/tips/C.png";
			break;
		case 4:
			setText("异常消息");
			 imagesString= "/tips/D.png";
			break;
			
			
			
	
		}
	
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		   Rectangle displayBounds = display .getPrimaryMonitor().getBounds(); 
	        Rectangle shellBounds = shell.getBounds(); 
	     int x = displayBounds.x + (displayBounds.width - shellBounds.width)>>1; 
	     int y = displayBounds.y + (displayBounds.height - shellBounds.height)>>1; 
	 	shell.setLocation(x,y);
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shell.setSize(335, 155);
		shell.setText("葫芦娃电影售票系统");
		shell.setLayout(new BorderLayout(0, 0));
		
		
	
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(composite_1, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(2, false));
		
		Label image_icon = new Label(composite, SWT.NONE);
		image_icon.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		image_icon.setImage(SWTResourceManager.getImage(MessageDialog.class,imagesString));
		GridData gd_image_icon = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_image_icon.widthHint = 75;
		gd_image_icon.heightHint = 75;
		image_icon.setLayoutData(gd_image_icon);
		
		
		Label messages = new Label(composite, SWT.NONE);
		messages.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_messages = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_messages.widthHint = 317;
		messages.setLayoutData(gd_messages);
		messages.setText(messageString);
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.SOUTH);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button btnNewButton = new Button(composite_2, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			shell.dispose();
			}
		});
		btnNewButton.setText("确定");

	}
	

}
