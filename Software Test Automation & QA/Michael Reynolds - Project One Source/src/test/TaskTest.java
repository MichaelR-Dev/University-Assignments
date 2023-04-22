package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Task;
import src.TaskService;

class TaskTest {

	String passID = "0001";
	String passName = "Schedule appt";
	String passDescription = "Schedule Doctor's Apt";

	@BeforeEach
	void setup() {
		
		TaskService.Instance();
		TaskService.clearTasks();
		
	}
	
	@Test
	void testTaskConstructor() {
		
		Task TaskClass = new Task(passID, passName, passDescription);
		
		assertTrue(TaskClass.getTaskID().equals(passID));
		assertTrue(TaskClass.getTaskName().equals(passName));
		assertTrue(TaskClass.getTaskDescription().equals(passDescription));
		
	}
	
	@Test
	void testTaskSetters() {
		
		String newName = "Do laundry";
		String newDescription = "Do the laundry before 8pm";
		
		Task TaskClass = new Task(passID, passName, passDescription);
		
		assertTrue(TaskClass.getTaskID().equals(passID));
		assertTrue(TaskClass.getTaskName().equals(passName));
		assertTrue(TaskClass.getTaskDescription().equals(passDescription));
		
		TaskClass.setTaskName(newName);
		TaskClass.setTaskDescription(newDescription);
		
		assertTrue(TaskClass.getTaskName().equals(newName));
		assertTrue(TaskClass.getTaskDescription().equals(newDescription));
		
	}
	
	@Test
	void testTaskIDDuplicate() {
		
		Task OriginalTaskClass = TaskService.addTask(passID, passName, passDescription);

		assertThrows(IllegalArgumentException.class, () -> {
			new Task("0001", passName, passDescription);
		});
		
		assertTrue(OriginalTaskClass != null);
		
	}
	
	@Test
	void testTaskDetailsNull() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(null, passName, passDescription);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(passID, null, passDescription);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(passID, passName, null);
		});
		
		Task TaskClass = new Task(passID, passName, passDescription);
		
		assertTrue(TaskClass != null);
	}
	
	@Test
	void testTaskDetailsTooLong() {
		String longString = "0123456789012345678901234567890012345678901234567892";
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(longString, passName, passDescription);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(passID, longString, passDescription);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(passID, passName, longString);
		});
		
	}
}
