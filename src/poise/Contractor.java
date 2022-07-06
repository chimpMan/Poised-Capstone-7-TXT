package poise;

import java.util.List;
import java.util.logging.Level;

/**
 * This code creates a class for Contractors
 * <p>
 * 
 * The class extends the Person class
 * 
 * @author Samuel Wendi Kariuki
 * @version 3.2 06 July 2022
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
	 * toString method example. 
	 * The method presents the class information on the Console.
	 *
	 * @return a String variable containing all the information of the class
	 * 
	 */
	@Override
	public String toString() {
		return super.getFirstName() + "|" + super.getSurname() + "|" + super.getPersonID() + "|" + super.getTelephone()
				+ "|" + super.getEmail() + "|" + super.getAddress() + "|" + super.getType() + "|" + contractorId;
	}
	
	
	/**
	 *
	 * method to modify contractor details <br>
	 * User is prompted for inputs.
	 * Inputs are manipulated to get a desired project.
	 * Objects in the list are parsed through one at a time.
	 * The object with a matching project number is picked.
	 * Necessary modification is performed.
	 * 
	 * @param arbitraryList4 List variable to access existing projects.
	 *
	 */

	public static void modifyContractor(List<Projects> arbitraryList4) {

		int modProject;
		int modContractor;
		int modchoice;
		String newMail;
		Long newTelephone;

		System.out.println("Enter the project ID of the project containing the contractor you wish to modify");
		modProject = Poised.KEYBOARD.nextInt();

		// parsing through the list
		for (int i = 0; i < arbitraryList4.size(); i++) {
			Projects thisProject = arbitraryList4.get(i);

			// matching the user entry with the project number
			if (thisProject.getProjectNumber() == modProject) {
				Contractor thisContractor = thisProject.getContractor();// new contractor object instantiated

				System.out.println("Enter the Contractor ID of the contractor you wish to modify");
				modContractor = Poised.KEYBOARD.nextInt();

				// matching the user entry to the ID
				if (thisContractor.getContractorId() == modContractor) {
					System.out.println("modify: 1. Telephone \n2. Email");// prompting specific change
					modchoice = Poised.KEYBOARD.nextInt();

					// changing telephone
					if (modchoice == 1) {
						System.out.println("Enter the new telephone of the contractor you wish to modify");
						newTelephone = Poised.KEYBOARD.nextLong();
						thisContractor.setTelephone(newTelephone);

						// changing mail
					} else if (modchoice == 2) {
						Poised.KEYBOARD.nextLine();
						System.out.println("Enter the new Email of the contractor you wish to modify");
						newMail = Poised.KEYBOARD.nextLine();
						thisContractor.setEmail(newMail);

					} else {
						Poised.logger.log(Level.WARNING, "Incorrect choice");
					}

					System.out.println((thisProject.getContractor()));

				} else {
					Poised.logger.log(Level.WARNING, "Incorrect Credentials");
				}
			}
		}
	}

}
