package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import controller.FolderPath;

@SuppressWarnings("serial")
public class HelpPanel extends JPanel {

	//ok Button - used to close dialog (set dialog invisible)
	private JButton okButton = new JButton("OK");
	
	//help Label - contains instruction text in html format
	private JLabel helpLabel = new JLabel();
	
	//file path
	private FolderPath path = new FolderPath();
	private String helpText = "";
	
	//parent dialog (mainpage); panel is added to the dialog
	private JDialog helpDialog = null;
	
	public HelpPanel() {
		//setup
		setLayout(null);
		setSize(DAGUI_Info.getHelpDialogWidth(), DAGUI_Info.getHelpDialogHeight());

		helpLabel.setBounds(30, 30, DAGUI_Info.getHelpDialogWidth() - 60, DAGUI_Info.getHelpDialogHeight() - DAGUI_Info.getSaveButtonHeight() - 180);
		readHelp();
		helpLabel.setText(helpText);
		add(helpLabel);
		
		//https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java
		okButton.setBounds(DAGUI_Info.getSaveButtonDistFromLeft() + 200, DAGUI_Info.getSaveButtonDistFromTop() + 290, DAGUI_Info.getSaveButtonWidth(), DAGUI_Info.getSaveButtonHeight());
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				helpDialog.setVisible(false);
			}
		});
		add(okButton);
		setVisible(true);
	}
	//set dialog
	public void setDialog(JDialog dialog) {
		helpDialog = dialog;
	}
	//read the html help file
	private void readHelp() {
		//https://www.w3schools.com/java/java_files_create.asp
		try {
			String filePath = path.getFilePath() + path.getHelpFileName() + ".html";
			File f = new File(filePath);
			if (f.exists()) {
				  //helpText
				BufferedReader reader = new BufferedReader(
					new FileReader(filePath));
				//https://stackoverflow.com/questions/7190618/most-efficient-way-to-check-if-a-file-is-empty-in-java-on-windows
				String curLine = "";
				//create string with the given text in file
				while ((curLine = reader.readLine()) != null) {
					helpText += curLine;
				}
			    reader.close();
			}
			else
			{
				helpText = "Help file not found!";
			}
	    } catch (IOException e) {
	    	helpText = "Error reading help file.";
	    	e.printStackTrace();
	    }
	}
}
