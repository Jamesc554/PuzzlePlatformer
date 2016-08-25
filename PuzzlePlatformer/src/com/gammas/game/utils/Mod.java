package com.gammas.game.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gammas.game.models.World;

public class Mod {
	public List<World> levels = new ArrayList<World>();

	public String modRef;
	public String modName;
	public String modDesc;
	public String modAuthor;
	public String modVersion;
	public String modGameVersion;
	
	public String modFileLocation;
	public String levelFileLocation;
	public String spriteFileLocation;
	public String luaFileLocation;
	public String xmlFileLocation;

	public Mod(String filePath) {
		modFileLocation = filePath;
		LoadMod(filePath);
		LoadTiles();
		LoadLevels();
	}

	public void LoadMod(String filePath) {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = builderFactory.newDocumentBuilder();
			Document document = dBuilder.parse(filePath + "/package.xml");
			document.normalize();

			NodeList rootNodes = document.getElementsByTagName("package");
			Element rootNode = (Element) rootNodes.item(0);
			modRef = (String) ((Element) rootNode.getElementsByTagName("ModRef").item(0)).getTextContent();
			modName = (String) ((Element) rootNode.getElementsByTagName("ModName").item(0)).getTextContent();
			modDesc = (String) ((Element) rootNode.getElementsByTagName("Description").item(0)).getTextContent();
			modAuthor = (String) ((Element) rootNode.getElementsByTagName("Author").item(0)).getTextContent();
			modVersion = (String) ((Element) rootNode.getElementsByTagName("ModVersion").item(0)).getTextContent();
			modGameVersion = (String) ((Element) rootNode.getElementsByTagName("GameVersion").item(0)).getTextContent();

			levelFileLocation = (String) ((Element) rootNode.getElementsByTagName("LevelFileLocation").item(0)).getTextContent();
			spriteFileLocation = (String) ((Element) rootNode.getElementsByTagName("SpriteFileLocation").item(0)).getTextContent();
			luaFileLocation = (String) ((Element) rootNode.getElementsByTagName("LuaFileLocation").item(0)).getTextContent();
			xmlFileLocation = (String) ((Element) rootNode.getElementsByTagName("XmlFileLocation").item(0)).getTextContent();

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void LoadTiles() {
		Tiles.ReadFromXML(modFileLocation + xmlFileLocation + "/tiles.xml", modFileLocation + spriteFileLocation + "/");
	}

	public void LoadLevels(){
		File folder = new File(modFileLocation + levelFileLocation);
		File[] files = folder.listFiles();
		
		for (int i = 0; i < files.length; i++){
			if (files[i].isFile()){
				World newWorld = new World(files[i], "level-" + i);
				newWorld.CreateWorld();
				levels.add(newWorld);
			}
		}
	}
}
