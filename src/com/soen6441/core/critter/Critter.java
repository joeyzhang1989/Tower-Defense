package com.soen6441.core.critter;

import org.dom4j.Element;

import com.soen6441.core.Timer;
import com.soen6441.core.TimerListener;
import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.effect.Effect;
import com.soen6441.core.log.Log;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.play.Play;

/**
 * @author Zhe Zhao 
 * @version $Revision: 1.0 $
 */
public class Critter extends MapItem implements TimerListener{

	/*
	 * Mark - Basic - Properties
	 */
	 
	
	private int totalHp;
	private int hp;
	private AffectableValue speed;
	
	private int reward;
	private int stealAmount;

	/*
	 * Mark - Basic - Observerable
	 */
	
	public static String OBSERVABLE_EVENT_PROPERTY_HP_DID_CHANGE = "ObservableEvent_PropertyHpDidChange";
	public static String OBSERVABLE_EVENT_PROPERTY_LOCATION_DID_CHANGE = "ObservableEvent_PropertyLocationDidChange";
	
	/*
	 * Mark - Basic - Methods
	 */
	

	/**
	 * Method damaged.
	 * @param damage int
	 */
	public void damaged(int damage) {
		this.setHp(this.getHp() - damage);
	}
	 
	
	/*
	 * Mark - Basic - Getters & Setters
	 */
	 

	/**
	 * Method getTotalHp.
	 * @return int 
	 */
	public int getTotalHp() {
		return totalHp;
	}

	/**
	 * Method setTotalHp.
	 * @param totalHp int
	 */
	public void setTotalHp(int totalHp) {
		this.totalHp = totalHp;
	}

	/**
	 * Method getHp.
	 * @return int 
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Method setHp.
	 * @param hp int
	 */
	public void setHp(int hp) {
		if (hp <= 0) {
			hp = 0;

			Log log = new Log(Log.CATEGORY_WAVE).message("Critter Killed");
			play.getLogger().addLog(log);
			play.alterScore(1);
			play.earnCoins(this.getReward());
			for (Effect effect:this.getAllEffects()) {
				this.removeEffect(effect);
			}
			map.removeCritter(this);
		}
		this.hp = hp;

		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_HP_DID_CHANGE);
	}

	/**
	 * Method getSpeed.
	 * @return AffectableValue 
	 */
	public AffectableValue getSpeed() {
		return speed;
	}

	/**
	 * Method setSpeed.
	 * @param speed AffectableValue
	 */
	public void setSpeed(AffectableValue speed) {
		this.speed = speed;
	}

	/**
	 * Method getReward.
	 * @return int 
	 */
	public int getReward() {
		return reward;
	}

	/**
	 * Method setReward.
	 * @param reward int
	 */
	public void setReward(int reward) {
		this.reward = reward;
	}

	/**
	 * Method getStealAmount.
	 * @return int 
	 */
	public int getStealAmount() {
		return stealAmount;
	}

	/**
	 * Method setStealAmount.
	 * @param stealAmount int
	 */
	public void setStealAmount(int stealAmount) {
		this.stealAmount = stealAmount;
	}
	
	/*
	 * Mark - Map Item Override - Methods
	 */
	 
	@Override
	protected void resetAffectableValue() {
		
		this.speed.setAffectedValue(this.speed.getOriginalValue ());
	}
	
	/*
	 * Mark - Runner Timer - Methods
	 */
	 
	/**
	 * Method timerTick.
	 * @param timer Timer
	 * @see com.soen6441.core.TimerListener#timerTick(Timer) 
	 */
	@Override
	public void timerTick(Timer timer) {
		
		double out = map.moveOnPath(this, this.getSpeed().getAffectedValue() / Play.RUNNER_FPS);
		
		if (out > 0) {
			Log log = new Log(Log.CATEGORY_WAVE).message("Critter Reached Endpoint");
			play.getLogger().addLog(log);
			
			play.alterLife(-1);
			play.spendCoins(this.getStealAmount());
			map.removeCritter(this);
		} else {
			this.setChanged();
			this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_LOCATION_DID_CHANGE);
		}
		
	}
	
	/*
	 * Mark - Archive - Methods
	 */
	 
	/**
     * @author Zhe Zhao
	 * @version $Revision: 1.0 $
	 */
	public class NameForArchiving{
		public static final String Class = "Critter";
		private static final String Speed="speed";
		private static final String Reward="reward";
		private static final String Hp="hp";
		private static final String TotalHp="totalHp";
		private static final String StealAmount="stealAmount";
	}
	
	/**
	 * Method decode.
	 * @param element Element
	 * @see com.soen6441.core.IArchive#decode(Element)
	 */
	@Override
	public void decode(Element element) {

		AffectableValue speed=new AffectableValue(Double.parseDouble(element.element(NameForArchiving.Speed).getText()));
		this.setSpeed(speed);
		
		this.setReward(Integer.parseInt(element.element(NameForArchiving.Reward).getText()));
		
		this.setHp(Integer.parseInt(element.element(NameForArchiving.Hp).getText()));
		
		this.setTotalHp(Integer.parseInt(element.element(NameForArchiving.TotalHp).getText()));
		
		this.setStealAmount(Integer.parseInt(element.element(NameForArchiving.StealAmount).getText()));
				
		super.decode(element);
	}
	
	/**
	 * Method encode.
	 * @return Element 
	 * @see com.soen6441.core.IArchive#encode()
	 */
	@Override
	public Element encode() {
		return super.encode();
	}
	

	
}
