package DuangFunc;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class token_string {
	
	
	
	public  ArrayList<String> token_string_a(String token_string) {
		   ArrayList<String> arrayList=new ArrayList<String>();
		  String str=token_string;
          StringTokenizer st = new StringTokenizer(str, "_");    // 用逗号分隔
         
           while (st.hasMoreTokens()) {   // 判断是否已经到结尾
          
           arrayList.add(st.nextToken());
         
           }// 打印下一个字段
           return arrayList;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	

}
