package mappers;

import java.util.List;

import pojo.Country;
import pojo.CountryLanguage;

public interface CountryLanguageMapper {
	public List<CountryLanguage> findCountryLanguageByCountryCode(String countryCode) throws Exception;
}
