package DuangFunc;

public class Caculate_seat {
	private int colonm=1;
	private int row=0;
	
public String caculate_seat(String seat_serial,int index,int cont){ 
	  
	//  int matrix[][]={};
	  int fence=cont;
	   char seat_seriall[]= seat_serial.toCharArray();
	  for(int i=0;i<index;i++){
		  char temp=seat_seriall[i];
		  if(temp!='0'){
			  row++;			  
			  if(row>fence){			  
				  row=1;
				  colonm++;
				  fence=cont;
			  }
		  }
		  else{			  
			 fence--;
		  }
		  
		 
		  
	  }
	  return "("+colonm+","+row+")";
}
			// TODO Auto-generated method stub
	
		
  
}
