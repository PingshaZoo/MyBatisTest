package firstMyBatis.batis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

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
		for(int i = 1;i<100;i++) {
			City city = sqlSession.selectOne("City.findCityByID", i);
			System.out.println(city.toString());
		}

		sqlSession.close();
		
	}
}
