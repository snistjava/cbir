package com.snist.it.cbir.ui;

import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.snist.it.cbir.service.ImageHistogram;

public class MainFrame extends JFrame {

  private static final long serialVersionUID = 1L;
	public ImageHistogram srchistogram, resulthistogram;

	// UI Components
	public JTextField loadPath;
	public JLabel loadImage;
	public JLabel result;

	public JButton browseSourceImg;
	public JButton browseDBImg;
	public JButton showRGBHistogram;
	public JButton search;

	public JPanel srcImagePanel;
	public JPanel resultImagePanel;

	public JScrollPane scrollPane;

	public File srcFile, dbFile;
	public BufferedImage srcBufferedImg, dbBufferedImage;

	public ArrayList<BufferedImage> imageDBList;

	public static final int baseSize = 128;

	public static final String basePath = "D:\\CBIR Workspace\\CorelDB";
	public CustomActionListener customActionListener;
	
	public MainFrame() 
	{
		
		setLayout(null);
		loadImage = new JLabel("Load Image");
		loadImage.setBounds(10, 10, 250, 30);
		add(loadImage);

		loadPath = new JTextField();
		loadPath.setBounds(10, 60, 500, 30);
		add(loadPath);

		browseSourceImg = new JButton("Select an Image for search");
		browseSourceImg.setBounds(600, 60, 200, 30);
		add(browseSourceImg);

		srcImagePanel = new JPanel();
		srcImagePanel.setBounds(10, 150, 300, 200);
		srcImagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(srcImagePanel);

		browseDBImg = new JButton("Select the image Database directory");
		browseDBImg.setBounds(400, 150, 300, 30);
		add(browseDBImg);

		search = new JButton("Search The Similar Images in Database");
		search.setBounds(400, 200, 300, 30);
		add(search);

		showRGBHistogram = new JButton("Show Histogram for Source Image");
		showRGBHistogram.setBounds(400, 250, 300, 30);
		add(showRGBHistogram);

		result = new JLabel("Results");
		result.setBounds(10,380, 250, 30);
		add(result);

		
		resultImagePanel=new JPanel();
		scrollPane = new JScrollPane(resultImagePanel);
		scrollPane.setBounds(10, 430, 850, 200);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);

		customActionListener=new CustomActionListener(this);
		browseSourceImg.addActionListener(customActionListener);
		browseDBImg.addActionListener(customActionListener);
		showRGBHistogram.addActionListener(customActionListener);
		search.addActionListener(customActionListener);

	}

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
		mainFrame.setSize(900, 700);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
