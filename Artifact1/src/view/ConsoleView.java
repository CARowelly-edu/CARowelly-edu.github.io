package view;

import model.Appointment;
import model.Contact;
import model.Task;

import java.time.format.DateTimeFormatter;

/**
 * ConsoleView handles all user interaction through the console.
 * It displays information to the user and formats model data for presentation.
 */
public class ConsoleView {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	/**
	 * Displays the details of an Appointment in a readable format.
	 * 
	 * @param appointment the Appointment object to display
	 */
	public void displayAppointment(Appointment appointment) {
		if (appointment == null) {
			System.out.println("Appointment not found.");
		} else {
			System.out.println("Appointment ID: " + appointment.getAppointmentId());
			System.out.println("Date: " + appointment.getAppointmentDate().format(formatter));
			System.out.println("Description: " + appointment.getAppointmentDescription());
			System.out.println("------------------------------");
		}
	}
	
	/**
	 * Displays the details of a Contact in a readable format.
	 * 
	 * @param contact the Contact object to display
	 */
	public void displayContact(Contact contact) {
		if (contact == null) {
			System.out.println("Contact not found.");
		} else {
			System.out.println("Contact ID: " + contact.getContactId());
			System.out.println("Name: " + contact.getFirstName() + " " + contact.getLastName());
			System.out.println("Address: " + contact.getAddress());
			System.out.println("Phone: " + contact.getPhoneNumber());
			System.out.println("------------------------------");
		}
	}
	
	/**
	 * Displays the details of a Task in a readable format.
	 * 
	 * @param task the Task object to display
	 */
	public void displayTask(Task task) {
		if (task == null) {
			System.out.println("Task not found.");
		} else {
			System.out.println("Task ID: " + task.getTaskId());
			System.out.println("Name: " + task.getName());
			System.out.println("Description: " + task.getTaskDescription());
			System.out.println("------------------------------");
		}
	}
}
