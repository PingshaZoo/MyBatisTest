package mappers;

import pojo.Country;
import pojo.CountryLanguage;

public interface CountryLanguageMapper {
	public CountryLanguage findCountryLanguageByCountryCode(String countryCode) throws Exception;
}
