package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Task;
import service.TaskService;


class TaskServiceTest {

	private static TaskService taskService;
	
	@BeforeAll
	static void setup() {
		taskService = TaskService.getService();
	}
	
	@Test
	void testAddTaskSuccess() {
		Task task = new Task("123456", "Bob", "Mike check 1 2");	
		assertTrue(taskService.addTask(task));
		
		Task getTask = taskService.getTask("123456");
		
		assertTrue(getTask.getTaskId().contentEquals(task.getTaskId()));
		assertTrue(getTask.getName().contentEquals(task.getName()));
		assertTrue(getTask.getDescription().contentEquals(task.getDescription()));

	}
	
	@Test
	void testAddMultipleTasksSuccess() {
		Task task1 = new Task("123458", "Bob", "Mike check 1 2");
		Task task2 = new Task("123459", "Sue", "Mike check 1 2");
		
		assertTrue(taskService.addTask(task1));
		assertTrue(taskService.addTask(task2));

	}
	
	@Test
	void testAddTaskDuplicateIdFail() {
		Task task1 = new Task("123457", "Bob", "Mike check 1 2");
		Task task2 = new Task("123457", "Sue", "Mike check 1 2");
		
		assertTrue(taskService.addTask(task1));
		assertFalse(taskService.addTask(task2));

	}
	
	@Test
	void testGetTaskAndUpdateSuccess() {
		Task task1 = new Task("1234", "Bob", "Mike check 1 2");

		assertTrue(taskService.addTask(task1));
		Task updateTask = taskService.getTask(task1.getTaskId());
		
		updateTask.setName("Sue");
		updateTask = taskService.getTask(updateTask.getTaskId());
		assertTrue(updateTask.getName().equals("Sue"));
		
	}
	
	@Test
	void testGetTaskAndDeleteSuccess() {
		Task task1 = new Task("12", "Bob", "Mike check 1 2");

		assertTrue(taskService.addTask(task1));
		
		Task task2 = taskService.getTask(task1.getTaskId());
		assertTrue(taskService.deleteTask(task2.getTaskId()));

		assertTrue(taskService.getTask(task2.getTaskId()) == null);
		
	}
	
	@Test
	void testDeleteInvalidTaskFail() {
		String invalidTaskId = "1";

		assertFalse(taskService.deleteTask(invalidTaskId));
	}

}
