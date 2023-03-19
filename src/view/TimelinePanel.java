package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import model.ConfirmedAppointment;

@SuppressWarnings("serial")
public class TimelinePanel extends JPanel{
	//panel contains the list of confirmed appointments
	private ArrayList<ConfirmedAppointmentPanel> confirmedAppointmentPanels = new ArrayList<ConfirmedAppointmentPanel>();
	
	//action listener (main page frame)
	private ActionListener actionListener = null;
	
	public TimelinePanel(ActionListener actionListener) {
		setLayout(null);
		setSize(MPGUI_Info.getTimelinePanelWidth(), MPGUI_Info.getTimelinePanelHeight());
		setPreferredSize(new Dimension(MPGUI_Info.getTimelinePanelWidth(), MPGUI_Info.getTimelinePanelHeight()));
		setMinimumSize(new Dimension(MPGUI_Info.getTimelinePanelWidth(), MPGUI_Info.getTimelinePanelHeight()));
		setMaximumSize(new Dimension(Short.MAX_VALUE, MPGUI_Info.getTimelinePanelHeight()));

		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		this.actionListener = actionListener;
		
		setVisible(true);
	}
	public void updateConfirmedAppointmentPanels(ArrayList<ConfirmedAppointment> timeline) {
		for(int i = 0; i < confirmedAppointmentPanels.size(); i++) {
			remove(confirmedAppointmentPanels.get(i));	//to remove them from gui
		}
		//empty timelineGUI arraylist
		confirmedAppointmentPanels.clear();
		
		//get confirmed appointments from "timeline" array list and create a new confirmed appointment panel
		for(ConfirmedAppointment curAppointment: timeline) {
			createConfirmedAppointmentPanel(curAppointment);
		}
		int width = 0;
		for(int i = 0; i < confirmedAppointmentPanels.size(); i++) {
			// Adds confirmed appointment panels 
			confirmedAppointmentPanels.get(i).setLocation(MPGUI_Info.getConfirmedAppointmentPanelWidth() * i, 0);
			add(confirmedAppointmentPanels.get(i));
			//get height of timeline panel (given new confirmed appointments are added)
			width += MPGUI_Info.getConfirmedAppointmentPanelWidth();
		}
		/*if width of timeline panel (given new confirmed appointment panels are added) is less than 
		 * timeline panel width, keep size as is*/
		if(width < MPGUI_Info.getTimelinePanelWidth()) {
			setSize(new Dimension(MPGUI_Info.getTimelinePanelWidth(), MPGUI_Info.getTimelinePanelHeight()));
			setPreferredSize(new Dimension(MPGUI_Info.getTimelinePanelWidth(), MPGUI_Info.getTimelinePanelHeight()));
		}
		else
		{
			/*else, adjust size to calculated width given the new added confirmed appointments 
			 * (affects associated scroll pane)*/
			setSize(new Dimension(width, MPGUI_Info.getTimelinePanelHeight()));
			setPreferredSize(new Dimension(width, MPGUI_Info.getTimelinePanelHeight()));
		}
	}
	private void createConfirmedAppointmentPanel(ConfirmedAppointment appointment) {
		//create a new confirmed appointment panel
		confirmedAppointmentPanels.add(new ConfirmedAppointmentPanel(appointment, this.actionListener));
	}
}
