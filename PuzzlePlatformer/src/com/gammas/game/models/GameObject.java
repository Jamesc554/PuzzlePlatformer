package com.gammas.game.models;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gammas.game.utils.References;

public class GameObject {

	public int x, y;
	public int width, height;
	public Sprite sprite;
	public String name = "air";
	public String imageFileName;

	public GameObject(int _x, int _y, int _width, int _height, String _imageFileName) {
		x = _x;
		y = _y;
		width = _width;
		height = _height;
		try {
			sprite = new Sprite(ImageIO.read(new File(_imageFileName)));
		} catch (IOException e) {
			try {
				sprite = new Sprite(ImageIO.read(new File(References.TileSpriteLocation + "Error.png")));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.err.println("Failed to load: " + _imageFileName);
		}

		imageFileName = _imageFileName;
		name = imageFileName.substring(28, imageFileName.length() - 4);
		System.out.println(name);
	}

	public void KeyPressed(KeyEvent e){
	}
	
	public void KeyReleased(KeyEvent e){
	}
	
	public void update(double deltaTime) {
	}

	public void draw(Graphics g) {
		sprite.draw(g, x, y);
	}
}
