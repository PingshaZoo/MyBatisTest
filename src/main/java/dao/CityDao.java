package dao;

import pojo.City;
/*
 * mybatis 可以只写mapper的xml和java文件就可以了，不需要写dao和它的接口实现
 */
public interface CityDao {

	public City selectCity(String id) throws Exception;
	public void insertCity(City city) throws Exception;
	public void deleteCity(String id) throws Exception;
	public void updatename(City city) throws Exception;
}
