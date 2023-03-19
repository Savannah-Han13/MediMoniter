package view;

import java.awt.Color;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class HelpButton extends CustomButton{
	//Help Button - opens up dialog of instructions for using application
	public HelpButton(ActionListener actionListener) {
		//setup
		setSize(MPGUI_Info.getHelpButtonWidth(), MPGUI_Info.getHelpButtonHeight());
		setText("Help");
		
		setBackground(Color.LIGHT_GRAY);
	}
}
