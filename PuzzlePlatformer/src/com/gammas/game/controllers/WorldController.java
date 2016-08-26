package com.gammas.game.controllers;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

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

	public void KeyPressed(KeyEvent e){
		for (int x = 0; x < worldSizeX; x++) {
			for (int y = 0; y < worldSizeY; y++) {
				world.GetTileAt(x, y).KeyPressed(e);
			}
		}

		for (int i = 0; i < world.gameObjects.size(); i++) {
			world.gameObjects.get(i).KeyPressed(e);
		}
	}
	
	public void KeyReleased(KeyEvent e){
		for (int x = 0; x < worldSizeX; x++) {
			for (int y = 0; y < worldSizeY; y++) {
				world.GetTileAt(x, y).KeyReleased(e);
			}
		}

		for (int i = 0; i < world.gameObjects.size(); i++) {
			world.gameObjects.get(i).KeyReleased(e);
		}
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
