package com.gammas.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.gammas.game.controllers.WorldController;

public class Utopia extends Canvas {
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
	public static Utopia instance;

	public Utopia() throws IOException {

		instance = this;
		worldController = new WorldController(60, 34);

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

}
