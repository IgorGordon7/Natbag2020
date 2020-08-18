import java.time.LocalTime;
import java.time.LocalDateTime;


public class DeparturesFlight extends Flight implements Comparable<DeparturesFlight> {
	private String destinationCity;
	private String destinationCountry;
	private String DepartureAirport;

	public DeparturesFlight(String companyFlight, String numFlight, LocalDateTime scheduledTime, int terminal,
			String destinationCity,String destinationCountry,String day, String DepartureAirport) {
		super(companyFlight, numFlight, scheduledTime, terminal);
		this.destinationCity = destinationCity;
		this.destinationCountry= destinationCountry;
		this.day=day;
		this.DepartureAirport=DepartureAirport;
	}

	public int compareTo(DeparturesFlight o) {
		return this.scheduledTime.compareTo(o.scheduledTime);
	}
	
	public String toString() {
		return super.toString() + "Destination Country=" + destinationCountry + "\nDestination City="+destinationCity +"\nDay=" + day +
	"\nAirport="+DepartureAirport + "\n";

	}
	public String  getDestinationCity() {
		return destinationCity;
	}
	public String  getDestinationCountry() {
		return destinationCountry ;
	}
	
	public String getDepartureDay() {
		return day;
	}
}
