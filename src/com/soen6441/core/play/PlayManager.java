package com.soen6441.core.play;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.soen6441.core.map.GridMap;


/**
 * The class PalyManager will handle the task of saving the gamePlay in XML file and reading the XML file to generate gamePlay;
 * 
 * @author Mohammad Ali
 * @author Chenglong Zhang
 * @version $Revision: 1.0 $
 */
public class PlayManager {

	/**
	 * The method save will save the gamePlay in the specified file
	 * 
	 * @param file The name of the File where to save .
	 * @param play A play object
	 */

	public void save(File file, Play play) {
		play.getMap().getPathManager().generatePathsFromRoadItems ();

		// Creating the xml file for map
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("xml");

		rootElement.add(play.encode());

		// lets write to a file
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(new FileWriter(file));
			writer.write(document);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	
	/**
	 * The method read will read the specified file and generate a Play object.
	 * This method will use Xpath to navigate through MapXml file.
	 * Requires jaxen 1.1-beta-6.jar located in Dom4j lib folder.
	 * 
	 * @param file The name of the File form which to read 
     * @return Play  
     */
	public Play read(File file) {

		int coins = 0;
		

		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// reading the number of coins stored in map F
		Node coinsNode = document.selectSingleNode("//xml/Play/coins");
		coins = Integer.parseInt(coinsNode.getText());


		/*
		 * Creating a GridMap Object in order to create an Play object after. In
		 * order to create a GridMap object we need a MapPath object
		 */

		GridMap map = new GridMap();

		Element gridMapNode = (Element) document.selectSingleNode("//xml/Play/map/GridMap");
		map.decode(gridMapNode);

		// Finally creating a Play object to return
		Play play = Play.currentPlay();
		play.setCoins(coins);
		play.setLife(10);
		play.setMap(map);
		
		// map.getPathManager().generateRoadItemsFromPaths  ();  // giving Null Pointer Exception
		
		return play;

	}  
}
