package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Contact;
import src.ContactService;

class ContactServiceTest {
	
	String passID = "0001";
	String passFirstName = "Michael";
	String passLastName = "Reynolds";
	String passPhoneNumber = "4232313951";
	String passAddress = "777 AnyTown Rd, AnyState, USA";

	@BeforeAll
	static void setupAll() {
		ContactService.Instance();
	}
	
	@BeforeEach
	void setupEach() {
		ContactService.clearContacts();
	}
	
	@Test
	void testAddContact() {
		ContactService.addContact(passID, passFirstName, passLastName, passPhoneNumber, passAddress);
		
		assertTrue(ContactService.verifyContactExists(passID));
		assertTrue(ContactService.viewContactByID(passID) != null);
	}
	
	@Test
	void testAddExistingContact() {
		
		Contact newContact = new Contact(passID, passFirstName, passLastName, passPhoneNumber, passAddress);
		
		ContactService.addContact(newContact);
		
		assertTrue(ContactService.verifyContactExists(passID));
		assertTrue(ContactService.viewContactByID(passID) != null);
		
	}
	
	@Test
	void testDuplicateIDContact() {
		
		String newFirstName = "Howard";
		String newLastName = "Dashwood";
		String newPhoneNumber = "4489928514";
		String newAddress = "Albit Dr, Apt #3, NY, NY, USA";
		
		ContactService.addContact(passID, passFirstName, passLastName, passPhoneNumber, passAddress);
		
		assertTrue(ContactService.verifyContactExists(passID));
		assertTrue(ContactService.viewContactByID(passID) != null);
		
		assertThrows(IllegalArgumentException.class, () -> {
			ContactService.addContact(passID, newFirstName, newLastName, newPhoneNumber, newAddress);
		});
		
	}
	
	@Test
	void testDeleteContactByID() {
		ContactService.addContact(passID, passFirstName, passLastName, passPhoneNumber, passAddress);
		
		assertTrue(ContactService.verifyContactExists(passID));
		assertTrue(ContactService.viewContactByID(passID) != null);
		
		ContactService.deleteContact(passID);
		
		assertFalse(ContactService.verifyContactExists(passID));
		assertFalse(ContactService.viewContactByID(passID) != null);
	}
	
	@Test
	void testUpdateContactByID() {
		String newFirstName = "Howard";
		String newLastName = "Dashwood";
		String newPhoneNumber = "4489928514";
		String newAddress = "Albit Dr, Apt #3, NY, NY, USA";
		
		ContactService.addContact(passID, passFirstName, passLastName, passPhoneNumber, passAddress);
		
		Contact ContactClass = ContactService.viewContactByID(passID);
		
		assertTrue(ContactClass.getID().equals(passID));
		assertTrue(ContactClass.getFirstName().equals(passFirstName));
		assertTrue(ContactClass.getLastName().equals(passLastName));
		assertTrue(ContactClass.getPhoneNumber().equals(passPhoneNumber));
		assertTrue(ContactClass.getAddress().equals(passAddress));
		
		ContactService.updateContact(passID, newFirstName, newLastName, newPhoneNumber, newAddress);
		
		assertTrue(ContactClass.getID().equals(passID));
		assertTrue(ContactClass.getFirstName().equals(newFirstName));
		assertTrue(ContactClass.getLastName().equals(newLastName));
		assertTrue(ContactClass.getPhoneNumber().equals(newPhoneNumber));
		assertTrue(ContactClass.getAddress().equals(newAddress));
	}

}
