package model;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class Appointment {
	//appointment contains...
	//type of medical appointment (ex. dentist, eye doctor, family doctor)
	protected String medicalSpecialistType;
	//doctor name (ex: dr.Proto)
	protected String doctorName;
	//location appointment is occurring at (ex: 21 fake st.)
	protected String location;
	//date of appointment 
	protected Date date;
	//time of appointment 
	protected Time time;
	
	//date and time formats; used to format date and time in various other parts of the program
	public static SimpleDateFormat longDateFormater = new SimpleDateFormat("EEEEE, MMMMM dd, yyyy");
	public static SimpleDateFormat shortDateFormater = new SimpleDateFormat("MM/dd/yyyy");
	public static SimpleDateFormat standardShortDateFormater = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat timeFormater = new SimpleDateFormat("hh:mm a");
	
	//if appointment is created with none of the relevant information; this constructor is used
	//same result as passing "null" for all information in the second constructor
	public Appointment() {
	}
	public Appointment(String medicalSpecialistType, String doctorName, String location, Date date, Time time) {
		//set the given information
		setMedicalSpecialistType(medicalSpecialistType);
		setDoctorName(doctorName);
		setLocation(location);
		setDate(date);
		setTime(time);
	}
	//getters and setters
	public String getMedicalSpecialistType() {
		return this.medicalSpecialistType;
	}
	public void setMedicalSpecialistType(String medicalSpecialistType) {
		this.medicalSpecialistType = medicalSpecialistType;
	}
	public String getDoctorName() {
		return this.doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
	//utility methods
	//used to copy information from existing appointment
	public void CopyFrom(Appointment appointment) {
		this.setMedicalSpecialistType(appointment.getMedicalSpecialistType());
		this.setDoctorName(appointment.getDoctorName());
		this.setLocation(appointment.getLocation());
		this.setDate(new Date(appointment.getDate().getTime()));
		this.setTime(new Time(appointment.getTime().getTime()));
	}
	//used to format dates
	public String getLongDateString() {
		return longDateFormater.format(this.getDate());
	}
	public String getShortDateString() {
		return shortDateFormater.format(this.getDate());
	}
	public String getStandardShortDateString() {
		return standardShortDateFormater.format(this.getDate());
	}
	public String getTimeString() {
		return timeFormater.format(this.getTime());
	}
	public String getSuggestionMessage() {
		return "";
	}
	public boolean isTodayOrFutureDate() {
		//check if date from suggested appointment is today
		Date now = new Date(new java.util.Date().getTime());
		String today = Appointment.standardShortDateFormater.format(now);
		
		//true if today is earlier than given appointment date
		return (today.compareTo(this.getStandardShortDateString()) <= 0);
	}
}