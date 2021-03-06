package com.soen6441.ui.common;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class GridPoint {

	/*
	 * Mark - Constructors
	 */
	
	public GridPoint() {
		super();
	}
	
	/**
	 * Constructor for GridPoint.
	 * @param row int
	 * @param column int
	 */
	public GridPoint(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	
	/*
	 * Mark - Basic - Properties
	 */
	
	private int row;
	private int column;


	/*
	 * Mark - Basic - Getters & Setters
	 */
	
	/**
	 * Method getRow.
	 * @return int 
     */
	public int getRow() {
		return row;
	}
	/**
	 * Method setRow.
	 * @param row int
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * Method getColumn.	
	 * @return int 
     */
	public int getColumn() {
		return column;
	}
	/**
	 * Method setColumn.
	 * @param column int
	 */
	public void setColumn(int column) {
		this.column = column;
	}
}
