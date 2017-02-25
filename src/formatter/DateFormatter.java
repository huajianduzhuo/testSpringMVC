package formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {
	
	private String datePattern;
	private SimpleDateFormat dateFormate;
	private String datePattern2;
	private SimpleDateFormat dateFormate2;
	
	public DateFormatter(String datePattern, String datePattern2){
		this.datePattern = datePattern;
		dateFormate = new SimpleDateFormat(datePattern);
		dateFormate.setLenient(false);
		
		this.datePattern2 = datePattern2;
		dateFormate2 = new SimpleDateFormat(datePattern2);
		dateFormate2.setLenient(false);
	}

	@Override
	public String print(Date arg0, Locale arg1) {
		try {
			return dateFormate.format(arg0);
		} catch (Exception e) {
			try {
				return dateFormate2.format(arg0);
			} catch (Exception e2) {
				e.printStackTrace();
				throw new IllegalArgumentException("日期格式转化为字符串错误.");
			}
		}
	}

	@Override
	public Date parse(String arg0, Locale arg1) throws ParseException {
		try {
			return dateFormate.parse(arg0);
		} catch (ParseException e) {
			try {
				return dateFormate2.parse(arg0);
			} catch (ParseException e2) {
				// the error message will be displayed when using <form:errors>
				throw new IllegalArgumentException("invalid date format. Please use pattern \" " + datePattern + " \" or \" " + datePattern2 + " \"");
			}
		}
	}

}
