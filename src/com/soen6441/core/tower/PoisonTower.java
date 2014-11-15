package com.soen6441.core.tower;

import java.util.List;

import org.dom4j.Element;

import com.soen6441.core.critter.Critter;
import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.effect.PoisonEffect;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapItemSelector;

public class PoisonTower extends Tower {
	/*
	 * Properties
	 */
	protected AffectableValue poisonDamage;
	protected AffectableValue poisonCdTime;
	protected int poisonTimes;
	private String effectImageName;
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
		PoisonTower poisonTower = (PoisonTower)tower;
		super.copyTo(poisonTower);
		poisonTower.poisonDamage = new AffectableValue(this.poisonDamage.getOriginalValue ());
		poisonTower.poisonCdTime = new AffectableValue(this.poisonCdTime.getOriginalValue ());
		poisonTower.poisonTimes = this.poisonTimes;
		poisonTower.effectImageName = this.effectImageName;
	
	}
	
	/**
	 * Return detail information of a PoisonTower object.
	 * @return String
	 */
	@Override
	public String getDetailInformation() {
		String result = super.getDetailInformation();
		result += "dotDamage : " + this.poisonDamage.getOriginalValue () + "%" + System.getProperty("line.separator")
				 +"dotCD : " + this.poisonCdTime.getOriginalValue () + "s" + System.getProperty("line.separator")
				 +"dotTimes :" + this.poisonTimes + "s" + System.getProperty("line.separator");
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
		private static final String EfecctImageName = "effectImageName";
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
		this.setEffectImageName(element.element(NameForArchiving.EfecctImageName).getText());
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
		element.addElement(NameForArchiving.EfecctImageName).addText(this.getEffectImageName());
		return element;
	}
	/*
	 * Attack methods
	 */
	@Override
	protected void searchForTargets() {
		MapItemSelector selector = map.getItemSelector();
		List<MapItem> targets = selector
				.filterByType(Critter.class)
				.filterByCircularArea(this.getLocation(), this.getRange().getEffectedValue())
				.sortByDirectlyClosestToPoint(this.getLocation())
				.filterByAmount(1)
				.getItems();
		
		this.setTargets(targets);
	}
	
	@Override
	protected void attack() {
		for (MapItem item:this.getTargets()) {
			Critter critter = (Critter)item;
			critter.damaged((int) this.getDamage().getEffectedValue());
			
			PoisonEffect effect = new PoisonEffect(POISON_EFFECT);
			effect.setPoisonDamage((int) this.getPoisonDamage().getEffectedValue());
			effect.setPoisonTimes(this.getPoisonTimes());
			effect.setPoisonCdTime(this.getPoisonCdTime().getEffectedValue());
			effect.setCellImageName(this.effectImageName);
			
			critter.addEffect(effect);		
		}
	}
	
	public void reinforce(double enhanceRate) {
		super.reinforce(enhanceRate);
		this.poisonCdTime.setEffectedValue(this.poisonCdTime.getOriginalValue () * enhanceRate);
		this.poisonDamage.setEffectedValue(this.poisonDamage.getOriginalValue () * enhanceRate);
	}
	
	@Override
	protected void resetAffectableValue() {
		super.resetAffectableValue();
		this.poisonCdTime.setEffectedValue(this.poisonCdTime.getOriginalValue ());
		this.poisonDamage.setEffectedValue(this.poisonDamage.getOriginalValue ());
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

	public String getEffectImageName() {
		return effectImageName;
	}

	public void setEffectImageName(String effectImageName) {
		this.effectImageName = effectImageName;
	}

}
