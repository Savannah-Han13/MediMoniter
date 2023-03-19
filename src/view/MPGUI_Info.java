package view;

public class MPGUI_Info {
	//values used for setting GUI sizes and locations
	//the purpose of this class is to allow easy changes to the setup of each screen in terms of UI element placement
	//size of main page and welcome page windows
	private static final int pageHeight = 830;
	private static final int pageWidth = 820;
	
	/**main page**/
	//panel info
	//both time and suggestions are placed the same distance away from the boundaries and have the same length
	private static final int distFromLeft = 30;
	private static final int mainPagePanelWidth = pageWidth - 2 * distFromLeft;
	private static final int mainPagePanelHeight = pageHeight - 2 * distFromLeft;
	
	//timeline distance from window boundaries and size
	private static final int timelineDistFromTop = 60;
	private static final int timelinePanelWidth = mainPagePanelWidth;
	private static final int timelinePanelHeight = 250;
	
	//suggestions distance from window boundaries and size
	private static final int suggestionsDistFromTop = timelinePanelHeight + 140;
	private static final int suggestionPanelWidth = mainPagePanelWidth;
	private static final int suggestionPanelHeight = 300;
	
	//confirmed appointment panel 
	private static final int confirmedAppointmentPanelWidth = 210;	
	private static final int confirmedAppointmentPanelHeight = 250;
	
	//suggested appointment panel width
	private static final int suggestedAppointmentPanelWidth = mainPagePanelWidth - 20;	
	private static final int suggestedAppointmentPanelHeight = 120;
	
	//label info
	//timeline title
	private static final int timelineTitleHeight = 40;
	private static final int timelineTitleWidth = 200;
	private static final int timelineTitleDistFromTop = timelineDistFromTop - timelineTitleHeight - 15;
	
	//suggestions title
	private static final int suggestionsTitleHeight = 40;
	private static final int suggestionsTitleWidth = 200;
	private static final int suggestionsTitleDistFromTop = suggestionsDistFromTop - suggestionsTitleHeight - 10;
	
	//button info
	//rectangular buttons (confirm, save, addNewAppointment, etc)
	private static final int helpButtonHeight = 35;
	private static final int helpButtonWidth = 80;
	private static final int helpButtonDistFromLeft = distFromLeft + mainPagePanelWidth - helpButtonWidth - 40;
	private static final int helpButtonDistFromTop = timelineDistFromTop - helpButtonHeight - 17;

	//main page (addNewAppointmentButton)
	private static final int createNewAppointButtonButtonHeight = 35;
	private static final int createNewAppointButtonButtonWidth = 190;
	private static final int createNewButtonDistFromLeft = helpButtonDistFromLeft - createNewAppointButtonButtonWidth - 10;
	private static final int createNewAppointButtonDistFromTop = timelineDistFromTop - createNewAppointButtonButtonHeight - 17;


	//square buttons (editAppointment, deleteAppointment)
	private static final int  squareButtonWidth = 40;
	
	//getters
	public static int getPageHeight() {
		return pageHeight;
	}
	public static int getPageWidth() {
		return pageWidth;
	}
	public static int getDistFromLeft() {
		return distFromLeft;
	}
	public static int getMainPagePanelWidth() {
		return mainPagePanelWidth;
	}
	public static int getMainPagePanelHeight() {
		return mainPagePanelHeight;
	}
	public static int getTimelineDistFromTop() {
		return timelineDistFromTop;
	}
	public static int getTimelinePanelHeight() {
		return timelinePanelHeight;
	}
	public static int getTimelinePanelWidth() {
		return timelinePanelWidth;
	}
	public static int getSuggestionsDistFromTop() {
		return suggestionsDistFromTop;
	}
	public static int getSuggestionPanelWidth() {
		return suggestionPanelWidth;
	}
	public static int getSuggestionPanelHeight() {
		return suggestionPanelHeight;
	}
	public static int getCreateNewAppointButtonHeight() {
		return createNewAppointButtonButtonHeight;
	}
	public static int getCreateNewAppointButtonWidth() {
		return createNewAppointButtonButtonWidth;
	}
	public static int getSquareButtonWidth() {
		return squareButtonWidth;
	}
	public static int getCreateNewAppointButtonDistFromLeft() {
		return createNewButtonDistFromLeft;
	}
	public static int getCreateNewAppointButtonDistFromTop() {
		return createNewAppointButtonDistFromTop;
	}
	public static int getHelpButtonWidth() {
		return helpButtonWidth;
	}
	public static int getHelpButtonHeight() {
		return helpButtonHeight;
	}
	public static int getHelpButtonDistFromLeft() {
		return helpButtonDistFromLeft;
	}
	public static int getHelpButtonDistFromTop() {
		return helpButtonDistFromTop;
	}
	
	public static int getTimelineTitleHeight() {
		return timelineTitleHeight;
	}
	public static int getTimelineTitleWidth() {
		return timelineTitleWidth;
	}
	public static int getTimelineTitleDistFromTop() {
		return timelineTitleDistFromTop;
	}
	public static int getSuggestionsTitleHeight() {
		return suggestionsTitleHeight;
	}
	public static int getSuggestionsTitleWidth() {
		return suggestionsTitleWidth;
	}
	public static int getSuggestionsTitleDistFromTop() {
		return suggestionsTitleDistFromTop;
	}
	public static int getConfirmedAppointmentPanelWidth() {
		return confirmedAppointmentPanelWidth;
	}
	public static int getConfirmedAppointmentPanelHeight() {
		return confirmedAppointmentPanelHeight;
	}
	public static int getSuggestedAppointmentPanelWidth() {
		return suggestedAppointmentPanelWidth;
	}
	public static int getSuggestedAppointmentPanelHeight() {
		return suggestedAppointmentPanelHeight;
	}
}
