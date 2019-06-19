package dao;

import firstMyBatis.batis.po.City;

public interface CityDao {

	public City selectCity(String id) throws Exception;
	public void insertCity(City city) throws Exception;
	public void deleteCity(String id) throws Exception;
	public void updatename(City city) throws Exception;
}
