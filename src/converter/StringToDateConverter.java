package converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {
	
	private String datePattern;
	

	public StringToDateConverter(String datePattern) {
		this.datePattern = datePattern;
	}


	@Override
	public Date convert(String s) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
			sdf.setLenient(false);
			return sdf.parse(s);
		} catch (ParseException e) {
			//当使用<form:errors>时，错误信息会展示
			throw new IllegalArgumentException("Invalid date format. Please use this pattern "+datePattern+"");
		}
		
	}

}
