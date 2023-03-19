package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

//allows for easier use of the below method
@SuppressWarnings("serial")
public class CustomButton extends JButton{
	//scales images to fit labels, buttons, etc
	public Image scaleImg(String imgName, int width, int height) {
		//https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
		BufferedImage img = null;
		
		try {
		    img = ImageIO.read(new File(imgName));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return dimg;
	}
}
