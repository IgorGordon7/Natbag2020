import java.io.File;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import javax.management.loading.PrivateClassLoader;

import javafx.print.Collation;

public class Airport {
	private String airportName;
	private LandingFlight landing[];
	private int landingCounter;
	private DeparturesFlight departures[];
	private int departuresCounter;
	private int counterDays;

	public Airport(String name) {
		this.airportName = name;
		this.landing = new LandingFlight[1];
		this.landingCounter = 0;
		this.departures = new DeparturesFlight[1];
		this.departuresCounter = 0;
		this.counterDays=1;
	}

	public Airport(Scanner myObj, String name) {
		this.landing = new LandingFlight[1];
		this.landingCounter = 0;
		this.departures = new DeparturesFlight[1];
		this.departuresCounter = 0;
		this.airportName = name;
		while (myObj.hasNextLine()) {
			String companyFlight = myObj.nextLine();
			companyFlight = companyFlight.substring(companyFlight.indexOf("=") + 1);
			String numFlight = myObj.nextLine();
			numFlight = numFlight.substring(numFlight.indexOf("=") + 1);
			String scheduledTime = myObj.nextLine();
			scheduledTime = scheduledTime.substring(scheduledTime.indexOf("=") + 1);
			scheduledTime = scheduledTime.replace("T", "_");
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
			LocalDateTime scheduledTime1 = LocalDateTime.parse(scheduledTime, formatter1);
			String terminal = myObj.nextLine();
			terminal = terminal.substring(terminal.indexOf("=") + 1);
			int terminal1 = Integer.parseInt(terminal);
			String destinationCityTemp = myObj.nextLine();
			String destinationCity = destinationCityTemp.substring(destinationCityTemp.indexOf("=") + 1);
			String destinationCountry = myObj.nextLine();
			destinationCountry = destinationCountry.substring(destinationCountry.indexOf("=") + 1);
			String day = myObj.nextLine();
			day = day.substring(day.indexOf("=") + 1);
			String airport = myObj.nextLine();
			airport = airport.substring(airport.indexOf("=") + 1);
			if (destinationCityTemp.toLowerCase().indexOf("Destination".toLowerCase()) != -1) {
				addDepartureFlight(new DeparturesFlight(companyFlight, numFlight, scheduledTime1, terminal1,
						destinationCity, destinationCountry, day, airport));
			} else {
				addLandingFlight(new LandingFlight(companyFlight, numFlight, scheduledTime1, terminal1, destinationCity,
						destinationCountry, day, airport));
			}

		}

	}

	public void addDepartureFlight(DeparturesFlight departureFlight) {
		if (departures.length == departuresCounter) {
			DeparturesFlight temp[] = Arrays.copyOf(departures, departures.length + 1);
			departures = temp;
		}
		departures[departuresCounter] = departureFlight;
		++departuresCounter;
	}

	public void addLandingFlight(LandingFlight landigFlight) {
		if (landing.length == landingCounter) {
			LandingFlight temp[] = Arrays.copyOf(landing, landing.length + 1);
			landing = temp;
		}
		landing[landingCounter] = landigFlight;
		++landingCounter;
	}

	public String showLandingFlight() {
		StringBuffer sb = new StringBuffer();
		sb.append("View all Landing information:\n");
		Arrays.sort(landing);

		for (int j = 0; j < landingCounter; j++) {
			sb.append(landing[j].toString() + "\n");
		}
		return sb.toString();
	}

	public String showDeparturesFlight() {
		StringBuffer sb = new StringBuffer();
		sb.append("View all Departures information:\n");
		Arrays.sort(departures);

		for (int j = 0; j < departuresCounter; j++) {
			sb.append(departures[j].toString() + "\n");
		}
		return sb.toString();
	}

	public <T> String showDeparturesFlightScheduled(T g1, T g2) {
		StringBuffer sb = new StringBuffer();
		sb.append("View all Scheduled Departures information:\n");
		Arrays.sort(departures);

		if (g1 instanceof java.time.LocalDateTime && g2 instanceof java.time.LocalDateTime) {
			for (int j = 0; j < departuresCounter; j++) {
				if (departures[j].scheduledTime.isAfter((ChronoLocalDateTime<?>) g1)
						&& departures[j].scheduledTime.isBefore((ChronoLocalDateTime<?>) g2)) {
					sb.append(departures[j].toString() + "\n");
				}
			}
		} else if (g2.equals("Destination City")) {
			for (int j = 0; j < departuresCounter; j++) {
				if (departures[j].getDestinationCity().equals(g1)) {
					sb.append(departures[j].toString() + "\n");
				}
			}
		} else if (g2.equals("companyFlight")) {
			for (int j = 0; j < departuresCounter; j++) {
				if (departures[j].companyFlight.equals(g1)) {
					sb.append(departures[j].toString() + "\n");
				}
			}
		} else if (g2.equals("numFlight")) {
			for (int j = 0; j < departuresCounter; j++) {
				if (departures[j].numFlight.equals(g1)) {
					sb.append(departures[j].toString() + "\n");
				}
			}
		} else if (g2.equals("Destination Country")) {
			for (int j = 0; j < departuresCounter; j++) {
				if (departures[j].getDestinationCountry().equals(g1)) {
					sb.append(departures[j].toString() + "\n");
				}
			}
		} else if (g2.equals("Day")) {
			for (int j = 0; j < departuresCounter; j++) {
				if (departures[j].getDepartureDay().equals(g1)) {
					sb.append(departures[j].toString() + "\n");
				}
			}
		}

		return sb.toString();
	}

	public <T> String showLandingFlightScheduled(T g1, T g2) {
		StringBuffer sb = new StringBuffer();
		sb.append("View all Scheduled Landing information:\n");
		Arrays.sort(landing);

		if (g1 instanceof java.time.LocalDateTime && g2 instanceof java.time.LocalDateTime) {
			for (int j = 0; j < landingCounter; j++) {
				if (landing[j].scheduledTime.isAfter((ChronoLocalDateTime<?>) g1)
						&& landing[j].scheduledTime.isBefore((ChronoLocalDateTime<?>) g2)) {
					sb.append(landing[j].toString() + "\n");
				}
			}
		} else if (g2.equals("Landed")) {
			for (int j = 0; j < landingCounter; j++) {
				if (landing[j].getLandedFromCity().equals(g1)) {
					sb.append(landing[j].toString() + "\n");
				}
			}
		} else if (g2.equals("companyFlight")) {
			for (int j = 0; j < landingCounter; j++) {
				if (landing[j].companyFlight.equals(g1)) {
					sb.append(landing[j].toString() + "\n");
				}
			}
		} else if (g2.equals("numFlight")) {
			for (int j = 0; j < landingCounter; j++) {
				if (landing[j].numFlight.equals(g1)) {
					sb.append(landing[j].toString() + "\n");
				}
			}
		} else if (g2.equals("Landing Country")) {
			for (int j = 0; j < landingCounter; j++) {
				if (landing[j].landedFromCountry().equals(g1)) {
					sb.append(landing[j].toString() + "\n");
				}
			}
		} else if (g2.equals("Landing City")) {
			for (int j = 0; j < landingCounter; j++) {
				if (landing[j].getLandedFromCity().equals(g1)) {
					sb.append(landing[j].toString() + "\n");
				}
			}
		} else if (g2.equals("Day")) {
			for (int j = 0; j < landingCounter; j++) {
				if (landing[j].getLandingDay().equals(g1)) {
					sb.append(landing[j].toString() + "\n");
				}
			}
		}
		return sb.toString();
	}

	public String showDeparturesDataToWeb(String[] arg) {
		String[] array = { "html", "departures", "elal", "france", "paris", "CDG", "4", "6", "2020", "31", "7", "2020",
				"true", "false", "false", "false", "false", "false", "false"};
		// סינונים: 1. נחיותות או המראות 2. חברת תעופה 3. מדינה 4. עיר 5.שם שדה תעופה 6/
		// תאריך התחלה 7. תאריך סיום 8. יום בשבוע (בוליאן)
		// prepare filter array

		LocalDateTime startDateTime = null;
		LocalDateTime endDateTime = null;
		int day = 0, month = 0, year = 0, count = 0;
		Vector<DeparturesFlight> temp = new Vector<DeparturesFlight>();
		for (int i = 0; i < departures.length; i++) {
			temp.add(departures[i]);
		}

		for (int i = 2; i < array.length; i++) {
			boolean done = true;
			String tempFilter = array[i];
			if (tempFilter.matches(".*\\d.*")) {
				done = false;
				if (count == 0 || count == 3) {
					day = Integer.parseInt(tempFilter);
				} else if (count == 1 || count == 4) {
					month = Integer.parseInt(tempFilter);
				} else if (count == 2 || count == 5) {
					year = Integer.parseInt(tempFilter);
					if (count == 2) {
						startDateTime = LocalDateTime.of(year, month, day, 00, 00, 00);
					} else {
						endDateTime = LocalDateTime.of(year, month, day, 00, 00, 00);
					}
				}
				++count;
			}

			while (done) {
				temp = filters(array[i], temp);
				done = false;
			}

		}

		temp = checkIfFlightOkByDate(temp, startDateTime, endDateTime);

		return filterdSearch(temp);

	}

	private Vector<DeparturesFlight> checkIfFlightOkByDate(Vector<DeparturesFlight> temp, LocalDateTime startDateTime,
			LocalDateTime endDateTime) {
		for (int j = 0; j < temp.size(); j++) {
			if ((temp.get(j).scheduledTime.isAfter(startDateTime))
					&& (temp.get(j).scheduledTime.isBefore(endDateTime))) {
				continue;
			} else {
				temp.remove(j);
			}
		}
		return temp;
	}

	private String filterdSearch(Vector<DeparturesFlight> filter) {
		StringBuffer p = new StringBuffer();
		for (int i = 0; i < filter.size(); i++) {
			p.append(filter.get(i).toString() + "\n");
		}
		return p.toString();
	}

	private Vector<DeparturesFlight> filters(String string, Vector<DeparturesFlight> temp) {
		Vector<DeparturesFlight> tempfilterd = new Vector<DeparturesFlight>();
		for (int i = 0; i < temp.size(); i++) {

			if (string.equals("true") || string.equals("false")) {
				if (counterDays == 1 && string.equals("true")) {
					string = "Sunday";
				} else if (counterDays == 2 && string.equals("true")) {
					string = "Monday";
				} else if (counterDays == 3 && string.equals("true")) {
					string = "Tuesday";
				} else if (counterDays == 4 && string.equals("true")) {
					string = "Wednesday";
				} else if (counterDays == 5 && string.equals("true")) {
					string = "Thursday";
				} else if (counterDays == 6 && string.equals("true")) {
					string = "Friday";
				} else if (counterDays == 7 && string.equals("true")) {
					string = "Saturday";
				} else {
					string = "NotDay";
				}
				++counterDays;
			}

			if (temp.get(i).toString().toLowerCase().contains(string.toLowerCase())) {
				tempfilterd.add(temp.get(i));
			}
		}
		return tempfilterd;
	}
	
	
	
	

//	public String showLandingDataToWeb(String[] args) {
//		StringBuffer sb = new StringBuffer();
//		for (int j = 0; j < landingCounter; j++) {
//			if(landing[j].toString().toLowerCase().indexOf(args.toLowerCase())!=-1) {
//				sb.append(landing[j].toString() + "\n");
//			}
//		}
//		
//		
//		return sb.toString();
//	}

	@Override
	public String toString() {
		return "Airport" + airportName + "\n landing=" + Arrays.toString(landing) + "\n landingCounter="
				+ landingCounter + "\n departures=" + Arrays.toString(departures) + "\n departuresCounter="
				+ departuresCounter + "\n";
	}

	public int getLandingCounter() {
		return landingCounter;
	}

	public int getDeparturesCounter() {
		return departuresCounter;
	}

	public LandingFlight[] getLanding() {
		return landing;
	}

	public DeparturesFlight[] getDepartures() {
		return departures;
	}
}
