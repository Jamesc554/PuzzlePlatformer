package com.gammas.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.gammas.game.controllers.WorldController;
import com.gammas.game.models.World;
import com.gammas.game.utils.Mod;

public class Platformer extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public BufferStrategy strategy;
	public boolean running = true;

	public final int WIDTH = 1920;
	public final int HEIGHT = 1080;

	public int fps;
	public long lastFpsTime;

	public WorldController worldController;
	public static Platformer instance;
	
	public List<Mod> mods;

	public Platformer() throws IOException {

		instance = this;
		mods = LoadMods();
		worldController = new WorldController();
		worldController.SetWorld(mods.get(0).levels.get(0));

		frame = new JFrame("Utopia Sim - v0.01a");

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);
		setBounds(0, 0, WIDTH, HEIGHT);
		panel.add(this);
		setIgnoreRepaint(true);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		requestFocus();

		createBufferStrategy(2);
		strategy = getBufferStrategy();

		gameLoop();

	}

	public void gameLoop() {
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

		while (running) {
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) OPTIMAL_TIME);

			lastFpsTime += updateLength;
			fps++;

			if (lastFpsTime >= 1000000000) {
				System.out.println("(FPS: " + fps + ")");

				lastFpsTime = 0;
				fps = 0;
			}

			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);

			tick(delta);

			render(g);

			g.dispose();
			strategy.show();

			try {
				Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
			} catch (Exception e) {

			}
		}
	}

	public void tick(double deltaTime) {
		worldController.update(deltaTime);
	}

	public void render(Graphics g) {
		worldController.draw(g);
	}
	
	public List<Mod> LoadMods(){
		List<Mod> mods = new ArrayList<Mod>();
		
		File folder = new File("Assets");
		File[] files = folder.listFiles();
		
		for (int i = 0; i < files.length; i++){
			if (files[i].isDirectory()){
				Mod newMod = new Mod(files[i].getPath());
				System.out.println("Mod Path: " + files[i].getPath());
				mods.add(newMod);
			}
		}
		
		System.out.println("Amount of mods loaded: " + mods.size());
		return mods;
	}

}
