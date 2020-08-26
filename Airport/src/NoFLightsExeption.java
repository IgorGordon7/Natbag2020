
public class NoFLightsExeption extends Exception {

	public NoFLightsExeption() {
		super("There are no flights in the database, you must add flights first or upload from the file.");
	}
}
