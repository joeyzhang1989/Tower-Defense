package com.soen6441.core.tower;

import com.soen6441.core.effect.AffectableValue;

/**
 * This class is a specific type of tower, MudTower.
 * A MudTower can attack a single target and reduce its running speed.
 * 
 * @author Haiyang Sun
 */

public class MudTower extends Tower {
	public AffectableValue slowRate;
	public AffectableValue slowDuration;
	
	public void copyTo (MudTower mudTower) {
		
		super.copyTo(mudTower);
		mudTower.slowRate = new AffectableValue(this.slowRate.getOriginalValue ());
		mudTower.slowDuration = new AffectableValue(this.slowDuration.getOriginalValue ());
	}
	
	
	public AffectableValue getSlowRate() {
		return slowRate;
	}
	public void setSlowRate(AffectableValue slowRate) {
		this.slowRate = slowRate;
	}
	public AffectableValue getSlowDuration() {
		return slowDuration;
	}
	public void setSlowDuration(AffectableValue slowDuration) {
		this.slowDuration = slowDuration;
	}
}
