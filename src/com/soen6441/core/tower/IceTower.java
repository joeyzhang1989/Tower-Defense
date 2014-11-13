package com.soen6441.core.tower;

import org.dom4j.Element;

import com.soen6441.core.Timer;
import com.soen6441.core.critter.Critter;
import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.effect.SlowEffect;

/**
 * This class is a specific type of tower, IceTower.
 * A IceTower can attack a single target and reduce its running speed.
 * 
 * @see Tower
 * 
 * @author Haiyang Sun
 * @version $Revision: 1.0 $
 */

public class IceTower extends Tower {
	
	/*
	 * Special effects of IceTower.
	 */
	protected AffectableValue slowRate;
	protected AffectableValue slowDuration;
	private static final String ICE_EFFECT = "iceEffect";
	
	/**
	 * Copy properties from a IceTower object to another.
	 * 
	 * @param tower 
     * @see Tower#copyTo(Tower)   
     */
	@Override
	public void copyTo (Tower tower) {
		super.copyTo(tower);
		
		IceTower iceTower = (IceTower)tower;
		iceTower.slowRate = new AffectableValue(this.slowRate.getOriginalValue ());
		iceTower.slowDuration = new AffectableValue(this.slowDuration.getOriginalValue ());
		
	}
	
	/**
	 * Return detail information of a IceTower object.
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
	 * Inner class cantains name strings for archiving.
	 * @author Haiyang Sun
	 *
	 */
	public class NameForArchiving{
		public static final String Class = "IceTower";
		private static final String SlowRate = "slowRate";
		private static final String SlowDuration = "slowDuration";
	}
	/**
	 * Set value of properties of an ice tower object.
	 * @see Tower#decode(Element)
	 * @see IArchive
	 */
	@Override
	public void decode(Element element) {
		
		super.decode(element);
		this.setSlowRate(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.SlowRate).getText())));
		this.setSlowDuration(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.SlowDuration).getText())));
	}
	
	/**
	 * Encode value of properties of an ice tower object.
	 * @see Tower#encode(Element)
	 * @see IArchive
	 */
	@Override
	public Element encode() {
		Element element = super.encode();
		element.setName(NameForArchiving.Class);
		element.addElement(NameForArchiving.SlowRate).addText(String.valueOf(this.slowRate.getOriginalValue ()));
		element.addElement(NameForArchiving.SlowDuration).addText(String.valueOf(this.slowDuration.getOriginalValue ()));
		return element;
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
	public void attack() {
		super.attack();
		this.getTimer().setRepeats(true);
		this.getTimer().setDelay((int) this.getCdTime().getEffectedValue());
		this.getTimer().setTimerListener(this);
		this.getTimer().start();
	}
	
	@Override
	public void timerTick(Timer timer) {
		
		super.timerTick(timer);
		this.setTargetSelector(this.getTargetSelector().sortByDirectlyClosestToPoint(this.getLocation()).filterByAmount(1));
		if (this.getTargetSelector().getItems().get(0) != null) {
			
			Critter critter = (Critter)targetSelector.getItems().get(0);
			critter.damaged((int) this.getDamage().getEffectedValue());
			critter.addEffect(new SlowEffect(ICE_EFFECT, this.slowRate.getEffectedValue(), this.slowDuration.getEffectedValue()));
		}
	}
	
}
