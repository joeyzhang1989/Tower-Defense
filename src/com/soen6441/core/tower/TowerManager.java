package com.soen6441.core.tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.soen6441.core.effect.AffectableValue;

/**
 * This class responds to manage towers. Such as, create, upgrade, etc. 
 * This class uses dom4j to read XML files.
 * 
 * @see TowerManagerFactory
 * @see Tower
 * 
 * @author Haiyang Sun
 *
 * @version $Revision: 1.0 $
 */

public class TowerManager {
	
	/**
	 * Properties of TowerManager
	 */
	public String towerType;
	public String filePath;
	
	protected int initialPrice;
	
	
	/**
	 * This list is used to store all levels of tower object of a particular tower type.
	 */
	
	private List<Tower> leveledTowers = new ArrayList<Tower>();
	
	/**
	 * Constructor for TowerManager.
	 * @param towerType String
	 * @param filePath String
	 */
	public TowerManager(String towerType, String filePath) {
		
		this.towerType = towerType;
		this.filePath = filePath;
		
		this.analyse();
	}
	
	private Tower generateTower(String typeName) {
		Tower tower = null;
		try {
			tower = (Tower) (Class.forName("com.soen6441.core.tower." + typeName).newInstance());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return tower;
	}
	
	/**
	 * This method is used to read and analyze files that store tower information.
	 * All levels of tower object will be generated by this method and will be put in leveledTower list.
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
        this.initialPrice = (Integer.parseInt(towerManagerElement.element("initialPrice").getText()));
        Element leveledTowersElement = towerManagerElement.element("leveledTowers");
        
        for ( @SuppressWarnings("rawtypes")Iterator i = leveledTowersElement.elementIterator(); i.hasNext(); ) {
        	
            Element element = (Element)i.next();
                        
            Tower tower = this.generateTower(towerType);
            
            tower.setManager(this);
            
    		tower.setLevel(Integer.parseInt(element.element("level").getText()));
    		
    		tower.setUpgradePrice(Integer.parseInt(element.element("upgradePrice").getText()));
    		
    		tower.setSellPrice(Integer.parseInt(element.element("sellPrice").getText()));
    		
    		AffectableValue range = new AffectableValue(Double.parseDouble(element.element("range").getText()));
    		tower.setRange(range);
    		
    		AffectableValue damage= new AffectableValue(Double.parseDouble(element.element("damage").getText()));
    		tower.setDamage(damage);
    		
    		AffectableValue cdTime = new AffectableValue(Double.parseDouble(element.element("cdTime").getText()));
    		tower.setCdTime(cdTime);
    		
    		tower.setCellImageName(element.element("cellImageName").getText());
    		
    		tower.setName(element.element("towerName").getText());
    		
    		tower.setDescription(element.element("description").getText());
    		
    		
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
		
		Tower tower = leveledTowers.get(0);
		Tower newTower = this.generateTower(towerType);
		tower.copyTo(newTower);
		return newTower;
		
	}
	
	/**
	 * This method is used to upgrade an existing tower.
	 * 
	 * @param tower a Tower object
	 */
	
	public void upgrade(Tower tower) {
			
		if (tower.level < leveledTowers.size()) {
			
			
			leveledTowers.get(tower.level).copyTo(tower);
			
		}
				
	}
	
	/**
	 * Check whether the tower is in its highest level, if it is, return false, else return true;
	 * To use this method should call from tower
	 * 
	 * @see Tower#canUpgrade()
	 * 
	 * @param tower an existing tower
	 * @return boolean
	 */
	protected boolean canUpgrade(Tower tower) {
		
		if (tower.level >= leveledTowers.size()) {
			return false;
		}
		return true;
	}

	/**
	 * Method getTowerType.
	 * @return String
	 */
	public String getTowerType() {
		return towerType;
	}

	/**
	 * Method setTowerType.
	 * @param towerType String
	 */
	public void setTowerType(String towerType) {
		this.towerType = towerType;
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

	/**
	 * Method getInitialPrice.
	 * @return int
	 */
	public int getInitialPrice() {
		return initialPrice;
	}

	/**
	 * Method setInitialPrice.
	 * @param initialPrice int
	 */
	public void setInitialPrice(int initialPrice) {
		this.initialPrice = initialPrice;
	}

	/**
	 * Method getLeveledTowers.
	 * @return List<Tower>
	 */
	public List<Tower> getLeveledTowers() {
		return leveledTowers;
	}

	/**
	 * Method setLeveledTowers.
	 * @param leveledTowers List<Tower>
	 */
	public void setLeveledTowers(List<Tower> leveledTowers) {
		this.leveledTowers = leveledTowers;
	}	
	
}
