package dao;

import java.util.List;

import firstMyBatis.batis.po.City;



/*
 * 使用CityMapper，mybatis自动生成代理对象CityMapper现实了该接口，不再需要写DaoImpl实现类 和Dao接口！
 */
public interface CityMapper {


	public City findCityByID(String id) throws Exception;
	public List<City> findCityByName(String name) throws Exception;
	public void insertCity(City city) throws Exception;
	public void deleteCityById(String id) throws Exception;
	public void updateCity(City city) throws Exception;
	
}
