package src;


public class Task {

	//Task Unique Identifier
	final String taskID;
	
	//Task Details
	String taskName;
	String taskDescription;
	
	/******************************************************************************************/
	
//Constructor
	
	public Task(String ID, String name, String description){
		
		verifyTaskIDValid(ID);
		verifyTaskIDUnique(ID);
		verifyTaskName(name);
		verifyTaskDescription(description);
		
		this.taskID = ID;
		this.taskName = name;
		this.taskDescription = description;
		
	}
	
	/******************************************************************************************/
	
//Getters
	
	public String getTaskID() {
		
		return this.taskID;
	}
	
	public String getTaskName() {
		
		return this.taskName;
	}
	
	public String getTaskDescription() {
		
		return this.taskDescription;
	}
	
	/******************************************************************************************/
	
//Setters
	
	public void setTaskName(String newName) {
		verifyTaskName(newName);
		this.taskName = newName;
	}
	
	public void setTaskDescription(String newDescription) {
		verifyTaskDescription(newDescription);
		this.taskDescription = newDescription;
	}
	
	/******************************************************************************************/
	
//Validation
	
	//Verify Unique ID for Contact Constructor
	public static boolean verifyTaskIDUnique(String ID) {
		if(ID == null || ID.length() > 10 || TaskService.getTaskIDs().contains(ID)) {
			throw new IllegalArgumentException("Task ID must be Unique, is non-nullable, and must be 10 or less characters long");
		}
		
		return true;
	}
	
	public static boolean verifyTaskIDValid(String ID) {
		if(ID == null || ID.length() > 10) {
			throw new IllegalArgumentException("Task ID must be non-nullable, and must be 10 or less characters long");
		}
		
		return true;
	}
	
	public static boolean verifyTaskName(String name) {
		if(name == null || name.length() > 20) {
			throw new IllegalArgumentException("Task Name is non-nullable, and must be 20 or less characters long");
		}
		
		return true;
	}
	
	public static boolean verifyTaskDescription(String description) {
		if(description == null || description.length() > 50) {
			throw new IllegalArgumentException("Task Description is non-nullable, and must be 50 or less characters long");
		}
		
		return true;
	}
}
