package main;

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

import curd.CityCurd;
import pojo.City;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		CityCurd.tryfindCityCountryLazyLoad("");
	}
}
