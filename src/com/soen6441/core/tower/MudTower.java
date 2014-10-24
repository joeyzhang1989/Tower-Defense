package com.soen6441.core.tower;

import com.soen6441.core.effect.AffectableValue;

/**
 * This class is a specific type of tower, MudTower.
 * A MudTower can attack a single target and reduce its running speed.
 * 
 * @see Tower
 * 
 * @author Haiyang Sun
 */

public class MudTower extends Tower {
	
	/**
	 * ome particular properties of MudTower.
	 */
	public AffectableValue slowRate;
	public AffectableValue slowDuration;
	
	/**
	 * Copy properties from a MudTower object to another.
	 * @param mudTower MudTower
	 * @see Tower#copyTo(Tower) 
	 *
	 */
	public void copyTo (MudTower mudTower) {
		
		super.copyTo(mudTower);
		mudTower.slowRate = new AffectableValue(this.slowRate.getOriginalValue ());
		mudTower.slowDuration = new AffectableValue(this.slowDuration.getOriginalValue ());
	}
	
	/*
	 * General getters and setters
	 */
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
}
