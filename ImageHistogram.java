package com.snist.it.cbir.service;

import java.awt.image.BufferedImage;

public class ImageHistogram {

  private BufferedImage image;
	private int imageHeight;
	private int imageWidth;
	private int[] redBin = new int[256];
	private int[] greenBin = new int[256];
	private int[] blueBin = new int[256];
	private int sumRed, sumGreen, sumBlue;

	public int getSumRed() {
		return sumRed;

	}

	public void setSumRed(int sumRed) {
		this.sumRed = sumRed;
	}

	public int getSumGreen() {
		return sumGreen;
	}

	public void setSumGreen(int sumGreen) {
		this.sumGreen = sumGreen;
	}

	public int getSumBlue() {
		return sumBlue;
	}

	public void setSumBlue(int sumBlue) {
		this.sumBlue = sumBlue;
	}

	public void getRBGHistogram() {

		sumBlue = 0;
		sumGreen = 0;
		sumRed = 0;

		for (int i = 0; i < getImageHeight(); i++) {
			for (int j = 0; j < getImageWidth(); j++) {

				int pixel = image.getRGB(j, i);

				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				redBin[red]++;
				greenBin[green]++;
				blueBin[blue]++;

			}
		}

		for (int i = 0; i < 256; i++) {

			sumRed += redBin[i] * i;
			sumGreen += greenBin[i] * i;
			sumBlue += blueBin[i] * i;

		}

	}

	public int[] getRedBin() {
		return redBin;
	}

	public void setRedBin(int[] redBin) {
		this.redBin = redBin;
	}

	public int[] getGreenBin() {
		return greenBin;
	}

	public void setGreenBin(int[] greenBin) {
		this.greenBin = greenBin;
	}

	public int[] getBlueBin() {
		return blueBin;
	}

	public void setBlueBin(int[] blueBin) {
		this.blueBin = blueBin;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		setImageHeight(image.getHeight());
		setImageWidth(image.getWidth());
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public ImageHistogram(BufferedImage image) {
		this.setImage(image);
		setImageHeight(image.getHeight());
		setImageWidth(image.getWidth());
	}

	public ImageHistogram() {
	}

}
