package com.snist.it.cbir.ui;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.snist.it.cbir.service.EucledianDistanceService;
import com.snist.it.cbir.service.RGBService;
import com.snist.it.cbir.util.ImageSelectionUtility;

public class CustomActionListener implements ActionListener {
  public double[] a,b,d;
	double c;
	public MainFrame mainFrame;
	public EucledianDistanceService eucledianService;
	public ArrayList<BufferedImage> srcBufferedImageArray;
	RGBService rgb=new RGBService();

	public CustomActionListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		System.out.println("Action Listener Constructor invoked  ");
	}

	public BufferedImage rescale(BufferedImage originalImage) {
		BufferedImage resizedImage = new BufferedImage(MainFrame.baseSize,MainFrame.baseSize, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, MainFrame.baseSize,MainFrame.baseSize, null);
		g.dispose();
		return resizedImage;
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Select an Image for search")) {
			mainFrame.srcFile = ImageSelectionUtility.getImage();
			
			try {
				mainFrame.srcBufferedImg = rescale(ImageIO.read(mainFrame.srcFile));
				} catch (IOException e) {
				System.out.println("Exception happened while reading the image for rescale");
				e.printStackTrace();
				}
				
			String sourceFilePath = mainFrame.srcFile.getAbsolutePath();
			mainFrame.loadPath.setText(sourceFilePath);
			mainFrame.srcImagePanel.setLayout(new BorderLayout(0, 0));
			mainFrame.srcImagePanel.add(new JLabel(new ImageIcon(mainFrame.srcBufferedImg)));
			mainFrame.setVisible(true);

		} else if (ae.getActionCommand().equals("Select the image Database directory")) {
			mainFrame.imageDBList = new ArrayList<BufferedImage>();
			
			try {
				File[] filesInDirectory = ImageSelectionUtility.getDirectoryFiles();
				for (File file : filesInDirectory) {
					mainFrame.dbFile = file;
					mainFrame.dbBufferedImage = rescale(ImageIO.read(file));
					mainFrame.imageDBList.add(mainFrame.dbBufferedImage);
					}
				
				/*for (int i = 0; i < mainFrame.imageDBList.size(); i++) {
					BufferedImage img = (BufferedImage) mainFrame.imageDBList.get(i);
				
					mainFrame.resultImagePanel.setLayout(new GridLayout());
					mainFrame.resultImagePanel.add(new JLabel(new ImageIcon(img)));
					mainFrame.setVisible(true);
					
				}*/
			} catch (Exception iOException) {
				System.out.println("Exception raised while reading the Database images ");
				iOException.printStackTrace();
			}
		} else if (ae.getActionCommand().equals("Show Histogram for Source Image")) {
			imageHistogramActionPerformed(ae);
			}
		else if(ae.getActionCommand().equals("Search The Similar Images in Database")){
			 a=rgb.getRgbMean(mainFrame.srcBufferedImg);
			
			for(int i=0;i<3;i++)
			{
				System.out.println(a[i]);	
			}
			
			for (int i = 0; i < mainFrame.imageDBList.size()-1; i++)
			{
				BufferedImage img = (BufferedImage) mainFrame.imageDBList.get(i);
				 b=rgb.getRgbMean(img);
				 for(int j=0;j<3;j++)
					{
						System.out.println(b[j]);	
					}
				eucledianService.findEcledianDistance(a, b);
				 /*d[i]=c;
				 System.out.println(d[i]);
				for(int j=0;j<3;j++)
				{
					System.out.println(b[j]);	
					//System.out.println(c);
				}*/
			}
	
			
		}
		else {
			JOptionPane.showMessageDialog(null,
					"You must select one image to be the reference.",
					"Aborting...", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void imageHistogramActionPerformed(ActionEvent ae) {

		new ImageHistogramFrame(mainFrame.srcBufferedImg);

		System.out.println("Search event generated");

	}
	
}
