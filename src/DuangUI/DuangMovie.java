package DuangUI;



import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import databaseO.DBFactory;
import databaseO.MovieDAO;
import DuangClass.Movie;
 

public class DuangMovie extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	// 三个关于电影票类的对象
		DBFactory dbFactory = new DBFactory();
		MovieDAO movieDAO = new MovieDAO();
		Movie movie = new Movie();
		private Image image = null; 
		private String userID;
		public  int num; 
		String mNameString = "";	//	电影名称
		String sNum = ""; //	场次id
		int seatCount = 0;	// 	座位数量 
		int sStruct = 0;	//	座位结构
		String hNum = "";	//	厅号
		String sDateString = "";	//	场次日期
		String full_seat="";
		//获取数据库中总共的电影部数
		int numOfMovie = movieDAO.numberOfMovie("movie",dbFactory);
		
		//查询数据库中的电影信息并存到list中
		List<Movie> list = movieDAO.searchMovie(dbFactory);
	
	public DuangMovie(final Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		

		final String[] movieName = new String[numOfMovie];
		final String[] movieDate = new String[numOfMovie];
		final String[] movieDirector = new String[numOfMovie];
		final String[] movieActor= new String[numOfMovie];
		final String[] movieStyle = new String[numOfMovie];
		final String[] movieD = new String[numOfMovie];
		final String[] movieDescription = new String[numOfMovie];
		final float[] movieScore= new float[numOfMovie];
		final String[] movieImage = new String[numOfMovie];
		final String[] movieID = new String[numOfMovie];
		for (int i = 0; i <numOfMovie ; i++) {
			// 这里为一个电影块
			
			movie = list.get(i);
			
			movieID[i] = movie.getMovie_Id();		
			movieName[i] = movie.getMovie_Name();
			movieActor[i] =movie.getMovie_Actor();
			movieD[i] = movie.getMovie_D();
			movieDate[i] = movie.getMovie_Date();
			movieDescription[i] = movie.getMoivie_Description();
			movieDirector[i] = movie.getMovie_Director();
			movieStyle[i] = movie.getMovie_Style();
			movieScore[i] = movie.getMovie_Score();
			movieImage[i] = movie.getMovie_Image();
		}
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new BorderLayout(0, 0));
		
		Label lblNewLabel = new Label(composite, SWT.CENTER);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.BOLD | SWT.ITALIC));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel.setText("最近热映的电影");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new BorderLayout(0, 0));
		
		final Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2.setLayoutData(BorderLayout.NORTH);
		composite_2.setLayout(new BorderLayout(0, 0));
		Composite picture = new Composite(composite_2, SWT.NONE);
		picture.setLayout(new BorderLayout());
		picture.setLayoutData(BorderLayout.CENTER);
		ImageComposite ic = new ImageComposite(picture, SWT.NONE, new Image(null, "src/pictureUI/advertise.png"),
				"Scaled");
		ic.setLayoutData(BorderLayout.CENTER);
	
		ScrolledComposite scrolledComposite = new ScrolledComposite(composite_1, SWT.BORDER | SWT.H_SCROLL | SWT.NONE);
		scrolledComposite.setLayoutData(BorderLayout.CENTER);
		scrolledComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite movie = new Composite(scrolledComposite, SWT.BORDER);
		movie.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	
		//	为广告赋值
		for(int i=0; i < numOfMovie; i++){
			
			Composite block_product = new Composite(movie, SWT.NONE);
			block_product.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			block_product.setLayout(new GridLayout(2, false));
			

			Composite white = new Composite(block_product, SWT.NONE);
			white.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			GridData gd_white = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_white.widthHint = 190;
			white.setLayoutData(gd_white);
			new Label(block_product, SWT.NONE);
			// 电影海报
			Composite composite_6 = new Composite(block_product, SWT.NONE);
			composite_6.setLayout(new BorderLayout());
			composite_6.setLayoutData(BorderLayout.CENTER);
			System.out.println(movieImage[i]);
			ImageComposite ic1 = new ImageComposite(composite_6, SWT.NONE, new Image(null, movieImage[i]),
					"Scaled");
			
			ic1.setLayoutData(BorderLayout.CENTER);
			composite_6.layout();
			composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			
			GridData gd_composite_6 = new GridData(SWT.CENTER, SWT.CENTER, false,
					false, 1, 1);
			gd_composite_6.heightHint = 271;
			gd_composite_6.widthHint = 186;
			composite_6.setLayoutData(gd_composite_6);
			
			Composite composite_11 = new Composite(block_product, SWT.NONE);
			GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_composite_1.widthHint = 16;
			composite_11.setLayoutData(gd_composite_1);
			composite_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			
			Composite composite1 = new Composite(block_product, SWT.NONE);
			GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_composite.widthHint = 190;
			gd_composite.heightHint = 195;
			composite1.setLayoutData(gd_composite);
			composite1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			//	电影导演
			Label movieDescri = new Label(composite1, SWT.WRAP | SWT.HORIZONTAL | SWT.CENTER);
			movieDescri.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
			movieDescri.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			movieDescri.setBounds(10, 27, 170, 21);
			movieDescri.setText(movieDirector[i]);
		//	movieDescri.sets
			
			// 电影名称
			Label label_1 = new Label(composite1, SWT.CENTER);
			label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
			label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			label_1.setBounds(10, 0, 170, 24);
			label_1.setText(movieName[i]);
			
			Label label = new Label(composite1, SWT.WRAP | SWT.HORIZONTAL | SWT.CENTER);
			label.setText(movieD[i]);
			label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
			label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			label.setBounds(10, 54, 170, 21);
			
			
			
			final Button btnNewButton1 = new Button(composite1, SWT.NONE);
			btnNewButton1.setData(i);
			
			btnNewButton1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					num = Integer.parseInt(btnNewButton1.getData().toString());
					Control[] ctrls = parent.getChildren();
					for (Control ctrl : ctrls) {
						ctrl.dispose();
					}
					
					MovieTicket aGui = new MovieTicket(parent, SWT.NONE, movieID[num], movieName[num], movieDate[num], movieStyle[num], movieDirector[num]
							,movieActor[num], movieScore[num], "2d",movieDescription[num],movieImage[num]);
					aGui.setLayoutData(BorderLayout.CENTER);
					parent.layout();

				}
			});
			
			btnNewButton1.setBounds(47, 81, 100, 27);
			btnNewButton1.setText("电影详情");
			new Label(block_product, SWT.NONE);
			
			

		}
		
		movie.setLayout(new FillLayout(SWT.HORIZONTAL));
		scrolledComposite.setContent(movie);
		scrolledComposite.setMinSize(movie.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		parent.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		parent.setLayoutData(BorderLayout.CENTER);
		parent.setLayout(new BorderLayout(0, 0));
	
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
