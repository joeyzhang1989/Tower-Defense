package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.List;

import com.soen6441.core.map.validator.ConnectedPathValidator;
import com.soen6441.core.map.validator.EndPointValidator;
import com.soen6441.core.map.validator.PathLengthValidator;
import com.soen6441.core.map.validator.StartPointValidator;


public class PathManager {
	
	/*
	 * Mark - Context
	 */
	
	private GridMap map;
	
	public GridMap getMap() {
		return map;
	}

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
		validators.add(new PathLengthValidator());
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

	public String getErrorMessage() {
		return erroredValidator.getErrorMessage();
	}
	
	/* 
	 * Mark - Path to Items Convertion 
	 */
	
	public void generatePathsFromRoadItems (){
		
	}

	public void generateRoadItemsFromPaths (){
		
	}
}
