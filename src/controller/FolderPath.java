package controller;

public class FolderPath {
	//contains folder path name
	String filePath = "files/";
	String imagePath = "images/";
	String suggestionsFileName = "suggestions";
	String timelineFileName = "timeline";
	String helpFileName = "help";
	String infoSeparator = "^";
	String appointmentSeparator = "~";
	
	//getters
	public String getFilePath() {
		return filePath;
	}
	public String getImagePath() {
		return imagePath;
	}
	public String getSuggestionsFileName() {
		return suggestionsFileName;
	}
	public String getTimelineFileName() {
		return timelineFileName;
	}
	public String getHelpFileName() {
		return helpFileName;
	}
	public String getInfoSeparator() {
		return infoSeparator;
	}
	public String getAppointmentSeparator() {
		return appointmentSeparator;
	}
}
