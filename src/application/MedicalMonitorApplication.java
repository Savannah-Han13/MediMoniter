package application;

import controller.MedicalMonitorController;

/**Name: Savannah Han
 * 
 * Date: January 17, 2022
 * 
 * Course Code:
 * - Course: ICS4U1-01 
 * - Teacher: Mr.Fernandes
 * 
 * Title: Medical Appointment Monitor
 * 
 * Description: 
 * The medical appointment monitor primarily aims to help keep track of medical appointments 
 * on a timeline and provides the user with suggestions or recommendations for booking future appointments 
 * based on existing appointments on the timeline; it is useful as people may often forget to book such
 * appointments given the length of time between each one
 * 
 * Features: 
 * - Timeline and suggestions update themselves based on changes done on main page
 * - Create, delete, edit, confirm, and help features
 * - Suggested appointments provides recommended dates for when the next appointment should occur 
 *   and a recommended date to book the appointment and contact the given doctor
 * - Confirmed scheduled appointments will expire after the given date has passed 
 * 
 * Major Skills:
 * - Use of MVC design
 * - Object-oriented programming
 * - File read and write
 * - Action event and action listener
 * - Validation
 * - Java swing
 * - Use of dialogs and options
 * - Use of images and image icons
 * - Use of text fields and combo boxes for user input
 * - Use of html text file and text breaks to display longer pieces of text
 * 
 * External Resources:
 *   https://stackoverflow.com/questions/428918/how-can-i-increment-a-date-by-one-day-in-java 
 *   https://www.w3schools.com/java/java_files_create.asp 
 *   https://stackoverflow.com/questions/7190618/most-efficient-way-to-check-if-a-file-is-empty-in-java-on-windows 
 *   https://stackoverflow.com/questions/7833689/java-string-new-line 
 *   https://www.w3schools.com/html/html_lists.asp
 *   https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel 
 *   https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java
 *   https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
 *   
 * Areas of Concern:
 * - Appointments will only expire based on date but not time
 * - Draft appointment does not validate whether time chosen is earlier than current time
 * - Duplicate appointments or appointments with the same date and time can be made; if there are duplicate appointments, 
 *   the first appointment created will be deleted regardless of which appointment the delete button was pressed from 
 * - No validation for potentially nonsensical doctor name or location provided by the user such as a string of random 
 *   letters or irrelevant phrases such as: “this is not a location” or inputs in a similar vein to this
 * - Timeline panel and suggestions panel will have empty gaps on the sides for the scrollbar to appear; this is an 
 *   design issue and does not affect functionality
**/

//application
public class MedicalMonitorApplication {
	public static void main(String[] args) {
		new MedicalMonitorController();
	}
}
