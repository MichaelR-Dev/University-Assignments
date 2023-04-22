package src;

public class Contact {
	
	//Unique Contact Identifier
	private final String contact_ID;
	
	//Contact Details
	private String contact_firstName;
	private String contact_lastName;
	private String contact_phoneNumber;
	private String contact_address;
	
	/******************************************************************************************/
	
	//Constructor
	public Contact(String ID, String firstName, String lastName, String phoneNumber, String address) 
	{
		//Constructor Param Validation
		verifyIDValid(ID);
		verifyIDUnique(ID);
		verifyFirstName(firstName);
		verifyLastName(lastName);
		verifyPhoneNumber(phoneNumber);
		verifyAddress(address);
		
		
		//Assign Contact Details
		this.contact_ID = ID;
		this.contact_firstName = firstName;
		this.contact_lastName = lastName;
		this.contact_phoneNumber = phoneNumber;
		this.contact_address = address;
	}
	
	/******************************************************************************************/
	
	//Getters
	public String getID() {
		return this.contact_ID;
	}
	
	public String getFirstName() {
		return this.contact_firstName;
	}
	
	public String getLastName() {
		return this.contact_lastName;
	}
	
	public String getPhoneNumber() {
		return this.contact_phoneNumber;
	}
	
	public String getAddress() {
		return this.contact_address;
	}
	
	/******************************************************************************************/
	
	//Setters
	public void setFirstName(String newFirstName) {
		this.contact_firstName = newFirstName;
	}
	
	public void setLastName(String newLastName) {
		this.contact_lastName = newLastName;
	}
	
	public void setPhoneNumber(String newPhoneNumber) {
		this.contact_phoneNumber = newPhoneNumber;
	}
	
	public void setAddress(String newAddress) {
		this.contact_address = newAddress;
	}
	
	/******************************************************************************************/
	
	//Validation
	
	//Verify Unique ID for Contact Constructor
	public static void verifyIDUnique(String ID) {
		if(ID == null || ID.length() > 10 || ContactService.getContactIDs().contains(ID)) {
			throw new IllegalArgumentException("Contact ID must be Unique, is non-nullable, and must be 10 or less characters long");
		}
	}
	
	public static void verifyIDValid(String ID) {
		if(ID == null || ID.length() > 10) {
			throw new IllegalArgumentException("Contact ID must be Unique, is non-nullable, and must be 10 or less characters long");
		}
	}

	public static void verifyFirstName(String firstName) {
		if(firstName == null || firstName.length() > 10) {
			throw new IllegalArgumentException("Contact First Name is non-nullable and must be 10 or less characters long");
		}
	}
	
	public static void verifyLastName(String lastName) {
		if(lastName == null || lastName.length() > 10) {
			throw new IllegalArgumentException("Contact Last Name is non-nullable and must be 10 or less characters long");
		}
	}
	
	public static void verifyPhoneNumber(String phoneNumber) {
		if(phoneNumber == null || phoneNumber.length() != 10) {
			throw new IllegalArgumentException("Contact Phone Number is non-nullable and must be 10 characters long");
		}
	}
	
	public static void verifyAddress(String address) {
		if(address == null || address.length() > 30) {
			throw new IllegalArgumentException("Contact Address is non-nullable and must be 30 or less characters long");
		}
	}
}
