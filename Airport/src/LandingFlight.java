import java.time.LocalTime;
import java.time.LocalDateTime;

public class LandingFlight extends Flight implements Comparable<LandingFlight>{
private String LandedFrom;



public LandingFlight(String companyFlight, String numFlight, LocalDateTime scheduledTime, int terminal, String LandedFrom) {
	super(companyFlight, numFlight, scheduledTime, terminal);
	this.LandedFrom=LandedFrom;
}
	
public String toString() {
	return super.toString()+"Landed From="+LandedFrom+"\n";
}


public int compareTo(LandingFlight o) {
	return this.scheduledTime.compareTo(o.scheduledTime);
}
	
}
