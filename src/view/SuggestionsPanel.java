package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import model.SuggestedAppointment;

@SuppressWarnings("serial")
public class SuggestionsPanel extends JPanel{
	//panel contains the list of confirmed appointments
	private ArrayList<SuggestedAppointmentPanel> suggestedAppointmentPanels = new ArrayList<SuggestedAppointmentPanel>();
	
	//action listener (main page)
	ActionListener actionListener;
	
	public SuggestionsPanel(ActionListener actionListener) {
		//setup
		setLayout(null);
		setSize(MPGUI_Info.getSuggestionPanelWidth(), MPGUI_Info.getSuggestionPanelHeight());
		setPreferredSize(new Dimension(MPGUI_Info.getTimelinePanelWidth(), MPGUI_Info.getTimelinePanelHeight()));
		setMinimumSize(new Dimension(MPGUI_Info.getTimelinePanelWidth(), MPGUI_Info.getTimelinePanelHeight()));
		setMaximumSize(new Dimension(Short.MAX_VALUE, MPGUI_Info.getTimelinePanelHeight()));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		//actionListener
		this.actionListener = actionListener;
		
		setVisible(true);
	}
	public void updateSuggestedAppointmentPanels(ArrayList<SuggestedAppointment> suggestions) {
		for(int i = 0; i < suggestedAppointmentPanels.size(); i++) {
			remove(suggestedAppointmentPanels.get(i));	//to remove them from gui
		}
		//empty suggested appointment panels arraylist
		suggestedAppointmentPanels.clear();
		
		//get suggested appointments from "suggestions" array list and create a new suggested appointment panel
		for(SuggestedAppointment curAppointment: suggestions) {
			createSuggestedAppointmentPanel(curAppointment);
		}
		int height = 0;
		for(int i = 0; i < suggestedAppointmentPanels.size(); i++) {
			//adds suggested appointment panels 
			suggestedAppointmentPanels.get(i).setLocation(0, MPGUI_Info.getSuggestedAppointmentPanelHeight() * i);
			add(suggestedAppointmentPanels.get(i));
			//get height of suggestions panel (given new suggested appointments are added)
			height += MPGUI_Info.getSuggestedAppointmentPanelHeight();
		}
		/*if height of suggestions panel (given new suggested appointment panels are added) is less than 
		 * suggestions panel height, keep size as is*/
		if(height < MPGUI_Info.getSuggestedAppointmentPanelHeight()) {
			setSize(new Dimension(MPGUI_Info.getSuggestedAppointmentPanelWidth(), MPGUI_Info.getSuggestedAppointmentPanelHeight()));
			setPreferredSize(new Dimension(MPGUI_Info.getSuggestedAppointmentPanelWidth(), MPGUI_Info.getSuggestedAppointmentPanelHeight()));
		}
		else
		{
			/*else, adjust size to calculated height given the new added suggested appointment panels 
			(affects associated scroll pane)*/
			setSize(new Dimension(MPGUI_Info.getSuggestedAppointmentPanelWidth(), height));
			setPreferredSize(new Dimension(MPGUI_Info.getSuggestedAppointmentPanelWidth(), height));
		}
	}
	private void createSuggestedAppointmentPanel(SuggestedAppointment appointment) {
		//create a new suggested appointment panel 
		suggestedAppointmentPanels.add(new SuggestedAppointmentPanel(appointment, this.actionListener));
	}
}
