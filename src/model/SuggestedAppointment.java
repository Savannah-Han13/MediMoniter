package model;

import java.sql.Date;
import java.sql.Time;

public class SuggestedAppointment extends Appointment{
	//if appointment is created with none of the relevant information; this constructor is used
	//same result as passing "null" for all information in the second constructor
	public SuggestedAppointment() {	
	}
	//constructor
	public SuggestedAppointment(String medicalSpecialistType, String doctorName, String location, Date date, Time time) {
		super(medicalSpecialistType, doctorName, location, date, time);
	}
	public static int getMedicalAppointmentInterval(String medicalSpecialistType) {
		//each medical specialist type has a interval of time between the next and most recent appointment
		int interval = 0;
		switch(medicalSpecialistType) {
			case "Dentist": 
				//dentist has interval of 180 days (~6 months)
				interval = 180;
			break;
			case "Eye Doctor":
				//eye doctor has interval of 730 days (~2 years)
				interval = 730;
			break;
			case "Family Doctor":
				//family doctor has interval of 365 days (~1 year)
				interval = 365;
			break;
		}
		return interval;
	}
	public String getSuggestionMessage() {
		//create suggestion message (displayed on suggested appointment panel)
		String medicalSpecialistType = getMedicalSpecialistType();
		int interval = SuggestedAppointment.getMedicalAppointmentInterval(medicalSpecialistType);
		
		//https://stackoverflow.com/questions/7833689/java-string-new-line 
		//https://www.w3schools.com/html/html_lists.asp
		//use html to format
		String suggestionMessage = "<html>Apointment type:&nbsp;&nbsp;&nbsp;" + medicalSpecialistType + "<br>";
		suggestionMessage += "Doctor name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + getDoctorName() + "<br>";
		suggestionMessage += "Doctor location:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + getLocation() + "<br>";
		suggestionMessage += "When to book:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[[BookDate]]<br>";
		suggestionMessage += "Appointment date:&nbsp;[[AppointmentDate]]";
		suggestionMessage += "</html>";
		
		//contains recommended date to book suggested appointment (date to contact doctor)
		String bookDateStr = "";
		//conatins reccommended date for the suggested appointment (date of occurence)
		String appointmentDateStr = "";
		
		//get date of most recent appointment date (appointment suggested appointment is based on)
		Date recentConfirmedAppointmentDate = Date.valueOf(this.getDate().toLocalDate().minusDays(interval));
		//format date (yyyy-MM-dd)
		String recentConfirmedAppointmentDateStr = SuggestedAppointment.standardShortDateFormater.format(
				recentConfirmedAppointmentDate);
		
		//get today's date as string in the format (yyyy-MM-dd)
		String today = SuggestedAppointment.standardShortDateFormater.format(new Date(new java.util.Date().getTime()));
		
		//get suggested date as string in the format (yyyy-MM-dd)
		String suggestedAppointmentDateStr = this.getStandardShortDateString();
		
		//suggested appointment date is later than or is today; passed the suggestion date already then date to book is "now"
		if(suggestedAppointmentDateStr.compareTo(today) <= 0) {
			bookDateStr = "Now";
			//initial reccomended/suggested date has passed; recommended appointment date is any date from now
			//essentially telling user to book appointment ASAP
			appointmentDateStr = "Any date from now";
		}
		else
		{
			//today is earlier than recent confirmed appointment date; most recent one is still in the future
			if(today.compareTo(recentConfirmedAppointmentDateStr) < 0) {
				/*booking date is after the recent confirmed appointment date 
				 * (the confirmed appointment the suggested appointment is based on)*/
				bookDateStr = "After " + SuggestedAppointment.longDateFormater.format(recentConfirmedAppointmentDate);
			}
			else
			{
				//the date of the most recent confirmed appointment date has passed 
				//(can book appointment now)
				bookDateStr = "Now";
			}
			//recommended appointment date (date in which appointment occurs) is the suggested date
			appointmentDateStr = "On or after " + this.getLongDateString();
		}
		//replace book date and appointment date with calculated book date and appointment date
		suggestionMessage = suggestionMessage.replace("[[BookDate]]", bookDateStr).replace("[[AppointmentDate]]", appointmentDateStr);
		
		return suggestionMessage;
	}
}
