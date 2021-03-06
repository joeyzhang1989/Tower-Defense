package com.soen6441.core.tower;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.soen6441.core.IArchive;
import com.soen6441.core.Timer;
import com.soen6441.core.TimerListener;
import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.strategy.EnableStrategy;
import com.soen6441.core.strategy.Strategy;



/**
 * This class is the parent class of all types of tower classes.
 * The class defines some common properties and methods of child tower classes.
 * To create or upgrade a tower object should use TowerManager, which is defined by TowerManagerFactory.
 * 
 * @see TowerManager
 * @see TowerManagerFactory
 * 
 * @author Haiyang Sun
 *
 * @version $Revision: 1.0 $
 */

public class Tower extends MapItem implements IArchive, TimerListener {
	
	/*
	 * Mark - Constructors
	 */
	
	public Tower() {
		initAttack();
	}
	
	/*
	 * Mark - Context - Properties
	 */

	/**
	 * Defines a TowerManager
	 * 
	 * @see TowerManager
	 */
	protected TowerManager manager;
	
	/*
	 * Mark - Context - Getters & Setters
	 */
	
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
	
	/*
	 * Mark - Basic - Properties
	 */
	
	private int level;
	private int upgradePrice;
	private int sellPrice;
	
	/*
	 * Mark - Basic - Methods
	 */
	
	/**
     * @author Haiyang Sun
	 * @version $Revision: 1.0 $
	 */
	public class NameForArchiving{
		public static final String Class = "Tower";
		private static final String Identity = "identity";
		private static final String Level = "level";
		private static final String UpgradePrice = "upgradePrice";
		private static final String SellPrice = "sellPrice";
		private static final String Range = "range";
		private static final String Damage = "damage";
		private static final String CdTime = "cdTime";
		private static final String Description = "description";
		private static final String Manager = "manager";
	}
	
	/**
	 * This method is used to read information from XML file and set value of properties of a tower object.
	 * @see IArchive#decode(Element)
	 */
	@Override
	public void decode(Element element) {
		Element identityElement = element.element(NameForArchiving.Identity);
		if (identityElement != null) {
			this.setIdentity(Long.parseLong(identityElement.getText()));
		}
		
		this.setLevel(Integer.parseInt(element.element(NameForArchiving.Level).getText()));
		
		this.setUpgradePrice(Integer.parseInt(element.element(NameForArchiving.UpgradePrice).getText()));
		
		this.setSellPrice(Integer.parseInt(element.element(NameForArchiving.SellPrice).getText()));
		
		AffectableValue range = new AffectableValue(Double.parseDouble(element.element(NameForArchiving.Range).getText()));
		this.setRange(range);
		
		AffectableValue damage= new AffectableValue(Double.parseDouble(element.element(NameForArchiving.Damage).getText()));
		this.setDamage(damage);
		
		AffectableValue cdTime = new AffectableValue(Double.parseDouble(element.element(NameForArchiving.CdTime).getText()));
		this.setCdTime(cdTime);
		
		this.setDescription(element.element(NameForArchiving.Description).getText());
		
		Element managerElement = element.element(NameForArchiving.Manager);
		if (managerElement != null) {
			this.manager = TowerManagerFactory.defaultFactory().getManager(this.getClass().getSimpleName());
		}
		
		super.decode(element);
		
		
		
	}
	
	/**
	 * This method is used to convert value of properties of a particular class to an XML data element.
	 * @see IArchive#encode()
	 */
	@Override
	public Element encode() {
		
		Element element = super.encode();
		element.setName(NameForArchiving.Class);
		element.addElement(NameForArchiving.Identity).addText(Long.toString(this.getIdentity()));
		element.addElement(NameForArchiving.Level).addText(String.valueOf(this.getLevel()));
		element.addElement(NameForArchiving.UpgradePrice).addText(String.valueOf(this.getUpgradePrice()));
		element.addElement(NameForArchiving.SellPrice).addText(String.valueOf(this.getSellPrice()));
		element.addElement(NameForArchiving.Range).addText(String.valueOf(this.range.getOriginalValue ()));
		element.addElement(NameForArchiving.Damage).addText(String.valueOf(this.damage.getOriginalValue ()));
		element.addElement(NameForArchiving.CdTime).addText(String.valueOf(this.cdTime.getOriginalValue ()));
		element.addElement(NameForArchiving.Description).addText(this.getDescription());
		element.addElement(NameForArchiving.Manager).addText("__DYNAMIC__");
		return element;
	}
	
	/**
	 * This method is used to check whether the tower is in its highest level.
	 * @return boolean 
     * @see TowerManager#canUpgrade(Tower)
     */
	public boolean canUpgrade() {
		
		return this.manager.canUpgrade(this);
		
	}
	
	/**
	 * This method is used to upgrade the level of a specific tower.
	 * Call TowerManager to upgrade this tower.
	 * 
	 * @see TowerManager#upgrade(Tower)  
     */
	public void upgrade() {
		
		manager.upgrade(this);
	
	}
	
	/**
	 * This method is used to transfer properties of a particular tower object
	 * to another, in order to avoid pointer exception of affactableValue.
	 * @param tower
	 * @see AffectableValue 
     */
	public void copyTo(Tower tower){
		
		tower.setManager(this.getManager());
		tower.setLevel(this.getLevel());
		tower.setUpgradePrice(this.getUpgradePrice());
		tower.setSellPrice(this.getSellPrice());
		tower.range = new AffectableValue(this.range.getOriginalValue ());
		tower.damage = new AffectableValue(this.damage.getOriginalValue ());
		tower.cdTime = new AffectableValue(this.cdTime.getOriginalValue ());
		tower.setCellImageName(this.getCellImageName());
		tower.setName(this.getName());
		tower.setDescription(this.getDescription());
		
	}
	
	/*
	 * Mark - Basic - Observerable
	 */
	
	public static String OBSERVABLE_EVENT_PROPERTY_LEVEL_DID_CHANGE = "ObservableEvent_PropertyLevelDidChange";
	
	/*
	 * Mark - Basic - Getters & Setters
	 */
	

	/**
	 * Method getLevel
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
	
	/*
	 * Mark - Attack - Properties 
	 */
	
	
	private AffectableValue range;
	private AffectableValue damage;
	private AffectableValue cdTime;
	private Timer cdTimer;
	private List<MapItem> targets;
	private String strategyName;
	
	private Timer linkingTimer;
	private boolean showLinkingEffect;

	/*
	 * Mark - Attack - Observerable
	 */
	
	public static String OBSERVABLE_EVENT_PROPERTY_STRATEGY_NAME_DID_CHANGE = "ObservableEvent_PropertyStrategyNameDidChange";
	
	/*
	 * Mark - Attack - Methods
	 */
	/**
	 * Method: Initial Attack
	 */
	private void initAttack() {
		cdTimer = new Timer();
		cdTimer.setTimerListener(this);
		targets = new ArrayList<MapItem>();
		strategyName = Strategy.STRATEGY_NAME_ON_PATH_CLOSEST_TO_END_POINT;
		
		linkingTimer = new Timer();
		linkingTimer.setTimerListener(this);
		linkingTimer.setTimeIntervalSecond(0.2);
		
	}
	
	protected void searchForTargets(){
		// search targets
	}
	
	/**
	 * The attack method of Tower, each kind of tower can obtain a different attack method.
	 */
	protected void attack() {
		// attack
	}
	
	/**
	 * Method use to control the CDTime.
	 * @param timer Timer
	 * @see com.soen6441.core.TimerListener#timerTick(Timer)
	 */
	@Override
	public void timerTick(Timer timer) {
		if (timer == play.getRunningTimer()) {
			if (!cdTimer.isRunning()) {
				searchForTargets();
				if (targets.size() > 0) {
					attack();
					cdTimer.setTimeIntervalSecond(cdTime.getAffectedValue());
					cdTimer.start();
					
					showLinkingEffect = true;
					linkingTimer.start();
				}
			}
		} else if (timer == linkingTimer) {
			showLinkingEffect = false;
		}
	}
	
	/**
	 * This method is use to enhance properties of the tower.
	 * It is called by Motivate Effect, EnergyTower.
	 * @param enhanceRate double
	 * @see MotivateEffect 
     * @see EnergyTower
     */
	public void reinforce (double enhanceRate) {
		
		this.damage.setAffectedValue(this.damage.getOriginalValue () * enhanceRate);
		this.cdTime.setAffectedValue(this.cdTime.getOriginalValue () / enhanceRate);
		this.range.setAffectedValue(this.range.getOriginalValue () * enhanceRate);
	}
	
	/**
	 * This method is used to reset all effected values of this object to its original values.
	 * @see MapItem
     */
	@Override
	protected void resetAffectableValue() {
		
		this.damage.setAffectedValue(this.damage.getOriginalValue ());
		this.cdTime.setAffectedValue(this.cdTime.getOriginalValue ());
		this.range.setAffectedValue(this.range.getOriginalValue ());
	}
	
	/**
	 * Method isStrategyEnabled.
	 * @return boolean
	 */
	public boolean isStrategyEnabled() {
		return this instanceof EnableStrategy;
	}
	
	/*
	 * Mark - Attack - Getters & Setters
	 */

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
	 * Method getTargets.
	 * @return List<MapItem>
	 */
	public List<MapItem> getTargets() {
		return targets;
	}

	/**
	 * Method setTargets.
	 * @param targets List<MapItem>
	 */
	public void setTargets(List<MapItem> targets) {
		this.targets = targets;
	}

	/**
	 * Method getStrategyName.
	 * @return String
	 */
	public String getStrategyName() {
		return strategyName;
	}

	/**
	 * Method setStrategyName.
	 * @param strategyName String
	 */
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;

		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_STRATEGY_NAME_DID_CHANGE);
	}
	

	/**
	 * Method isShowLinkingEffect.
	 * @return boolean
	 */
	public boolean isShowLinkingEffect() {
		return showLinkingEffect;
	}

	/**
	 * Method setShowLinkingEffect.
	 * @param showLinkingEffect boolean
	 */
	public void setShowLinkingEffect(boolean showLinkingEffect) {
		this.showLinkingEffect = showLinkingEffect;
	}

	/*
	 * Mark - Display - Properties
	 */

	protected String description;
	
	/*
	 * Mark - Display - Methods
	 */
	
	/**
	 * Method getDetailInformation.
	 * @return String 
     */
	public String getDetailInformation() {
		
		DecimalFormat df = new DecimalFormat("0.0");
		
		String result;
		result = "Range  : " + df.format(this.range.getAffectedValue()) + System.getProperty("line.separator")
				+"Damage : " + df.format(this.damage.getAffectedValue()) + System.getProperty("line.separator")
				+"CoolDownTime : " + df.format(this.cdTime.getAffectedValue()) + "s" +  System.getProperty("line.separator");
		return result;
	}

	/*
	 * Mark - Display - Getters & Setters
	 */
	
	/**
	 * Method getDescription.
	 * @return String
     */
	public String getDescription() {
		return description;
	}

	/**
	 * Method setDescription.
	 * @param description String
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/*
	 * Mark - Log - Properties
	 */
	 
	private long identity;

	/**
	 * Method getIdentity.
	 * @return long
	 */
	public long getIdentity() {
		return identity;
	}

	/**
	 * Method setIdentity.
	 * @param identity long
	 */
	public void setIdentity(long identity) {
		this.identity = identity;
	}
	
}