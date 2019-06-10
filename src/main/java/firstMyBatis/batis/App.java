package firstMyBatis.batis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import firstMyBatis.batis.po.City;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("user.dir"));

		String resPath = "SqlMapConfig.xml";

		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		City city = sqlSession.selectOne("City.findCityByID", 1);
		System.out.println(city.toString());

		List<City> citys = sqlSession.selectList("City.findCityByName", "foshan");
		System.out.println(citys.get(0).toString());

		city = new City();
		city.setId("65535");
		city.setCountryCode("CHN");
		city.setDistrict("FOSHAN");
		city.setName("PingSha");
		city.setPopulation("100000");
		sqlSession.insert("City.insertCity", city);
		sqlSession.commit();

		city = sqlSession.selectOne("City.findCityByID", 65535);
		System.out.println(city.toString());
		
		
		sqlSession.close();

	}
}
