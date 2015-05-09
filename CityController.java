///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AsTheCrowFlies.java
// File:             CityController.java
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * City controller class to manage operation on City class
 * adding available city
 * create trip
 * @author QuHarrison Terry, Yao Yao
 */
public class CityController {

	// earth radius
	private static final double R = 6372.8; // In kilometers
	private static final double METERS_TO_MILES = 0.000621371;
	
	private List<City> availableCities;
	private List<City> tripCities;
	private int numberOfAddedCities = 0;
	private double totalDistance;
	
	/**
	 * Constructs an instance with available cities
	 */
	public CityController() {
		availableCities = new ArrayList<City>();
	}

	/**
	 * Add new city into potential city list
	 */
	public void addCity(City city) {
		availableCities.add(city);
	}
	
	// Accessor
	public List<City> getAvailableCities() {
		return availableCities;
	}

	/**
	 * Parse city information from file strings 
	 * @param line the string that contains city information
	 */
	public void parseCityFromLine(String line) {
		// first split line by ,
		String[] tokens = line.split(",");
		// if there is no four component skip line
		if(tokens.length != 4) {
			return;
		}
		// get state name at first place
		String stateName = tokens[0];
		// second place is city name
		String cityName = tokens[1];
		
		// latitude
		Double latitude = null;
		try {
			latitude = Double.parseDouble(tokens[2]);
		} catch(NumberFormatException exp) {
			latitude = null;
		}
		// if latitude is wrong skip
		if(latitude == null) {
			return;
		}
		// latitude must be between -90 and 90 INCLUSIVE
		if(latitude.doubleValue() < -90 || latitude.doubleValue() > 90) {
			return;
		}

		// longitude
		Double longitude = null;
		try {
			longitude = Double.parseDouble(tokens[3]);
		} catch(NumberFormatException exp) {
			longitude = null;
		}
		// if longitude is wrong skip
		if(longitude == null) {
			return;
		}
		// longitude must be between -180 and 180 INCLUSIVE
		if(longitude.doubleValue() < -180 || longitude.doubleValue() > 180) {
			return;
		}		
		
		// if data are ok add city to available cities
		addCity(new City(stateName, cityName, latitude, longitude));
		numberOfAddedCities++;
	}

	/**
	 * Return true if succeed in saving
	 * available cities information
	 */
	public boolean writeAvailableCitiesToFile() {
		boolean write = true;
		// first create string to write
		StringBuilder stringBuilder = new StringBuilder();
		for(City city : getAvailableCities()) {
			stringBuilder.append(city.toString());
			stringBuilder.append(System.getProperty("line.separator"));
		}
		
		try {
            BufferedWriter output = new BufferedWriter
            		(new FileWriter(new File("available_cities.txt")));
            output.write(stringBuilder.toString());
            output.close();
        } catch ( IOException e ) {
        	write = false;
        }
		return write;
	}
	
	/**
	 * Return true if succeed in saving trip information
	 */
	public boolean writeTripInformationToFile(String filename, 
			String tripInformation) {
		boolean write = true;
		filename = filename.replaceAll(" ", "_");
		
		try {
            BufferedWriter output = new BufferedWriter
            		(new FileWriter(new File(filename)));
            output.write(tripInformation);
            output.close();
        } catch ( IOException e ) {
        	write = false;
        }
		return write;
	}
	
	/**
	 * Calculate distance between two cities
	 * @param source the city to start from
	 * @param distination the city where the trip ends
	 */
	 public String calculateDistance(City source, City destination) {
        double dLat = Math.toRadians(destination.getLatitude() 
        		- source.getLatitude());
        double dLon = Math.toRadians(destination.getLongitude()
        		- source.getLongitude());
        double lat1 = Math.toRadians(source.getLatitude());
        double lat2 = Math.toRadians(destination.getLatitude());
 
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        		Math.sin(dLon / 2) * Math.sin(dLon / 2) *
        		Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double distance = R * c * 1000;
     
        totalDistance += distance;
        
        int integerDistance = (int)distance;
        double miles = distance*METERS_TO_MILES;
        int integerMiles = (int)miles;
        
        return source.getCityName()+" to "+
        destination.getCityName()+" as the crow flies is about "+
        integerDistance+" meters (~"+integerMiles+" miles)";
        
    }
	
	public void resetNumberOfAddedCities() {
		numberOfAddedCities = 0;
	}
	
	public void initTripCities() {
		tripCities = new ArrayList<City>();
	}
	
	public int getNumberOfAddedCities() {
		return numberOfAddedCities;
	}

	public List<City> getTripCities() {
		return tripCities;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < getAvailableCities().size(); i++) {
			stringBuilder.append(getAvailableCities().get(i).toString());
			if(i != getAvailableCities().size() - 1) {
				stringBuilder.append(System.getProperty("line.separator"));
			}
		}
		return stringBuilder.toString();
	}

	// Accessors
	public double getTotalDistance() {
		return totalDistance;
	}
	
	public int getTotalDistanceInMeters() {
		double totalDistanceInMeters = totalDistance;
		return (int)totalDistanceInMeters;
	}
	public int getTotalDistanceInMiles() {
		double totalDistanceInMiles = totalDistance*METERS_TO_MILES;
		return (int)totalDistanceInMiles;
	}

	public void initTotalDistance() {
		totalDistance = 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((availableCities == null) ? 0 : 
					availableCities.hashCode());
		return result;
	}

	/**
	 * 
	 * @param obj another object
	 * @return whether both obj have same non-empty content
	 * In particular, do they have same available city list?
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityController other = (CityController) obj;
		if (availableCities == null) {
			if (other.availableCities != null)
				return false;
		} else if (!availableCities.equals(other.availableCities))
			return false;
		return true;
	}
}
