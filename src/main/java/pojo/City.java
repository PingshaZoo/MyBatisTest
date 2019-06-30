package pojo;

import java.io.Serializable;

public class City implements Serializable{

	public String id;
	public String name;
	public String countryCode;
	public String district;
	public String population;
	public Country country;

	public City() {
	};

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id + "  " + this.name + "  " + this.countryCode + "  " + this.district + "  " + this.population;
	}

}
