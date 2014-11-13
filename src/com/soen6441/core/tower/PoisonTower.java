package com.soen6441.core.tower;

import org.dom4j.Element;

import com.soen6441.core.Timer;
import com.soen6441.core.effect.AffectableValue;

public class PoisonTower extends Tower {
	/*
	 * Properties
	 */
	protected AffectableValue poisonDamage;
	protected AffectableValue poisonCdTime;
	protected int poisonTimes;
	public static final String POISON_EFFECT = "poisonEffect";
	
	/*
	 * Basic methods
	 */
	
	/**
	 * Copy properties from one PoisonTower object to another.
	 * 
	 * @param tower
	 * @see Tower#copyTo(Tower)  
     */
	@Override
	public void copyTo(Tower tower){
		PoisonTower PoisonTower = (PoisonTower)tower;
		super.copyTo(PoisonTower);
	}
	
	/**
	 * Return detail information of a PoisonTower object.
	 * @return String
	 */
	@Override
	public String getDetailInformation() {
		String result = super.getDetailInformation();
		return result;
	}
	
	/**
	 * Inner class cantains name strings for archiving.
	 * @author Haiyang Sun
	 *
	 */
	public class NameForArchiving{
		public static final String Class = "PoisonTower";
		private static final String PoisonDamage = "poisonDamage";
		private static final String PoisonCdTime = "poisonCdTime";
		private static final String PoisonTimes = "poisonTimes";
	}
	
	/**
	 * Set value of properties of a poison tower object.
	 * @see Tower#decode(Element)
	 * @see IArchive
	 */
	@Override
	public void decode(Element element) {
		super.decode(element);
		this.setPoisonDamage(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.PoisonDamage).getText())));
		this.setPoisonCdTime(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.PoisonCdTime).getText())));
		this.setPoisonTimes(Integer.parseInt(element.element(NameForArchiving.PoisonTimes).getText()));
	}
	
	/**
	 * Encode value of properties of a poison tower object.
	 * @see Tower#encode()
	 * @see IArchive
	 */
	@Override
	public Element encode() {
		Element element = super.encode();
		element.setName(NameForArchiving.Class);
		element.addElement(NameForArchiving.PoisonDamage).addText(String.valueOf(this.poisonDamage.getOriginalValue ()));
		element.addElement(NameForArchiving.PoisonCdTime).addText(String.valueOf(this.poisonCdTime.getOriginalValue ()));
		element.addElement(NameForArchiving.PoisonTimes).addText(String.valueOf(this.getPoisonTimes()));
		return element;
	}
	/*
	 * Attack methods
	 */
	@Override
	public void attack() {
		super.attack();
//		this.getTimer().setRepeats(true);
//		this.getTimer().setDelay((int) this.getCdTime().getEffectedValue());
//		this.getTimer().setTimerListener(this);
//		this.getTimer().start();
	}
	
	@Override
	public void timerTick(Timer timer) {
		
//		super.timerTick(timer);
//		this.setTargetSelector(this.getTargetSelector().sortByDirectlyClosestToPoint(this.getLocation()).filterByAmount(1));
//		if (this.getTargetSelector().getItems().get(0) != null) {
//			
//			Critter critter = (Critter)targetSelector.getItems().get(0);
//			critter.damaged((int) this.getDamage().getEffectedValue());
//			critter.addEffect(new PoisonEffect(POISON_EFFECT,(int) this.getPoisonDamage().getEffectedValue(), this.getPoisonCdTime().getEffectedValue(), this.getPoisonTimes()));
//		}
	}
	
	/*
	 * Getters and Setters
	 */
	public AffectableValue getPoisonDamage() {
		return poisonDamage;
	}

	public void setPoisonDamage(AffectableValue poisonDamage) {
		this.poisonDamage = poisonDamage;
	}

	public AffectableValue getPoisonCdTime() {
		return poisonCdTime;
	}

	public void setPoisonCdTime(AffectableValue poisonCdTime) {
		this.poisonCdTime = poisonCdTime;
	}

	public int getPoisonTimes() {
		return poisonTimes;
	}

	public void setPoisonTimes(int poisonTimes) {
		this.poisonTimes = poisonTimes;
	}

}
