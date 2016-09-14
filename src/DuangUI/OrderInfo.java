package DuangUI;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class OrderInfo extends Dialog {

	protected Object result;
	protected Shell shell;
	private Label lblNewLabel_4;
	private Label lblNewLabel_5;
	private Label lblOoid;
	private Label lblMname;
	private Label lblSence;
	private Label lblPrice;
	private Label lblXy;
	private Label lblTime;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public OrderInfo(Shell parent, int style) {
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
		/*
		 * try { Class.forName("net.sourceforge.jtds.jdbc.Driver"); //建立连接
		 * Connection conn =
		 * DriverManager.getConnection("jdbc:jtds:sqlserver://10.127.131.171/movie"
		 * ,"sa","1"); //准备sql PreparedStatement patmt =
		 * conn.prepareStatement("select * from staff");
		 * 
		 * //执行sql
		 * 
		 * 
		 * ResultSet rs = patmt.executeQuery();
		 * 
		 * conn.close();
		 * 
		 * } catch (ClassNotFoundException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 300);
		shell.setText(getText());
		shell.setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);

		DateTime dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setBounds(346, 237, 88, 24);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(10, 28, 61, 17);
		lblNewLabel.setText("订单ID");

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBounds(10, 73, 61, 17);
		lblNewLabel_1.setText("电影名字");

		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setBounds(10, 117, 61, 17);
		lblNewLabel_2.setText("场次");

		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setBounds(10, 166, 61, 17);
		lblNewLabel_3.setText("票价");

		lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setBounds(222, 28, 61, 17);
		lblNewLabel_4.setText("座号");

		lblNewLabel_5 = new Label(composite, SWT.NONE);
		lblNewLabel_5.setBounds(222, 76, 61, 17);
		lblNewLabel_5.setText("购票时间");

		lblOoid = new Label(composite, SWT.NONE);
		lblOoid.setBounds(88, 28, 61, 17);
		lblOoid.setText("oo_id");

		lblMname = new Label(composite, SWT.NONE);
		lblMname.setBounds(88, 70, 61, 17);
		lblMname.setText("m_name");

		lblSence = new Label(composite, SWT.NONE);
		lblSence.setBounds(88, 117, 61, 17);
		lblSence.setText("sence");

		lblPrice = new Label(composite, SWT.NONE);
		lblPrice.setBounds(88, 166, 61, 17);
		lblPrice.setText("price");

		lblXy = new Label(composite, SWT.NONE);
		lblXy.setBounds(302, 28, 61, 17);
		lblXy.setText("x,y");

		lblTime = new Label(composite, SWT.NONE);
		lblTime.setBounds(302, 73, 61, 17);
		lblTime.setText("time");

		Button btnQuit = new Button(composite, SWT.NONE);
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {

				shell.close();
			}
		});
		btnQuit.setBounds(225, 234, 80, 27);
		btnQuit.setText("Quit!");

	}

	protected Shell getShell() {
		// TODO Auto-generated method stub
		return null;
	}
}
