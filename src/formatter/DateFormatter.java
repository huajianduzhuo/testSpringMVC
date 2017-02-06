package formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {
	
	private String datePattern;
	private SimpleDateFormat dateFormate;
	
	public DateFormatter(String datePattern){
		this.datePattern = datePattern;
		dateFormate = new SimpleDateFormat(datePattern);
		dateFormate.setLenient(false);
	}

	@Override
	public String print(Date arg0, Locale arg1) {
		return dateFormate.format(arg0);
	}

	@Override
	public Date parse(String arg0, Locale arg1) throws ParseException {
		try {
			return dateFormate.parse(arg0);
		} catch (ParseException e) {
			// the error message will be displayed when using <form:errors>
			throw new IllegalArgumentException("invalid date format. Please use this pattern \" " + datePattern + " \"");
		}
	}

}
