package src;

import java.util.HashMap;
import java.util.Set;

public class ContactService {
	
	//Singleton
	private static ContactService ContactService_instance = null;
	
	//Contact Identifier List
	private static final HashMap<String, Contact> contactsList = new HashMap<String, Contact>(); // maintains a set of mapped unique IDs
	
	/******************************************************************************************/
	
	//Constructor
	private ContactService() {}
	
	/******************************************************************************************/
	
	//Getters
	//Get Singleton Instance
	public static synchronized ContactService Instance() {
		if(ContactService_instance == null)
			ContactService_instance = new ContactService();
		
		return ContactService_instance;
	}
	
	//Get Contacts
	public static Set<String> getContactIDs(){
		
		return ContactService.contactsList.keySet();
		
	}
	
	/******************************************************************************************/
	
	//Methods
	public static Contact addContact(String ID, String firstName, String lastName, String phone, String address) {
		//Create new contact to add to Contacts List;
		Contact newContact = new Contact(ID, firstName, lastName, phone, address);
		
		//Assign Contact by Identifier to HashMap
		contactsList.put(newContact.getID(), newContact);
		
		return newContact;
	}
	
	public static void addContact(Contact newContact) {
		
		//Assign Contact by Identifier to HashMap
		contactsList.put(newContact.getID(), newContact);
		
	}
	
	public static void updateContact(String ID, String newFirstName, String newLastName, String newPhoneNumber, String newAddress) {
		
		//Verification
		Contact.verifyIDValid(ID);
		
		if(newFirstName != null) {
			Contact.verifyFirstName(newFirstName);
			contactsList.get(ID).setFirstName(newFirstName);
		}
		
		if(newLastName != null) {
			Contact.verifyLastName(newLastName);
			contactsList.get(ID).setLastName(newLastName);
		}
		
		if(newPhoneNumber != null) {
			Contact.verifyPhoneNumber(newPhoneNumber);
			contactsList.get(ID).setPhoneNumber(newPhoneNumber);
		}
		
		if(newAddress != null) {
			Contact.verifyAddress(newAddress);
			contactsList.get(ID).setAddress(newAddress);
		}

	}
	
	public static Contact viewContactByID(String ID) {
		
		return contactsList.get(ID);
	}
	
	public static void deleteContact(String ID) {
		//Delete contact by Identifier to HashMap
		contactsList.remove(ID);
	}
	
	public static void clearContacts() {
		contactsList.clear();
	}
	
	//Validation

	public static boolean verifyContactExists(String ID) {
		
		if(!ContactService.getContactIDs().contains(ID)) {
			return false;
		}
		
		return true;
	}
}
