package com.soen6441.ui.common;

import java.util.List;

import com.soen6441.ui.parallel.View;

/**
 * This class defines the GridView, the main grid, map of the game.
 * 
 * @author JeanRaymondDaher
 * @see GridView
 */


public class GridView extends View {

	// this class should draw a GridView 
	//it should also know when some cell is selected 
	//so that we can do some stuff on inspector view
	
	
	private GridViewCell selectedCell;
	
	private int unitWidth;
	private int unitHeight;
	
	protected List<GridViewCell> cells ;
	
	
	
	
	
	public List<GridViewCell> getCells() {
		return cells;
	}

	public void setCells(List<GridViewCell> cells) {
		this.cells = cells;
	}

	public int getUnitWidth() {
		return unitWidth;
	}

	public void setUnitWidth(int unitWidth) {
		this.unitWidth = unitWidth;
	}

	public int getUnitHeight() {
		return unitHeight;
	}

	public void setUnitHeight(int unitHeight) {
		this.unitHeight = unitHeight;
	}


	public void addCell(GridViewCell cell,int row,int column){
		
	}

	public void removeCell(GridViewCell cell){
		
	}
	public void replaceCell(GridViewCell cell,GridViewCell newCell){
		
	}
	public void selectCell(int row,int column){
		
	}
	
}