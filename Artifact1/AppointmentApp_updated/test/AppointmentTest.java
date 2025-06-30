package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import model.Appointment;



class AppointmentTest {		
	
	LocalDateTime appointmentDate = LocalDateTime.of(2023,  9, 27, 9, 15);

	//Test Appointment create
	@Test
	void testCreateAppointmentSuccess() {
		Appointment appointment = new Appointment("123456", appointmentDate, "Test successful appointment creation");			
		assertTrue(appointment != null);
		assertTrue(appointment.getAppointmentId().equals("123456"));
		assertTrue(appointment.getAppointmentDate().toString().equals("2023-09-27T09:15"));
		assertTrue(appointment.getAppointmentDescription().equals("Test successful appointment creation"));
	}
	
	@Test
	void testCreateAppointmentAppointmentIdFails() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("12345678901", appointmentDate, "Test id length appointment creation failure");
		 });
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(null, appointmentDate, "Test id length appointment creation failure");
		 });
	}
	
	@Test
	void testCreateAppointmentDateFails() {
		LocalDateTime pastAppointmentDate = appointmentDate.withYear(2021);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("123456", pastAppointmentDate, "Test past appointmentDate appointment creation failure");
		});
		Assertions.assertThrows(NullPointerException.class, () -> {
			new Appointment("123456", null, "Test past appointmentDate appointment creation failure");
		});
	}
	
	@Test
	void testCreateAppointmentDescriptionFails() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("123456", appointmentDate, "Test appointment creation failure due to description length");
		});	
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("123456", appointmentDate, null);
		});	
	}	
}


