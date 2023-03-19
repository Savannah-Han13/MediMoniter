package view;

public class CAGUI_Info {
	//label size and location on confirmed appointment panel (used for setting size and location)
	private static final int labelDistFromTop = 30;
	private static final int labelDistFromLeft = 20;
	private static final int labelWidth = 170;
	private static final int labelHeight = 30;
	
	//square buttons size (editButton, deleteButton)
	private static final int squareButtonLength = 40;

	//getters
	public static int getLabelDistFromTop() {
		return labelDistFromTop;
	}

	public static int getLabelDistFromLeft() {
		return labelDistFromLeft;
	}

	public static int getLabelWidth() {
		return labelWidth;
	}

	public static int getLabelHeight() {
		return labelHeight;
	}

	public static int getSquareButtonLength() {
		return squareButtonLength;
	}
}
