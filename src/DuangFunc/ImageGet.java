/*
 * 通过URI将远程图片保存至本地
 * */
package DuangFunc;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

//	根据图片路径获取图片
public class ImageGet {
	//	传入图片的地址
	public void getImage(String[] ImageUri){
		DataInputStream di = null;
	    FileOutputStream fo = null;
	    byte[] b = new byte[1];      
	    try {
	       // input
	       URL url = new URL(ImageUri[0]);
	       URLConnection urlConnection = url.openConnection();
	       urlConnection.connect();
	       di = new DataInputStream(urlConnection.getInputStream());

	       // output
	       fo = new FileOutputStream(ImageUri[1]);

	       //  copy the actual file
	       //   (it would better to use a buffer bigger than this)
	       while(-1 != di.read(b,0,1))  {
	         fo.write(b,0,1);
	       }
	       di.close();  
	       fo.close();               
	     }
	     catch (Exception ex) {
	         ex.printStackTrace();
	         System.exit(1);
	     }
	     System.out.println("done.");  
	   }
	}

