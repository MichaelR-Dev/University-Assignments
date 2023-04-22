package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Ignore;
import org.junit.jupiter.api.*;

import src.*;

class AppointmentTest { 
	
	String passID = "0001";
	Date passDate = Date.from( LocalDate.of(2024, 4, 4).atStartOfDay( ZoneId.of( "Africa/Tunis" )).toInstant());
	String passDescription = "Scheduled Doctor's Apt";
	
	//Appointment Constructor Test
	@Test
	void testAppointmentConstructValid() {
		
		Appointment appointment = new Appointment(passID, passDate, passDescription);
		
		assertTrue(appointment.getID().equals(passID));
		assertTrue(appointment.getDate().equals(passDate));
		assertTrue(appointment.getDescription().equals(passDescription));
		
	}
	
	//Appointment Setters Tests
	@Test
	void testAppointmentSetDate() {
		
		Date newDate = Date.from( LocalDate.of(2025, 4, 4).atStartOfDay( ZoneId.of( "Africa/Tunis" )).toInstant());
		
		Appointment appointment = new Appointment(passID, passDate, passDescription);
		
		assertTrue(appointment.getID().equals(passID));
		assertTrue(appointment.getDate().equals(passDate));
		assertTrue(appointment.getDescription().equals(passDescription));
		
		appointment.setDate(newDate);
		
		assertTrue(appointment.getID().equals(passID));
		assertTrue(appointment.getDate().equals(newDate));
		assertTrue(appointment.getDescription().equals(passDescription));
		
	};
	
	@Test
	void testAppointmentSetDescription() {
		
		String newDescription = "New Description!";
		
		Appointment appointment = new Appointment(passID, passDate, passDescription);
		
		assertTrue(appointment.getID().equals(passID));
		assertTrue(appointment.getDate().equals(passDate));
		assertTrue(appointment.getDescription().equals(passDescription));
		
		appointment.setDescription(newDescription);
		
		assertTrue(appointment.getID().equals(passID));
		assertTrue(appointment.getDate().equals(passDate));
		assertTrue(appointment.getDescription().equals(newDescription));
		
	};
	
	//Appointment ID Tests
	
	@Test
	void testAppointmentInvalidIDLength() {
		String invalidID = "012345678910";
		
		assertThrows(IllegalArgumentException.class, () -> {
			Appointment appointment = new Appointment(invalidID, passDate, passDescription);
		});
	}
	
	@Test
	void testAppointmentInvalidIDNull() {
		
		String invalidID = null;
		
		assertThrows(IllegalArgumentException.class, () -> {
			Appointment appointment = new Appointment(invalidID, passDate, passDescription);
		});
		
	}
	
	//Appointment Date Tests
	
	@Test
	void testAppointmentInvalidDatePast() {
		Date invalidDate = Date.from( LocalDate.of(2020, 4, 4).atStartOfDay( ZoneId.of( "Africa/Tunis" )).toInstant());
		
		assertThrows(IllegalArgumentException.class, () -> {
			Appointment appointment = new Appointment(passID, invalidDate, passDescription);
		});
	}
	
	@Test
	void testAppointmentInvalidDateNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Appointment appointment = new Appointment(passID, null, passDescription);
		});
	}
	
	//Appointment Description Tests
	
	@Test
	void testAppointmentInvalidDescriptionLength() {
		String invalidDescription = "201392018491284908120948921840981290382109380192839012839021803982190382190392180";
		
		assertThrows(IllegalArgumentException.class, () -> {
			Appointment appointment = new Appointment(passID, passDate, invalidDescription);
		});
	}
	
	@Test
	void testAppointmentInvalidDescriptionNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Appointment appointment = new Appointment(passID, passDate, null);
		});
	}


}
