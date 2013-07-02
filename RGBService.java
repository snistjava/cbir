package com.snist.it.cbir.service;

import java.awt.image.BufferedImage;

public class RGBService {
  private static final long serialVersionUID = 1L;

	private int imageHeight;
	private int imageWidth;
	public BufferedImage rgbImg;
	public ImageHistogram imageHistogram;

	public double[] getRgbMean(BufferedImage rgbImg) {
		imageHeight = rgbImg.getHeight();
		imageWidth = rgbImg.getWidth();
		imageHistogram = new ImageHistogram(rgbImg);
		imageHistogram.getRBGHistogram();
		int sumRed = 0, sumGreen = 0, sumBlue = 0;
		int Rfreq[] = imageHistogram.getRedBin();
		int Gfreq[] = imageHistogram.getGreenBin();
		int Bfreq[] = imageHistogram.getBlueBin();
		for (int i = 0; i < 256; i++) {
			sumRed += Rfreq[i] * i;
			sumGreen += Gfreq[i] * i;
			sumBlue += Bfreq[i] * i;
		}

		double redMean = sumRed / (imageHeight * imageWidth);
		double greenMean = sumGreen / (imageHeight * imageWidth);
		double blueMean = sumBlue / (imageHeight * imageWidth);

		double rgb[] = { redMean, greenMean, blueMean };
		return rgb;
	}
}
