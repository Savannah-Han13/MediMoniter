package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.SuggestedAppointment;

//GUI layout of a suggested appointment
@SuppressWarnings("serial")
public class SuggestedAppointmentPanel extends JPanel implements ActionListener{
	private JLabel suggestionMessageLabel = new JLabel();
	private ConfirmButton confirmButton = null;
	
	private SuggestedAppointment suggestedAppointment;
	
	//action listener (main page)
	ActionListener actionListener;
	
	public SuggestedAppointmentPanel(SuggestedAppointment appointment, ActionListener actionListener) {
		//setup
		setLayout(null);
		setSize(MPGUI_Info.getSuggestedAppointmentPanelWidth(), MPGUI_Info.getSuggestedAppointmentPanelHeight());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.white);
		
		this.suggestedAppointment = appointment;
		
		//set action listener
		this.actionListener = actionListener;
		confirmButton = new ConfirmButton(this);
		
		//setup GUI components
		setupSuggestionLabel();
		confirmButton.setLocation(MPGUI_Info.getSuggestedAppointmentPanelWidth() - 120, 40);
		add(confirmButton);
	}
	//setup
	private void setupSuggestionLabel() {
		suggestionMessageLabel.setText(this.suggestedAppointment.getSuggestionMessage());
		suggestionMessageLabel.setBounds(20, 20, 
				MPGUI_Info.getSuggestedAppointmentPanelWidth() - 150, 
				MPGUI_Info.getSuggestedAppointmentPanelHeight() - 30);
		add(suggestionMessageLabel);
	}
	//returns the suggested appointment that this suggested appointment panel is based on
	public SuggestedAppointment getAppointment() {
		return this.suggestedAppointment;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String source = e.getSource().getClass().getSimpleName();
		switch(source) {
			case("ConfirmButton"):
				//notifies mainpage of confirm button being pressed
				ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Confirm");
				actionListener.actionPerformed(actionEvent);
			break;
		}	
	}
}
