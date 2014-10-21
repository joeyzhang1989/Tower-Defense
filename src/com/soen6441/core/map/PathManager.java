package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.List;

public class PathManager {
	
	private GridMap map;
	
	public GridMap getMap() {
		return map;
	}

	public void setMap(GridMap map) {
		this.map = map;
	}

	public PathManager() {
		validators = new ArrayList<PathValidator>();
	}

	private List<PathValidator> validators;
	private PathValidator erroredValidator;
	
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
	
	public void generatePathsFromRoadItems (){
		
	}

	public void generateRoadItemsFromPaths (){
		
	}
}
