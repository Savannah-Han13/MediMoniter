package view;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import controller.FolderPath;

@SuppressWarnings("serial")
public class DeleteButton extends CustomButton {
	//used to obtain name of image to use for icon and related file paths
	FolderPath path = new FolderPath();
	
	//Delete Button - used to delete the given confirmed appointment
	public DeleteButton(ActionListener actionListener) {
		//setup
		this.addActionListener(actionListener);
		setSize(CAGUI_Info.getSquareButtonLength(), CAGUI_Info.getSquareButtonLength());
		setBackground(ColorPalette.getPASTEL_RED());
		
		//set icon of edit button
		setIcon(new ImageIcon(scaleImg(path.getImagePath() + "deleteIcon.png", 
				CAGUI_Info.getSquareButtonLength() - 10, CAGUI_Info.getSquareButtonLength() - 10)));
		
	}
}
