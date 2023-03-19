package view;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import controller.FolderPath;


@SuppressWarnings("serial")
public class EditButton extends CustomButton {
	FolderPath path = new FolderPath();
	
	//Edit Button - used to edit the given confirmed appointment
	public EditButton(ActionListener actionListener) {
		//setup
		this.addActionListener(actionListener);
		setSize(CAGUI_Info.getSquareButtonLength(), CAGUI_Info.getSquareButtonLength());
		setBackground(ColorPalette.getPASTEL_GRAY());
		
		//set icon of edit button
		setIcon(new ImageIcon(scaleImg(path.getImagePath() + "editIcon.png", 
				CAGUI_Info.getSquareButtonLength() - 10, CAGUI_Info.getSquareButtonLength() - 10)));
	}
}
