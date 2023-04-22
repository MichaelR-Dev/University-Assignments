package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Appointment;
import src.AppointmentService;

class AppointmentServiceTest { 
	
	String passID = "0001";
	Date passDate = Date.from( LocalDate.of(2024, 4, 4).atStartOfDay( ZoneId.of( "Africa/Tunis" )).toInstant());
	String passDescription = "Scheduled Doctor's Apt";
	
	@BeforeEach
	void BeforeClass() {
		AppointmentService.Instance();
		AppointmentService.ClearAppointments();
	}
	
	@Test
	void testAppointmentServiceAddUniqueIDAppointment() {
		
		assertTrue(AppointmentService.GetAppointment(passID) == null);
		
		Appointment newAppointment = new Appointment(passID, passDate, passDescription);
		AppointmentService.AddAppointment(newAppointment);
		
		assertTrue(AppointmentService.GetAppointment(passID) != null);
		
	}
	
	@Test
	void testAppointmentServiceAddConstructedAppointment() {
		assertTrue(AppointmentService.GetAppointment(passID) == null);
		
		AppointmentService.AddAppointment(passID, passDate, passDescription);
		
		assertTrue(AppointmentService.GetAppointment(passID) != null);
	};
	
	@Test
	void testAppointmentServiceRemoveAppointmentbyID() {
		
		assertTrue(AppointmentService.GetAppointment(passID) == null);
		
		Appointment newAppointment = new Appointment(passID, passDate, passDescription);
		AppointmentService.AddAppointment(newAppointment);
		
		assertTrue(AppointmentService.GetAppointment(passID) != null);
		
		AppointmentService.RemoveAppointment(passID);
		
		assertTrue(AppointmentService.GetAppointment(passID) == null);
		
	}
	
	@Test
	void testFailAppointmentServiceAddAppointmentDuplicateID() {
		
		assertTrue(AppointmentService.GetAppointment(passID) == null);
		
		Appointment newAppointment = new Appointment(passID, passDate, passDescription);
		AppointmentService.AddAppointment(newAppointment);
		
		assertTrue(AppointmentService.GetAppointment(passID) != null);
		
		assertThrows(IllegalArgumentException.class, () -> {
			Appointment duplicateAppointment = new Appointment(passID, passDate, passDescription);
		});
		
	}
	
	@Test
	void testFailAppointmentServiceRemoveAppointmentNonExistant() {

		assertThrows(IllegalArgumentException.class, () -> {
			AppointmentService.RemoveAppointment(passID);
		});
		
	}


}
