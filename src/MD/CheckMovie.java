package MD;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CheckMovie extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CheckMovie(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
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
		shell.setSize(605, 306);
		shell.setText(getText());
		shell.setLayout(new BorderLayout(0, 0));
		//	返回系统
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		button.setLayoutData(BorderLayout.SOUTH);
		button.setText("返回系统");
		
		Label label = new Label(shell, SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		label.setLayoutData(BorderLayout.CENTER);
		label.setText("恭喜您，已经成功录入电影信息!");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(CheckMovie.class, "/pictureUI/up1.png"));
		label_1.setLayoutData(BorderLayout.NORTH);

	}
}
