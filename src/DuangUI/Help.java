package DuangUI;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import swing2swt.layout.BorderLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

public class Help extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Help(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
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
		shell.setSize(582, 332);
		shell.setText("帮助");
		shell.setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new BorderLayout(0, 0));
		// 返回主页
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();

			}
		});
		button.setText("返回系统");

		Label lblHelp = new Label(shell, SWT.WRAP);
		lblHelp.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblHelp.setLayoutData(BorderLayout.CENTER);
		lblHelp.setText("        感谢您的使用，我们是葫芦娃工作室，欢迎您的加入，本电影票购票软件分为两套系统，分别为管理员后台管理和前台购票，以不同身份登陆操作不同功能，以提供一套为用户和影院管理人员的完整解决方案。");
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		Composite picture = new Composite(composite_1, SWT.NONE);
		picture.setLayout(new BorderLayout());
		picture.setLayoutData(BorderLayout.CENTER);
		 ImageComposite ic = new ImageComposite(picture, SWT.NONE, new Image(null,"src/pictureUI/advertise.png"),
					"Scaled");
			ic.setLayoutData(BorderLayout.CENTER);
			picture.layout();
			picture.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			
			picture.setLayout(new BorderLayout(0, 0));
			
		composite_1.setLayoutData(BorderLayout.NORTH);

	}
}
