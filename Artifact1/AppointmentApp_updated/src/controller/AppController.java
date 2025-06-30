package controller;

import model.Appointment;
import model.Contact;
import model.Task;
import service.AppointmentService;
import service.ContactService;
import service.TaskService;

import java.util.List;
import java.util.ArrayList;

/**
 * AppController acts as the main controller in the MVC pattern.
 * It handles user actions by managing interactions between the view and model.
 * This class delegates data operations to service classes and updates the view accordingly.
 */
public class AppController {
	
	private AppointmentService appointmentService;
	private ContactService contactService;
	private TaskService taskService;
	
	/**
	 * Constructor initializes the controller with references to the service classes.
	 */
	public AppController() {
		this.appointmentService = AppointmentService.getService();
		this.contactService = ContactService.getService();
		this.taskService = TaskService.getService();
	}
	
	/**
	 * Adds a new Appointment by delegating to the AppointmentService.
	 * 
	 * @param appointment the Appointment object to add
	 * @return true if added successfully, false if ID exists
	 */
	public boolean addAppointment(Appointment appointment) {
		return appointmentService.addAppointment(appointment);
	}
	
	/**
	 * Deletes an appointment by ID.
	 * 
	 * @param appointmentId the ID of the appointment to delete
	 * @return true if deleted, false if not found
	 */
	public boolean deleteAppointment(String appointmentId) {
		return appointmentService.deleteAppointment(appointmentId);
	}
	
	/**
	 * Retrieves an appointment by ID.
	 * 
	 * @param appointmentId the ID of the appointment to retrieve
	 * @return the Appointment object or null if not found
	 */
	public Appointment getAppointment(String appointmentId) {
		return appointmentService.getAppointment(appointmentId);
	}
	
	/**
	 * Adds a new Contact via ContactService.
	 * 
	 * @param contact the Contact object to add
	 * @return true if added, false if ID exists
	 */
	public boolean addContact(Contact contact) {
		return contactService.addContact(contact);
	}
	
	/**
	 * Deletes a contact by ID.
	 * 
	 * @param contactId the ID of the contact to delete
	 * @return true if deleted, false if not found
	 */
	public boolean deleteContact(String contactId) {
		return contactService.deleteContact(contactId);
	}
	
	/**
	 * Retrieves a contact by ID.
	 * 
	 * @param contactId the ID of the contact to retrieve
	 * @return the Contact object or null if not found
	 */
	public Contact getContact(String contactId) {
		return contactService.getContact(contactId);
	}
	
	/**
	 * Adds a new Task via TaskService.
	 * 
	 * @param task the Task object to add
	 * @return true if added, false if ID exists
	 */
	public boolean addTask(Task task) {
		return taskService.addTask(task);
	}
	
	/**
	 * Deletes a task by ID.
	 * 
	 * @param taskId the ID of the task to delete
	 * @return true if deleted, false if not found
	 */
	public boolean deleteTask(String taskId) {
		return taskService.deleteTask(taskId);
	}
	
	/**
	 * Retrieves a task by ID.
	 * 
	 * @param taskId the ID of the task to retrieve
	 * @return the Task object or null if not found
	 */
	public Task getTask(String taskId) {
		return taskService.getTask(taskId);
	}

	/**
	 * Retrieves all appointments.
	 * 
	 * @return a list of all Appointment objects
	 */
	public List<Appointment> getAllAppointments() {
	    return appointmentService.getAllAppointments();
	}
}
