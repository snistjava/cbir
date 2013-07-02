package com.snist.it.cbir.ui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.snist.it.cbir.service.ImageHistogram;

public class ImageHistogramFrame extends JFrame{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int frameHeight = 500;
	private static final int frameWidth = 500;
	private int imageHeight;
	private int imageWidth;
	/**
	 * @param args
	 */
	
	public BufferedImage image = null;
	public ImageHistogram imageHistogram = new ImageHistogram();
	
	public ImageHistogramFrame(BufferedImage image){
		
        setSize(frameHeight,frameWidth);
        setTitle("RGB Histogram");
        setResizable(false);
       		
        this.image = image;
        imageHeight = image.getHeight();
        imageWidth = image.getWidth();
		imageHistogram=new ImageHistogram(image);
		imageHistogram.getRBGHistogram();
		
		setVisible(true);
	}
	
	public void paint(Graphics g) {
        int sumRed = 0, sumGreen = 0, sumBlue = 0;
        int Rfreq[] = imageHistogram.getRedBin();
        int Gfreq[] = imageHistogram.getGreenBin();
        int Bfreq[] = imageHistogram.getBlueBin();
        
        super.paint(g);
        Graphics2D g2D=(Graphics2D)g;
        
        for(int i=0;i<256;i++) {

            g2D.setColor(Color.RED);
            g2D.drawLine(50+i,frameHeight-50,50+i,frameHeight-(Rfreq[i]/10)-50 );
             
            g2D.setColor(Color.GREEN);
            g2D.drawLine(50+i,frameHeight-50,50+i,frameHeight-(Gfreq[i]/10)-50);
            
            g2D.setColor(Color.BLUE);
            g2D.drawLine(50+i,frameHeight-50,50+i,frameHeight-(Bfreq[i]/10)-50);
            
            sumRed+=Rfreq[i]*i;
            sumGreen+=Gfreq[i]*i;
            sumBlue+=Bfreq[i]*i;
            
        }
        g.setColor(Color.BLACK);
        
        g2D.drawLine(40,frameHeight-40,40,frameHeight-256);
        g2D.drawLine(40,frameHeight-40,256,frameHeight-40);
        g2D.drawString("0-->255",260,frameHeight-40);
        g2D.setFont(new Font("Calibri",Font.BOLD,12));
        
        g2D.drawString("RGB Histogram",300,100);
        g2D.drawString("Red Mean :",300,120);
        g2D.drawString(Long.toString( sumRed/(imageHeight*imageWidth)),400,120);
        g2D.drawString("Green Mean :",300,140);
        g2D.drawString(Long.toString(( sumGreen/(imageHeight*imageWidth))),400,140);
        g2D.drawString("Blue Mean :",300,160);
        g2D.drawString(Long.toString(( sumBlue/(imageHeight*imageWidth))),400,160);
        
        }
        
	}
