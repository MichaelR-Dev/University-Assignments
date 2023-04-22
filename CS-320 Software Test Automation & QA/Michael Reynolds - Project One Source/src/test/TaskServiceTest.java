package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Task;
import src.TaskService;

public class TaskServiceTest {
	
	String passID = "0001";
	String passName = "Schedule appt";
	String passDescription = "Schedule Doctor's Apt";

	@BeforeAll
	static void setupAll() {
		TaskService.Instance();
	}
	
	@BeforeEach
	void setupEach() {
		TaskService.clearTasks();
	}
	
	@Test
	void testAddTask() {
		
		TaskService.addTask(passID, passName, passDescription);
		
		assertTrue(TaskService.verifyTaskExists(passID));
		assertTrue(TaskService.viewTaskByID(passID) != null);
		
		
	}
	
	@Test
	void testAddExistingTask() {
		
		Task newTask = new Task(passID, passName, passDescription);
		
		TaskService.addTask(newTask);
		
		assertTrue(TaskService.verifyTaskExists(passID));
		assertTrue(TaskService.viewTaskByID(passID) != null);
		
	}
	
	@Test
	void testDuplicateIDTask() {
		
		String newName = "Do laundry";
		String newDescription = "Do the laundry before 8pm";
		
		TaskService.addTask(passID, passName, passDescription);
		
		assertTrue(TaskService.verifyTaskExists(passID));
		assertTrue(TaskService.viewTaskByID(passID) != null);
		
		assertThrows(IllegalArgumentException.class, () -> {
			TaskService.addTask(passID, newName, newDescription);
		});
		
	}
	
	@Test
	void testDeleteTaskByID() {
		TaskService.addTask(passID, passName, passDescription);
		
		assertTrue(TaskService.verifyTaskExists(passID));
		assertTrue(TaskService.viewTaskByID(passID) != null);
		
		TaskService.deleteTask(passID);
		
		assertFalse(TaskService.verifyTaskExists(passID));
		assertFalse(TaskService.viewTaskByID(passID) != null);
	}
	
	@Test
	void testUpdateTaskByID() {
		
		String newName = "Do laundry";
		String newDescription = "Do the laundry before 8pm";
		
		TaskService.addTask(passID, passName, passDescription);
		
		Task TaskClass = TaskService.viewTaskByID(passID);
		
		assertTrue(TaskClass.getTaskID().equals(passID));
		assertTrue(TaskClass.getTaskName().equals(passName));
		assertTrue(TaskClass.getTaskDescription().equals(passDescription));
		
		TaskService.updateTask(passID, newName, newDescription);
		
		assertTrue(TaskClass.getTaskID().equals(passID));
		assertTrue(TaskClass.getTaskName().equals(newName));
		assertTrue(TaskClass.getTaskDescription().equals(newDescription));
	}
}
