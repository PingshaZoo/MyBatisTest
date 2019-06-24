package curd;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mappers.CityMapper;
import pojo.City;

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

	public static void tryMapper() throws Exception {
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

}
