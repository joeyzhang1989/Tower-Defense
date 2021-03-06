package com.soen6441.core.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class Strategy {
	
	/*
	 * Mark - Basic - Properties
	 */
	
	private String name;
	private String imageName;
	private String onImageName;

	private String description;
	
	/*
	 * Mark - Basic - Getters & Setters
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
	 * Method getImageName.
	 * @return String
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * Method setImageName.
	 * @param imageName String
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * Method getOnImageName.
	 * @return String
	 */
	public String getOnImageName() {
		return onImageName;
	}

	/**
	 * Method setOnImageName.
	 * @param onImageName String
	 */
	public void setOnImageName(String onImageName) {
		this.onImageName = onImageName;
	}
	
	/**
	 * Method getDescription.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method setDescription.
	 * @param description String
	 */
	public void setDescription(String description) {
		this.description = description;

	}
	
	/*
	 * Mark - Supported Strategies - Properties
	 */
	 
	public static final String STRATEGY_NAME_DIRECTLY_CLOSEST_TO_POINT = "StrategyNameDirectlyClosestToPoint";
	public static final String STRATEGY_NAME_ON_PATH_CLOSEST_TO_END_POINT = "StrategyNameOnPathClosestToEndPoint";
	public static final String STRATEGY_NAME_WEAKEST = "StrategyNameWeakest";
	public static final String STRATEGY_NAME_STRONGEST = "StrategyNameStrongest";
	
	private static Map<String, Strategy> strategies;
	private static List<String> strategyNames;
	
	/*
	 * Mark - Supported Strategies - Methods
	 */
	 
	static {
		strategies = new HashMap<String, Strategy>();
		strategyNames = new ArrayList<String>();
		
		Strategy strategy;
		
		strategy = new Strategy();
		strategy.setName(STRATEGY_NAME_DIRECTLY_CLOSEST_TO_POINT);
		strategy.setImageName("strategy_1.png");
		strategy.setOnImageName("strategy_1_on.png");
		strategy.setDescription("Nearest to this tower ");
		strategies.put(strategy.getName(), strategy);
		strategyNames.add(strategy.getName());
		
		strategy = new Strategy();
		strategy.setName(STRATEGY_NAME_ON_PATH_CLOSEST_TO_END_POINT);
		strategy.setImageName("strategy_2.png");
		strategy.setOnImageName("strategy_2_on.png");
		strategy.setDescription("Nearest to the end point");
		strategies.put(strategy.getName(), strategy);
		strategyNames.add(strategy.getName());

		strategy = new Strategy();
		strategy.setName(STRATEGY_NAME_WEAKEST);
		strategy.setImageName("strategy_3.png");
		strategy.setOnImageName("strategy_3_on.png");
		strategy.setDescription("Weakest");
		strategies.put(strategy.getName(), strategy);
		strategyNames.add(strategy.getName());

		strategy = new Strategy();
		strategy.setName(STRATEGY_NAME_STRONGEST);
		strategy.setImageName("strategy_4.png");
		strategy.setOnImageName("strategy_4_on.png");
		strategy.setDescription("Strongest");
		strategies.put(strategy.getName(), strategy);
		strategyNames.add(strategy.getName());
	}
	

	/**
	 * Method getAllStrategies.
	 * @return List<Strategy>
	 */
	public static List<Strategy> getAllStrategies() {
		List<Strategy> sortedStrategies = new ArrayList<Strategy>();
		
		for (String strategyName : strategyNames) {
			sortedStrategies.add(strategies.get(strategyName));
		}
		
		return sortedStrategies;
	}
	
	/**
	 * Method getStrategy.
	 * @param strategyName String
	 * @return Strategy
	 */
	public static Strategy getStrategy(String strategyName) {
		return strategies.get(strategyName);
	}

	/**
	 * Method getStrategyNames.
	 * @return List<String>
	 */
	public static List<String> getStrategyNames() {
		return strategyNames;
	}
	
}
