package src;

import java.util.HashMap;
import java.util.Set;

public class TaskService {
	
//Singleton
	
		private static TaskService TaskService_instance = null;
		
		//Task Identifier List
		private static final HashMap<String, Task> tasksList = new HashMap<String, Task>(); // maintains a set of mapped unique IDs
		
		/******************************************************************************************/
		
//Constructor
		private TaskService() {}
		
		/******************************************************************************************/
		
//Getters
		
		//Get Singleton Instance
		public static synchronized TaskService Instance() {
			if(TaskService_instance == null)
				TaskService_instance = new TaskService();
			
			return TaskService_instance;
		}
		
		//Get Task
		public static Set<String> getTaskIDs(){
			
			return TaskService.tasksList.keySet();
			
		}
		
		/******************************************************************************************/
		
//Methods
		public static Task addTask(String ID, String name, String description) {
			
			//Create new Task to add to Tasks List;
			Task newTask = new Task(ID, name, description);
			
			//Assign Task by Identifier to HashMap
			tasksList.put(newTask.getTaskID(), newTask);
			
			return newTask;
		}
		
		public static void addTask(Task newTask) {
			
			//Assign Task by Identifier to HashMap
			tasksList.put(newTask.getTaskID(), newTask);
			
		}
		
		public static void updateTask(String ID, String newName, String newDescription) {
			
			//Verification Calls
			Task.verifyTaskIDValid(ID);
			TaskService.verifyTaskExists(ID);
			
			if(newName != null) {
				Task.verifyTaskName(newName);
				tasksList.get(ID).setTaskName(newName);
			}
			
			if(newDescription != null) {
				Task.verifyTaskDescription(newDescription);
				tasksList.get(ID).setTaskDescription(newDescription);
			}

		}
		
		public static Task viewTaskByID(String ID) {
			return tasksList.get(ID);
		}
		
		public static void deleteTask(String ID) {
			tasksList.remove(ID);
		}
		
		public static void clearTasks() {
			tasksList.clear();
		}
		
//Validation
		
		public static boolean verifyTaskExists(String ID) {
			if(!TaskService.getTaskIDs().contains(ID)) {
				return false;
			}
			
			return true;
		}
}
