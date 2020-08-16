import java.io.File;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

import javax.management.loading.PrivateClassLoader;

public class Airport {
private String airportName;
private LandingFlight landing[];
private int landingCounter;
private DeparturesFlight departures[];
private String country;
private int departuresCounter;
	
public Airport(String name) {
this.airportName=name;
this.landing=new LandingFlight[1];
this.landingCounter=0;
this.departures=new DeparturesFlight[1];
this.departuresCounter=0;
}

public Airport(Scanner myObj,String name) {
	this.landing=new LandingFlight[1];
	this.landingCounter=0;
	this.departures=new DeparturesFlight[1];
	this.departuresCounter=0;
	this.airportName=name;
	while (myObj.hasNextLine()) {
		String companyFlight = myObj.nextLine();
		companyFlight=companyFlight.substring(companyFlight.indexOf("=")+1);
		String numFlight=myObj.nextLine();
		numFlight=numFlight.substring(numFlight.indexOf("=")+1);
		String scheduledTime=myObj.nextLine();
		scheduledTime=scheduledTime.substring(scheduledTime.indexOf("=")+1);
		scheduledTime=scheduledTime.replace("T", "_");
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
		LocalDateTime scheduledTime1 = LocalDateTime.parse(scheduledTime, formatter1);
		String terminal =myObj.nextLine();
		terminal=terminal.substring(terminal.indexOf("=")+1);
		int terminal1=Integer.parseInt(terminal);
		String destinationTemp=myObj.nextLine();
		String destination=destinationTemp.substring(destinationTemp.indexOf("=")+1);
		
		if(destinationTemp.substring(0, destinationTemp.indexOf("=")).equals("Destination")) {
			addDepartureFlight(new DeparturesFlight(companyFlight,numFlight, scheduledTime1, terminal1,destination));
		}else {
			addLandingFlight(new LandingFlight(companyFlight,numFlight, scheduledTime1, terminal1,destination));
		}
	
	
	}

}


public void addDepartureFlight(DeparturesFlight departureFlight) {
	if(departures.length==departuresCounter) {
		DeparturesFlight temp[]=Arrays.copyOf(departures, departures.length+1);
		departures=temp;
	}
	departures[departuresCounter]=departureFlight;
	++departuresCounter;
}

public void addLandingFlight(LandingFlight landigFlight) {
	if(landing.length==landingCounter) {
		LandingFlight temp[]=Arrays.copyOf(landing, landing.length+1);
		landing=temp;
	}
	landing[landingCounter]=landigFlight;
	++landingCounter;
}

public String showLandingFlight() {
	StringBuffer sb= new StringBuffer();
	sb.append("View all Landing information:\n");
	Arrays.sort(landing);
	
	for (int j = 0; j < landingCounter; j++) {
	sb.append(landing[j].toString()+"\n");
	}
	return sb.toString();
}

public String showDeparturesFlight() {
	StringBuffer sb= new StringBuffer();
	sb.append("View all Departures information:\n");
	Arrays.sort(departures);
	
	for (int j = 0; j < departuresCounter; j++) {
	sb.append(departures[j].toString()+"\n");
	}
	return sb.toString();
}

public <T> String showDeparturesFlightScheduled(T g1, T g2) {
	StringBuffer sb= new StringBuffer();
	sb.append("View all Scheduled Departures information:\n");
	Arrays.sort(departures);
	
	if(g1 instanceof java.time.LocalDateTime && g2 instanceof java.time.LocalDateTime) {
		for (int j = 0; j < departuresCounter; j++) {
			if(departures[j].scheduledTime.isAfter((ChronoLocalDateTime<?>) g1)&&departures[j].scheduledTime.isBefore((ChronoLocalDateTime<?>) g2)) {
				sb.append(departures[j].toString()+"\n");
				}
			}
	}else if(g2.equals("Destination")) {
		for (int j = 0; j < departuresCounter; j++) {
			if(departures[j].getDestination().equals(g1)) {
				sb.append(departures[j].toString()+"\n");
				}
			}
	}else if(g2.equals("companyFlight")) {
		for (int j = 0; j < departuresCounter; j++) {
			if(departures[j].companyFlight.equals(g1)) {
				sb.append(departures[j].toString()+"\n");
				}
			}
	}else if(g2.equals("numFlight")) {
		for (int j = 0; j < departuresCounter; j++) {
			if(departures[j].numFlight.equals(g1)) {
				sb.append(departures[j].toString()+"\n");
				}
			}
	}
	
	
	return sb.toString();
}

public <T> String showLandingFlightScheduled(T g1, T g2) {
	StringBuffer sb= new StringBuffer();
	sb.append("View all Scheduled Landing information:\n");
	Arrays.sort(landing);
	
	if(g1 instanceof java.time.LocalDateTime && g2 instanceof java.time.LocalDateTime) {
		for (int j = 0; j < landingCounter; j++) {
			if(landing[j].scheduledTime.isAfter((ChronoLocalDateTime<?>) g1)&&landing[j].scheduledTime.isBefore((ChronoLocalDateTime<?>) g2)) {
				sb.append(landing[j].toString()+"\n");
				}
			}
	}else if(g2.equals("Landed")) {
		for (int j = 0; j < landingCounter; j++) {
			if(landing[j].getLandedFrom().equals(g1)) {
				sb.append(landing[j].toString()+"\n");
				}
			}
	}else if(g2.equals("companyFlight")) {
		for (int j = 0; j < landingCounter; j++) {
			if(landing[j].companyFlight.equals(g1)) {
				sb.append(landing[j].toString()+"\n");
				}
			}
	}else if(g2.equals("numFlight")) {
		for (int j = 0; j < landingCounter; j++) {
			if(landing[j].numFlight.equals(g1)) {
				sb.append(landing[j].toString()+"\n");
				}
			}
	}
	return sb.toString();
}

@Override
public String toString() {
	return "Airport" + airportName + "\n landing=" + Arrays.toString(landing) + "\n landingCounter="
			+ landingCounter + "\n departures=" + Arrays.toString(departures) + "\n departuresCounter="
			+ departuresCounter+"\n";
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