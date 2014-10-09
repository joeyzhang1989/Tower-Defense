package com.soen6441.logic.map;

import java.io.File;

import com.soen6441.logic.Play;


/**
 * The class PalyManager will handle the task of saving the gamePlay in XML file and reading the XML file to generate gamePlay;
 * 
 * @author Mohammad Ali
 * @since version 1.0
 */
public class PlayManager {

	
	/**
	 * The method save will save the gamePlay in the specified file
	 * 
	 * @parma file The name of the File where to save .
	 * @param play A play object
	 */
	
	public void save (File file ,Play play){
		
	}
	
	
	/**
	 * The method read will read the specified file and generate a Play object.
	 * 
	 * @parma file The name of the File where to save .
	 */
	public Play read(File file){
		
		Play play = null;// dummy value ! must be removed after implementation
		return play;
	}

}
