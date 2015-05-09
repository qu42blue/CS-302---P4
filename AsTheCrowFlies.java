///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 4
// Files:            AsTheCrowFiles.java
//					 City.java
//					 CityController.java
//
// Semester:         CS302 Spring 2015
//
// Author:           QuHarrison Terry
// Email:            qterry@wisc.edu
// CS Login:         quharrison
// Lecturer's Name:  Deb Deppeler
// Lab Section:      321
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Yao Yao
// Email:            yyao39@wisc.edu
// CS Login:         yyao
// Lecturer's Name:  Deb Deppeler
// Lab Section:      325
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main program interface
 * 
 * @author QuHarrison Terry, Yao Yao
 */
public class AsTheCrowFlies {
	
	// single Scanner instance
	private static Scanner scanner = new Scanner(System.in);
	/**
	 * Main method
	 * Prompt user to designate file directory
	 * Read file that contain city information
	 * Create trip information
	 * You can also directly put cities to the trip
	 * @param args
	 */
	public static void main(String[] args) {
		// main title
		System.out.println("As The Crow Flies");
		// initialize city controller
		CityController cityController = new CityController();
		
		// create infinite loop 
		while(true) {
			// showing menu
			System.out.println();
			System.out.println("1. Load available cities from a file");
			System.out.println("2. Display available cities");
			System.out.println("3. Create a trip");
			System.out.println("4. Add a city to available cities");
			System.out.println("5. Exit program");
			// enter choice
			System.out.print("Enter choice as integer [1-5]: ");
			// try to convert input line to integer
			int command = 0;
			while(true) {
				boolean exitSecond = false;
				try {
					// try to convert input string to integer
					command = Integer.parseInt(scanner.nextLine());
					exitSecond = true;
					// if it fails it will throw NumberFormatException
				} catch(NumberFormatException exp) {
					System.out.println("Invalid input. Try again.");
					System.out.print("Enter choice as integer [1-5]: ");
					exitSecond = false;
				}
				if(exitSecond) {
					break;
				}
			}
			boolean exit = false;
			// using switch command
			switch(command) {
			// Load available cities from a file
			case 1:
				System.out.print("Enter the filename: ");
				// check for entered filename 
				boolean correctFilename = true;
				
				String filename = scanner.nextLine();
				try {
					cityController.resetNumberOfAddedCities();
					// try to read file
					Scanner fileReader = new Scanner(new File(filename));
					// read line by line
					while(fileReader.hasNextLine()) {
						// call method to parse line into City
						cityController.parseCityFromLine
						(fileReader.nextLine());
					}
					fileReader.close();
				} catch (IOException e) {
					correctFilename = false;
				}				
				if(!correctFilename) {
					System.out.println("Unable to read file");
					System.out.println(cityController.getNumberOfAddedCities()
							+" cities added");
					
				} else {
					System.out.println(cityController.getNumberOfAddedCities() 
							+" cities added");
				}
				break;
			// Display available cities
			case 2:
				String print = cityController.toString();
				if(!print.isEmpty()) {
					System.out.println(print);
				}
				break;
				
			// Create a trip
			case 3:
				System.out.println
				("There are "+cityController.getAvailableCities().size()+
						" cities to choose from.");
				if(cityController.getAvailableCities().size() < 2) {
					System.out.println("Must have at least 2 cities to"
							+ " choose from.");
				} else {

					if(cityController.getTripCities()==null) {
						cityController.initTripCities();
						System.out.println("New trip created, needs at "
								+ "least two cities.");
					} else {
						System.out.print("Add to current trip (y/n)? ");
						String currentLine = scanner.nextLine();
						if(!currentLine.equals("y")) {
							cityController.getTripCities().clear();
						}
					}
					// proceed with creating trip
					while(true) {
						System.out.print("Enter next city name "
								+ "(or enter to end): ");
						String currentLine = scanner.nextLine();
						if(currentLine.isEmpty()) {
							if(cityController.getTripCities().size() < 2) {
								System.out.println("Must have at least 2 "
										+ "cities in a trip.");
								break;
							}
							
							StringBuilder fileOutput = new StringBuilder();
							String tripCity = "There are "+
							cityController.getTripCities().size()+
							" cities in this trip.";
							System.out.println(tripCity);
							fileOutput.append(tripCity);
							fileOutput.append(System.getProperty
									("line.separator"));
							// calculate distance between cities
							cityController.initTotalDistance();
							for(int i = 0; i < cityController.
									getTripCities().size(); i++) {
								int secondIndex = i+1;
								if(i == cityController.
										getTripCities().size()-1) {
									secondIndex = 0;
								}
								String distanceString = 
										cityController.calculateDistance
										(cityController.getTripCities().get(i),
												cityController.
												getTripCities().
												get(secondIndex));
								System.out.println(distanceString);
								fileOutput.append(distanceString);
								fileOutput.append(System.
										getProperty("line.separator"));
							}
							String totalDistanceString = "Total Distance: "
							+cityController.getTotalDistanceInMeters()+
							" meters (~"+cityController.
							getTotalDistanceInMiles()+" miles)";
							System.out.println(totalDistanceString);
							fileOutput.append(totalDistanceString);
							fileOutput.append(System.getProperty
									("line.separator"));
							
							System.out.print
							("Write trip details to file (y/n)? ");
							String line = scanner.nextLine();
							if(line.equals("y")) {
								System.out.print("Enter filename: ");
								String tripFilename = scanner.nextLine();
								cityController.
								writeTripInformationToFile
								(tripFilename, fileOutput.toString());
							}
								
							break;
						}
						// get city name from available cities 
						// and add it to trip cities
						for(City city : cityController.getAvailableCities()) {
							if(city.getCityName().equals
									(currentLine.toUpperCase())) {
								cityController.getTripCities().add(city);
							}
						}
					}
				}
				
				break;
				
			// Add a city to available cities
			case 4:
				System.out.print("Enter state name: ");
				String stateName = scanner.nextLine();
				System.out.print("Enter city name: ");
				String cityName = scanner.nextLine();
				
				Double latitude = null;
				// enter latitude
				while(true) {
					System.out.print("Enter latitude as double "
							+ "(-90.0 to 90.0): ");
					String latitudeString = scanner.nextLine();	
					try {
						latitude = Double.parseDouble(latitudeString);
					} catch(NumberFormatException exp) {
						System.out.println("Invalid input. Try again.");
						continue;
					}
					// latitude must be between -90 and 90 INCLUSIVE
					if(latitude.doubleValue() < -90 || 
							latitude.doubleValue() > 90) {
						System.out.println("Invalid input. Try again.");
						continue;
					}
					break;
				}
				
				Double longitude = null;
				// enter longitude
				while(true) {
					System.out.print("Enter longitude as double "
							+ "(-180.0 to 180.0): ");
					String longitudeString = scanner.nextLine();	
					try {
						longitude = Double.parseDouble(longitudeString);
					} catch(NumberFormatException exp) {
						System.out.println("Invalid input. Try again.");
						continue;
					}
					// longitude must be between -180 and 180 INCLUSIVE
					if(longitude.doubleValue() < -180 || 
							latitude.doubleValue() > 180) {
						System.out.println("Invalid input. Try again.");
						continue;
					}
					break;
				}

				City addedCity = new City(stateName, cityName, 
						latitude, longitude);
				cityController.addCity(addedCity);
				System.out.println("Added: "+addedCity.toString());
				break;
				
			// Exit program
			case 5:
				System.out.println("Thank you for your business.");
				if(!cityController.getAvailableCities().isEmpty()) {
					if(cityController.writeAvailableCitiesToFile()) {
						System.out.print("Saved available cities to "
								+ "available_cities.txt");
					} else {
						System.out.print("Failed to write available "
								+ "cities to available_cities.txt");
					}
				}
				
				exit = true;
				break;
			default:
				System.out.println("Invalid input. Try again.");
				continue;
			}
			
			// if exit program is entered
			if(exit) {
				break;
			}
		}
		
	}
}
