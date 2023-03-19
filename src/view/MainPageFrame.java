package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import model.ConfirmedAppointment;
import model.SuggestedAppointment;

@SuppressWarnings("serial")
public class MainPageFrame extends JFrame implements ActionListener{
	//GUI components
	//panels
	TimelinePanel timelinePanel = new TimelinePanel(this);
	SuggestionsPanel suggestionsPanel = new SuggestionsPanel(this);
	CreateNewAppointmentButton createNewAppointmentButton = null;
	
	//help button
	HelpButton helpButton = new HelpButton(this);
	
	//labels
	JLabel timelineTitleLabel = new JLabel("Timeline");
	JLabel suggestionsTitleLabel = new JLabel("Suggestions");
	JScrollPane timelineScrollPane = null;
	JScrollPane suggestionsScrollPane = null;
	DraftAppointmentPanel draftAppointmentPanel = null;
	
	//the appointment used for editing
	ConfirmedAppointment currentWorkingConfirmedAppointmentWIP = null;	
	
	//appointment dialog
	JDialog modelDialog = null;
	//help (instructions) dialog
	JDialog helpModelDialog = null;
	
	//action listener (controller)
	ActionListener actionListener; 
	
	//top panel - put timeline label, createNewAppointment button and helpButton
	JPanel topPanel = new JPanel();
	//contains suggestions title label
	JPanel suggestionsTitlePanel = new JPanel();
	
	public MainPageFrame(ActionListener actionListener) {
		//set layout as box layout (stacks GUI components vertically on frame)
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(MPGUI_Info.getPageWidth(), MPGUI_Info.getPageHeight());
		setTitle("Medical Appointment Monitor");
		
		//set action listener
		this.actionListener = actionListener;
		
		//set spacing 
		add(Box.createRigidArea(new Dimension(0,15)));
		
		//setup top panel
		topPanel.setMinimumSize(new Dimension(MPGUI_Info.getTimelinePanelWidth(), 50));
		topPanel.setPreferredSize(new Dimension(MPGUI_Info.getTimelinePanelWidth(), 50));
		topPanel.setMaximumSize(new Dimension(MPGUI_Info.getTimelinePanelWidth(), 50));
		topPanel.setLayout(null);

		//setup create new appointment button
		createNewAppointmentButton = new CreateNewAppointmentButton(this);
		createNewAppointmentButton.setLocation(
				MPGUI_Info.getCreateNewAppointButtonDistFromLeft(), 
				MPGUI_Info.getCreateNewAppointButtonDistFromTop());		
		createNewAppointmentButton.setText("Create New Appointment");
		topPanel.add(createNewAppointmentButton);
		
		//setup help button
		helpButton.addActionListener(this);
		helpButton.setLocation(
				MPGUI_Info.getHelpButtonDistFromLeft(), 
				MPGUI_Info.getHelpButtonDistFromTop());
		topPanel.add(helpButton);
		
		//timeline title
		timelineTitleLabel.setBounds(
				10, 
				MPGUI_Info.getTimelineTitleDistFromTop(),
				MPGUI_Info.getTimelineTitleWidth(),
				MPGUI_Info.getTimelineTitleHeight());
		topPanel.add(timelineTitleLabel);
		add(topPanel);
		
		//set bounds/locations for GUI components
		setupTimelineScrollPane();
		
		add(Box.createRigidArea(new Dimension(0,5)));
		
		//setup suggestions panel
		suggestionsTitlePanel.setMinimumSize(new Dimension(MPGUI_Info.getTimelinePanelWidth(), 40));
		suggestionsTitlePanel.setPreferredSize(new Dimension(MPGUI_Info.getTimelinePanelWidth(), 40));
		suggestionsTitlePanel.setMaximumSize(new Dimension(MPGUI_Info.getTimelinePanelWidth(), 40));
		suggestionsTitlePanel.setLayout(null);
		
		//setup suggestions label
		suggestionsTitleLabel.setBounds(
				10, 
				0,
				MPGUI_Info.getSuggestionsTitleWidth(), 
				MPGUI_Info.getSuggestionsTitleHeight());
		suggestionsTitlePanel.add(suggestionsTitleLabel);
		add(suggestionsTitlePanel);
		
		//setup suggestions scroll panel
		setupSuggestionsScrollPane();
		add(Box.createRigidArea(new Dimension(0,30)));
		setLocationRelativeTo(null);
		
		//setup fonts, colors, etc
		getContentPane().setBackground(ColorPalette.getSKY_GRAY());
		topPanel.setBackground(ColorPalette.getBLUE_GRAY());
		suggestionsTitlePanel.setBackground(topPanel.getBackground());
		
		topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		suggestionsTitlePanel.setBorder(topPanel.getBorder());
		
		timelineTitleLabel.setFont(new Font("Actor", Font.BOLD, 20));
		suggestionsTitleLabel.setFont(timelineTitleLabel.getFont());
		
		setVisible(true);
	}
	//utility
	//sets up scroll pane - contains timeline panel
	private void setupTimelineScrollPane() {
		//width + 4 to reserve space for the borders, height + 20 to reserve space for the horizontal scrollbar
		timelineScrollPane = new JScrollPane(timelinePanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		timelineScrollPane.setPreferredSize(new Dimension(MPGUI_Info.getTimelinePanelWidth() + 4, MPGUI_Info.getTimelinePanelHeight() + 20));
		timelineScrollPane.setMinimumSize(new Dimension(MPGUI_Info.getTimelinePanelWidth() + 4, MPGUI_Info.getTimelinePanelHeight() + 20));
		timelineScrollPane.setMaximumSize(new Dimension(MPGUI_Info.getTimelinePanelWidth() + 4, MPGUI_Info.getTimelinePanelHeight() + 20));
		
		//https://stackoverflow.com/questions/55454479/how-to-change-the-color-of-scrollbar-in-jscrollpane
		timelineScrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = ColorPalette.getLITTLE_GRAY();
		    }
		});

		add(timelineScrollPane);	
	}
	private void setupSuggestionsScrollPane() {
		suggestionsScrollPane = new JScrollPane(suggestionsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		suggestionsScrollPane.setPreferredSize(new Dimension(MPGUI_Info.getSuggestedAppointmentPanelWidth() + 20, MPGUI_Info.getSuggestedAppointmentPanelHeight() + 4));
		suggestionsScrollPane.setMinimumSize(new Dimension(MPGUI_Info.getSuggestedAppointmentPanelWidth() + 20, 0));
		suggestionsScrollPane.setMaximumSize(new Dimension(MPGUI_Info.getSuggestedAppointmentPanelWidth() + 20, Short.MAX_VALUE));
		
		//https://stackoverflow.com/questions/55454479/how-to-change-the-color-of-scrollbar-in-jscrollpane
		suggestionsScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = ColorPalette.getLITTLE_GRAY();
		    }
		});
		
		add(suggestionsScrollPane);	
	}
	public void updateMainpageFrame(ArrayList<ConfirmedAppointment> timeline, ArrayList<SuggestedAppointment> suggestions) {
		//update confirmed appointment panels on timeline panel
		timelinePanel.updateConfirmedAppointmentPanels(timeline);
		suggestionsPanel.updateSuggestedAppointmentPanels(suggestions);

		//"reload"/repaint the mainpage frame
		repaint();
	}
	//get current work in progress confirmed appointment 
	public ConfirmedAppointment getAppointment(){
		return this.currentWorkingConfirmedAppointmentWIP;
	}
	//dialog creator
	//help dialog
	private void createHelpDialog() {
		HelpPanel helpPanel = new HelpPanel();
		//setup dialog, set modal as true (freezes parent/main page frame)
		helpModelDialog = new JDialog(this, "Help", true);
		helpModelDialog.setLayout(null);
		helpModelDialog.add(helpPanel);
		helpPanel.setDialog(helpModelDialog);
		
		helpModelDialog.setSize(DAGUI_Info.getHelpDialogWidth(), DAGUI_Info.getHelpDialogHeight());
		helpModelDialog.setLocationRelativeTo(this);

		helpModelDialog.setVisible(true);
	}
	//create appointment dialog
	private void createDraftAppointmentDialog() {
		//new confirmed appointment is appointment being edited
		this.currentWorkingConfirmedAppointmentWIP = new ConfirmedAppointment();
		
		//pass action listener and confirmed appointment being edited
		draftAppointmentPanel = new DraftAppointmentPanel(this, this.currentWorkingConfirmedAppointmentWIP);
		//appointment is new
		draftAppointmentPanel.setIsNew(true);
		
		//setup dialog, set modal as true (freezes parent/main page frame)
		modelDialog = new JDialog(this, "New Appointment", true);
		modelDialog.setLayout(null);
		
		//setup
		modelDialog.add(draftAppointmentPanel);
		draftAppointmentPanel.setDialog(modelDialog);
		modelDialog.setSize(DAGUI_Info.getPageWidth(), DAGUI_Info.getPageHeight());
		
		modelDialog.setLocationRelativeTo(this);
		modelDialog.setVisible(false);
	}
	//controls various actions
	@Override
	public void actionPerformed(ActionEvent e) {
		String sourceName = e.getSource().getClass().getSimpleName();
		
		switch(sourceName) {
			case("CreateNewAppointmentButton"): {
					//create draft appointment
					if (modelDialog == null) {
						createDraftAppointmentDialog();
					} else {
						//draftAppointmentPanel.setAppointment(new ConfirmedAppointment("Eye Doctor", "Doctor 1", "Begonia Street", java.sql.Date.valueOf("2021-10-11"), new java.sql.Time(new java.util.Date().getTime())));
						this.currentWorkingConfirmedAppointmentWIP = new ConfirmedAppointment(null, null, null, null, null);
						draftAppointmentPanel.setAppointment(this.currentWorkingConfirmedAppointmentWIP);
						draftAppointmentPanel.setIsNew(true);
						modelDialog.setTitle("New Appointment");
					}
					modelDialog.setVisible(true);
				}
				break;
			case("DraftAppointmentPanel"): {
					DraftAppointmentPanel draftPanel = (DraftAppointmentPanel) e.getSource();
					//get the new confirmed appointment
					ConfirmedAppointment confirmedAppointment =  draftPanel.getAppointment();
					if(!draftPanel.getIsNew()) {
						this.currentWorkingConfirmedAppointmentWIP.CopyFrom(confirmedAppointment);
					}
					//notifies controller about the new added confirmed appointment
					ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, draftPanel.getIsNew() ? "New" : "Edit");
					actionListener.actionPerformed(actionEvent);
					modelDialog.setVisible(false);
				}
				break;
			case ("ConfirmedAppointmentPanel"): {	
					ConfirmedAppointmentPanel confirmedAppointmentPanel = (ConfirmedAppointmentPanel)e.getSource();
					ConfirmedAppointment appointment = confirmedAppointmentPanel.getAppointment();
					
					//work in progress confirmed appointment is the appointment from source of action
					this.currentWorkingConfirmedAppointmentWIP = appointment;
					
					//get action command
					String actionCommand = e.getActionCommand();
					
					//if the action is for editing the appointment
					if(actionCommand.equals("Edit")) {
						//if dialog has not been created yet, create dialog
						if (modelDialog == null) {
							createDraftAppointmentDialog();
						}
						ConfirmedAppointment workingAppointment = new ConfirmedAppointment();
						//copy appointment information from appointment to be edited
						workingAppointment.CopyFrom(appointment);
						
						//set copied information to draft appointment panel
						draftAppointmentPanel.setAppointment(workingAppointment);
						
						//appointment is being edited and therefore is not new
						draftAppointmentPanel.setIsNew(false);
						modelDialog.setTitle("Appointment - Editting in Progress");
						modelDialog.setVisible(true);
					} 
					//if action command is delete, notify controller
					else if(actionCommand.equals("Delete")) {
						//https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
						Object[] options = { "YES", "NO" };
						int choice = JOptionPane.showOptionDialog(
								this, "Are you sure you want to delete this " + appointment.getMedicalSpecialistType() + 
								" appointment?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
								null, options, options[0]);
						//if choice is ok option (yes), delete the appointment
						if(choice == JOptionPane.OK_OPTION) {
							ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Delete");
							actionListener.actionPerformed(actionEvent);
						}
					}
				}
				break;
			case("SuggestedAppointmentPanel"): {
					SuggestedAppointmentPanel suggestedAppointmentPanel = (SuggestedAppointmentPanel)e.getSource();
					
					//get action command
					String actionCommand = e.getActionCommand();
					
					//if command is to confirm suggested appointment
					if(actionCommand.equals("Confirm")) {
						//if dialog has not be created before, create dialog
						if (modelDialog == null) {
							createDraftAppointmentDialog();
						}
						SuggestedAppointment suggestedAppointment = suggestedAppointmentPanel.getAppointment();
						
						//appointment to edit is a new confirmed appointment
						currentWorkingConfirmedAppointmentWIP = new ConfirmedAppointment();
						
						//copy appointment information from suggested appointment
						currentWorkingConfirmedAppointmentWIP.CopyFrom(suggestedAppointment);
						
						//if the suggested date has passed, set date and time as null (default as current date and time)
						if(!currentWorkingConfirmedAppointmentWIP.isTodayOrFutureDate()) {
							currentWorkingConfirmedAppointmentWIP.setDate(null);
							currentWorkingConfirmedAppointmentWIP.setTime(null);
						}
						//set copied information to draft appointment panel
						draftAppointmentPanel.setAppointment(currentWorkingConfirmedAppointmentWIP);
						
						//a confirmed appointment will be created after this process, therefore appointment is new
						draftAppointmentPanel.setIsNew(true);
						modelDialog.setTitle("New Appointment - Confirmation in Progress");
						modelDialog.setVisible(true);
					}
				}
				break;
			case "HelpButton": {
					//if the help dialog has not been created, create the help dialog
					if(helpModelDialog == null) {
						this.createHelpDialog();
					}
					else
					{
						//else, make the help dialog visible again
						helpModelDialog.setVisible(true);
					}
				}
				break;
		}
	}
}

