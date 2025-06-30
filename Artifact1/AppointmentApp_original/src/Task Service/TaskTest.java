package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import model.Task;

class TaskTest {

	//Test Task create
	@Test
	void testCreateTaskSuccess() {
		Task task = new Task("123456", "Bob", "Test successful task creation");	
		
		assertTrue(task != null);
		assertTrue(task.getTaskId().equals("123456"));
		assertTrue(task.getName().equals("Bob"));
		assertTrue(task.getDescription().equals("Test successful task creation"));
	}
	
	@Test
	void testCreateTaskTaskIdFails() {
		  Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  new Task("12345678901", "Bob", "Test task creation failure due to taskId length");
		    });	
	}
	
	@Test
	void testCreateNameFails() {
		  Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  new Task("123456", "My name is Inigo Montoya", "Test task creation failure due to name length");
		    });	
	}
	
	@Test
	void testCreateDescriptionFails() {
		  Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  new Task("123456", "Bob", "Test task creation failure due to description length");
		    });	
	}	
	
	//Test Task Update
	
	@Test
	void testUpdateTaskSuccess() {
		Task task = new Task("123456", "Bob", "Test successful task update");	
		
		task.setName("Jeff");
		task.setDescription("Test task updates");
		
		assertTrue(task.getTaskId().equals("123456"));
		assertTrue(task.getName().equals("Jeff"));
		assertTrue(task.getDescription().equals("Test task updates"));
	}
	
	@Test
	void testUpdateNameFails() {
		Task task = new Task("123456", "Bob", "Test task updates");	
		assertFalse(task.setName("My name is Inigo Montoya"));
	}
	
	@Test
	void testUpdateDescriptionFails() {
		Task task = new Task("123456", "Bob", "Test task updates");	
		assertFalse(task.setDescription("Test a task update failure due to description length"));
	}
}
