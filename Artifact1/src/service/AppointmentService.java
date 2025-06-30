package service;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import model.Appointment;

public class AppointmentService {
	
	private static AppointmentService reference = new AppointmentService();
	private final Map<String, Appointment> appointments;
	
	 AppointmentService() {
		 this.appointments = new HashMap<String, Appointment>();
	 }
	 
		//Create a singleton Appointment Service
	 public static AppointmentService getService() {
		 return reference;
	 }
	 
	 public boolean addAppointment(Appointment appointment) {
		 boolean isSuccess = false;
		 
		 if(!appointments.containsKey(appointment.getAppointmentId())) {
			 appointments.put(appointment.getAppointmentId(), appointment);
			 isSuccess = true;
		 }
		 return isSuccess;
	 }
	 
	 public boolean deleteAppointment(String appointmentId) {
		 return appointments.remove(appointmentId) != null;
	 }
	 
	 public Appointment getAppointment(String appointmentId) {
		 return appointments.get(appointmentId);
	 }
	 public List<Appointment> getAllAppointments() {
	        return new ArrayList<>(appointments.values());
	    }

}

