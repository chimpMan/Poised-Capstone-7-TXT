package poise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * @version 3.2 06 July 2022
 */

public class Poised {

	/**
	 * Constant value for user input
	 */
	public static final Scanner KEYBOARD = new Scanner(System.in);

	/**
	 * Constant value for text file name
	 */
	public static final String FILENAME = ("SavedProjects.txt");

	/**
	 * logger object
	 */
	public static final Logger logger = Logger.getLogger(Poised.class.getName());

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
				6. Save before exiting
				7. Save and exit """;

		while (true) { // the main menu will persist until a user exits
			System.out.println(menu);
			int menuSelect = KEYBOARD.nextInt();

			// navigating the menu
			try {

				// Create a project
				if (menuSelect == 1) {
					Projects.setProjectDetails(projectsList);

					// modify a project
				} else if (menuSelect == 2) {
					Projects.modifyProject(projectsList);

					// View a project
				} else if (menuSelect == 3) {
					Projects.viewProjects(projectsList);

					// Modify a project's contractor
				} else if (menuSelect == 4) {
					Contractor.modifyContractor(projectsList);

					// Finalise a project
				} else if (menuSelect == 5) {
					finalise(projectsList);

					// exit
				} else if (menuSelect == 6) {
					saveProjects(projectsList);

				} else if (menuSelect == 7) {
					System.out.println("\nGoodbye!\n");
					saveProjects(projectsList);
					break;
				}

			} catch (InputMismatchException e) {
				logger.log(Level.WARNING, "Incorrect input"); // error logging when input is incorrect
			}
		}
	}

	/**
	 *
	 * method to finalise projects <br>
	 * User is prompted for inputs. 
	 * Inputs are manipulated to get a desired project Objects in the list are parsed through one at a time. 
	 * The object with a matching project number is picked. 
	 * Project object is confirmed to be completed or incomplete . 
	 * Completed projects are marked as Finalised.
	 * Finalised projects are saved in a text CompletedProjects.txt. 
	 * Incomplete projects are invoiced.
	 * 
	 * @param arbitraryList5 List variable to access existing projects.
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
	 * An invoice is put together using customer details.
	 * Invoice is printed to screen.
	 * 
	 * @param finalisedProject Projects variable is an instantiated project object.
	 * @param totalFee float variable is the cost of a project.
	 * @param totalPaid float variable is the amount paid for a project.
	 * @param projname String variable stores a project name.
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
	 * Completion status of a project is checked.
	 * User is prompted to enter a completion date.
	 * if it is 100% completed, Project object is marked as finalised.
	 * Project object written to a CompletedProject.txt
	 * 
	 * @param finalisedProject Projects variable is an instantiated project object.
	 * @param projname String variable stores completion status.
	 * @param completionStat  String variable for completed projects.
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
	 * A file is created to store the projects. 
	 * Objects in the list are parsed through one at a time.
	 * The object with a matching project number is picked. 
	 * All attributes of the object are obtained.
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
	 * A file is created to store and access the projects.
	 * A list is created to store values from the file.
	 * A scanner is used to parse through each char.
	 * Separation of values is done Values are split by the pipe character.
	 * Each value is stored in an array Array values are passed into the Projects constructor.
	 * The constructed project is added to our created list.
	 * 
	 * @param fileName String variable to access the file.
	 * @return the list of projects read from a text file.
	 * @throws FileNotFoundException Signals failure to open path.
	 *
	 */
	public static List<Projects> readProjects(String fileName) throws FileNotFoundException {

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
