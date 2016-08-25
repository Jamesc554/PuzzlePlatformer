package com.gammas.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

public class Window extends JPanel {

	private static final long serialVersionUID = -4616124642815256857L;
	public BufferedImage canvas;
	private int[][] pixels;
	public Random rand;

	public Window(int width, int height, Color c) {
		rand = new Random();
		canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		pixels = new int[width][height];
		fillCanvas(c);
	}

	public void fillCanvas(Color c) {

		for (int x = 0; x < canvas.getWidth(); x++) {
			for (int y = 0; y < canvas.getHeight(); y++) {
				/*
				 * Random Colour 10/10 int color = new Color(rand.nextInt(254) +
				 * 1, rand.nextInt(254) + 1, rand.nextInt(254) + 1).getRGB();
				 */
				int color = c.getRGB();
				pixels[x][y] = color;
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(canvas, null, null);
	}

	public void SetPixel(int x, int y, Color c) {
		pixels[x][y] = c.getRGB();
		System.out.println(c.getRGB());
	}

	public void Render() {
		for (int x = 0; x < pixels.length; x++) {
			for (int y = 0; y < pixels[0].length; y++) {
				canvas.setRGB(x, y, pixels[x][y]);
			}
		}

		repaint();
	}

	public void SetPixel(int x, int y, int rgb) {
		pixels[x][y] = rgb;
	}
}
