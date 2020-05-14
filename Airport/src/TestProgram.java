import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;


public class TestProgram {

	@Test
	public void statementTest1() {
		Airport natbag = createAirport();
		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("View all Departures information:"+"\n");
		expectedResult.append("Flight: Company Flight="+"El-Al"+"\n");
		expectedResult.append(" Flight Number="+"LY001"+"\n");
		expectedResult.append(" Scheduled Time="+LocalDateTime.of(2020, 05, 20, 00, 45, 00)+"\n");
		expectedResult.append(" Terminal Number="+3+"\n");
		expectedResult.append("Destination="+"New-york"+"\n");
		expectedResult.append("\n");
		expectedResult.append("Flight: Company Flight="+"El-Al"+"\n");
		expectedResult.append(" Flight Number="+"LY315"+"\n");
		expectedResult.append(" Scheduled Time="+LocalDateTime.of(2020, 05, 20, 10, 10, 00)+"\n");
		expectedResult.append(" Terminal Number="+3+"\n");
		expectedResult.append("Destination="+"London"+"\n");
		expectedResult.append("\n");
		assertEquals(expectedResult.toString(), natbag.showDeparturesFlight());
	}

	private Airport createAirport() {
		Airport natbag = new Airport("Natbag");
		LocalDateTime time = LocalDateTime.of(2020, 05, 20, 10, 10, 00);
		natbag.addDepartureFlight(new DeparturesFlight("El-Al", "LY315", time, 3, "London"));
		time = LocalDateTime.of(2020, 05, 20, 00, 45, 00);
		natbag.addDepartureFlight(new DeparturesFlight("El-Al", "LY001", time, 3, "New-york"));
		return natbag;
	}
}
