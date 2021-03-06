import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	private static Airport natbag = new Airport("Natbag");

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Airport natbag = new Airport("Natbag");
		
		System.out.println("Flight Menu:\n" + "1.Add landing flight.\n" + "2.Add departure flight.\n"
				+ "3.View all takeoff details(sorted by date and time).\n"
				+ "4.View all landing information(sorted by date and time).\n"
				+ "5.Saves details of all flights typed into a file.\n"
				+ "6.Loading flight information from an existing file.\n" + "7.Scheduled Time Seacrh.\n"
				+ "8.Exit program.\n" + "Enter your choice");

		boolean again = true;
		while (again) {
			int choice = scan.nextInt();

			switch (choice) {

			case 1:
				System.out.println("Enter Landing Flight Details:\n Enter Company Flight");
				String company1 = scan.next();
				System.out.println("Enter Flight Number");
				String numFlight1 = scan.next();
				System.out.println("Enter ScheduledTime: yyyy-MM-dd_HH:mm");
				String time1 = scan.next();
				DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
				LocalDateTime scheduledTime1 = LocalDateTime.parse(time1, formatter1);
				System.out.println("Enter Terminal Number");
				int terminalNum1 = scan.nextInt();
				System.out.println("Enter Source City:");
				String landedFromCity = scan.next();
				System.out.println("Enter Source Country:");
				String landedFromCountry = scan.next();
				System.out.println("Enter Day Flight:");
				String day = scan.next();
				System.out.println("Enter Airport Name:");
				String LandingAirport = scan.next();
				natbag.addLandingFlight(new LandingFlight(company1, numFlight1, scheduledTime1, terminalNum1,
						landedFromCity, landedFromCountry, day, LandingAirport));
				break;

			case 2:
				System.out.println("Enter Departure Flight Details:\n Enter Company Flight");
				String company2 = scan.next();
				System.out.println("Enter Flight Number");
				String numFlight2 = scan.next();
				System.out.println("Enter scheduledTime: yyyy-MM-dd_HH:mm");
				String time2 = scan.next();
				DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
				LocalDateTime scheduledTime2 = LocalDateTime.parse(time2, formatter2);
				System.out.println("Enter Terminal Number");
				int terminalNum2 = scan.nextInt();
				System.out.println("Enter Destination City:");
				String destinationCity = scan.next();
				System.out.println("Enter Destination Country:");
				String destinationCountry = scan.next();
				System.out.println("Enter Day Flight:");
				day = scan.next();
				System.out.println("Enter Airport Name:");
				String DepartureAirport = scan.next();
				natbag.addDepartureFlight(new DeparturesFlight(company2, numFlight2, scheduledTime2, terminalNum2,
						destinationCity, destinationCountry, day, DepartureAirport));
				break;

			case 3:
				try {
					natbag.checkIfFlightsEmpty();
				
				System.out.println(natbag.showDeparturesFlight());
				break;
				}catch(NoFLightsExeption e) {
					System.out.println(e.getMessage());
				}
				

			case 4:
				try {
					natbag.checkIfFlightsEmpty();
				
				System.out.println(natbag.showLandingFlight());
				break;
				}catch(NoFLightsExeption e) {
					System.out.println(e.getMessage());
				}

			case 5:
				try {
					File f = new File("airport.txt");
					if (f.createNewFile()) {
						System.out.println("File created: " + f.getName());
					} else {
						System.out.println("File already exists.");
					}
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}

				try {
					DeparturesFlight[] temp1 = natbag.getDepartures();
					LandingFlight[] temp2 = natbag.getLanding();
					FileWriter myWriter = new FileWriter("Airport.txt", true);
					for (int i = 0; i < natbag.getDeparturesCounter(); i++) {
						myWriter.write(temp1[i].toString());
					}
					for (int i = 0; i < natbag.getLandingCounter(); i++) {
						myWriter.write(temp2[i].toString());
					}
					myWriter.close();
					System.out.println("Successfully wrote to the file.");
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}

				break;

			case 6:

				try {
					File myObj = new File("airport.txt");
					Scanner myReader = new Scanner(myObj);
					natbag = new Airport(myReader, "natbag");
					myReader.close();
				} catch (FileNotFoundException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}

				break;

			case 7:
				try {
						natbag.checkIfFlightsEmpty();
					System.out.println("1. Search for Departures\n" + "2. Search for Arrivals\n");
					int answer = scan.nextInt();
					switch (answer) {
					case 1:
						System.out.println(
								"1. Search by Flight Number\n" + "2. Search by Flight Company\n" + "3. Search by Date\n"
										+ "4. Search by Destination City\n" + "5. Search by Destination Country\n"
										+ "6. Search by Day\n" + "7. Search by Airport name");
						int answer1 = scan.nextInt();
						switch (answer1) {

						case 1:
							System.out.println("Type the Flight number");
							String flightNum = scan.next();

							System.out.println(natbag.showDeparturesFlightScheduled(flightNum, "numFlight"));
							break;

						case 2:
							System.out.println("Type the Flight Company");
							String flightCompany = scan.next();

							System.out.println(natbag.showDeparturesFlightScheduled(flightCompany, "companyFlight"));
							break;

						case 3:
							System.out.println("Time from: yyyy-MM-dd_HH:mm");
							String timeFrom = scan.next();
							DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
							LocalDateTime scheduledTime3 = LocalDateTime.parse(timeFrom, formatter3);

							System.out.println("Time to: yyyy-MM-dd_HH:mm");
							String timeTo = scan.next();
							DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
							LocalDateTime scheduledTime4 = LocalDateTime.parse(timeTo, formatter4);
							System.out.println(natbag.showDeparturesFlightScheduled(scheduledTime3, scheduledTime4));
							break;
						case 4:
							System.out.println("Type the Destination City");
							String dest = scan.next();
							System.out.println(natbag.showDeparturesFlightScheduled(dest, "Destination City"));
							break;
						case 5:
							System.out.println("Type the Destination Country");
							dest = scan.next();
							System.out.println(natbag.showDeparturesFlightScheduled(dest, "Destination Country"));
							break;
						case 6:
							System.out.println("Type the Day");
							day = scan.next();
							System.out.println(natbag.showDeparturesFlightScheduled(day, "Day"));
							break;
						case 7:
							System.out.println("Type the Airport");
							String airport = scan.next();
							System.out.println(natbag.showDeparturesFlightScheduled(airport, "Airport name"));
							break;
						}

						break;

					case 2:
						System.out.println("1. Search by Flight Number\n" + "2. Search by Flight Company\n"
								+ "3. Search by Date\n" + "4. Search by Landing City\n" + "5. Search by Landing Country"
								+ "6. Search by Day" + "7. Search by Airport name");
						int answer2 = scan.nextInt();
						switch (answer2) {

						case 1:
							System.out.println("Type the Flight number");
							String flightNum = scan.next();
							String numFlight = "numFlight";
							System.out.println(natbag.showLandingFlightScheduled(flightNum, numFlight));
							break;

						case 2:
							System.out.println("Type the Flight Company");
							String flightCompany = scan.next();
							String companyFlight = "companyFlight";
							System.out.println(natbag.showLandingFlightScheduled(flightCompany, companyFlight));
							break;

						case 3:
							System.out.println("Time from: yyyy-MM-dd_HH:mm");
							String timeFrom1 = scan.next();
							DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
							LocalDateTime scheduledTime5 = LocalDateTime.parse(timeFrom1, formatter5);

							System.out.println("Time to: yyyy-MM-dd_HH:mm");
							String timeTo1 = scan.next();
							DateTimeFormatter formatter6 = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
							LocalDateTime scheduledTime6 = LocalDateTime.parse(timeTo1, formatter6);
							System.out.println(natbag.showLandingFlightScheduled(scheduledTime5, scheduledTime6));
							break;
						case 4:
							System.out.println("Type the Landing City");
							String arrivalCity = scan.next();

							System.out.println(natbag.showLandingFlightScheduled(arrivalCity, "Landing City"));
							break;
						case 5:
							System.out.println("Type the Landing Country");
							String arrivalCountry = scan.next();
							System.out.println(natbag.showLandingFlightScheduled(arrivalCountry, "Landing Country"));
							break;
						case 6:
							System.out.println("Type the Day");
							day = scan.next();
							System.out.println(natbag.showLandingFlightScheduled(day, "Day"));
							break;
						case 7:
							System.out.println("Type the Airport");
							String airport = scan.next();
							System.out.println(natbag.showLandingFlightScheduled(airport, "Airport name"));
							break;
						}
						break;

					default:
						break;
					}
					break;
				} catch (NoFLightsExeption e) {
					System.out.println(e.getMessage());
				}
				

			case 8:
				System.out.println("Bye");
				again = false;
				break;

			}
		}
	}

	public void getDataFromWeb(String[] args) {

		if (args.length > 0) {
			try {
				File myObj = new File("airport.txt");
				Scanner myReader = new Scanner(myObj);
				natbag = new Airport(myReader, "natbag");
				myReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
		

			System.out.println("Argument were found are:");
			for (int i = 0; i < args.length; i++) {
				if (args[0].equals("html")) {
					System.out.println("<br>");
					System.out.println(args[i]);
				}else {
					System.out.println();
					System.out.println(args[i]+"\n");
					
				}
			}
			if(args[0].equals("html")) {
				System.out.println("<br>");
				System.out.println("Flights that stands with the conditions are:"+"<br>");
			}else {
				System.out.println("\nFlights that stands with the conditions are:"+"\n");
			}
			if (args[1].equals("departures")) {

				System.out.println(natbag.showDeparturesDataToWeb(args));
			} else if (args[1].equals("arrivals")) {

				System.out.println(natbag.showLandingDataToWeb(args));

			}

	}
}
