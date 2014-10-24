package com.soen6441.core.tower;

import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.play.Play;


/**
 * This class is the parent class of all types of tower classes.
 * The class defines some common properties and methods of child tower classes.
 * To create or upgrade a tower object should use TowerManager, which is defined by TowerManagerFactory.
 * 
 * @see TowerManager
 * @see TowerManagerFactory
 * 
 * 
 * @author Haiyang Sun
 *
 */

public class Tower extends MapItem {
	
	/**
	 * Properties of Tower
	 */
	protected int level;
	protected int initialPrice;
	protected int upgradePrice;
	protected int sellPrice;
	
	/**
	 * Defines a TowerManager
	 * 
	 * @see TowerManager
	 */
	protected TowerManager manager;
	
	/**
	 * Affectable properties of Tower
	 * 
	 * @see AffectableValue
	 */
	protected AffectableValue range;
	protected AffectableValue damage;
	protected AffectableValue cdTime;
	
	/**
	 * This method is used to upgrade the level of a specific tower.
	 * Check whether the currency of a user is enough to upgrade that tower. If it is enough, upgrade the tower and reduce user's currency.
	 * 
	
	 * @see TowerManager#upgrade(Tower) */

	public void upgrade() {
		
		if (Play.currentPlay().getCoins() >= this.upgradePrice) {
			
			manager.upgrade(this);
			int afterUpgradeCoins;
			afterUpgradeCoins = Play.currentPlay().getCoins() - this.upgradePrice;
			Play.currentPlay().setCoins(afterUpgradeCoins);
		}
	}
	
	/**
	 * The method is used to sell an existing tower.
	 * Add the sell price of the tower to a user's currency, then remove the tower from the map.
	 * 
	 */
	public void sell() {
		int afterSellCoins;
		afterSellCoins = Play.currentPlay().getCoins() + this.sellPrice;
		Play.currentPlay().setCoins(afterSellCoins);
		
	}
	
	/**
	 * The attack method of Tower
	 */
	
	public void attack() {
		
	}
	
	/**
	 * This method is used to transfer properties of a particular tower object to another, in order to avoid pointer exception of affactableValue.
	
	 * @param tower
	 * @see AffectableValue */
	
	public void copyTo(Tower tower){
		tower.level = this.level;
		tower.upgradePrice = this.upgradePrice;
		tower.sellPrice = this.sellPrice;
		tower.range = new AffectableValue(this.range.getOriginalValue ());
		tower.damage = new AffectableValue(this.damage.getOriginalValue ());
		tower.cdTime = new AffectableValue(this.cdTime.getOriginalValue ());
		tower.setCellImageName(this.getCellImageName());
	}

	/*
	 * Mark - Basic - Observerable
	 */
	
	public static String OBSERVABLE_EVENT_PROPERTY_LEVEL_DID_CHANGE = "ObservableEvent_PropertyLevelDidChange";
	
	/*
	 * Mark - Basic - Getters & Setters
	 */
	

	/**
	 * Method getLevel.
	 * @return int
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Method setLevel.
	 * @param level int
	 */
	public void setLevel(int level) {
		this.level = level;
		
		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_LEVEL_DID_CHANGE);
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
	 * Method getUpgradePrice.
	 * @return int
	 */
	public int getUpgradePrice() {
		return upgradePrice;
	}

	/**
	 * Method setUpgradePrice.
	 * @param upgradePrice int
	 */
	public void setUpgradePrice(int upgradePrice) {
		this.upgradePrice = upgradePrice;
	}

	/**
	 * Method getSellPrice.
	 * @return int
	 */
	public int getSellPrice() {
		return sellPrice;
	}

	/**
	 * Method setSellPrice.
	 * @param sellPrice int
	 */
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	/**
	 * Method getRange.
	 * @return AffectableValue
	 */
	public AffectableValue getRange() {
		return range;
	}

	/**
	 * Method setRange.
	 * @param range AffectableValue
	 */
	public void setRange(AffectableValue range) {
		this.range = range;
	}

	/**
	 * Method getDamage.
	 * @return AffectableValue
	 */
	public AffectableValue getDamage() {
		return damage;
	}

	/**
	 * Method setDamage.
	 * @param damage AffectableValue
	 */
	public void setDamage(AffectableValue damage) {
		this.damage = damage;
	}

	/**
	 * Method getCdTime.
	 * @return AffectableValue
	 */
	public AffectableValue getCdTime() {
		return cdTime;
	}

	/**
	 * Method setCdTime.
	 * @param cdTime AffectableValue
	 */
	public void setCdTime(AffectableValue cdTime) {
		this.cdTime = cdTime;
	}

	/**
	 * Method getManager.
	 * @return TowerManager
	 */
	public TowerManager getManager() {
		return manager;
	}

	/**
	 * Method setManager.
	 * @param manager TowerManager
	 */
	public void setManager(TowerManager manager) {
		this.manager = manager;
	}
	
}