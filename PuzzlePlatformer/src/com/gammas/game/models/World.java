package com.gammas.game.models;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.gammas.game.utils.References;
import com.gammas.game.utils.Tiles;

public class World {

	public int width, height;
	private Tile[][] tiles;
	private Color[][] level;
	public List<GameObject> gameObjects = new ArrayList<GameObject>();
	public String levelName;

	public World(int _width, int _height, String _levelName) {
		width = _width;
		height = _height;
		levelName = _levelName;
	}

	public void CreateWorld() {
		tiles = new Tile[width][height];
		level = LoadLevel();
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Tile newTile = Tiles.GetTile(GetTileFromColour(level[x][y]));
				newTile.x = x;
				newTile.y = y;
				tiles[x][y] = newTile;
			}
		}
	}

	public Color[][] LoadLevel() {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File(References.LevelFileLocation + levelName + ".png"));
		} catch (IOException e) {
			System.err.println("Failed to load: " + levelName + ".png");
			System.exit(1);
		}

		Color[][] tempArray = new Color[image.getWidth()][image.getHeight()];
				
		for (int x = 0; x < image.getWidth(); x++){
			for (int y = 0; y < image.getHeight(); y++){
				tempArray[x][y] = new Color(image.getRGB(x, y));
			}
		}
		
		return tempArray;
	}

	public Tile GetTileAt(int x, int y) {
		return tiles[x][y];
	}

	public void SetTile(int x, int y, Tile tile) {
		tiles[x][y] = tile;
	}

	public int GetTileFromColour(Color color) {
		if (color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255)
			return 0;
		
		if (color.getRed() == 0 && color.getGreen() == 255 && color.getBlue() == 0)
			return 2;
		
		if (color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0)
			return 3;

		System.err.println("Unable to find a Tile matching R: " + color.getRed() + " G: " + color.getGreen() + " B: "
				+ color.getBlue());
		return 1;
	}

}
