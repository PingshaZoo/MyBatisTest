package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pojo.City;

/*
 * mybatis 可以只写mapper的xml和java文件就可以了，不需要写dao和它的接口实现
 */
public class CityDaoImpl implements CityDao {

	CityDaoImpl(SqlSessionFactory factory) {
		this.sqlSessionFactory = factory;
	}

	private SqlSessionFactory sqlSessionFactory;

	
	
	public City selectCity(String id) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		City city = sqlSession.selectOne("dao.CityMapper.findCityByID", id);
		sqlSession.close();
		return city;
	}

	public void insertCity(City city) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		sqlSession.insert("dao.CityMapper.insertCity", city);
		sqlSession.commit();
		sqlSession.close();
	}

	public void deleteCity(String id) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		sqlSession.delete("dao.CityMapper.deleteCityById", id);
		sqlSession.commit();
		sqlSession.close();

	}

	public void updatename(City city) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		sqlSession.update("dao.CityMapper.updateCity", city);
		sqlSession.commit();
		sqlSession.close();
	}

}
