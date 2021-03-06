package com.soen6441.core.critter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.soen6441.core.effect.AffectableValue;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class CritterManager {

	/*
	 * Mark - Constructors
	 */

	/**
	 * Constructor for CritterManager.
	 * @param critterName String
	 * @param filePath String
	 */
	public CritterManager(String critterName, String filePath) {
		this.critterName = critterName;
		this.filePath = filePath;
		this.analyse();
	}
	
	/*
	 * Mark - Basic - Properties
	 */
	 
	private String critterName;

	private String filePath;

	private Critter critter;
	

	/*
	 * Mark - Basic - Methods
	 */
	 
	public void analyse() {
		
		//Read XML file which stores tower information.
		SAXReader reader = new SAXReader();
	    Document document = null;
	    
		try {
			document = reader.read(filePath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		Element root = document.getRootElement();
		Element critterElement = root.element(Critter.NameForArchiving.Class);
		critter = new Critter();
		critter.decode(critterElement);
	}
	
	/**
	 * Method generateCritter.
	 * @param critterMultiplier CritterMultiplier
	 * @return Critter 
     */
	public Critter generateCritter(CritterMultiplier critterMultiplier) {
		Critter newCritter= new Critter();
		newCritter.setName(critter.getName());
		newCritter.setSpeed(new AffectableValue(critter.getSpeed().getOriginalValue () * critterMultiplier.getSpeedMultiplier()));		
		newCritter.setTotalHp((int) (critterMultiplier.getHpMultiplier()* critter.getTotalHp()));
		newCritter.setHp(newCritter.getTotalHp());
		newCritter.setReward((int) (critter.getReward()* critterMultiplier.getRewardMultiplier()));
		newCritter.setStealAmount(critter.getStealAmount());
		newCritter.setName(critter.getName());
		newCritter.setCellImageName(critter.getCellImageName());
		
		return newCritter;
	}
	
	/*
	 * Mark - Basic - Getters & Setters
	 */

	/**
	 * Method getCritterName.
	 * @return String
	 */
	public String getCritterName() {
		return critterName;
	}

	/**
	 * Method setCritterName.
	 * @param critterName String
	 */
	public void setCritterName(String critterName) {
		this.critterName = critterName;
	}

	/**
	 * Method getFilePath.
	 * @return String 
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Method setFilePath.
	 * @param filePath String
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
