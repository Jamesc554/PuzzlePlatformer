package com.gammas.game.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.gammas.game.Platformer;

public class KeyInputHandler extends KeyAdapter {
	
	Platformer p;
	
	public KeyInputHandler(Platformer _p){
		p = _p;
	}
	
	public void keyPressed(KeyEvent e) {
		p.worldController.KeyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		p.worldController.KeyReleased(e);
	}
}
