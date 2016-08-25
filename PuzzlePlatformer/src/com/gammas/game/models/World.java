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
	
	private Tile[][] tiles;
	private Color[][] level;
	public List<GameObject> gameObjects = new ArrayList<GameObject>();
	public String levelName;
	public File levelFile;
	public int width, height;

	public World(File _levelFile, String _levelName) {
		levelName = _levelName;
		levelFile = _levelFile;
	}

	public void CreateWorld() {
		level = LoadLevel();
		
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				Tile newTile = Tiles.GetTile((level[x][y]));
				newTile.x = x;
				newTile.y = y;
				tiles[x][y] = newTile;
			}
		}
	}

	public Color[][] LoadLevel() {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(levelFile);
		} catch (IOException e) {
			System.err.println("Failed to load: " + levelName + ".png");
			System.exit(1);
		}

		Color[][] tempArray = new Color[image.getWidth()][image.getHeight()];
		tiles = new Tile[image.getWidth()][image.getHeight()];
		width = image.getWidth();
		height = image.getHeight();
				
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

}
