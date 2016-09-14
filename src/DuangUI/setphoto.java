package DuangUI;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;

public class setphoto extends Dialog {

	protected Object result;
	protected Shell shell;
   public String image_urlString;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public setphoto(Shell parent, int style) {
		super(parent, style);
		setText("请选择头像");
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
		shell.setSize(243, 251);
		shell.setText(getText());
		shell.setLayout(new BorderLayout(0, 0));
		
		Composite composite_4 = new Composite(shell, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_4.setLayoutData(BorderLayout.CENTER);
		composite_4.setLayout(new GridLayout(5, false));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(null);
		
		final Label lblNewLabel = new Label(composite, SWT.HORIZONTAL);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(0, 0, 237, 27);
		lblNewLabel.setText("");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBounds(0, 27, 237, 27);
		
	
		btnNewButton_1.setText("确认选择");
	
		
	for(int i=1;i<=13;i++){
		final String image_nameString=i+".png";
		System.out.println(image_nameString);
		Composite picture = new Composite(composite_4, SWT.NONE);
		picture.setLayout(new BorderLayout());
		picture.setLayoutData(BorderLayout.CENTER);
		final ImageComposite ic = new ImageComposite(picture, SWT.NONE, new Image(null, "src/icon/"+image_nameString),
				"Scaled");
		ic.setLayoutData(BorderLayout.CENTER);
		picture.layout();
		
		final GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 40;
		gd_btnNewButton.heightHint = 40;
		picture.setLayoutData(gd_btnNewButton);
	
		ic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				image_urlString= "/icon/"+image_nameString;
				lblNewLabel.setText("请点击确定结束选择！");
			}
			
		});
	
		
		
		}
		
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				   Register rg=new Register(getParent(), getStyle());
				  
		            shell.dispose();
			}
		});
	

	}
}
