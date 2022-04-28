package poise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This code manages projects for a company called Poised
 * <p>
 * 
 * @author Samuel Wendi Kariuki
 * @version 3.0 06 April 2022
 */

public class Poised {

	/**
	 * Constant value for user input
	 */
	private static final Scanner KEYBOARD = new Scanner(System.in);

	/**
	 * Constant value for text file name
	 */
	private static final String FILENAME = ("SavedProjects.txt");

	/**
	 * logger object
	 */
	private static Logger logger = Logger.getLogger(Poised.class.getName());

	public static void main(String[] args) throws FileNotFoundException {

		// using a list to store the projects
		List<Projects> projectsList = new ArrayList<>();

		// creating the text file needed to read inputs
		while (true) {
			File file4 = new File(FILENAME);

			if (file4.exists()) {
				break;

			} else {
				System.out.println("SavedProjects.txt will be automatically created");

				try (FileWriter file5 = new FileWriter(FILENAME)) {
					System.out.println(file5.toString() + " Your file has now been created\nProceed to work on it");

				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}

		// when this list is empty
		// it is populated with the text file data
		// through the readProjects method
		if (projectsList.isEmpty()) {
			projectsList = readProjects(FILENAME);
		}

		// main menu
		String menu = """
				Choose an option
				1. Create a project
				2. Edit an existing project
				3. View a project
				4. Change contractor's details
				5. Finalise a project
				6. Save and exit """;

		while (true) { // the main menu will persist until a user exits
			System.out.println(menu);
			int menuSelect = KEYBOARD.nextInt();

			// navigating the menu
			try {

				// Create a project
				if (menuSelect == 1) {
					setProjectDetails(projectsList);

					// modify a project
				} else if (menuSelect == 2) {
					modifyProject(projectsList);

					// View a project
				} else if (menuSelect == 3) {
					viewProjects(projectsList);

					// Modify a project's contractor
				} else if (menuSelect == 4) {
					modifyContractor(projectsList);

					// Finalise a project
				} else if (menuSelect == 5) {
					finalise(projectsList);

					// exit
				}else if (menuSelect == 6) {
					System.out.println("\nGoodbye!\n");
					saveProjects(projectsList);
					break;
				}
				else {
					System.out.println("Your entry is not on the list. Try again");
				}

			} catch (InputMismatchException e) {
				logger.log(Level.WARNING, "Incorrect input"); // error logging when input is incorrect
			}
		}
	}

	/**
	 *
	 * method to get the project details <br>
	 * The method prints series of prompts message on the Console It also allows for
	 * entry of user values Inputs are manipulated to generate a Projects object The
	 * object is printed to screen
	 *
	 * @param arbitraryList List variable to store Projects objects
	 */
	private static void setProjectDetails(List<Projects> arbitraryList) {

		KEYBOARD.nextLine(); // this line allows console to shift from integer to string stream

		System.out.println("Enter the name of your Project");
		String projectName = (KEYBOARD.nextLine());

		System.out.println("Enter the type of building of your project.\nE.g. House, apartment block or store, etc.");
		String buildType = (KEYBOARD.nextLine());

		System.out.println("Enter the Project Number of your Project");
		int projectNumber = (KEYBOARD.nextInt());

		System.out.println("Enter the ERF number of your Project");
		int erfNumber = (KEYBOARD.nextInt());

		System.out.println("Enter the Total fee of your Project");
		float totalFee = (KEYBOARD.nextFloat());

		System.out.println("Enter the Total amount paid of your Project");
		float totalpaid = (KEYBOARD.nextFloat());

		// workaround to read the next line with a different type of String
		KEYBOARD.nextLine();

		System.out.println("Enter the address of your Project");
		String projectAddress = (KEYBOARD.nextLine());

		System.out.println("Enter the Deadline of your project in the format dd MMMMM yyyy");
		String deadline = (KEYBOARD.nextLine());

		String completionDate;
		System.out.println("Enter the Completion Status of your project as a percentage");

		int completionStatus = (KEYBOARD.nextInt());
		if (completionStatus < 100) {
			KEYBOARD.nextLine();
			completionDate = "Incomplete"; // temporarily set the date to incomplete until it's completed

		} else {
			KEYBOARD.nextLine();
			System.out.println("Enter the Completion Date of your project in the format dd MMMMM yyyy");
			completionDate = (KEYBOARD.nextLine());
		}

		// empty project name is set to the building type and customer surname
		if (projectName.equals("")) {
			String surname;
			System.out.println("\nWhat is the surname of the customer?");
			surname = KEYBOARD.nextLine();
			projectName = (buildType + " " + surname);

		}
		// setting the customer details in an array
		String[] customerInfo = setPersonDetails("Customer");
		int personIdInt = Integer.parseInt(customerInfo[2]); // the constructor needs an integer value
		long personTelephoneInt = Long.parseLong(customerInfo[3]); // the constructor needs a long value
		int typeIdInt = Integer.parseInt(customerInfo[7]); // the constructor needs an integer value

		Customer customer = new Customer(customerInfo[0], customerInfo[1], personIdInt, personTelephoneInt,
				customerInfo[4], customerInfo[5], customerInfo[6], typeIdInt);// instantiating the customer

		// setting the architect details in an array
		String[] architectInfo = setPersonDetails("Architect");
		personIdInt = Integer.parseInt(architectInfo[2]); // the constructor needs an integer value
		personTelephoneInt = Long.parseLong(architectInfo[3]); // the constructor needs a long value
		typeIdInt = Integer.parseInt(architectInfo[7]); // the constructor needs an integer value

		Architect architect = new Architect(architectInfo[0], architectInfo[1], personIdInt, personTelephoneInt,
				architectInfo[4], architectInfo[5], architectInfo[6], typeIdInt); // instantiating the architect

		// setting the contractor details in an array
		String[] contractorInfo = setPersonDetails("Contractor");
		personIdInt = Integer.parseInt(contractorInfo[2]); // the constructor needs an integer value
		personTelephoneInt = Long.parseLong(contractorInfo[3]);// the constructor needs a long value
		typeIdInt = Integer.parseInt(contractorInfo[7]); // the constructor needs an integer value

		Contractor contractor = new Contractor(contractorInfo[0], contractorInfo[1], personIdInt, personTelephoneInt,
				contractorInfo[4], contractorInfo[5], contractorInfo[6], typeIdInt); // instantiating the contractor

		// Project object that is populated by user input
		Projects createdProject = new Projects(projectName, buildType, projectNumber, projectAddress, erfNumber,
				totalFee, totalpaid, deadline, completionStatus, completionDate, customer, architect, contractor);

		String printedProjects=createdProject.toString();
		logger.log(Level.INFO, printedProjects);// logging the printed project object

		arbitraryList.add(createdProject);// adding each project to a list

	}

	/**
	 *
	 * method to set a persons details <br>
	 * The method prints series of prompts message on the Console It also allows for
	 * entry of user values Inputs are manipulated to generate a Person object The
	 * object is printed to screen
	 *
	 * @param arbitraryList List variable to store Person objects
	 * @return A string containing the type value of a person
	 */
	private static String[] setPersonDetails(String type) {

		System.out.println("Enter the first name of the " + type);
		String fname = KEYBOARD.nextLine();

		System.out.println("Enter the second name of the " + type);
		String sname = KEYBOARD.nextLine();

		String fullname = String.format("%s %s", fname, sname);

		System.out.println("Enter the PersonID of " + fullname);
		int personId = KEYBOARD.nextInt();
		String personIdString = Integer.toString(personId);

		System.out.println("Enter the telephone of " + fullname + " Enter a 9 digit number: 7xxxxxxxx");
		long telephone = KEYBOARD.nextInt();
		String telephoneString = Long.toString(telephone);

		System.out.println("Enter the " + type + "ID of " + fullname);
		int typeId = KEYBOARD.nextInt();
		String typeIdString = Integer.toString(typeId);

		KEYBOARD.nextLine();

		System.out.println("Enter the email of  " + fullname);
		String email = KEYBOARD.nextLine();

		System.out.println("Enter the address of " + fullname);
		String address = KEYBOARD.nextLine();

		// storing the details in an array
		String[] contentsArray = new String[8];
		contentsArray[0] = fname;
		contentsArray[1] = sname;
		contentsArray[2] = personIdString;
		contentsArray[3] = telephoneString;
		contentsArray[4] = email;
		contentsArray[5] = address;
		contentsArray[6] = type;
		contentsArray[7] = typeIdString;

		return contentsArray;
	}

	/**
	 *
	 * method to view a project in multiple ways <br>
	 * The method prints series of prompts message on the Console It also allows for
	 * entry of user values in decision making
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
	private static void viewProjects(List<Projects> arbitraryList2) {

		int viewChoice; // selecting the view options from the menu

		System.out.println(
				"Do you wish to view\n1. A specific Project.\n2. Unfinished Projects\n3. Finished Projects\n4. Overdue Projects\n5. All projects\n");
		viewChoice = KEYBOARD.nextInt();// user input that chooses a specific choice

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
				readProjects(FILENAME).toString();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {
			logger.log(Level.WARNING, "invalid choice\n");
		}
	}

	/**
	 *
	 * method to view a specific project <br>
	 * The method prints series of prompts message on the Console It also allows for
	 * entry of user values in decision making The user can view a specific project
	 * -A specific project can be selected by its name or unique number
	 *
	 * @param arbitraryList2 List variable to access existing projects
	 *
	 */
	private static void specificProject(List<Projects> arbitraryList2) {

		int viewProject;
		int chooseBy;

		System.out.println("Do you want to choose via  \n1. project name  \n2. project number?");
		chooseBy = KEYBOARD.nextInt();
		KEYBOARD.nextLine();

		if (chooseBy == 1) {
			System.out.println("enter the project name");
			String projectname = KEYBOARD.nextLine();

			for (int i = 0; i < arbitraryList2.size(); i++) {
				Projects thisProject = arbitraryList2.get(i);
				if (thisProject.getProjectName().equals(projectname)) {
					System.out.println(thisProject);

				}
			}
		}

		else if (chooseBy == 2) {
			System.out.println("enter the project number\n");
			viewProject = KEYBOARD.nextInt();

			for (int i = 0; i < arbitraryList2.size(); i++) {
				Projects thisProject = arbitraryList2.get(i);
				if (thisProject.getProjectNumber() == viewProject) {
					System.out.println(thisProject);
				}
			}

		} else {
			logger.log(Level.WARNING, "invalid choice\n");
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
	private static void unfinshedProjects(List<Projects> arbitraryList2) {
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
	 * Objects in the list are parsed through one at a time The object with a
	 * matching project number is picked Overdue projects are printed to screen
	 * 
	 * @param arbitraryList2 List variable to access existing projects
	 * @param currentDate    Date variable to get today's date
	 * @param dateFormat     SimpleDateFormat variable to get a desired format
	 *
	 */
	private static void deadlineDate(List<Projects> arbitraryList2, Date currentDate, SimpleDateFormat dateFormat) {
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
	 * User is prompted for inputs Inputs are manipulated to get a desired project
	 * Objects in the list are parsed through one at a time The object with a
	 * matching project number is picked Necessary modification is performed
	 * 
	 * @param arbitraryList3 List variable to access existing projects
	 *
	 */
	private static void modifyProject(List<Projects> arbitraryList3) {

		int modProject;
		int modchoice;
		String newDate;
		Float newPaid;

		System.out.println("Which project do you want to modify? Enter the projectID\n");
		modProject = KEYBOARD.nextInt();

		// parsing through the list for the matching project number
		for (int i = 0; i < arbitraryList3.size(); i++) {
			Projects modifiedProjects = arbitraryList3.get(i); // instantiating new project object

			if (modifiedProjects.getProjectNumber() == modProject) {
				System.out.println("modify: 1. Due Date \n2. Total amount paid");// prompting specific change
				modchoice = KEYBOARD.nextInt();

				if (modchoice == 1) {
					KEYBOARD.nextLine();
					System.out.println("enter your new due date. Enter a date in the format dd MMMM YYYY");
					newDate = KEYBOARD.nextLine();
					modifiedProjects.setDeadline(newDate);

				} else if (modchoice == 2) {
					System.out.println("enter your new amount paid ");
					newPaid = KEYBOARD.nextFloat();
					modifiedProjects.setTotalpaid(newPaid);

				} else {
					logger.log(Level.WARNING, "invalid entry\n");
				}
			}
		}
	}

	/**
	 *
	 * method to modify contractor details <br>
	 * User is prompted for inputs Inputs are manipulated to get a desired project
	 * Objects in the list are parsed through one at a time The object with a
	 * matching project number is picked Necessary modification is performed
	 * 
	 * @param arbitraryList4 List variable to access existing projects
	 *
	 */
	private static void modifyContractor(List<Projects> arbitraryList4) {

		int modProject;
		int modContractor;
		int modchoice;
		String newMail;
		Long newTelephone;

		System.out.println("Enter the project ID of the project containing the contractor you wish to modify");
		modProject = KEYBOARD.nextInt();

		// parsing through the list
		for (int i = 0; i < arbitraryList4.size(); i++) {
			Projects thisProject = arbitraryList4.get(i);

			// matching the user entry with the project number
			if (thisProject.getProjectNumber() == modProject) {
				Contractor thisContractor = thisProject.getContractor();// new contractor object instantiated

				System.out.println("Enter the Contractor ID of the contractor you wish to modify");
				modContractor = KEYBOARD.nextInt();

				// matching the user entry to the ID
				if (thisContractor.getContractorId() == modContractor) {
					System.out.println("modify: 1. Telephone \n2. Email");// prompting specific change
					modchoice = KEYBOARD.nextInt();

					// changing telephone
					if (modchoice == 1) {
						System.out.println("Enter the new telephone of the contractor you wish to modify");
						newTelephone = KEYBOARD.nextLong();
						thisContractor.setTelephone(newTelephone);

						// changing mail
					} else if (modchoice == 2) {
						KEYBOARD.nextLine();
						System.out.println("Enter the new Email of the contractor you wish to modify");
						newMail = KEYBOARD.nextLine();
						thisContractor.setEmail(newMail);

					} else {
						logger.log(Level.WARNING, "Incorrect choice");
					}

					System.out.println((thisProject.getContractor()));

				} else {
					logger.log(Level.WARNING, "Incorrect Credentials");
				}
			}
		}
	}

	/**
	 *
	 * method to finalise projects <br>
	 * User is prompted for inputs Inputs are manipulated to get a desired project
	 * Objects in the list are parsed through one at a time The object with a
	 * matching project number is picked Project object is confirmed to be completed
	 * or incompleted Completed projects are marked as Finalised Finalised projects
	 * are saved in a text CompletedProjects.txt Incomplete projects are invoiced
	 * 
	 * @param arbitraryList5 List variable to access existing projects
	 *
	 */
	private static void finalise(List<Projects> arbitraryList5) {

		System.out.println("Enter the project ID of the project you wish to finalise");
		int finalProject = KEYBOARD.nextInt();

		// parsing through each project in the list
		for (int i = 0; i < arbitraryList5.size(); i++) {
			Projects finalisedProject = arbitraryList5.get(i);

			// matching the user entry with project number
			if (finalisedProject.getProjectNumber() == finalProject) {

				float totalFee = finalisedProject.getTotalFee();
				float totalpaid = finalisedProject.getTotalpaid();
				String projname = finalisedProject.getProjectName();
				KEYBOARD.nextLine();

				if ((totalFee - totalpaid) > 0) {
					generateInvoice(finalisedProject, totalFee, totalpaid, projname);

				} else {
					finalisedProject.setCompletionStatus(100);

					if (finalisedProject.getCompletionStatus() == 100) {
						String completionStat = "Finalised";
						completedProject(finalisedProject, projname, completionStat);

					}
				}
			} else {
				logger.log(Level.WARNING, "The project number you have entered is invalid");
			}
		}
	}

	/**
	 *
	 * method to generate invoices <br>
	 * An invoice is put together using customer details Invoice is printed to
	 * screen
	 * 
	 * @param finalisedProject Projects variable is an instantiated project object
	 * @param totalFee         float variable is the cost of a project
	 * @param totalPaid        float variable is the amount paid for a project
	 * @param projname         String variable stores a project name
	 *
	 */
	private static void generateInvoice(Projects finalisedProject, float totalFee, float totalpaid, String projname) {

		Customer thisCustomer = finalisedProject.getCustomer();// instantiating a customer

		String fullname = thisCustomer.getFirstName() + " " + thisCustomer.getSurname();

		String contactDetails = String.format("Telephone: %s%nEmail: %s%nAddress: %s%n", thisCustomer.getTelephone(),
				thisCustomer.getEmail(), thisCustomer.getAddress());

		float remainder = (totalFee - totalpaid);

		String invoice = "\nCustomer name: " + fullname + "\nProject name: " + projname + "\n" + contactDetails
				+ "\nTotal Fee: " + totalFee + "\nTotalPaid: " + totalpaid + "\nBalance: " + remainder + "\n";

		System.out.println(invoice);
	}

	/**
	 *
	 * method to complete a project <br>
	 * Completion status of a project is checked User is prompted to enter a
	 * completion date if it is 100% completed Project object is marked as finalised
	 * Project object written to a CompletedProject.txt
	 * 
	 * @param finalisedProject Projects variable is an instantiated project object
	 * @param projname         String variable stores completion status
	 * @param completionStat   String variable is initialised to Finalised for
	 *                         completed projects
	 *
	 */
	private static void completedProject(Projects finalisedProject, String projname, String completionStat) {

		if (completionStat.equals("Finalised")) {

			System.out.println("When was the project completed? Enter a date in the format dd MMMM YYYY");
			finalisedProject.setCompletionDate(KEYBOARD.nextLine());

			File file1 = new File("CompletedProject.txt");

			// writing to file using filewriter and printwriter
			FileWriter fw;
			try {
				fw = new FileWriter(file1, true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println("project " + projname + " has been finalised" + finalisedProject.toString() + "\n");
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 * method to save projects to file <br>
	 * A file is created to store the projects Objects in the list are parsed
	 * through one at a time The object with a matching project number is picked All
	 * attributes of the object are obtained
	 * 
	 * @param arbitraryList6 List variable to access existing projects
	 *
	 */
	private static void saveProjects(List<Projects> arbitraryList6) {

		File file2 = new File(FILENAME);// a text file that will be used to get the

		for (int i = 0; i < arbitraryList6.size(); i++) {
			Projects myProjects = arbitraryList6.get(i);

			String concatAttributes = myProjects.getProjectName() + "|" + myProjects.getBuildType() + "|"
					+ myProjects.getProjectNumber() + "|" + myProjects.getProjectAddress() + "|"
					+ myProjects.getErfNumber() + "|" + myProjects.getTotalFee() + "|" + myProjects.getTotalpaid() + "|"
					+ myProjects.getDeadline() + "|" + myProjects.getCompletionStatus() + "|"
					+ myProjects.getCompletionDate() + "|" + myProjects.getCustomer() + "|" + myProjects.getArchitect()
					+ "|" + myProjects.getContractor();

			FileWriter fw;
			try {
				fw = new FileWriter(file2, true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(concatAttributes);
				pw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 *
	 * method to allow reading of projects from SavedProjects.txt <br>
	 * A file is created to store and access the projects A list is created to store
	 * values from the file A scanner is used to parse through each char Separation
	 * of values is done Values are split by the pipe character Each value is stored
	 * in an array Array values are passed into the Projects constructor The
	 * constructed project is added to our created list
	 * 
	 * @param fileName String variable to access the file
	 * @return the list of projects read from a text file
	 * @throws FileNotFoundException Signals that an attempt to open the file
	 *                               denoted by a specified path name has failed.
	 *
	 */
	private static List<Projects> readProjects(String fileName) throws FileNotFoundException {

		File file3 = new File(fileName);
		List<Projects> projList = new ArrayList<>();

		try (Scanner sc = new Scanner(file3)) {

			while (sc.hasNextLine()) { // reading the text file line by line
				String line = sc.nextLine(); // parsing through each character
				String[] items = line.split("\\|"); // splitting by the pipe character

				// setting the object variables from the text file
				String projectName = items[0];
				String buildType = items[1];
				int projectNumber = Integer.parseInt(items[2]);
				String projectAddress = items[3];
				int erfNumber = Integer.parseInt(items[4]);
				float totalFee = Float.parseFloat(items[5]);
				float totalpaid = Float.parseFloat(items[6]);
				String deadline = items[7];
				int completionStatus = Integer.parseInt(items[8]);
				String completionDate = items[9];

				Customer thisCustomer = new Customer(items[10], items[11], Integer.parseInt(items[12]),
						Long.parseLong(items[13]), items[14], items[15], items[16], Integer.parseInt(items[17]));

				Architect thisArchitect = new Architect(items[18], items[19], Integer.parseInt(items[20]),
						Long.parseLong(items[21]), items[22], items[23], items[24], Integer.parseInt(items[25]));

				Contractor thisContractor = new Contractor(items[26], items[27], Integer.parseInt(items[28]),
						Long.parseLong(items[29]), items[30], items[31], items[32], Integer.parseInt(items[33]));

				Projects newProjects = new Projects(projectName, buildType, projectNumber, projectAddress, erfNumber,
						totalFee, totalpaid, deadline, completionStatus, completionDate, thisCustomer, thisArchitect,
						thisContractor);

				projList.add(newProjects);
			}
		}
		return projList;
	}
}
