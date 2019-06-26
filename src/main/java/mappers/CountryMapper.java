package mappers;

import java.util.List;

import pojo.City;
import pojo.Country;

public interface CountryMapper {

	public Country findCountryByCode(String id) throws Exception;
	
	public List<Country> findCountry(String code) throws Exception;
}
