package poise;

/**
 * This code creates a class for Contractors
 * <p>
 * 
 * The class extends the Person class
 * 
 * @author Samuel Wendi Kariuki
 * @version 3.0 06 April 2022
 * @see Person
 */
public class Contractor extends Person {
	// attributes
	int contractorId;

	// constructor
	public Contractor(String firstName, String surname, int personID, long telephone, String email, String address,
			String type, int contractorId) {
		super(firstName, surname, personID, telephone, email, address, type);
		this.contractorId = contractorId;
	}

	public int getContractorId() {
		return contractorId;
	}

	public void setContractorId(int contractorId) {
		this.contractorId = contractorId;
	}

	/**
	 *
	 * toString method example. The method presents the class information on the
	 * Console
	 *
	 * @return a String variable containing all the information of the class
	 */
	@Override
	public String toString() {
		return super.getFirstName() + "|" + super.getSurname() + "|" + super.getPersonID() + "|" + super.getTelephone()
				+ "|" + super.getEmail() + "|" + super.getAddress() + "|" + super.getType() + "|" + contractorId;
	}

}
