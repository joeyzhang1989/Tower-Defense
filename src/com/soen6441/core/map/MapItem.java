package com.soen6441.core.map;


import java.util.Map;

import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.effect.Effect;
import com.soen6441.core.effect.IAffectable;


/**
 * This class will define the amount of distance the object has to travel along the path in the Map.  
 * 
 * @author Mohammad Ali
 */

public class MapItem implements IAffectable{

	private String name;
	private GridMap map;
	private MapPoint location;
	
	private Map<String , Effect> effects;
	
	
	// Removes the effects from a MapItem
	private void updateAffectableValues(){
		
		// TODO implementation will be done Later
	}

     /*
      * The following four methods (addEffect(), getEffect() ,removeEffect(), getAffectableValue()) of interface IAffectable must be overridden
      */
	       
	@Override
	public void addEffect(Effect effect) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Effect getEffect(String type) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void removeEffect(Effect effect) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public AffectableValue getAffectableValue (String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
