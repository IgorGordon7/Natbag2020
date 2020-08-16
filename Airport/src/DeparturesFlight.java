import java.time.LocalTime;
import java.time.LocalDateTime;


public class DeparturesFlight extends Flight implements Comparable<DeparturesFlight> {
	private String destinationCity;
	private String destinationCountry;

	public DeparturesFlight(String companyFlight, String numFlight, LocalDateTime scheduledTime, int terminal, String destinationCity,String destinationCountry,String day) {
		super(companyFlight, numFlight, scheduledTime, terminal,day);
		this.destinationCity = destinationCity;
		this.destinationCountry= destinationCountry;
	}

	public int compareTo(DeparturesFlight o) {
		return this.scheduledTime.compareTo(o.scheduledTime);
	}
	
	public String toString() {
		return super.toString() + "Destination Country:" + destinationCountry + "\nDestination City: "+destinationCity;

	}
	public String  getDestinationCity() {
		return destinationCity;
	}
	public String  getDestinationCountry() {
		return destinationCountry ;
}
}
