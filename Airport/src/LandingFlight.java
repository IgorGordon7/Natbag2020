import java.time.LocalTime;
import java.time.LocalDateTime;

public class LandingFlight extends Flight implements Comparable<LandingFlight>{
private String LandedFromCity;
private String LandedFromCountry;


public LandingFlight(String companyFlight, String numFlight, LocalDateTime scheduledTime, int terminal, String LandedFrom,String landedFromCountry,String day) {
	super(companyFlight, numFlight, scheduledTime, terminal,day);
	this.LandedFromCity=LandedFrom;
	this.LandedFromCountry=landedFromCountry;
}
	
public String toString() {
	return super.toString()+"Landed From City:"+LandedFromCity+"\nLanden from Country:"+LandedFromCountry;
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
}
