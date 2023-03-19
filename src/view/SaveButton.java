package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;

//Save Button - used to save input from draft appointment panel to a confirmed appointment
@SuppressWarnings("serial")
public class SaveButton extends JButton{
	public SaveButton(ActionListener actionListener) {
		//setup
		this.addActionListener(actionListener);
		setText("Save");
		setSize(DAGUI_Info.getSaveButtonWidth(), DAGUI_Info.getSaveButtonHeight());
		setBackground(ColorPalette.getPISTACHIO());
	}
}
