package DuangUI;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import swing2swt.layout.BorderLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.wb.swt.SWTResourceManager;

public class NowMovie extends Composite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public NowMovie(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		Label label = new Label(composite, SWT.NONE);
		label.setText("正在热映：");

		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new BorderLayout(0, 0));

		ScrolledComposite scrolledComposite = new ScrolledComposite(
				composite_1, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(BorderLayout.CENTER);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		for (int i = 0; i <= 5; i++) {
			// 这里为一个电影块
			Composite block_product = new Composite(composite, SWT.NONE);
			block_product.setLayout(new GridLayout(1, false));
			new Label(block_product, SWT.NONE);

			Composite composite_6 = new Composite(block_product, SWT.NONE);
			GridData gd_composite_6 = new GridData(SWT.LEFT, SWT.CENTER, false,
					false, 1, 1);
			gd_composite_6.heightHint = 120;
			gd_composite_6.widthHint = 82;
			composite_6.setLayoutData(gd_composite_6);

			Label lblNewLabel_2 = new Label(composite_6, SWT.NONE);
			lblNewLabel_2.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_RED));
			lblNewLabel_2.setBounds(0, 10, 82, 100);
			lblNewLabel_2.setText("New Label");

			Label lblNewLabel = new Label(block_product, SWT.NONE);
			lblNewLabel.setText("电影名");
		}

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
