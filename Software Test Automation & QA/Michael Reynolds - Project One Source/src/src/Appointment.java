package src;

import java.util.Date;

public class Appointment {
	
//Unique Final Non-nullable appointment ID <= 10 length
	final private String appointment_ID;
	
//Non-nullable present/future appointment date;
	private Date appointment_Date;
	
//Non-nullable <= 50 length
	private String appointment_Description;
	
	public Appointment(String ID, Date date, String description) {
		
		//Validation
		ValidateID(ID);
		AppointmentService.VerifyUniqueID(ID);
		ValidateDate(date);
		ValidateDescription(description);
		
		this.appointment_ID = ID;
		this.appointment_Date = date;
		this.appointment_Description = description;
		
	}
	
//Getters
	public String getID() {
		return this.appointment_ID;
	}
	
	public Date getDate() {
		return this.appointment_Date;
	}
	
	public String getDescription() {
		return this.appointment_Description;
	}
	
//Setters
	public void setDate(Date newDate) {
		
		ValidateDate(newDate);

		this.appointment_Date = newDate;
		
	}
	
	public void setDescription(String newDescription) {
		
		ValidateDescription(newDescription);
		
		this.appointment_Description = newDescription;
		
	}
	
//Validators
	static void ValidateID(String ID) throws IllegalArgumentException {
		if( ID == null || ID.length() > 10) {
			throw new IllegalArgumentException("ID field must NOT be greater than 10 length and cannot be null.");
		}
	}
	
	static void ValidateDate(Date date) throws IllegalArgumentException {
		if( date == null || date.before(new Date())) {
			throw new IllegalArgumentException("Date field must NOT be past and cannot be null.");
		}
	}
	
	static void ValidateDescription(String description) throws IllegalArgumentException {
		if( description == null || description.length() > 50) {
			throw new IllegalArgumentException("Description field must NOT be greater than 50 length and cannot be null.");
		}
	}
	
}