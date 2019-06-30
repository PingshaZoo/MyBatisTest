package curd;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mappers.CityMapper;
import mappers.CountryLanguageMapper;
import mappers.CountryMapper;
import pojo.City;
import pojo.Country;
import pojo.CountryLanguage;

public class CityCurd {

	public static List<City> selectList() throws Exception {
		String resPath = "SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<City> citys = sqlSession.selectList("dao.CityMapper.findCityByName", "pingsha");
//		List<City> citys = sqlSession.selectList("City.findCityByName", "foshan");
		for (City city : citys) {
			System.out.println(city.toString());
		}

		sqlSession.close();
		return citys;
	}

	public static City selectOne() throws Exception {
		String resPath = "SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		City city = sqlSession.selectOne("dao.CityMapper.findCityByID", "1");
		System.out.println(city.toString());
		sqlSession.close();
		return city;

	}

	public static void insertOne() throws Exception {
		String resPath = "SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		City city = new City();
//		city.setId("65535"); // id 可以是这里赋值，也可以通过自增主键的方式插入
		city.setCountryCode("CHN");
		city.setDistrict("FOSHAN");
		city.setName("PingSha");
		city.setPopulation("100000");
		sqlSession.insert("dao.CityMapper.insertCity", city);
		sqlSession.commit();
		System.out.println(city.toString());
		sqlSession.close();

	}

	public static void deleteCity() throws Exception {
		String resPath = "SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("dao.CityMapper.deleteCityById", "333");
		sqlSession.commit();
		sqlSession.close();
	}

	public static void updateCity() throws Exception {
		City city = selectOne();
		String resPath = "SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		city.setPopulation("147258");
		sqlSession.update("dao.CityMapper.updateCity", city);
		sqlSession.commit();
		sqlSession.close();
		selectOne();
	}

	public static void tryMapperInterfaceGenerator() throws Exception {
		String resPath = "SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
		List<City> citys = cityMapper.findCityByName("los");
		for (City city : citys) {
			System.out.println(city.toString());
		}

	}

	public static void tryFindCitysByCity() throws Exception {
		String resPath = "SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
		City city = null;
//		city.setId("23");
//		city.setCountryCode("NLD");
		List<City> citys = cityMapper.findCitysByCity(city);

		for (City c : citys) {
			System.out.println(c.toString());
		}

	}

	/*
	 * 动态sql的拼接 foreach遍历参数
	 */
	public static void tryFindCitysByIds() throws Exception {
		String resPath = "SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
		ArrayList<String> ids = new ArrayList<String>();
		ids.add("66");
		ids.add("666");
		List<City> citys = cityMapper.findCitysByIds(ids);
		for (City c : citys) {
			System.out.println(c.toString());

		}
		System.out.println(citys.size());
	}

	public static void tryMapperfindCountryByCode() throws Exception {
		String resPath = "SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
		Country country = countryMapper.findCountryByCode("CHN");
		System.out.println(country.toString());

	}

	/*
	 * 一个国家有多个语言和多个城市   暂时不使用LazyLoad
	 */
	public static void tryMapperfindCountry(String code) throws Exception {
		String resPath = "SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
		List<Country> countries = countryMapper.findCountry(code);
		System.out.println(countries.size());
		for(Country country:countries) {
			System.out.println("Countries name = " + country.getName() + "  citys size = " + country.citys.size());
			for (City c : country.citys) {
				System.out.println(c.name);
			}
			System.out.println("Countries name = " + country.getName() + "  language size = " + country.countryLanguages.size());
			for (CountryLanguage lang : country.countryLanguages) {
				System.out.println(lang.language);
			}
		}
	}

	public static void tryFindCountryLanguageByCountryCode(String code) throws Exception {
		String resPath = "SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		CountryLanguageMapper countryLanguageMapper = sqlSession.getMapper(CountryLanguageMapper.class);
		List<CountryLanguage> countryLanguage = countryLanguageMapper.findCountryLanguageByCountryCode(code);
		for (CountryLanguage c : countryLanguage) {
			System.out.println(c.language);
		}
	}
	
	
	public static void tryfindCityCountryLazyLoad(String id) throws Exception {
		String resPath = "SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
		List<City> citys = cityMapper.findCityCountryLazyLoad(id);
		System.out.println(citys.size());
		for (City c : citys) {
			System.out.println(c.name);
		}
	}

}
