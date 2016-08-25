package com.gammas.game.models;

import java.awt.Graphics;
import java.awt.Image;

public class Sprite {

	private Image image;

	public Sprite(Image _image) {
		image = _image;
	}

	public int GetWidth() {
		return image.getWidth(null);
	}

	public int GetHeight() {
		return image.getHeight(null);
	}

	public void draw(Graphics g, int x, int y) {
		g.drawImage(image, x * 32, y * 32, null);
	}

}
