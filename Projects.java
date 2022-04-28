package poise;

/**
 * This code creates a class for projects
 * <p>
 * 
 * @author Samuel Wendi Kariuki
 * @version 3.0 06 April 2022
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
	 * toString method example. The method presents the class information on the
	 * Console
	 *
	 * @return output String variable containing all the information of the class
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

}
