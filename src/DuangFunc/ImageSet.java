package DuangFunc;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;

public class ImageSet {
	// 图片自定义
	public void setImage(Label label, Image image)
	{
		if (image != null) {
			int width = label.getSize().x;
			int height = label.getSize().y;
			int imageWidth = image.getImageData().width;
			int imageHeight = image.getImageData().height;
			
	}
		else {
			System.out.println("您的图片不存在");
		}

}
}
