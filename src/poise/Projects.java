package poise;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

/**
 * This code creates a class for projects
 * <p>
 * 
 * @author Samuel Wendi Kariuki
 * @version 3.2 06 July 2022
 */
public class Projects {

	// attributes
	private String projectName;
	private String buildType;
	private int projectNumber;
	private String projectAddress;
	private int erfNumber;
	private float totalFee;
	private float totalpaid;
	private String deadline;
	private int completionStatus;
	private String completionDate;
	private Customer customer;
	private Architect architect;
	private Contractor contractor;

	// constructors
	public Projects(String projectName, String buildType, int projectNumber, String projectAddress, int erfNumber,
			float totalFee, float totalpaid, String deadline, int completionStatus, String completionDate,
			Customer customer, Architect architect, Contractor contractor) {
		this.projectName = projectName;
		this.buildType = buildType;
		this.projectNumber = projectNumber;
		this.projectAddress = projectAddress;
		this.erfNumber = erfNumber;
		this.totalFee = totalFee;
		this.totalpaid = totalpaid;
		this.deadline = deadline;
		this.completionStatus = completionStatus;
		this.completionDate = completionDate;
		this.customer = customer;
		this.architect = architect;
		this.contractor = contractor;
	}

	// getters and setters
	public String getProjectName() {
		return projectName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Architect getArchitect() {
		return architect;
	}

	public void setArchitect(Architect architect) {
		this.architect = architect;
	}

	public Contractor getContractor() {
		return contractor;
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public int getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public int getErfNumber() {
		return erfNumber;
	}

	public void setErfNumber(int erfNumber) {
		this.erfNumber = erfNumber;
	}

	public float getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(float totalFee) {
		this.totalFee = totalFee;
	}

	public float getTotalpaid() {
		return totalpaid;
	}

	public void setTotalpaid(float totalpaid) {
		this.totalpaid = totalpaid;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public int getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(int completionStatus) {
		this.completionStatus = completionStatus;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	/**
	 *
	 * toString method example. 
	 * The method presents the class information on the Console.
	 *
	 * @return output String variable containing all the information of the class.
	 * 
	 */
	public String toString() {
		String output = ("\nProject Name: " + projectName);
		output += ("\nBuildType: " + buildType + "\n" + "ProjectNumber: " + projectNumber + "\n" + "ProjectAddress: "
				+ projectAddress);
		output += ("\nerfNumber: " + erfNumber + "\n" + "TotalFee: " + totalFee + "\n" + "Totalpaid: " + totalpaid);
		output += ("\nDeadline: " + deadline + "\n" + "CompletionStatus: " + completionStatus);
		output += ("\nCompletionDate: " + completionDate + "\n");
		output += (customer + "\n" + architect + "\n" + contractor + "\n");

		return output;

	}
	
	/**
	 *
	 * method to view a project in multiple ways <br>
	 * The method prints series of prompts message on the Console.
	 * It also allows for entry of user values in decision making.
	 * 
	 * 1. The user can view a specific project - A specific project can be selected
	 * by its name or unique number - A call to the method specificProject is made
	 * 
	 * 2. The user can view unfinished projects - A call to the method
	 * unfinishedProject is made
	 * 
	 * 3. The user can view finished projects - A call to the method finishedProject
	 * is made
	 * 
	 * 4. The user can view overdue projects
	 *
	 * @param arbitraryList2 List variable to access existing projects
	 *
	 */
	static void viewProjects(List<Projects> arbitraryList2) {

		int viewChoice; // selecting the view options from the menu

		System.out.println("""
				Do you wish to view
				1. A specific Project.
				2. Unfinished Projects.
				3. Finished Projects.
				4. Overdue Projects.
				All projects
				""");
		viewChoice = Poised.KEYBOARD.nextInt();// user input that chooses a specific choice

		// viewing a specific project
		if (viewChoice == 1) {
			specificProject(arbitraryList2);

			// viewing a unfinished projects
		} else if (viewChoice == 2) {
			unfinshedProjects(arbitraryList2);

			// viewing a finished projects
		} else if (viewChoice == 3) {
			finishedProjects(arbitraryList2);

			// viewing a overdue projects
		} else if (viewChoice == 4) {
			Date currentDate = new Date(); // new date object to compare dates
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMM yyyy"); // formatting our desired dates
			deadlineDate(arbitraryList2, currentDate, dateFormat);

			// viewing all projects
		} else if (viewChoice == 5) {
			try {
				Poised.readProjects(Poised.FILENAME).toString();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {
			Poised.logger.log(Level.WARNING, "invalid choice\n");
		}
	}

	/**
	 *
	 * method to view a specific project <br>
	 * The method prints series of prompts message on the Console. 
	 * It also allows for entry of user values in decision making.
	 * The user can view a specific project.
	 * A specific project can be selected by its name or unique number
	 *
	 * @param arbitraryList2 List variable to access existing projects
	 *
	 */
	static void specificProject(List<Projects> arbitraryList2) {

		int viewProject;
		int chooseBy;

		System.out.println("Do you want to choose via  \n1. project name  \n2. project number?");
		chooseBy = Poised.KEYBOARD.nextInt();
		Poised.KEYBOARD.nextLine();

		if (chooseBy == 1) {
			System.out.println("enter the project name");
			String projectname = Poised.KEYBOARD.nextLine();

			for (int i = 0; i < arbitraryList2.size(); i++) {
				Projects thisProject = arbitraryList2.get(i);
				if (thisProject.getProjectName().equals(projectname)) {
					System.out.println(thisProject);

				}
			}
		}
		else if (chooseBy == 2) {
			System.out.println("enter the project number\n");
			viewProject = Poised.KEYBOARD.nextInt();

			for (int i = 0; i < arbitraryList2.size(); i++) {
				Projects thisProject = arbitraryList2.get(i);
				if (thisProject.getProjectNumber() == viewProject) {
					System.out.println(thisProject);
				}
			}

		} else {
			Poised.logger.log(Level.WARNING, "invalid choice\n");
		}
	}

	/**
	 *
	 * method to view unfinished projects <br>
	 * Objects in the list are parsed through one at a time The object with a
	 * matching project number is picked Incomplete projects are printed to screen
	 * 
	 * @param arbitraryList2 List variable to access existing projects
	 *
	 */
	static void unfinshedProjects(List<Projects> arbitraryList2) {
		for (Projects thisProject : arbitraryList2) {
			if (thisProject.getCompletionStatus() < 100) {
				System.out.println(thisProject);
			}
		}
	}

	/**
	 *
	 * method to view finished projects <br>
	 * Objects in the list are parsed through one at a time The object with a
	 * matching project number is picked Completed projects are printed to screen
	 * 
	 * @param arbitraryList2 List variable to access existing projects
	 *
	 */
	private static void finishedProjects(List<Projects> arbitraryList2) {
		for (Projects thisProject : arbitraryList2) {
			if (thisProject.getCompletionStatus() == 100) {
				System.out.println(thisProject);
			}
		}
	}

	/**
	 *
	 * method to check for overdue projects <br>
	 *
	 * Objects in the list are parsed through one at a time.
	 * The object with a matching project number is picked.
	 * Overdue projects are printed to screen
	 * 
	 * @param arbitraryList2 List variable to access existing projects
	 * @param currentDate Date variable to get today's date
	 * @param dateFormat SimpleDateFormat variable to get a desired format
	 *
	 */
	static void deadlineDate(List<Projects> arbitraryList2, Date currentDate, SimpleDateFormat dateFormat) {
		try {
			for (int i = 0; i < arbitraryList2.size(); i++) {
				Projects thisProject = arbitraryList2.get(i);
				Date importDate = dateFormat.parse(thisProject.getDeadline());// converting string date to date type

				// printing the overdue dates if the current date is after the deadline date
				if (currentDate.after(importDate)) {
					System.out.println(thisProject);
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * method to modify projects <br>
	 * User is prompted for inputs.
	 * Inputs are manipulated to get a desired project.
	 * Objects in the list are parsed through one at a time.
	 * The object with a matching project number is picked.
	 * Necessary modification is performed.
	 * 
	 * @param arbitraryList3 List variable to access existing projects
	 *
	 */
	static void modifyProject(List<Projects> arbitraryList3) {

		int modProject;
		int modchoice;
		String newDate;
		Float newPaid;

		System.out.println("Which project do you want to modify? Enter the projectID\n");
		modProject = Poised.KEYBOARD.nextInt();

		// parsing through the list for the matching project number
		for (int i = 0; i < arbitraryList3.size(); i++) {
			Projects modifiedProjects = arbitraryList3.get(i); // instantiating new project object

			if (modifiedProjects.getProjectNumber() == modProject) {
				System.out.println("modify: 1. Due Date \n2. Total amount paid");// prompting specific change
				modchoice = Poised.KEYBOARD.nextInt();

				if (modchoice == 1) {
					Poised.KEYBOARD.nextLine();
					System.out.println("enter your new due date. Enter a date in the format dd MMMM YYYY");
					newDate = Poised.KEYBOARD.nextLine();
					modifiedProjects.setDeadline(newDate);

				} else if (modchoice == 2) {
					System.out.println("enter your new amount paid ");
					newPaid = Poised.KEYBOARD.nextFloat();
					modifiedProjects.setTotalpaid(newPaid);

				} else {
					Poised.logger.log(Level.WARNING, "invalid entry\n");
				}
			}
		}
	}
	
	/**
	 *
	 * method to get the project details <br>
	 * User is prompted for inputs.
	 * Inputs are manipulated to get a desired project.
	 * object is printed to screen
	 *
	 * @param arbitraryList List variable to store Projects objects
	 */
	static void setProjectDetails(List<Projects> arbitraryList) {

		Poised.KEYBOARD.nextLine(); // this line allows console to shift from integer to string stream

		System.out.println("Enter the name of your Project");
		String projectName = (Poised.KEYBOARD.nextLine());

		System.out.println("Enter the type of building of your project.\nE.g. House, apartment block or store, etc.");
		String buildType = (Poised.KEYBOARD.nextLine());

		System.out.println("Enter the Project Number of your Project");
		int projectNumber = (Poised.KEYBOARD.nextInt());

		System.out.println("Enter the ERF number of your Project");
		int erfNumber = (Poised.KEYBOARD.nextInt());

		System.out.println("Enter the Total fee of your Project");
		float totalFee = (Poised.KEYBOARD.nextFloat());

		System.out.println("Enter the Total amount paid of your Project");
		float totalpaid = (Poised.KEYBOARD.nextFloat());

		// workaround to read the next line with a different type of String
		Poised.KEYBOARD.nextLine();

		System.out.println("Enter the address of your Project");
		String projectAddress = (Poised.KEYBOARD.nextLine());

		System.out.println("Enter the Deadline of your project in the format dd MMMMM yyyy");
		String deadline = (Poised.KEYBOARD.nextLine());

		String completionDate;
		System.out.println("Enter the Completion Status of your project as a percentage");

		int completionStatus = (Poised.KEYBOARD.nextInt());
		if (completionStatus < 100) {
			Poised.KEYBOARD.nextLine();
			completionDate = "Incomplete"; // temporarily set the date to incomplete until it's completed

		} else {
			Poised.KEYBOARD.nextLine();
			System.out.println("Enter the Completion Date of your project in the format dd MMMMM yyyy");
			completionDate = (Poised.KEYBOARD.nextLine());
		}

		// empty project name is set to the building type and customer surname
		if (projectName.equals("")) {
			String surname;
			System.out.println("\nWhat is the surname of the customer?");
			surname = Poised.KEYBOARD.nextLine();
			projectName = (buildType + " " + surname);

		}
		// setting the customer details in an array
		String[] customerInfo = Person.setPersonDetails("Customer");
		int personIdInt = Integer.parseInt(customerInfo[2]); // the constructor needs an integer value
		long personTelephoneInt = Long.parseLong(customerInfo[3]); // the constructor needs a long value
		int typeIdInt = Integer.parseInt(customerInfo[7]); // the constructor needs an integer value

		Customer customer = new Customer(customerInfo[0], customerInfo[1], personIdInt, personTelephoneInt,
				customerInfo[4], customerInfo[5], customerInfo[6], typeIdInt);// instantiating the customer

		// setting the architect details in an array
		String[] architectInfo = Person.setPersonDetails("Architect");
		personIdInt = Integer.parseInt(architectInfo[2]); // the constructor needs an integer value
		personTelephoneInt = Long.parseLong(architectInfo[3]); // the constructor needs a long value
		typeIdInt = Integer.parseInt(architectInfo[7]); // the constructor needs an integer value

		Architect architect = new Architect(architectInfo[0], architectInfo[1], personIdInt, personTelephoneInt,
				architectInfo[4], architectInfo[5], architectInfo[6], typeIdInt); // instantiating the architect

		// setting the contractor details in an array
		String[] contractorInfo = Person.setPersonDetails("Contractor");
		personIdInt = Integer.parseInt(contractorInfo[2]); // the constructor needs an integer value
		personTelephoneInt = Long.parseLong(contractorInfo[3]);// the constructor needs a long value
		typeIdInt = Integer.parseInt(contractorInfo[7]); // the constructor needs an integer value

		Contractor contractor = new Contractor(contractorInfo[0], contractorInfo[1], personIdInt, personTelephoneInt,
				contractorInfo[4], contractorInfo[5], contractorInfo[6], typeIdInt); // instantiating the contractor

		// Project object that is populated by user input
		Projects createdProject = new Projects(projectName, buildType, projectNumber, projectAddress, erfNumber,
				totalFee, totalpaid, deadline, completionStatus, completionDate, customer, architect, contractor);

		arbitraryList.add(createdProject);// adding each project to a list
	}
}
