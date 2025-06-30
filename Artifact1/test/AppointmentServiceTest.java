package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Appointment;
import service.AppointmentService;

class AppointmentServiceTest {
	
	private static AppointmentService appointmentService;
	
	@BeforeAll
	static void setup() {
		appointmentService = AppointmentService.getService();
	}
	
	LocalDateTime date = LocalDateTime.of(2023,  9, 27, 9, 15);
	
	@Test
	void testAddAppointmentSuccess() {
		Appointment appointment = new Appointment("123456", date, "Test successful appointment creation");	
		assertTrue(appointmentService.addAppointment(appointment));
		
		Appointment getAppointment = appointmentService.getAppointment("123456");
		
		assertTrue(getAppointment.getAppointmentId().contentEquals(appointment.getAppointmentId()));
		assertTrue(getAppointment.getAppointmentDate().toString().contentEquals(appointment.getAppointmentDate().toString()));
		assertTrue(getAppointment.getAppointmentDescription().contentEquals(appointment.getAppointmentDescription()));

	}
	
	@Test
	void testAddMultipleAppointmentsSuccess() {
		Appointment appointment1 = new Appointment("123457", date, "Test successful appointment creation");
		Appointment appointment2 = new Appointment("123458", date, "Test successful appointment creation");
		
		assertTrue(appointmentService.addAppointment(appointment1));
		assertTrue(appointmentService.addAppointment(appointment2));

	}
	
	@Test
	void testAddAppointmentDuplicateIdFail() {
		Appointment appointment1 = new Appointment("123459", date, "Test successful appointment creation");
		Appointment appointment2 = new Appointment("123459", date, "Test successful appointment creation");
		
		assertTrue(appointmentService.addAppointment(appointment1));
		assertFalse(appointmentService.addAppointment(appointment2));

	}
	
	@Test
	void testGetAppointmentAndDeleteSuccess() {
		Appointment appointment1 = new Appointment("12", date, "Mike check 1 2");

		assertTrue(appointmentService.addAppointment(appointment1));
		
		Appointment appointment2 = appointmentService.getAppointment(appointment1.getAppointmentId());
		assertTrue(appointmentService.deleteAppointment(appointment2.getAppointmentId()));

		assertTrue(appointmentService.getAppointment(appointment2.getAppointmentId()) == null);
		
	}
	
	@Test
	void testDeleteInvalidAppointmentFail() {
		String invalidAppointmentId = "1";

		assertFalse(appointmentService.deleteAppointment(invalidAppointmentId));
	}

}
