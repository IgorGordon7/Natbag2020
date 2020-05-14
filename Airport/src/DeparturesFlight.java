import java.time.LocalTime;
import java.time.LocalDateTime;


public class DeparturesFlight extends Flight implements Comparable<DeparturesFlight> {
	private String destination;


	public DeparturesFlight(String companyFlight, String numFlight, LocalDateTime scheduledTime, int terminal, String destination) {
		super(companyFlight, numFlight, scheduledTime, terminal);
		this.destination = destination;
	
	}

	public int compareTo(DeparturesFlight o) {
		return this.scheduledTime.compareTo(o.scheduledTime);
	}
	
	public String toString() {
		return super.toString() + "Destination=" + destination + "\n";

	}
}
