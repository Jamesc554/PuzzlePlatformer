package com.gammas.game.models;

import com.gammas.game.Platformer;

public class Tile extends GameObject {

	public Tile(int _x, int _y, int _width, int _height, String imageFileName) {
		super(_x, _y, _width, _height, imageFileName);
	}
	
	public Tile(int _width, int _height, String imageFileName) {
		super(0, 0, _width, _height, imageFileName);
	}

	
	public Tile[] GetNeighbours(){
		Tile[] tiles = new Tile[8];
		World world = Platformer.instance.worldController.world;
		int i = 0;
		
		for (int xx = x - 1; xx < x + 1; xx++){
			for (int yy = y - 1; yy < y + 1; yy++){
				if (world.GetTileAt(xx, yy) != null){
					tiles[i] = world.GetTileAt(xx, yy);
				}
			}
		}
		
		/* // Top Left
		if (world.GetTileAt(x - 1, y - 1) != null)
			tiles[0] = world.GetTileAt(x - 1, y - 1);
		
		// Top Middle
		if (world.GetTileAt(x, y - 1) != null)
			tiles[1] = world.GetTileAt(x, y - 1);
		
		// Top Right
		if (world.GetTileAt(x + 1, y - 1) != null)
			tiles[2] = world.GetTileAt(x + 1, y - 1);
		
		// Middle Left
		if (world.GetTileAt(x - 1, y) != null)
			tiles[3] = world.GetTileAt(x - 1, y);
		
		// Middle Right
		if (world.GetTileAt(x + 1, y) != null)
			tiles[4] = world.GetTileAt(x + 1, y);
		
		// Bottom Left
		if (world.GetTileAt(x - 1, y + 1) != null)
			tiles[5] = world.GetTileAt(x - 1, y + 1);
		
		// Bottom Middle
		if (world.GetTileAt(x, y + 1) != null)
			tiles[6] = world.GetTileAt(x, y + 1);
		
		// Bottom Right
		if (world.GetTileAt(x + 1, y + 1) != null)
			tiles[7] = world.GetTileAt(x + 1, y + 1); */
		
		return tiles;
	}
}
