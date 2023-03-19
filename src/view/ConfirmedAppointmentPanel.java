package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.ConfirmedAppointment;

//GUI layout of a confirmed appointment
@SuppressWarnings("serial")
public class ConfirmedAppointmentPanel extends JPanel implements ActionListener{
	//text displayed on confirmed appointment panel
	private JLabel medicalSpecialistTypeLabel = new JLabel();
	private JLabel doctorNameLabel = new JLabel();
	private JLabel locationLabel = new JLabel();
	private JLabel dateLabel = new JLabel();
	private JLabel timeLabel = new JLabel();
	
	//label array for above labels
	private JLabel[]labelArray = new JLabel[] {
			medicalSpecialistTypeLabel, doctorNameLabel, locationLabel, dateLabel, timeLabel};
	
	//buttons (edit button - for editing, delete button - for deleting)
	EditButton editButton = null;
	DeleteButton deleteButton = null;
	
	//appointment passed from timeline panel and action listener (mainpage)
	ConfirmedAppointment appointment = null;
	ActionListener actionListener = null;
	
	//constructor
	public ConfirmedAppointmentPanel(ConfirmedAppointment appointment, ActionListener actionListener) {
		//setup panel
		setLayout(null);
		setSize(MPGUI_Info.getConfirmedAppointmentPanelWidth(), MPGUI_Info.getTimelinePanelHeight());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.WHITE);
		
		//pass action listener to buttons; given confirmed appointment is the appointment to use for getting information
		this.appointment = appointment;
		this.actionListener = actionListener;
		editButton = new EditButton(this);
		deleteButton = new DeleteButton(this);
		
		//set text on labels; get this information from passed confirmed appointment
		medicalSpecialistTypeLabel.setText(appointment.getMedicalSpecialistType());
		doctorNameLabel.setText(appointment.getDoctorName());
		locationLabel.setText(appointment.getLocation());
		dateLabel.setText(appointment.getLongDateString());
		timeLabel.setText(appointment.getTimeString());
		
		//setup GUI components
		setupLabels();
		setupButtons();
		
		//set colors, fonts, etc
		medicalSpecialistTypeLabel.setFont(new Font("Abel", Font.BOLD, 16));
		
		setVisible(true);
	}
	private void setupLabels() {
		//addLabels
		for(int i = 0; i < labelArray.length; i++) {
			labelArray[i].setBounds(
					CAGUI_Info.getLabelDistFromLeft(), 
					CAGUI_Info.getLabelDistFromTop() * (i+1), 
					CAGUI_Info.getLabelWidth(),
					CAGUI_Info.getLabelHeight());
			add(labelArray[i]);
		}
		medicalSpecialistTypeLabel.setFont(new Font("Calibri", Font.BOLD, 16));
	}
	private void setupButtons() {
		//set location for buttons and add to confirmed appointment panel
		editButton.setLocation(MPGUI_Info.getConfirmedAppointmentPanelWidth() - MPGUI_Info.getSquareButtonWidth() - 60, 200);
		add(editButton);
		
		deleteButton.setLocation(MPGUI_Info.getConfirmedAppointmentPanelWidth() - 50, 200);
		add(deleteButton);
	}
	//utility
	//used to get the confirmed appointment this panel is basing information off of
	public ConfirmedAppointment getAppointment() {
		return this.appointment;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ActionEvent actionEvent = null;
		String source = e.getSource().getClass().getSimpleName();
		switch(source) {
		case("EditButton"):
			//notifies mainpage of edit button being pressed
			actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Edit");
		break;
		case("DeleteButton"):
			//notifies mainpage of delete button being pressed
			actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Delete");
			
		break;
		}
		actionListener.actionPerformed(actionEvent);
	}
}
