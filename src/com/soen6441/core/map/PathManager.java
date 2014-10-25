package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.List;

import com.soen6441.core.map.validator.ConnectedPathValidator;
import com.soen6441.core.map.validator.EndPointValidator;
import com.soen6441.core.map.validator.RoadQuantityValidator;
import com.soen6441.core.map.validator.StartPointValidator;

/**
 * 
 * 
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class PathManager {
	
	/*
	 * Mark - Context
	 */
	
	private GridMap map;
	
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

	/*
	 * Mark - Constuctors 
	 */
	
	public PathManager() {
		validators = new ArrayList<PathValidator>();
		validators.add(new StartPointValidator());
		validators.add(new EndPointValidator());
		validators.add(new RoadQuantityValidator());
		validators.add(new ConnectedPathValidator());
			
	}

	/*
	 * Mark - Validation - Properties
	 */
	
	private List<PathValidator> validators;
	private PathValidator erroredValidator;

	/*
	 * Mark - Validation - Methods
	 */
	
	/**
	 * Calling this method to validate if the items on the map could form a valid path(s)
	 * When the validation falled, call {@link #getErrorMessage()} to get the reason of the failure.
	 * 
	 * @return true, if the map is valid to save. false, if the map is not valid.
     */
	public boolean validate() {
		for (PathValidator validator:validators) {
			validator.setMap(map);
			boolean result = validator.validate();
			if (!result) {
				erroredValidator = validator;
				return false;
			}
		}
		return true;
	}

	/**
	 * Method getErrorMessage.
	 * @return String
	 */
	public String getErrorMessage() {
		return erroredValidator.getErrorMessage();
	}
	
	/* 
	 * Mark - Path to Items Convertion 
	 */
	
	public void generatePathsFromRoadItems (){
		
	}

	/**
	 * This method will geneate {@link Road} object and set them on to the {@link GridMap}
	 * based on the {@link GridMap#getPaths()}
	 */
	public void generateRoadItemsFromPaths (){
		
	}
}
