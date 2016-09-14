package DuangUI;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

/**
 * 可设置背景图片的Composite Composite本身是可以设置背景图片的，但是只提供了平铺的显示方式，该类实现了居中、平铺和拉伸三种显示方式。
 * 
 * @author 003
 */
public class ImageComposite extends Composite implements PaintListener {
	/**
	 * 居中
	 */
	public static final String CENTER = "Center";
	/**
	 * 平铺
	 */
	public static final String TILED = "Tiled";
	/**
	 * 拉伸
	 */
	public static final String SCALED = "Scaled";
	/**
	 * 背景图片
	 */
	private Image backgroundImage;
	/**
	 * 背景图片显示模式
	 */
	private String imageDisplayMode;
	/**
	 * 背景图片显示模式索引（引入此属性有助于必要时扩展）
	 */
	private int modeIndex;

	/**
	 * 构造一个没有背景图片的ImageComposite
	 * 
	 * @param parent
	 *            父组件
	 * @param style
	 *            风格
	 */
	public ImageComposite(Composite parent, int style) {
		this(parent, style, null, CENTER);
	}

	/**
	 * 构造一个具有指定背景图片和指定显示模式的ImageComposite
	 * 
	 * @param parent
	 *            父组件
	 * @param style
	 *            风格
	 * @param image
	 *            背景图片
	 * @param modeName
	 *            背景图片显示模式
	 */
	public ImageComposite(Composite parent, int style, Image image,
			String modeName) {
		super(parent, style);
		addPaintListener(this);
		setBackgroundImage(image);
		setImageDisplayMode(modeName);
	}

	/**
	 * 获取背景图片
	 * 
	 * @return 背景图片
	 * @see org.eclipse.swt.widgets.Control#getBackgroundImage(Image)
	 */
	@Override
	public Image getBackgroundImage() {
		return backgroundImage;
	}

	/**
	 * 设置背景图片
	 * 
	 * @param 背景图片
	 * @see org.eclipse.swt.widgets.Control#setBackgroundImage(Image)
	 */
	@Override
	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
		this.redraw();
	}

	/**
	 * 获取背景图片显示模式
	 * 
	 * @return 显示模式
	 */
	public String getImageDisplayMode() {
		return imageDisplayMode;
	}

	/**
	 * 设置背景图片显示模式
	 * 
	 * @param modeName
	 *            模式名称，取值仅限于ImagePane.TILED ImagePane.SCALED ImagePane.CENTRE
	 */
	public void setImageDisplayMode(String modeName) {
		if (modeName != null) {
			modeName = modeName.trim();
			// 居中
			if (modeName.equalsIgnoreCase(CENTER)) {
				this.imageDisplayMode = CENTER;
				modeIndex = 0;
			}
			// 平铺
			else if (modeName.equalsIgnoreCase(TILED)) {
				this.imageDisplayMode = TILED;
				modeIndex = 1;
			}
			// 拉伸
			else if (modeName.equalsIgnoreCase(SCALED)) {
				this.imageDisplayMode = SCALED;
				modeIndex = 2;
			}
			this.redraw();
		}
	}

	/**
	 * 绘图事件
	 */
	public void paintControl(PaintEvent e) {
		// 如果设置了背景图片则显示
		if (backgroundImage != null) {
			int width = this.getSize().x;
			int height = this.getSize().y;
			int imageWidth = backgroundImage.getImageData().width;
			int imageHeight = backgroundImage.getImageData().height;
			switch (modeIndex) {
			// 居中
			case 0: {
				int x = (width - imageWidth) / 2;
				int y = (height - imageHeight) / 2;
				e.gc.drawImage(backgroundImage, x, y);
				break;
			}
			// 平铺
			case 1: {
				for (int ix = 0; ix < width; ix += imageWidth) {
					for (int iy = 0; iy < height; iy += imageHeight) {
						e.gc.drawImage(backgroundImage, ix, iy);
					}
				}
				break;
			}
			// 拉伸
			case 2: {
				ImageData data = backgroundImage.getImageData().scaledTo(width,
						height);
				e.gc.drawImage(new Image(e.display, data), 0, 0);
				break;
			}
			}
		}
	}
}
