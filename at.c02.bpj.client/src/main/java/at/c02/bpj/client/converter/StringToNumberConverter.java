package at.c02.bpj.client.converter;

import java.text.NumberFormat;
import java.text.ParseException;

import com.google.common.base.Strings;

import javafx.util.StringConverter;

public class StringToNumberConverter<T extends Number> extends StringConverter<T> {

	private NumberFormat format;

	public StringToNumberConverter(NumberFormat format) {
		this.format = format;
	}

	@Override
	public String toString(T object) {
		if (object == null) {
			return null;
		}
		return format.format(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T fromString(String string) {
		if (Strings.isNullOrEmpty(string)) {
			return null;
		}
		try {
			return (T) format.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
