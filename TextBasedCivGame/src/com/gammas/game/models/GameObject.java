package com.gammas.game.models;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameObject {

	public int x, y;
	public int width, height;
	public Sprite sprite;
	public String name = "air";

	public GameObject(int _x, int _y, int _width, int _height, String imageFileName) {
		x = _x;
		y = _y;
		width = _width;
		height = _height;
		try {
			sprite = new Sprite(ImageIO.read(new File(imageFileName)));
		} catch (IOException e) {
			System.err.println("Failed to load: " + imageFileName);
		}

		name = imageFileName.substring(0, imageFileName.length() - 4);
		System.out.println(name);
	}

	public void update(double deltaTime) {

	}

	public void draw(Graphics g) {
		sprite.draw(g, x, y);
	}
}
