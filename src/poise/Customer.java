package poise;

/**
 * This code creates a class for Customers
 * <p>
 * 
 * The class extends the Person class
 * 
 * @author Samuel Wendi Kariuki
 * @version 3.2 06 July 2022
 * @see Person
 */
public class Customer extends Person {
	// attributes
	int customerId;

	// methods
	public Customer(String firstName, String surname, int personID, long telephone, String email, String address,
			String type, int customerId) {
		super(firstName, surname, personID, telephone, email, address, type);
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
				+ "|" + super.getEmail() + "|" + super.getAddress() + "|" + super.getType() + "|" + customerId;
	}

}
