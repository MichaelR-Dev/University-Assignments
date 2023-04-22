package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Contact;
import src.ContactService;

class ContactTest {
	
	String passID = "0001";
	String passFirstName = "Michael";
	String passLastName = "Reynolds";
	String passPhoneNumber = "4232313951";
	String passAddress = "777 AnyTown Rd, AnyState, USA";

	@BeforeEach
	void setup() {
		ContactService.Instance();
		ContactService.clearContacts();
	}
	
	@Test
	void testContactConstructor() {
		Contact ContactClass = new Contact(passID, passFirstName, passLastName, passPhoneNumber, passAddress);
		
		assertTrue(ContactClass.getID().equals(passID));
		assertTrue(ContactClass.getFirstName().equals(passFirstName));
		assertTrue(ContactClass.getLastName().equals(passLastName));
		assertTrue(ContactClass.getPhoneNumber().equals(passPhoneNumber));
		assertTrue(ContactClass.getAddress().equals(passAddress));
	}
	
	@Test
	void testContactSetters() {
		String newFirstName = "Howard";
		String newLastName = "Dashwood";
		String newPhoneNumber = "4489928514";
		String newAddress = "Albit Dr, Apt #3, NY, NY, USA";
		
		Contact ContactClass = new Contact(passID, passFirstName, passLastName, passPhoneNumber, passAddress);
		
		assertTrue(ContactClass.getID().equals(passID));
		assertTrue(ContactClass.getFirstName().equals(passFirstName));
		assertTrue(ContactClass.getLastName().equals(passLastName));
		assertTrue(ContactClass.getPhoneNumber().equals(passPhoneNumber));
		assertTrue(ContactClass.getAddress().equals(passAddress));
		
		ContactClass.setFirstName(newFirstName);
		ContactClass.setLastName(newLastName);
		ContactClass.setPhoneNumber(newPhoneNumber);
		ContactClass.setAddress(newAddress);
		
		assertTrue(ContactClass.getFirstName().equals(newFirstName));
		assertTrue(ContactClass.getLastName().equals(newLastName));
		assertTrue(ContactClass.getPhoneNumber().equals(newPhoneNumber));
		assertTrue(ContactClass.getAddress().equals(newAddress));
	}
	
	@Test
	void testContactIDDuplicate() {
		
		Contact OriginalContactClass = ContactService.addContact(passID, passFirstName, passLastName, passPhoneNumber, passAddress);

		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("0001", passFirstName, passLastName, passPhoneNumber, passAddress);
		});
		
		assertTrue(OriginalContactClass != null);
		
	}
	
	@Test
	void testContactDetailsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(null, passFirstName, passLastName, passPhoneNumber, passAddress);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(passID, null, passLastName, passPhoneNumber, passAddress);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(passID, passFirstName, null, passPhoneNumber, passAddress);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(passID, passFirstName, passLastName, null, passAddress);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(passID, passFirstName, passLastName, passPhoneNumber, null);
		});
		
		Contact ContactClass = new Contact(passID, passFirstName, passLastName, passPhoneNumber, passAddress);
		
		assertTrue(ContactClass != null);
	}
	
	@Test
	void testContactDetailsTooLong() {
		String longString = "0123456789012345678901234567890";
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(longString, passFirstName, passLastName, passPhoneNumber, passAddress);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(passID, longString, passLastName, passPhoneNumber, passAddress);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(passID, passFirstName, longString, passPhoneNumber, passAddress);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(passID, passFirstName, passLastName, longString, passAddress);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(passID, passFirstName, passLastName, passPhoneNumber, longString);
		});
		
	}
	
	@Test
	void testContactPhoneNumberTooShort() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(passID, passFirstName, passLastName, "123456789", passAddress);
		});
	}

}
