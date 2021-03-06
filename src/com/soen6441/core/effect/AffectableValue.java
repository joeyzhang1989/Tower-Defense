package com.soen6441.core.effect;

/**
 * Any value which will be affected should use this class as a type.
 * The originalValue is the original value.
 * The effectedValue will be calculated by the class who has a property of this type
 * 
 * @author Zhe Zhao
 *
 * 
 * @version $Revision: 1.0 $
 */
public class AffectableValue implements Cloneable {
	
	/*
	 * Properties
	 */
	
	/**
	 * The originalValue is the original value.
	 */
	private double originalValue ;
	
	/**
	 * The effectedValue will be calculated by the class who has a property of this type
	 */
	private double affectedValue;

	/* 	
	 * Constructors 
	 */
	
	/**
	 * An general constructor
	 */
	public AffectableValue() {
		super();
	}

	/**
	 * An construcutor 
	 * @param originalValue  double
	 */
	public AffectableValue(double originalValue ) {
		super();
		this.originalValue  = originalValue ;
		this.affectedValue = originalValue ;
	}
	
	/*
	 * Getters and Setters
	 */
	
	/**
	 * Method getOriginalValue .
	 * @return double
	 * @author Zhe Zhao
     */
	public double getOriginalValue () {
		return originalValue ;
	}
	
	/**
	 * Method setOriginalValue .
	 * @param originalValue  double
	 */
	public void setOriginalValue (double originalValue ) {
		this.originalValue  = originalValue ;
		this.affectedValue = originalValue ;
	}
	
	/**
	 * Method getEffectedValue.
	 * @return double  
     */
	public double getAffectedValue() {
		return affectedValue;
	}
	
	/**
	 * Method setEffectedValue.
	 * @param affectedValue double
	 */
	public void setAffectedValue(double affectedValue) {
		this.affectedValue = affectedValue;
	}
}
