package view;

public class DAGUI_Info {
	//contains information related to GUI component layout on draft appointment
	//window size
	private static final int pageHeight = 400;
	private static final int pageWidth = 520;
	
	//user input fields sizes
	private static final int fieldHeight = 30;
	private static final int fieldWidth = 300;
	
	//label sizes
	private static final int labelHeight = 30;
	private static final int labelWidth = 150;
	
	//setting locations for labels and fields
	private static final int spacingBetweenLabels = 40;
	private static final int labelDistFromLeft = 40;
	private static final int medSpecialLabelDistFromTop = 50;
	private static final int doctorNameLabelDistFromTop = medSpecialLabelDistFromTop + spacingBetweenLabels;
	private static final int locationLabelDistFromTop = doctorNameLabelDistFromTop + spacingBetweenLabels;
	private static final int dateLabelDistFromTop = + locationLabelDistFromTop + spacingBetweenLabels;
	private static final int timeLabelDistFromTop = dateLabelDistFromTop + spacingBetweenLabels;
	
	private static final int fieldDistFromLeft = labelDistFromLeft + labelWidth;
	private static final int fieldFromTop = 80;
	
	//size and location for save button
	private static final int saveButtonHeight = 30; 
	private static final int saveButtonWidth = 80;
	private static final int saveButtonDistFromTop = pageHeight - saveButtonHeight - 60;
	private static final int saveButtonDistFromLeft = pageWidth - saveButtonWidth - 60;
	
	//size and location for cancel button
	private static final int cancelButtonHeight = 30; 
	private static final int cancelButtonWidth = 80;
	private static final int cancelButtonDistFromTop = pageHeight - cancelButtonHeight - 60;
	private static final int cancelButtonDistFromLeft = pageWidth - cancelButtonWidth - 145;

	/**getters**/
	public static int getPageWidth() {
		return pageWidth;
	}
	public static int getPageHeight() {
		return pageHeight;
	}
	public static int getFieldWidth() {
		return fieldWidth;
	}
	public static int getFieldHeight() {
		return fieldHeight;
	}
	public static int getSpacingbetweenlabels() {
		return spacingBetweenLabels;
	}
	public static int getLabeldistfromleft() {
		return labelDistFromLeft;
	}
	public static int getMedspeciallabeldistfromtop() {
		return medSpecialLabelDistFromTop;
	}
	public static int getDoctornamelabeldistfromtop() {
		return doctorNameLabelDistFromTop;
	}
	public static int getLocationlabeldistfromtop() {
		return locationLabelDistFromTop;
	}
	public static int getDatelabeldistfromtop() {
		return dateLabelDistFromTop;
	}
	public static int getTimelabeldistfromtop() {
		return timeLabelDistFromTop;
	}
	public static int getFielddistfromleft() {
		return fieldDistFromLeft;
	}
	public static int getFieldfromtop() {
		return fieldFromTop;
	}
	public static int getLabelHeight() {
		return labelHeight;
	}
	public static int getLabelWidth() {
		return labelWidth;
	}
	public static int getSaveButtonWidth() {
		return saveButtonWidth;
	}
	public static int getSaveButtonHeight() {
		return saveButtonHeight;
	}
	public static int getSaveButtonDistFromTop() {
		return saveButtonDistFromTop;
	}
	public static int getSaveButtonDistFromLeft() {
		return saveButtonDistFromLeft;
	}
	public static int getCancelButtonWidth() {
		return cancelButtonWidth;
	}
	public static int getCancelButtonHeight() {
		return cancelButtonHeight;
	}
	public static int getCancelButtonDistFromTop() {
		return cancelButtonDistFromTop;
	}
	public static int getCancelButtonDistFromLeft() {
		return cancelButtonDistFromLeft;
	}
	public static int getHelpDialogWidth() {
		return (pageWidth + 220);
	}
	public static int getHelpDialogHeight() {
		return (pageHeight + 300);
	}
}
