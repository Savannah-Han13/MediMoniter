package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Appointment;
import model.AppointmentComparor;
import model.ConfirmedAppointment;
import model.SuggestedAppointment;
import view.MainPageFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MedicalMonitorController implements ActionListener{
	MainPageFrame mainPage = null;
	
	//array list of confirmed appointments and suggestions
	private ArrayList<ConfirmedAppointment> timeline = new ArrayList<ConfirmedAppointment>();
    private ArrayList<SuggestedAppointment> suggestions = new ArrayList<SuggestedAppointment>();
    public static ArrayList<String> medicalSpecialistTypesList = new ArrayList<String>(
    		Arrays.asList("Dentist", "Eye Doctor", "Family Doctor"));

    private FolderPath path = new FolderPath();
    
    private boolean timelineHasContent = false;
    private boolean suggestionsHasContent = false;
    
	public MedicalMonitorController() {
		//create new mainpage frame and set visible
		mainPage = new MainPageFrame(this);
		mainPage.setVisible(true);
		
		//creates files if the files do not yet exist
		createTimelineFile();
		createSuggestionsFile();
		
		//populate "timeline" and "suggestions" array list if associated files exist 
		if(timelineHasContent) {
			populateTimeline();			
		}
		//
		if(suggestionsHasContent) {
			populateSuggestions();			
		}
		//update main page gui
		mainPage.updateMainpageFrame(timeline, suggestions);
	}
	private void populateAppointments(String filePath, boolean forConfirmedAppointments) {
		//get appointments from given file
		try {
			Scanner reader = new Scanner(
					new FileReader(filePath));
			
			//read until end of file
			reader.useDelimiter(Pattern.compile(path.getAppointmentSeparator()));
			while(reader.hasNext()) {
				//get existing appointment information; read until "~"
				String appointmentLine = reader.next();
				
				//if populating confirmed appointments, create confirmed appointments based on appointments in file
				if(forConfirmedAppointments) {
					ConfirmedAppointment existingAppointment = (ConfirmedAppointment) createExistingAppointment(appointmentLine, true);
					if(existingAppointment != null) {
						//check if appointment has expired; add the appointment if not
						if(existingAppointment.isTodayOrFutureDate()) {
							timeline.add(existingAppointment);							
						}
					}					
				}
				else
				{
					//if populating confirmed appointments, create suggested appointments based on appointments in file
					SuggestedAppointment existingAppointment = (SuggestedAppointment) createExistingAppointment(appointmentLine, false);
					if(existingAppointment != null) {
						suggestions.add(existingAppointment);						
					}					
				}

			}
			//close reader
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Appointment cannot be read");
		      e.printStackTrace();
		}
	}
	private void populateTimeline() {
		//provides "timeline" file name and indicates appointments to be added are confirmed appointments
		populateAppointments(path.getFilePath() + path.getTimelineFileName() + ".txt", true);
	}
	private void populateSuggestions() {
		//provides "suggestions" file name and indicates appointments to be added are suggested appointments
		populateAppointments(path.getFilePath() + path.getSuggestionsFileName() + ".txt", false);
	}
	private Appointment createExistingAppointment(String appointmentLine, boolean forConfirmedAppointment) {
		String[] appInfoArr = appointmentLine.split("\\" + path.getInfoSeparator()); 
		String medicalSpecialistType = null;
		String doctorName = null;
		String location = null;
		Date date = null;
		Time time = null;
		
		if (appInfoArr.length == 5) {
			medicalSpecialistType = appInfoArr[0];
			doctorName = appInfoArr[1];
			location = appInfoArr[2];
			try {
				date = new Date(Appointment.standardShortDateFormater.parse(appInfoArr[3]).getTime());
				time = new Time(Appointment.timeFormater.parse(appInfoArr[4]).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}

		}
		return (forConfirmedAppointment ? (new ConfirmedAppointment(medicalSpecialistType, doctorName, location, date, time)) : (new SuggestedAppointment(medicalSpecialistType, doctorName, location, date, time)));
	}
	//utility
	@Override
	public void actionPerformed(ActionEvent e) {
		String sourceName = e.getSource().getClass().getSimpleName();
		
		switch(sourceName) {
			case("MainPageFrame"): {
					//save appointment to files in controller; append to end of "timeline" file
					MainPageFrame mainpageFrame = (MainPageFrame) e.getSource();
					ConfirmedAppointment confirmedAppointment = mainpageFrame.getAppointment();
					
					//get action command; differentiates between different functions of the main page
					String actionCommand = e.getActionCommand();
					
					//create new appointment
					if(actionCommand.equals("New")) {
						timeline.add(confirmedAppointment);
						
						//sort timeline arraylist
						timeline.sort(new AppointmentComparor());
					} 
					//delete the current appointment
					else if(actionCommand.equals("Delete")) {
						removeConfirmedAppointment(confirmedAppointment);
					} 
					//edit the current appointment
					else if(actionCommand.equals("Edit")) {
						//sort timeline arraylist
						timeline.sort(new AppointmentComparor());
					}
					
					//save timeline and suggestions
					saveTimeline();
					saveSuggestions();
				
				//notify mainpage to update gui of timeline
				mainpageFrame.updateMainpageFrame(timeline, suggestions);
			}
			break;
		}
	}
	//create timeline and suggestions files
	private void createTimelineFile() {
		//https://www.w3schools.com/java/java_files_create.asp
		try {
	      File f = new File(path.getFilePath() + path.getTimelineFileName() + ".txt");
	      if (f.createNewFile()) {
	        System.out.println("File created: " + f.getName());
	      } 
	      else 
	      {
	        System.out.println("File already exists.");
	      //check if file has content
	        BufferedReader reader = new BufferedReader(
					new FileReader(path.getFilePath() + path.getTimelineFileName() + ".txt"));
			//https://stackoverflow.com/questions/7190618/most-efficient-way-to-check-if-a-file-is-empty-in-java-on-windows
	        if (reader.readLine() != null && f.length() != 0) {
	        	timelineHasContent = true;
	        }
	        reader.close();
	      }
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
	private void createSuggestionsFile() {
		//https://www.w3schools.com/java/java_files_create.asp
		try {
	      File f = new File(path.getFilePath() + path.getSuggestionsFileName() + ".txt");
	      if (f.createNewFile()) {
	        System.out.println("File created: " + f.getName());
	      } 
	      else 
	      {
	        System.out.println("File already exists.");
	        //check if file has content
	        BufferedReader reader = new BufferedReader(
					new FileReader(path.getFilePath() + path.getSuggestionsFileName() + ".txt"));
			//https://stackoverflow.com/questions/7190618/most-efficient-way-to-check-if-a-file-is-empty-in-java-on-windows
	        if (reader.readLine() != null && f.length() != 0) {
	        	suggestionsHasContent = true;
	        }   
	        reader.close();
	      }
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
	private int findSuggestedAppointmentByMedicalSpecialistType(String medicalSpecialistType) {
		int index = -1;
		for(int i = 0; i < suggestions.size(); i++) {
			SuggestedAppointment appointment = suggestions.get(i);
			//find the suggested appointment of this medical specialist type
			if(appointment.getMedicalSpecialistType().equalsIgnoreCase(medicalSpecialistType)){
				index = i;
				break;
			}
		}
		return index;
	}
	private void removeSuggestedAppointmentByMedicalSpecialistType(String medicalSpecialistType) {
		//remove the suggested appointment of this medical specialist type
		int index = findSuggestedAppointmentByMedicalSpecialistType(medicalSpecialistType);
		if(index >= 0) {
			suggestions.remove(index);
		}
	}
	private void removeConfirmedAppointment(ConfirmedAppointment confirmedAppointment) {
		//deletes the given confirmed appointment
		//find the appointment
		int index = findAppointment(confirmedAppointment);
		if(index >= 0) {
			timeline.remove(index);
		}
		
		//if no more this type of medical specialist type, remove the suggested appointment as well
		boolean found = false;
		String medicalSpecialistType = confirmedAppointment.getMedicalSpecialistType();
		for(ConfirmedAppointment appointment: timeline) {
			if(appointment.getMedicalSpecialistType().equalsIgnoreCase(medicalSpecialistType)) {
				found = true;
				break;
			}
		}

		if(!found) {
			removeSuggestedAppointmentByMedicalSpecialistType(medicalSpecialistType);
		}
	}
	private void saveTimeline() {
		PrintWriter writer;
		
		//file update
		try {
			//clears all content in "timeline" file
			writer = new PrintWriter(path.getFilePath() + path.getTimelineFileName() + ".txt");
			writer.print("");
			writer.close();
			
			//adds all old and new appointments
			writer = new PrintWriter(
					new FileOutputStream(new File(path.getFilePath() + path.getTimelineFileName() + ".txt"), true));
			
			//add all confirmed appointments and append to "timeline" file
			for(ConfirmedAppointment curAppointment: timeline) {
				addConfirmedAppointmentToFile(writer, curAppointment);
			}
			
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}
	private int findAppointment(ConfirmedAppointment appointment) {
		//compare date of appointment with existing appointments
		int index = -1;
		for(int i = 0; i < timeline.size(); i++) {
			ConfirmedAppointment curAppointment = timeline.get(i);
			/*if the appointment shares the same date and time as current appointment
			 *the current appointment is the appointment to be removed*/
			if(appointment.getDate().equals(curAppointment.getDate())
					&& appointment.getTime().equals(curAppointment.getTime()) 
					&& appointment.getMedicalSpecialistType().equalsIgnoreCase(curAppointment.getMedicalSpecialistType())){
				index = i;
				break;
			}
		}
		return index;
	}
	private void addConfirmedAppointmentToFile(PrintWriter writer, ConfirmedAppointment appointment) {
		//append the following information
		/*- medical specialist type
		 *- doctor name
		 *- location
		 *- date
		 *- time
		 *- line separator (indicates end of informaton for this appointment*/
		writer.append(appointment.getMedicalSpecialistType() + path.getInfoSeparator());
		writer.append(appointment.getDoctorName() + path.getInfoSeparator());
		writer.append(appointment.getLocation() + path.getInfoSeparator());
		writer.append(Appointment.standardShortDateFormater.format(appointment.getDate()) + path.getInfoSeparator());
		writer.append(Appointment.timeFormater.format(appointment.getTime()));
		writer.append(path.getAppointmentSeparator());
	}
	private void saveSuggestions() {
		//create suggestions based on most recent confirmed appointment algorithm <here>
		for(String medicalSpecialistType: medicalSpecialistTypesList) {
			ConfirmedAppointment mostRecent = getRecentConfirmedAppointment(medicalSpecialistType);
			/*does not create a suggested appointment for the current medical specialist type 
			 * if there is no recent appointment for the current type (no appointments for that type exist at all)*/
			if(mostRecent == null) {
				continue;
			}
			SuggestedAppointment newSuggested = createSuggestedAppointment(mostRecent);
			int index = findSuggestedAppointmentByMedicalSpecialistType(medicalSpecialistType);
			
			if(index < 0) {
				//if not found, add suggestion
				suggestions.add(newSuggested);				
			}
			else
			{
				//if found, get existing suggestion and update its information from new suggestion
				SuggestedAppointment existingSuggested = suggestions.get(index);
				existingSuggested.CopyFrom(newSuggested);
			}
		}
		//sort the suggestions
		suggestions.sort(new AppointmentComparor());
		
		PrintWriter writer;
		
		//file update
		try {
			//clears all content in "suggestions" file
			writer = new PrintWriter(path.getFilePath() + path.getSuggestionsFileName() + ".txt");
			writer.print("");
			writer.close();
			
			writer = new PrintWriter(
					new FileOutputStream(new File(path.getFilePath() + path.getSuggestionsFileName() + ".txt"), true));
			
			//add all suggested appointments and append to "suggestions" file
			for(SuggestedAppointment curAppointment: suggestions) {
				addSuggestedAppointmentToFile(writer, curAppointment);
			}
			//close writer
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	private SuggestedAppointment createSuggestedAppointment(ConfirmedAppointment recentAppointment) {
		//copy the following information from the most recent confirmed appointment
		String medicalSpecialistType = recentAppointment.getMedicalSpecialistType();
		String doctorName = recentAppointment.getDoctorName();
		String location = recentAppointment.getLocation();
		Time time = recentAppointment.getTime();
		
		//get suggested date
		Date date = getSuggestedDate(medicalSpecialistType, recentAppointment.getDate());
		
		return new SuggestedAppointment(medicalSpecialistType, doctorName, location, date, time);
	}
	private Date getSuggestedDate(String medicalSpecialistType, Date recentDate) {
		//find suggested date
		
		//each medical specialist type has a interval of time between the next and most recent appointment
		int interval = SuggestedAppointment.getMedicalAppointmentInterval(medicalSpecialistType);
		
		//get the date after the given intervals (add the given number of days) 
		return Date.valueOf(recentDate.toLocalDate().plusDays(interval));
	}
	private ConfirmedAppointment getRecentConfirmedAppointment(String medicalSpecialistType) {
		ConfirmedAppointment mostRecent = null;
		
		//assumes timeline is already ordered in terms of day and time
		for(int i = timeline.size() - 1; i >= 0; i--) {
			ConfirmedAppointment curAppointment = timeline.get(i);
			//if the current appointment is not the current medical specialist type, continue
			if(!curAppointment.getMedicalSpecialistType().equals(medicalSpecialistType)) {
				continue;
			}
			//once the current appointment is found, break the loop
			mostRecent = curAppointment;
			break;
		}
		//returns null if no recent appointments for this medical specialist type exists
		return mostRecent;
	}
	private void addSuggestedAppointmentToFile(PrintWriter writer, SuggestedAppointment appointment) {
		//append the following information
				/*- medical specialist type
				 *- doctor name
				 *- location
				 *- date
				 *- time
				 *- line separator (indicates end of informaton for this appointment*/
				writer.append(appointment.getMedicalSpecialistType() + path.getInfoSeparator());
				writer.append(appointment.getDoctorName() + path.getInfoSeparator());
				writer.append(appointment.getLocation() + path.getInfoSeparator());
				writer.append(Appointment.standardShortDateFormater.format(appointment.getDate()) + path.getInfoSeparator());
				writer.append(Appointment.timeFormater.format(appointment.getTime()));
				writer.append(path.getAppointmentSeparator());
	}
}
