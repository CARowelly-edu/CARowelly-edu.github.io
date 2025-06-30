package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.AppController;
import model.Appointment;
import model.Contact;
import model.Task;

import java.time.LocalDateTime;

/**
 * This class tests the functionality of the AppController class,
 * which serves as the central coordinator for managing Appointments,
 * Contacts, and Tasks in the application.
 */
class AppControllerTest {

    private AppController controller;

    /**
     * Sets up a new instance of AppController before each test.
     */
    @BeforeEach
    void setUp() {
        controller = new AppController();
    }

    /**
     * Tests adding and retrieving an Appointment.
     * Ensures the added appointment is successfully stored and can be retrieved.
     */
    @Test
    void testAddAndGetAppointment() {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(1);
        Appointment appointment = new Appointment("A123", futureDate, "Dentist Visit");

        assertTrue(controller.addAppointment(appointment)); // Check successful addition
        Appointment fetched = controller.getAppointment("A123");

        assertNotNull(fetched); // Ensure it was stored
        assertEquals("A123", fetched.getAppointmentId()); // Validate correct retrieval
    }

    /**
     * Tests deleting an existing Appointment and confirms it's no longer available.
     */
    @Test
    void testDeleteAppointment() {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(2);
        Appointment appointment = new Appointment("A124", futureDate, "Meeting");

        controller.addAppointment(appointment);
        assertTrue(controller.deleteAppointment("A124")); // Check deletion success
        assertNull(controller.getAppointment("A124")); // Ensure it’s gone
    }

    /**
     * Tests adding and retrieving a Contact.
     * Confirms the contact is correctly stored and data matches.
     */
    @Test
    void testAddAndGetContact() {
        Contact contact = new Contact("C123", "Jane", "Doe", "123 Main St", "5551234567");

        assertTrue(controller.addContact(contact)); // Add contact
        Contact fetched = controller.getContact("C123");

        assertNotNull(fetched); // Make sure it's not null
        assertEquals("Jane", fetched.getFirstName()); // Validate stored data
    }

    /**
     * Tests deleting a Contact and confirms the contact no longer exists.
     */
    @Test
    void testDeleteContact() {
        Contact contact = new Contact("C124", "John", "Smith", "456 Elm St", "5559876543");

        controller.addContact(contact);
        assertTrue(controller.deleteContact("C124")); // Delete contact
        assertNull(controller.getContact("C124")); // Ensure contact was deleted
    }

    /**
     * Tests adding and retrieving a Task.
     * Validates that the task was saved and fields are correct.
     */
    @Test
    void testAddAndGetTask() {
        Task task = new Task("T123", "Clean", "Clean the house");

        assertTrue(controller.addTask(task)); // Add task
        Task fetched = controller.getTask("T123");

        assertNotNull(fetched); // Ensure it's saved
        assertEquals("Clean", fetched.getName()); // Confirm correct data
    }

    /**
     * Tests deleting a Task and ensures it’s removed from the system.
     */
    @Test
    void testDeleteTask() {
        Task task = new Task("T124", "Workout", "30-minute run");

        controller.addTask(task);
        assertTrue(controller.deleteTask("T124")); // Delete task
        assertNull(controller.getTask("T124")); // Confirm deletion
    }
}
