package poise;

/**
 * This code creates a class for persons
 * <p>
 * 
 * @author Samuel Wendi Kariuki
 * @version 3.0 06 April 2022
 */
public class Person {
	// attributes
	private String firstName;
	private String surname;
	private int personId;
	private long telephone;
	private String email;
	private String address;
	private String type;

	// methods
	public Person(String firstName, String surname, int personID, long telephone, String email, String address,
			String type) {
		this.firstName = firstName;
		this.surname = surname;
		this.personId = personID;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.type = type;

	}

	// getters and setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getPersonID() {
		return personId;
	}

	public void setPersonID(int personID) {
		this.personId = personID;
	}

	public long getTelephone() {
		return telephone;
	}

	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 *
	 * toString method example. The method presents the class information on the
	 * Console
	 *
	 * @return output String variable containing all the information of the class
	 */
	public String toString() {
		String output = ("\nFirstName: " + firstName);
		output += ("\nSurname: " + surname + "\n" + "PersonID: " + personId + "\n" + "Telephone: " + telephone);
		output += ("\nEmail: " + email + "\n" + "Address: " + address + "\n" + "Type: " + type + "\n");
		return output;

	}
}
