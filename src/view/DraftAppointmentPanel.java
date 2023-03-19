package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import model.Appointment;
import model.ConfirmedAppointment;

//GUI layout of a draft appointment
@SuppressWarnings("serial")
public class DraftAppointmentPanel extends JPanel implements ActionListener{
	//instructional text for clarity
	private JLabel medicalSpecialistTypeLabel = new JLabel("Medical Specialist Type");
	private JLabel dateLabel = new JLabel("Date");
	private JLabel timeLabel = new JLabel("Time");
	private JLabel doctorNameLabel = new JLabel("Doctor Name");
	private JLabel locationLabel = new JLabel("Location");
	
	//save button
	SaveButton saveButton = new SaveButton(this);
	
	//cancel button
	JButton cancelButton = new JButton("Cancel");
	
	String[] medicalSpecialists = {"Dentist", "Eye Doctor", "Family Doctor"};
	
	//user input fields
	JComboBox<String> medicalSpecialistComboBox = new JComboBox<String>(medicalSpecialists);
	
	JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
	JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
	
	JTextField doctorNameTextField = new JTextField();
	JTextField locationTextField = new JTextField();
	
	//general error label
	JLabel generalErrorMessageLabel = new JLabel(
			"Error: please fill in all fields and ensure date is not earlier than today");
	
	//custom date and time models for spinners
	SimpleDateFormat dateModel = Appointment.shortDateFormater;
	SimpleDateFormat timeModel = Appointment.timeFormater;
	
	//"becomes" a confirmed appointment upon saving (user input information sent back to mainpage frame)
	ConfirmedAppointment draftConfirmedAppointment;
	
	//action listener (mainpage)
	ActionListener actionListener;
	
	//parent dialog
	JDialog appointmentDialog = null;
	
	/*true if the draft appointment is used to create a new appointment or confirm a suggested appointment 
	 *false if draft appointment is used to edit an appointment*/
	boolean isNew = true;
	
	//constructor
	public DraftAppointmentPanel(ActionListener actionListener, ConfirmedAppointment draftConfirmedAppointment){
		//set layout
		setLayout(null);
		setSize(DAGUI_Info.getPageWidth(), DAGUI_Info.getPageHeight());
		
		//set action listener
		this.actionListener = actionListener;
		
		//GUI setup
		guiSetup();
		setAppointment(draftConfirmedAppointment);
		
		//setup error message label
		generalErrorMessageLabel.setBounds(
				DAGUI_Info.getSaveButtonDistFromLeft() - 290, 
				DAGUI_Info.getSaveButtonDistFromTop() - 40, 390, 30);
		generalErrorMessageLabel.setForeground(Color.RED);
		add(generalErrorMessageLabel);
		generalErrorMessageLabel.setVisible(false);
		
		//colors, fonts, etc
		setBackground(ColorPalette.getSKY_GRAY());
		cancelButton.setBackground(ColorPalette.getPASTEL_RED());
	}
	private void guiSetup() {
		setupLabels();
		setupFields();
		
		//setup cancel button - used to cancel the draft appointment
		cancelButton.setLocation(
				DAGUI_Info.getCancelButtonDistFromLeft(), 
				DAGUI_Info.getCancelButtonDistFromTop());
		cancelButton.setSize(DAGUI_Info.getCancelButtonWidth(),DAGUI_Info.getCancelButtonHeight());

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appointmentDialog.setVisible(false);
			}
		});
		add(cancelButton);
		
		//setup save button
		saveButton.setLocation(
				DAGUI_Info.getSaveButtonDistFromLeft(), 
				DAGUI_Info.getSaveButtonDistFromTop());
		add(saveButton);
		
		setVisible(true);
	}
	private void setupLabels() {
		//medical specialist combo box
		medicalSpecialistTypeLabel.setBounds(
				DAGUI_Info.getLabeldistfromleft(), 
				DAGUI_Info.getMedspeciallabeldistfromtop(), 
				DAGUI_Info.getLabelWidth(), 
				DAGUI_Info.getLabelHeight());
		add(medicalSpecialistTypeLabel);
		
		//doctor name text field
		doctorNameLabel.setBounds(
				DAGUI_Info.getLabeldistfromleft(), 
				DAGUI_Info.getDoctornamelabeldistfromtop(), 
				DAGUI_Info.getLabelWidth(), 
				DAGUI_Info.getLabelHeight());
		add(doctorNameLabel);
		
		//location text field
		locationLabel.setBounds(
				DAGUI_Info.getLabeldistfromleft(), 
				DAGUI_Info.getLocationlabeldistfromtop(), 
				DAGUI_Info.getLabelWidth(), 
				DAGUI_Info.getLabelHeight());
		add(locationLabel);
		
		//date label
		dateLabel.setBounds(
				DAGUI_Info.getLabeldistfromleft(), 
				DAGUI_Info.getDatelabeldistfromtop(), 
				DAGUI_Info.getLabelWidth(), 
				DAGUI_Info.getLabelHeight());
		add(dateLabel);
		
		//time label
		timeLabel.setBounds(
				DAGUI_Info.getLabeldistfromleft(), 
				DAGUI_Info.getTimelabeldistfromtop(), 
				DAGUI_Info.getLabelWidth(), 
				DAGUI_Info.getLabelHeight());
		add(timeLabel);
	}
	private void setupFields() {
		//medical specialist combo box
		medicalSpecialistComboBox.setBounds(
				DAGUI_Info.getFielddistfromleft(), 
				DAGUI_Info.getMedspeciallabeldistfromtop(), 
				DAGUI_Info.getFieldWidth(), 
				DAGUI_Info.getFieldHeight());
		add(medicalSpecialistComboBox);
		medicalSpecialistComboBox.setBackground(Color.WHITE);
		
		//doctor name text field
		doctorNameTextField.setBounds(
				DAGUI_Info.getFielddistfromleft(), 
				DAGUI_Info.getDoctornamelabeldistfromtop(), 
				DAGUI_Info.getFieldWidth(), 
				DAGUI_Info.getFieldHeight());
		add(doctorNameTextField);
		
		//location text field
		locationTextField.setBounds(
				DAGUI_Info.getFielddistfromleft(), 
				DAGUI_Info.getLocationlabeldistfromtop(), 
				DAGUI_Info.getFieldWidth(), 
				DAGUI_Info.getFieldHeight());
		add(locationTextField);
		
		//date spinner
		dateSpinner.setBounds(
				DAGUI_Info.getFielddistfromleft(), 
				DAGUI_Info.getDatelabeldistfromtop(), 
				DAGUI_Info.getFieldWidth(), 
				DAGUI_Info.getFieldHeight());
		add(dateSpinner);
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, dateModel.toPattern()));
		
		//time spinner
		timeSpinner.setBounds(
				DAGUI_Info.getFielddistfromleft(), 
				DAGUI_Info.getTimelabeldistfromtop(), 
				DAGUI_Info.getFieldWidth(), 
				DAGUI_Info.getFieldHeight());
		add(timeSpinner);
		timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, timeModel.toPattern()));
	}
	//set dialog
	public void setDialog(JDialog dialog) {
		appointmentDialog = dialog;
	}
	public void setAppointment(ConfirmedAppointment confirmedAppointmentWIP) {
		//set the draft confirmed appointment as the new work in progress confirmed appointment
		this.draftConfirmedAppointment = confirmedAppointmentWIP;
		
		//populate work in progress confirmed appointment
		String medicalSpecialistType = this.draftConfirmedAppointment.getMedicalSpecialistType();
		this.medicalSpecialistComboBox.setSelectedItem(medicalSpecialistType);
		
		//if medical specialist type is not empty, disable the medical specialist type combo box (disallow change)
		if(medicalSpecialistType != null && medicalSpecialistType.length() > 0) {
			this.medicalSpecialistComboBox.setEnabled(false);
		}
		else
		{
			//otherwise, enable the medical specialist combo box
			this.medicalSpecialistComboBox.setEnabled(true);
		}
		
		this.doctorNameTextField.setText(this.draftConfirmedAppointment.getDoctorName());
		this.locationTextField.setText(this.draftConfirmedAppointment.getLocation());
		
		//when editing; initial date on spinner is date of the confirmed appointment being edited
		//if not null, there is a date 
		java.sql.Date date = this.draftConfirmedAppointment.getDate();
		
		if (date != null) {
			this.dateSpinner.setValue(new Date(date.getTime()));		
		}
		else 
		{
			this.dateSpinner.setValue(new Date());
		}
		
		//when editing; initial time on spinner is time of the confirmed appointment being edited
		//if not null, there is a time 
		java.sql.Time time = this.draftConfirmedAppointment.getTime();
		
		if (time != null) {
			this.timeSpinner.setValue(new Date(time.getTime()));		
		}
		else 
		{
			this.timeSpinner.setValue(new Date());
		}
		generalErrorMessageLabel.setVisible(false);
	}
	//isNew = true when draft appointment is for a new confirmed appointment
	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}
	//getter
	public boolean getIsNew() {
		return this.isNew;
	}
	
	//used for main page to access the new confirmed appointment
	public ConfirmedAppointment getAppointment(){
		return this.draftConfirmedAppointment;
	}
	//utility
	//used obtain user input from fields 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String source = e.getSource().getClass().getSimpleName();
		switch(source) {
		case("SaveButton"):
			boolean inputIsValid = inputIsValid();
			//validation; check if all fields have information
			if(inputIsValid) {
				setupDraftConfirmedAppointment();
			
				//notifies mainpage of save button being pressed
				ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "save");
				actionListener.actionPerformed(actionEvent);
			}
			else
			{
				//show error message
				generalErrorMessageLabel.setVisible(true);
			}
			break;
		}
	} 
	private boolean inputIsValid() {
		boolean isValid = true;
		
		//check if any of the following inputs are invalid
		if((String) medicalSpecialistComboBox.getSelectedItem() == null) {
			isValid = false;
		}
		if(doctorNameTextField.getText().isBlank()) {
			isValid = false;
		}
		if(locationTextField.getText().isBlank()) {
			isValid = false;
		}
		//compare user given date is earlier than today; in that case, input invalid
		Date date = (Date) dateSpinner.getValue();
		String dateStr = Appointment.standardShortDateFormater.format(date);
		
		Date now = new Date();
		String today = Appointment.standardShortDateFormater.format(now);
		
		if(dateStr.compareTo(today) < 0) {
			isValid = false;
		}
		if(dateSpinner.getValue() == null) {
			isValid = false;
		}
		if(timeSpinner.getValue() == null) {
			isValid = false;
		}
		return isValid;
	}
	private void setupDraftConfirmedAppointment() {
		//get user input information
		String medicalSpecialistType = (String) medicalSpecialistComboBox.getSelectedItem();
		String doctorName = doctorNameTextField.getText();
		String location = locationTextField.getText();
		Date date = (Date) dateSpinner.getValue();
		Date date2 = null;
		//create a new date without time
		try {
			//create string without time, and parse to new date
			date2 = this.dateModel.parse(this.dateModel.format(date));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Date time = (Date) timeSpinner.getValue();
		
		//create a new time without date
		Date time2 = null;
		try {
			//create string without date, and parse to new time
			time2 = this.timeModel.parse(this.timeModel.format(time));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}		
		//set draft appointment with the given information
		this.draftConfirmedAppointment.setMedicalSpecialistType(medicalSpecialistType);
		this.draftConfirmedAppointment.setDoctorName(doctorName);
		this.draftConfirmedAppointment.setLocation(location);
		
		//new java sql date/time here
		this.draftConfirmedAppointment.setDate(new java.sql.Date(date2.getTime()));
		this.draftConfirmedAppointment.setTime(new java.sql.Time(time2.getTime()));
	}
}
