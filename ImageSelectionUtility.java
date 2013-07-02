package com.snist.it.cbir.util;


import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
 
public class ImageSelectionUtility {

  public static  File getImage(){
			File selectedFile=null;
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileFilter imageFilter = new FileNameExtensionFilter("image files ", "jpeg","jpg","gif","png","bmp");
			fileChooser.addChoosableFileFilter(imageFilter);

			int ret = fileChooser.showDialog(null, "Select an image for search ");
			if(ret==JFileChooser.CANCEL_OPTION){
				JOptionPane.showMessageDialog(null, "Please Select an image");
				}else	if (ret == JFileChooser.APPROVE_OPTION) {
							 selectedFile = fileChooser.getSelectedFile();
							}
			 return selectedFile;
	}
	
public static File[] getDirectoryFiles(){
	File[] path=null;
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	int ret = fileChooser.showDialog(null, "Select the Image DB Directory");
		if(ret==JFileChooser.CANCEL_OPTION){
		JOptionPane.showMessageDialog(null, "Please Select the Image database path");
		}else	if (ret == JFileChooser.APPROVE_OPTION) {
			path = fileChooser.getSelectedFile().listFiles();
			}
			
	return path;
}

    /* code for testing the Utility 
     
      public static void main(String[] args) throws IOException {
		
		System.out.println(selectDirectoryPath());
		File selectedFile=selectFile();
		BufferedImage image = ImageIO.read(selectedFile);
		JLabel label = new JLabel(new ImageIcon(image));
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(label);
		f.pack();
		f.setLocation(200,200);
		f.setVisible(true);
		}
		*/
}
