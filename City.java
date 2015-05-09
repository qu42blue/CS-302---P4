///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AsTheCrowFiles.java
// File:             City.java
// Semester:         CS302 Spring 2015
//
// Author:           QuHarrison Terry
// CS Login:         quharrison
// Lecturer's Name:  Deb Deppeler
// Lab Section:      321
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Yao Yao
// Email:            yyao39@wisc.edu
// CS Login:         yyao
// Lecturer's Name:  Deb Deppeler
// Lab Section:      325
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * City class to hold objects from file
 * @author QuHarrison Terry, Yao Yao
 */
public class City {
	
	private String stateName;
	private String cityName;
	private double latitude;
	private double longitude;
	
	
	/**
	 * Constructs an instance with state name, city name
	 * latitude and longitude value
	 * @param stateName The name of the state.
	 * @param cityName the name of the city.
	 * @param latitude the latitude value
	 * @param longitude the longitude value
	 */
	public City(String stateName, String cityName, 
			double latitude, double longitude) {
		this.stateName = stateName.toUpperCase();
		this.cityName = cityName.toUpperCase();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	// Accessors
	public String getStateName() {
		return stateName;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	@Override
	public String toString() {
		return stateName+","+cityName+","+latitude+","+longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cityName == null) ? 0 : cityName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((stateName == null) ? 0 : stateName.hashCode());
		return result;
	}

	/**
	 * 
	 * @param obj another object
	 * @return whether Object obj is a city and this city
	 * has the same name, state, longitude and latitude
	 * as the other one
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
			return false;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		if (stateName == null) {
			if (other.stateName != null)
				return false;
		} else if (!stateName.equals(other.stateName))
			return false;
		return true;
	}
}
