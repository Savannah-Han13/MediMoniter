package model;

import java.util.Comparator;


public class AppointmentComparor implements Comparator<Appointment> {
	//compares date and time of two appointments
	public int compare(Appointment a1, Appointment a2)
	    {
		 //compare date
		 //if appointment 1's date is later than appointment 2's date, return 1
	        if(a1.getDate().compareTo(a2.getDate()) > 0) {
	        	return 1;
	        } 
	        //if appointment 1's date is earlier than appointment 2's date, return -1
	        else if (a1.getDate().compareTo(a2.getDate()) < 0) {
	        	return -1;
	        }
	        //compare time
	        //if appointment 1's time is later than appointment 2's date, return 1
	        if(a1.getTime().compareTo(a2.getTime()) > 0) {
	        	return 1;
	        } 
	        //if appointment 1's date is earlier than appointment 2's date, return -1
	        else if (a1.getTime().compareTo(a2.getTime()) < 0) {
	        	return -1;
	        }
	        //if date and time are same, return 0
	        return 0;
	    }
}
