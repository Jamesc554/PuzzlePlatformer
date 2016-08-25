package com.gammas.game.controllers;

import java.awt.Graphics;

import com.gammas.game.models.World;

public class WorldController {
	public World world;
	public int worldSizeX, worldSizeY;

	public WorldController() {
		
	}
	
	public void SetWorld(World _world){
		world = _world;
		worldSizeX = _world.width;
		worldSizeY = _world.height;
	}

	public void update(double deltaTime) {
		for (int x = 0; x < worldSizeX; x++) {
			for (int y = 0; y < worldSizeY; y++) {
				world.GetTileAt(x, y).update(deltaTime);
			}
		}

		for (int i = 0; i < world.gameObjects.size(); i++) {
			world.gameObjects.get(i).update(deltaTime);
		}
	}

	public void draw(Graphics g) {

		for (int x = 0; x < worldSizeX; x++) {
			for (int y = 0; y < worldSizeY; y++) {
				world.GetTileAt(x, y).draw(g);
			}
		}

		for (int i = 0; i < world.gameObjects.size(); i++) {
			world.gameObjects.get(i).draw(g);
		}
	}

}
