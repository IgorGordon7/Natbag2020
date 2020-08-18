import java.time.LocalTime;
import java.time.LocalDateTime;

public class LandingFlight extends Flight implements Comparable<LandingFlight> {
	private String LandedFromCity;
	private String LandedFromCountry;
	private String LandingAirport;

	public LandingFlight(String companyFlight, String numFlight, LocalDateTime scheduledTime, int terminal,
			String LandedFrom, String landedFromCountry, String day, String LandingAirport) {
		super(companyFlight, numFlight, scheduledTime, terminal);
		this.LandedFromCity = LandedFrom;
		this.LandedFromCountry = landedFromCountry;
		this.day=day;
		this.LandingAirport=LandingAirport;
	}

	public String toString() {
		return super.toString() + "Landed From City=" + LandedFromCity + "\nLanden from Country=" + LandedFromCountry +"\nDay=" + day +
	"\nAirport="+ LandingAirport +"\n";
	}

	public int compareTo(LandingFlight o) {
		return this.scheduledTime.compareTo(o.scheduledTime);
	}

	public String getLandedFromCity() {
		return LandedFromCity;
	}

	public String landedFromCountry() {
		return LandedFromCountry;
	}
	
	public String getLandingDay() {
		return day;
	}
}
