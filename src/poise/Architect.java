package poise;

/**
 * This code creates a class for Architects
 * <p>
 * 
 * The class extends the Person class
 * 
 * @author Samuel Wendi Kariuki
 * @version 3.2 06 July 2022
 * @see Person
 */
public class Architect extends Person {
	// attributes
	int architectId;

	// constructor
	public Architect(String firstName, String surname, int personID, long telephone, String email, String address,
			String type, int architectId) {
		super(firstName, surname, personID, telephone, email, address, type);
		this.architectId = architectId;
	}

	public int getArchitectId() {
		return architectId;
	}

	public void setArchitectId(int architectId) {
		this.architectId = architectId;
	}

	/**
	 *
	 * toString method example. 
	 * The method presents the class information on the Console.
	 *
	 * @return a String variable containing all the information of the class
	 */
	@Override
	public String toString() {
		return super.getFirstName() + "|" + super.getSurname() + "|" + super.getPersonID() + "|" + super.getTelephone()
				+ "|" + super.getEmail() + "|" + super.getAddress() + "|" + super.getType() + "|" + architectId;
	}

}
