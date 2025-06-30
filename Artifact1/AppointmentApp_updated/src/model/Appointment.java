package model;

import java.time.LocalDateTime;

public class Appointment {
	
	private String appointmentId;
	private LocalDateTime appointmentDate;
	private String appointmentDescription;
	
	
	public Appointment(String appointmentId, LocalDateTime appointmentDate, String appointmentDescription) {
			
			//valiappointmentDate inputs against requirements
			boolean validId = appointmentId != null && appointmentId.length() <= 10;
				
			if(validId) {
				this.appointmentId = appointmentId;
			}

			boolean validAppointmentDate = appointmentDate.isAfter(LocalDateTime.now()) && appointmentDate != null;
			
			if(validAppointmentDate) {
				this.appointmentDate = appointmentDate;
			}
			
			boolean validAppointmentDescription = appointmentDescription != null && appointmentDescription.length() <= 50;
			
			if(validAppointmentDescription) {
				this.appointmentDescription = appointmentDescription;
			}
			
			if(!validId || !validAppointmentDate || !validAppointmentDescription) {
				throw new IllegalArgumentException("Invalid input");
			}
	}
	
	public String getAppointmentId() {
		return appointmentId;
	}
	
	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}
	
	public String getAppointmentDescription() {
		return appointmentDescription;
	}

}

