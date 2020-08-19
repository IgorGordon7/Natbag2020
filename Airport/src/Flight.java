import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class Flight {
	protected String companyFlight;
	protected String numFlight;
	protected LocalDateTime scheduledTime;
	protected int terminal;
	protected String day;

	
	
	public Flight(String companyFlight, String numFlight, LocalDateTime scheduledTime, int terminal) {
		this.companyFlight = companyFlight;
		this.numFlight = numFlight;
		this.scheduledTime=scheduledTime;
		this.terminal = terminal;
		
	}

	@Override
	public String toString() {
		return "Flight: Company Flight=" + companyFlight + "\nFlight Number=" + numFlight + "\nScheduled Time="
				+ scheduledTime + "\nTerminal Number=" + terminal+"\n";
	}

	public String getCompanyFlight() {
		return companyFlight;
	}

	public void setCompanyFlight(String companyFlight) {
		this.companyFlight = companyFlight;
	}

	public String getNumFlight() {
		return numFlight;
	}

	public void setNumFlight(String numFlight) {
		this.numFlight = numFlight;
	}

	public LocalDateTime getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(LocalDateTime scheduledTime) {
	this.scheduledTime=scheduledTime;
	}

	public int getTerminal() {
		return terminal;
	}

	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}

}
