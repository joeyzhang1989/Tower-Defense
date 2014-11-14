package com.soen6441.core.effect;

import com.soen6441.core.Timer;
import com.soen6441.core.TimerListener;
import com.soen6441.core.critter.Critter;

public class SlowEffect extends Effect implements TimerListener{
	
	double slowRate;
	double slowDuration;

	public SlowEffect(String type) {
		
		this.setType(type);
		
	}
	
	public SlowEffect() {
		
		
	}
	@Override
	public boolean strongerThan (Effect effect) {
		
		if (effect.type.equalsIgnoreCase(type)) {
			SlowEffect exist = (SlowEffect)effect;			
			if (this.getSlowRate() > exist.getSlowRate()) {
				return true;
			}
		}
		return false;
		
	}
	
	@Override
	public void start() {
		this.getTimer().setTimeIntervalSecond(this.getSlowDuration());
		this.getTimer().start();
	}
	
	@Override
	public void affect() {
		
		Critter critter = (Critter)this.getOn();
		double effectedSpeed = critter.getSpeed().getOriginalValue () * slowRate;
		critter.getSpeed().setEffectedValue(effectedSpeed);
		
	}
	@Override
	public void timerTick(Timer timer) {
		
		this.getOn().removeEffect(this);
		
	}
	
	@Override
	public void stop() {
		
		this.getTimer().stop();
		
	}

	public double getSlowRate() {
		return slowRate;
	}

	public void setSlowRate(double slowRate) {
		this.slowRate = slowRate;
	}

	public double getSlowDuration() {
		return slowDuration;
	}

	public void setSlowDuration(double slowDuration) {
		this.slowDuration = slowDuration;
	}
	
}
