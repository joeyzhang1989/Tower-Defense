package com.soen6441.core.map;


import java.util.Map;
import java.util.Observable;

import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.effect.Effect;
import com.soen6441.core.effect.IAffectable;


/**
 * This class will define the distance the object has to travel along the path in the Map.  
 * 
 * @author Zhe Zhao
 * @author Mohammad Ali

 */

public class MapItem extends Observable implements IAffectable{

	private String name;
	private GridMap map;
	private MapPoint location;
	
	private Map<String , Effect> effects;
	
	/*
	 * Getters & Setters - Basic
	 */
	
	/**
	 * Method getName.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method setName.
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method getMap.
	 * @return GridMap
	 */
	public GridMap getMap() {
		return map;
	}

	/**
	 * Method setMap.
	 * @param map GridMap
	 */
	public void setMap(GridMap map) {
		this.map = map;
	}

	/**
	 * Method getLocation.
	 * @return MapPoint
	 */
	public MapPoint getLocation() {
		return location;
	}

	/**
	 * Method setLocation.
	 * @param location MapPoint
	 */
	public void setLocation(MapPoint location) {
		this.location = location;
	}
	
	/*
	 * Methods - from IAffectable
	 */
	
	// Removes the effects from a MapItem
	private void updateAffectableValues(){
		
		// TODO implementation will be done Later
	}

     /*
      * The following four methods (addEffect(), getEffect() ,removeEffect(), getAffectableValue()) of interface IAffectable must be overridden
      */
	       
	/**
      * Method addEffect.
      * @param effect Effect
      * @see com.soen6441.core.effect.IAffectable#addEffect(Effect)
      */
     @Override
	public void addEffect(Effect effect) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Method getEffect.
	 * @param type String
	 * @return Effect
	 * @see com.soen6441.core.effect.IAffectable#getEffect(String)
	 */
	@Override
	public Effect getEffect(String type) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Method removeEffect.
	 * @param effect Effect
	 * @see com.soen6441.core.effect.IAffectable#removeEffect(Effect)
	 */
	@Override
	public void removeEffect(Effect effect) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Method getAffectableValue .
	 * @param name String
	 * @return AffectableValue
	 * @see com.soen6441.core.effect.IAffectable#getAffectableValue (String)
	 */
	@Override
	public AffectableValue getAffectableValue (String name) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
