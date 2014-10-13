package com.soen6441.core.tower;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.soen6441.core.effect.AffectableValue;

/**
 * This class responds to manage towers. Such as, create, upgrade, etc. 
 * 
 * @author Haiyang Sun
 *
 */

public class TowerManager {
	
	/**
	 * Properties of TowerManager
	 */
	public String towerType;
	public String filePath;
	public File dataFile;
	
	
	
	private List<Tower> leveledTowers;
	
	public TowerManager(String towerType, String filePath) {
		
		this.towerType = towerType;
		this.filePath = filePath;	
	}
	/**
	 * This method is used to read and analyze files that store tower information.
	 */
	
	public void analyse() {
		
		SAXReader reader = new SAXReader();
	    Document document = null;
		try {
			document = reader.read(filePath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
        Element towerManagerElement = root.element("TowerManager");
        Element leveledTowersElement = towerManagerElement.element("leveledTowers");
        for ( @SuppressWarnings("rawtypes")Iterator i = leveledTowersElement.elementIterator(); i.hasNext(); ) {
            Element element = (Element)i.next();
            Tower tower = new Tower();
            tower.setManager(this);
    		tower.setLevel(Integer.parseInt(element.element("level").getText()));
    		tower.setInitialPrice(Integer.parseInt(element.element("initialPrice").getText()));
    		tower.setUpgradePrice(Integer.parseInt(element.element("upgradePrice").getText()));
    		tower.setSellPrice(Integer.parseInt(element.element("sellPrice").getText()));
    		AffectableValue range = new AffectableValue(Double.parseDouble(element.element("range").getText()));
    		tower.setRange(range);
    		
    		AffectableValue damage= new AffectableValue(Double.parseDouble(element.element("damage").getText()));
    		tower.setDamage(damage);
    		
    		AffectableValue cdTime = new AffectableValue(Double.parseDouble(element.element("cdTime").getText()));
    		tower.setCdTime(cdTime);		
    		if (this.towerType == "BottleTower") {
    			BottleTower towerResult = (BottleTower)tower;
    			leveledTowers.add(towerResult);
    		}
    		if (this.towerType == "MudTower") {
    			MudTower towerResult = (MudTower)tower;
    			towerResult.setSlowRate(new AffectableValue(Double.parseDouble(element.element("slowRate").getText())));
    			towerResult.setSlowDuration(new AffectableValue(Double.parseDouble(element.element("slowDuration").getText())));
    			leveledTowers.add(towerResult);
    		}
    		
    		
           	
        }
        
	}
	
	/**
	 * Create a specific Tower object.
	 * 
	 * @return Tower 
	 */
	
	public Tower createTower() {
		//new
		Tower tower = leveledTowers.get(0);
		//copyTo
		if (this.towerType == "BottleTower") {
			BottleTower bottleTower = (BottleTower)tower;
			BottleTower newTower = new BottleTower();
			bottleTower.copyTo(newTower);
			return newTower;
		}
		if (this.towerType == "MudTower") {
			MudTower mudTower = (MudTower)tower;
			MudTower newTower = new MudTower();
			mudTower.copyTo(newTower);
			return newTower;
		}
		return null;
	}
	
	/**
	 * This method is used to upgrade an existing tower.
	 * 
	 * @param tower a Tower object
	 */
	
	public void upgrade(Tower tower) {
		//copyTo
		switch (tower.level) {
		case 1:
			Tower level2Tower = leveledTowers.get(1);
			if (this.towerType == "BottleTower") {
				BottleTower bottleTower = (BottleTower)level2Tower;
				bottleTower.copyTo(tower);
			}
			if (this.towerType == "MudTower") {
				MudTower mudTower = (MudTower)level2Tower;
				mudTower.copyTo(tower);
			}
			break;
		case 2:
			Tower level3Tower = leveledTowers.get(2);
			if (this.towerType == "BottleTower") {
				BottleTower bottleTower = (BottleTower)level3Tower;
				bottleTower.copyTo(tower);
			}
			if (this.towerType == "MudTower") {
				MudTower mudTower = (MudTower)level3Tower;
				mudTower.copyTo(tower);
			}
			break;	
		}
		
			
	}	
	
}
