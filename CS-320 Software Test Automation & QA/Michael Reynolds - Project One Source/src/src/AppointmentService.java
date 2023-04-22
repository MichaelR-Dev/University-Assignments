package src;

import java.util.Date;
import java.util.HashMap;

public final class AppointmentService {
	//Variables
	
		//Singleton
		static private AppointmentService AppointmentService_instance = null;
		
		//Appointments List
		static private HashMap<String, Appointment> registeredAppointments = new HashMap<String, Appointment>();
		
			/******************************************************************************************/
	
	//Constructor
			private AppointmentService() {}
			
			/******************************************************************************************/
			
	//Getters
			
			//Get Singleton Instance
			public static synchronized AppointmentService Instance() {
				if(AppointmentService_instance == null)
					AppointmentService_instance = new AppointmentService();
				
				return AppointmentService_instance;
			}
			
			/******************************************************************************************/
	
	//Methods
			
			public static void AddAppointment(String ID, Date date, String Description) {
				VerifyUniqueID(ID);
				
				Appointment newAppointment = new Appointment(ID, date, Description);
				
				registeredAppointments.put(ID, newAppointment);
			};
				
			public static void AddAppointment(Appointment newAppointment) {
				VerifyUniqueID(newAppointment.getID());
				
				registeredAppointments.put(newAppointment.getID(), newAppointment);
			};
			
			public static Appointment GetAppointment(String getID) {
				Appointment.ValidateID(getID);
				
				return registeredAppointments.get(getID);
			};
			
			public static void RemoveAppointment(String ID) {
				Appointment.ValidateID(ID);
				VerifyIDExists(ID);
				
				registeredAppointments.remove(ID);
			};
			
			public static void ClearAppointments() {
				registeredAppointments.clear();
			};
		
			/******************************************************************************************/
	
	//Verify
			public static void VerifyUniqueID(String ID) throws IllegalArgumentException {
				if(registeredAppointments.containsKey(ID)) {
					throw new IllegalArgumentException("Invalid, ID is not Unique and an Appointment with this ID exists.");
				}
			}
			
			public static void VerifyIDExists(String ID) throws IllegalArgumentException {
				if(!registeredAppointments.containsKey(ID) || registeredAppointments.get(ID) == null) {
					throw new IllegalArgumentException("Invalid, ID does not exist in appointments list.");
				}
			}
		
			/******************************************************************************************/
}