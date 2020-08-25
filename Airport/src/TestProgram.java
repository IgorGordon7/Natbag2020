import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;


public class TestProgram {

	@Test
	public void statementTest1() {
		Airport natbag = createAirport();
		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("View all Departures information:"+"\n");
		expectedResult.append("Flight: Company Flight="+"elal"+"\n");
		expectedResult.append("Flight Number="+"7890"+"\n");
		expectedResult.append("Scheduled Time="+LocalDateTime.of(2020, 06, 16, 14, 30, 00)+"\n");
		expectedResult.append("Terminal Number="+3+"\n");
		expectedResult.append("Destination Country="+"france"+"\n");
		expectedResult.append("Destination City="+"paris"+"\n");
		expectedResult.append("Day="+"sunday"+"\n");
		expectedResult.append("Airport="+"CDG"+"\n");
		
		expectedResult.append("\n"+"View all Landing information:"+"\n");
		expectedResult.append("Flight: Company Flight="+"elal"+"\n");
		expectedResult.append("Flight Number="+"23010"+"\n");
		expectedResult.append("Scheduled Time="+LocalDateTime.of(2020, 07, 18, 20, 00, 00)+"\n");
		expectedResult.append("Terminal Number="+3+"\n");
		expectedResult.append("Landed From Country="+"england"+"\n");
		expectedResult.append("Landed From City="+"london"+"\n");
		expectedResult.append("Day="+"tuesday"+"\n");
		expectedResult.append("Airport="+"Queen"+"\n"+"\n");

		StringBuffer allFlights=new StringBuffer();
		allFlights.append(natbag.showDeparturesFlight());
		allFlights.append(natbag.showLandingFlight());
		
		
		assertEquals(expectedResult.toString(), allFlights.toString());
	}

	private Airport createAirport() {
		Airport natbag = new Airport("Natbag");
		LocalDateTime time = LocalDateTime.of(2020, 06, 16, 14, 30, 00);
		natbag.addDepartureFlight(new DeparturesFlight("elal", "7890", time, 3, "paris","france","sunday","CDG"));
		time = LocalDateTime.of(2020, 07, 18, 20, 00, 00);
		natbag.addLandingFlight(new LandingFlight("elal", "23010", time, 3, "london","england","tuesday","Queen"));
		return natbag;
		
	}
}
