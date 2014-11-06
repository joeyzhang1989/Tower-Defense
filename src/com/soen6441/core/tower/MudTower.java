package com.soen6441.core.tower;

import org.dom4j.Element;

import com.soen6441.core.effect.AffectableValue;

/**
 * This class is a specific type of tower, MudTower.
 * A MudTower can attack a single target and reduce its running speed.
 * 
 * @see Tower
 * 
 * @author Haiyang Sun
 * @version $Revision: 1.0 $
 */

public class MudTower extends Tower {
	
	/*
	 * Special effects of MudTower.
	 */
	protected AffectableValue slowRate;
	protected AffectableValue slowDuration;
	
	/**
	 * Copy properties from a MudTower object to another.
	 * 
	 * @param tower 
     * @see Tower#copyTo(Tower)   
     */
	@Override
	public void copyTo (Tower tower) {
		super.copyTo(tower);
		
		MudTower mudTower = (MudTower)tower;
		mudTower.slowRate = new AffectableValue(this.slowRate.getOriginalValue ());
		mudTower.slowDuration = new AffectableValue(this.slowDuration.getOriginalValue ());
		
	}
	
	/**
	 * Return detail information of a MudTower object.
	 * @return String
	 */
	@Override
	public String getDetailInformation() {
		String result = super.getDetailInformation();
		result += "DcRate : " + this.slowRate.getOriginalValue () + "%" + System.getProperty("line.separator")
				 +"DcTime : " + this.slowDuration.getOriginalValue ()/1000 + "s" + System.getProperty("line.separator");
		return result;
	}

	/**
	 * Method getSlowRate.
     * @return AffectableValue  
     */
	public AffectableValue getSlowRate() {
		return slowRate;
	}
	
	/**
	 * Method setSlowRate.
	 * @param slowRate AffectableValue
	 */
	public void setSlowRate(AffectableValue slowRate) {
		this.slowRate = slowRate;
	}
	
	/**
	 * Method getSlowDuration.
     * @return AffectableValue 
     */
	public AffectableValue getSlowDuration() {
		return slowDuration;
	}
	
	/**
	 * Method setSlowDuration.
	 * @param slowDuration AffectableValue
	 */
	public void setSlowDuration(AffectableValue slowDuration) {
		this.slowDuration = slowDuration;
	}
	
	@Override
	public void decode(Element element) {
		
		this.setSlowRate(new AffectableValue(Double.parseDouble(element.element("slowRate").getText())));
		this.setSlowDuration(new AffectableValue(Double.parseDouble(element.element("slowDuration").getText())));
	}
}
