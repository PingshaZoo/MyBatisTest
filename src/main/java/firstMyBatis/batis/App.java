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
		
		
		
		String resPath = "/SqlMapConfig.xml";
//		File f = new File(resPath);
//		FileInputStream is = new FileInputStream(f);
//		byte b[] = new byte[512];
//		is.read(b, 0, 512);
//		System.out.println(new String(b));
		
		Reader reader = Resources.getResourceAsReader(resPath);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		City city = sqlSession.selectOne("City.findCityByID", 1);
		System.out.println(city.toString());
		sqlSession.close();
		
	}
}
