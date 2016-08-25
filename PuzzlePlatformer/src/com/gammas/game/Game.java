package com.gammas.game;

import java.io.IOException;

import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

public class Game {

	public static void main(String[] args) throws IOException {
		LuaState L = LuaStateFactory.newLuaState();
		L.openLibs();
		
		L.LdoFile("Assets/Lua/hello.lua");
		
		new Platformer();
	}

}
