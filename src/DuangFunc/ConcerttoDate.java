package DuangFunc;

import java.text.ParseException;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

public class ConcerttoDate {

	
	public Date converttoDate(int year,int month,int day){
        try {
            String str =""+year+"-"+month+"-"+day;
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            
            Date d = (Date) f.parse(str);
            
            return d;
        } catch (ParseException e) {            e.printStackTrace();
        }
		return null;
		
	}
	public Date converttoTime(int hour,int minutes,int second){
        try {
            String str =""+hour+"-"+minutes+"-"+second;
            SimpleDateFormat f = new SimpleDateFormat("hh-MM-ss");
            
            Date d = (Date) f.parse(str);
            
            return d;
        } catch (ParseException e) {        
        	e.printStackTrace();
        }
		return null;
		
	}
	public Date converttoTimeAndDate(int year,int month,int day,int hour,int minutes,int second){
        try {
            String str =""+year+"-"+month+"-"+day+"-"+hour+"-"+minutes+"-"+second;
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd-hh-MM-ss");
            
            Date d = (Date) f.parse(str);
            
            return d;
        } catch (ParseException e) {        
        	e.printStackTrace();
        }
		return null;
		
	}
	
}
