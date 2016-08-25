/**
 * 
 */
package com.gammas.game.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gammas.game.models.Tile;

/**
 * @author James Carter
 *
 */
public class Tiles {

	private static List<Tile> tiles = new ArrayList<Tile>();
	private static Map<String, Integer> tileIdDic = new HashMap<String, Integer>();
	private static Map<Color, Integer> tileColorDic = new HashMap<Color, Integer>();

	public static void GeneratePrototypes() {

		// AddTile(new Tile(32, 32, References.TileSpriteLocation + "Air.png"));
		// AddTile(new Tile(32, 32, References.TileSpriteLocation +
		// "Error.png"));
		// AddTile(new Tile(32, 32, References.TileSpriteLocation +
		// "Grass.png"));
		// AddTile(new Tile(32, 32, References.TileSpriteLocation +
		// "Brick.png"));
	}

	public static void ReadFromXML(String filePath, String spriteFilePath) {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = builderFactory.newDocumentBuilder();
			Document document = dBuilder.parse(filePath);
			document.normalize();

			NodeList rootNodes = document.getElementsByTagName("tiles");
			Element rootNode = (Element) rootNodes.item(0);
			NodeList tilesList = rootNode.getElementsByTagName("tile");

			for (int i = 0; i < tilesList.getLength(); i++) {
				Element tile = (Element) tilesList.item(i);

				String tileFilePath = References.TileSpriteLocation + "Error.png";

				tileFilePath = spriteFilePath + tile.getAttribute("spriteLocation") + tile.getAttribute("spriteFile");

				Tile newTile = new Tile(Integer.parseInt(tile.getAttribute("width")),
						Integer.parseInt(tile.getAttribute("height")), tileFilePath);
				Color color = new Color(Integer.parseInt(tile.getAttribute("R")),
						Integer.parseInt(tile.getAttribute("G")), Integer.parseInt(tile.getAttribute("B")));

				AddTile(newTile, color);
			}

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void AddTile(Tile tile, Color color) {
		tileIdDic.put(tile.name, tiles.size());
		tileColorDic.put(color, tiles.size());
		tiles.add(tile);
	}

	public static Tile GetTile(String tileName) {
		Tile oldTile = tiles.get(tileIdDic.get(tileName));
		Tile newTile = new Tile(oldTile.width, oldTile.height, oldTile.imageFileName);
		return newTile;
	}

	public static Tile GetTile(int tileId) {
		Tile oldTile = tiles.get(tileId);
		Tile newTile = new Tile(oldTile.width, oldTile.height, oldTile.imageFileName);
		return newTile;
	}

	public static Tile GetTile(Color color) {
		Tile oldTile = tiles.get(tileColorDic.get(color));
		Tile newTile = new Tile(oldTile.width, oldTile.height, oldTile.imageFileName);
		return newTile;
	}

}
