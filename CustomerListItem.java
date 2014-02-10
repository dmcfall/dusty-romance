package dusty;

public class CustomerListItem {

	protected int customer_id; 
	protected String firstName;
	protected String lastName;
	protected String fullName;
	protected double romBalance;
	protected double regBalance;
	

	public CustomerListItem() {
	}

	public CustomerListItem(int custID, String fName, String lName) {

		this.customer_id = custID;
		this.firstName = fName;
		this.lastName= lName;
		this.fullName=lName + ", " + fName; 
	}
	
	public CustomerListItem(int custID, String fName, String lName, double regBal, double romBal) {

		this.customer_id = custID;
		this.firstName = fName;
		this.lastName= lName;
		this.fullName=lName + ", " + fName;
		this.romBalance = romBal;
		this.regBalance = regBal;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
