package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ConfirmButton extends JButton {
	//confirm button - used to confirm a suggested appointment (draft appointment is shown with suggested information)
	public ConfirmButton(ActionListener actionListener) {
		//setup
		this.addActionListener(actionListener);
		setText("Confirm");
		setSize(DAGUI_Info.getSaveButtonWidth(), DAGUI_Info.getSaveButtonHeight());
		setBackground(ColorPalette.getPISTACHIO());
	}
}
