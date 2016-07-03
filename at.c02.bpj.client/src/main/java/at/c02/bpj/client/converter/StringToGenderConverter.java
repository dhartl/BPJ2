package at.c02.bpj.client.converter;

import at.c02.bpj.client.api.model.Gender;
import javafx.util.StringConverter;

public class StringToGenderConverter extends StringConverter<Gender> {

	private static final String MALE = "männlich";
	private static final String FEMALE = "weiblich";

	@Override
	public String toString(Gender gender) {
		switch (gender) {
		case F:
			return FEMALE;
		case M:
			return MALE;
		default:
			break;
		}
		return null;
	}

	@Override
	public Gender fromString(String string) {
		switch (string) {
		case MALE:
			return Gender.M;
		case FEMALE:
			return Gender.F;
		}
		return null;
	}

}
