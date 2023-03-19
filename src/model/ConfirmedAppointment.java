package model;

import java.sql.Date;
import java.sql.Time;

public class ConfirmedAppointment extends Appointment{
	//if appointment is created with none of the relevant information; this constructor is used
	//same result as passing "null" for all information in the second constructor
	public ConfirmedAppointment() {
	}
	//constructor
	public ConfirmedAppointment(String medicalSpecialistType, String doctorName, String location, Date date, Time time) {
		super(medicalSpecialistType, doctorName, location, date, time);
	}
}
