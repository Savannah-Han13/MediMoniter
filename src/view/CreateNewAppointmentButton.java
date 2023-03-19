package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class CreateNewAppointmentButton extends JButton{
	//Create New Appointment Button - used to create a new appointment
	public CreateNewAppointmentButton(ActionListener actionListener) {
		this.addActionListener(actionListener);
		setSize(MPGUI_Info.getCreateNewAppointButtonWidth(), MPGUI_Info.getCreateNewAppointButtonHeight());
		setText("Create New Appointment");
		setBackground(ColorPalette.getPISTACHIO());
		setVisible(true);
	}
}
