import java.io.File;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;



public class Airport {
	private String airportName;
	private LandingFlight landing[];
	private int landingCounter;
	private DeparturesFlight departures[];
	private int departuresCounter;
	private int counterDays;
	private Vector<Flight> tempAfterFilter = new Vector<Flight>();

	public Airport(String name) {
		this.airportName = name;
		this.landing = new LandingFlight[1];
		this.landingCounter = 0;
		this.departures = new DeparturesFlight[1];
		this.departuresCounter = 0;
		this.counterDays=0;
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
				if (departures[j].getDestinationCity().toLowerCase().equals(g1.toString().toLowerCase())) {
					sb.append(departures[j].toString() + "\n");
				}
			}
		} else if (g2.equals("companyFlight")) {
			for (int j = 0; j < departuresCounter; j++) {
				if (departures[j].companyFlight.toLowerCase().equals(g1.toString().toLowerCase())) {
					sb.append(departures[j].toString() + "\n");
				}
			}
		} else if (g2.equals("numFlight")) {
			for (int j = 0; j < departuresCounter; j++) {
				if (departures[j].numFlight.toLowerCase().equals(g1.toString().toLowerCase())) {
					sb.append(departures[j].toString() + "\n");
				}
			}
		} else if (g2.equals("Destination Country")) {
			for (int j = 0; j < departuresCounter; j++) {
				if (departures[j].getDestinationCountry().toLowerCase().equals(g1.toString().toLowerCase())) {
					sb.append(departures[j].toString() + "\n");
				}
			}
		} else if (g2.equals("Day")) {
			for (int j = 0; j < departuresCounter; j++) {
				if (departures[j].getDepartureDay().toLowerCase().equals(g1.toString().toLowerCase())) {
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
				if (landing[j].getLandedFromCity().toLowerCase().equals(g1.toString().toLowerCase())) {
					sb.append(landing[j].toString() + "\n");
				}
			}
		} else if (g2.equals("companyFlight")) {
			for (int j = 0; j < landingCounter; j++) {
				if (landing[j].companyFlight.toLowerCase().equals(g1.toString().toLowerCase())) {
					sb.append(landing[j].toString() + "\n");
				}
			}
		} else if (g2.equals("numFlight")) {
			for (int j = 0; j < landingCounter; j++) {
				if (landing[j].numFlight.toLowerCase().equals(g1.toString().toLowerCase())) {
					sb.append(landing[j].toString() + "\n");
				}
			}
		} else if (g2.equals("Landing Country")) {
			for (int j = 0; j < landingCounter; j++) {
				if (landing[j].landedFromCountry().toLowerCase().equals(g1.toString().toLowerCase())) {
					sb.append(landing[j].toString() + "\n");
				}
			}
		} else if (g2.equals("Landing City")) {
			for (int j = 0; j < landingCounter; j++) {
				if (landing[j].getLandedFromCity().toLowerCase().equals(g1.toString().toLowerCase())) {
					sb.append(landing[j].toString() + "\n");
				}
			}
		} else if (g2.equals("Day")) {
			for (int j = 0; j < landingCounter; j++) {
				if (landing[j].getLandingDay().toLowerCase().equals(g1.toString().toLowerCase())) {
					sb.append(landing[j].toString() + "\n");
				}
			}
		}
		return sb.toString();
	}

	public String showDeparturesDataToWeb(String[] arg) {
		LocalDateTime startDateTime = null;
		LocalDateTime endDateTime = null;
		int day = 0, month = 0, year = 0, count = 0;
		Vector<Flight> temp = new Vector<Flight>();
		for (int i = 0; i < departures.length; i++) {
			temp.add(departures[i]);
		}
		for (int i = 2; i < arg.length; i++) {
			boolean done = true;
			String tempFilter = arg[i];
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
				if (tempFilter.equals("true") || tempFilter.equals("false")) {
					++counterDays;
				}
				temp=(doFilter(arg[i], temp));
				done = false;
			}

		}

		
			tempAfterFilter = checkIfFlightOkByDate(tempAfterFilter, startDateTime, endDateTime);
			Collections.sort(tempAfterFilter);
			String tempReturn =getFinalResult(tempAfterFilter);
			if(!(tempReturn.isEmpty())) {
				return tempReturn;
			}else {
				return "No flight has been found"+"\n<br>";
			}
		
	}
	
	private Vector<Flight> doFilter(String string, Vector<Flight> temp) {
		int i=0;
		boolean enterDays=false;
		String day="";
		for (; i < temp.size(); i++) {

			if (string.equals("true") || string.equals("false")) {
				enterDays=true;
				if (counterDays == 1 && string.equals("true")) {
					day = "Sunday";
				} else if (counterDays == 2 && string.equals("true")) {
					day = "Monday";
				} else if (counterDays == 3 && string.equals("true")) {
					day = "Tuesday";
				} else if (counterDays == 4 && string.equals("true")) {
					day = "Wednesday";
				} else if (counterDays == 5 && string.equals("true")) {
					day = "Thursday";
				} else if (counterDays == 6 && string.equals("true")) {
					day = "Friday";
				} else if (counterDays == 7 && string.equals("true")) {
					day = "Saturday";
				} else {
					day = "NotDay";
				}
				if(temp.get(i).toString().toLowerCase().contains(day.toLowerCase())) {
					tempAfterFilter.add(temp.get(i));
				}
			}
			
			if ((!(temp.get(i).toString().toLowerCase().contains(string.toLowerCase())))&&enterDays==false) {
				temp.remove(temp.get(i));
				i--;
			}
		}
		return temp;
	}

	private Vector<Flight> checkIfFlightOkByDate(Vector<Flight> temp, LocalDateTime startDateTime,
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

	private String getFinalResult(Vector<Flight> filter) {
		StringBuffer p = new StringBuffer();
		for (int i = 0; i < filter.size(); i++) {
			p.append(filter.get(i).toString() + "\n");
			p.append("<br>");
		}
		counterDays=0;
		tempAfterFilter.clear();
		return p.toString();
	}


	public String showLandingDataToWeb(String[] args) {
		LocalDateTime startDateTime = null;
		LocalDateTime endDateTime = null;
		int day = 0, month = 0, year = 0, count = 0;
		Vector<Flight> temp = new Vector<Flight>();
		for (int i = 0; i < landing.length; i++) {
			temp.add(landing[i]);
		}
		for (int i = 2; i < args.length; i++) {
			boolean done = true;
			String tempFilter = args[i];
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
				if (tempFilter.equals("true") || tempFilter.equals("false")) {
					++counterDays;
				}
				temp=(doFilter(args[i], temp));
				done = false;
			}

		}

		
			tempAfterFilter = checkIfFlightOkByDate(tempAfterFilter, startDateTime, endDateTime);
			Collections.sort(tempAfterFilter);
			String tempReturn =getFinalResult(tempAfterFilter);
			if(!(tempReturn.isEmpty())) {
				return tempReturn;
			}else {
				return "No flight has been found"+"\n<br>";
			}
		
	}

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
	public void checkIfFlightsEmpty() throws NoFLightsExeption {
		
		if(getDeparturesCounter()==0||getLandingCounter()==0) {
			throw new NoFLightsExeption();
		}
	}
}
