package com.soen6441.ui.map.cell;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

import javax.swing.ImageIcon;

import com.soen6441.core.tower.Tower;
import com.soen6441.ui.map.MapItemCell;
import com.soen6441.ui.parallel.ImageAssets;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class TowerCell extends MapItemCell {
	
	/*
	 * Mark - Displaying - Methods
	 */
	
	/**
	 * Method paint.
	 * @param g Graphics
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(ImageAssets.imageNamed(getItemTower().getCellImageName()), 0, 0, null);
		super.paintSelection(g);
	}
	
	/**
	 * Method update.
	 * @param o Observable
	 * @param arg Object
	 * @see java.util.Observer#update(Observable, Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		super.update(o, arg);
		if (arg == Tower.OBSERVABLE_EVENT_PROPERTY_LEVEL_DID_CHANGE){
			this.repaint();
		}
	}

	/*
	 * Mark - Convenience - Methods
	 */
	
	/**
	 * Method getItemTower.
	 * @return Tower
	 */
	public Tower getItemTower(){
		return (Tower)this.getItem();
	}
}
