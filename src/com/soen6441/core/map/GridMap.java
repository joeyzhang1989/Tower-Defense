package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.DefaultElement;

import com.soen6441.core.ArchiveCenter;
import com.soen6441.core.IArchive;
import com.soen6441.core.critter.Critter;
import com.soen6441.core.log.Logger;
import com.soen6441.core.play.Play;
import com.soen6441.core.tower.Tower;
import com.soen6441.ui.scene.EditingScene;

/**
 * <h1>Introduction</h1>
 * <p>GridMap is the core logic of the game.</p>
 * <p>It represent the logical map in the game, and manage anything on the map.</p>
 * 
 * <br></br>
 * <h1>Tasks</h1>
 * 
 * <h3>Tasks - Item Management</h3>
 * <h5>Basic Concepts</h5>
 * <p>There is a 2D coordinate in the GridMap.</p>
 * <p>{@link MapPoint}, consist of x and y, represent the point on the coordinate.</p>
 * <p>{@link #width} and {@link #height} indicate the boundary of the coordinate.</p>
 * <p>Anything on the map, will be considered as an Item.</p>
 * <p>Any class representing a map item, should inherit class {@link MapItem}.</p>
 * <p>Knowned subclasses of {@link MapItem}: {@link Road}, {@link Tower}.</p>
 * <p>There will be only one on-grid {@link MapItem} at one location, where the x and y are integer.</p>
 * 
 * <h5>To set the boundary</h5>
 * <p>Use {@link #setWidth(int)} and {@link #setHeight(int)} to setup the map before using it</p>
 * <p>Once the width or height are changed, every item on the map will be removed to build a map with the new size</p>
 * 
 * <h5>To add an item</h5>
 * <p>Call {@link #setItem(MapItem, MapPoint)} to add an on-grid item at a specific location on the map.</p>
 * <p>Call {@link #setItem(MapItem)} if the location of the item has already set.</p>
 * <p><strong>Attention:</strong> you need to call {@link #getItem(MapPoint)} before calling this method, 
 * to check if there is an existed item on the location.</p>
 * 
 * <h5>To check an item</h5>
 * <p>Call {@link #getItem(MapPoint)} with a location to get an on-grid item.</p>
 * <p><code>null</code> means nothing on the location or the location is out of the boundary.</p>
 * 
 * <h5>To remove an item from the map</h5>
 * <p>Call {@link #removeItem(MapItem)} with a location to remove an on-grid item.</p>
 * 
 * <h5>Other convince methods</h5>
 * <p>Call {@link #getAllItems()} to get all on-grid items.</p>
 * 
 * <br></br>
 * <h3>Tasks - Selection</h3>
 * <h5>Basic Concepts</h5>
 * <p>The GridMap actually do not support selection behaviour.<p>
 * <p>However, it provide some method to record a location but not to manage the selection.</p>
 * 
 * <h5>To record a selected item</h5>
 * <p>To record a item as selected, you can only record the location.</p>
 * <p>Call {@link #setSelectedPoint(MapPoint)} to record where to be selected.</p>
 * 
 * <h5>To get a selected item</h5>
 * <p>Call {@link #getSelectedItem()} to get the selected item.</p>
 * <p>Or, call {@link #getSelectedPoint()} to get only the selected location.</p>
 * 
 * <br></br>
 * <h3>Tasks - Path</h4>
 * <h5>General Idea</h5>
 * <p>The GridMap actually has two group of information to describe the path on the map.</p>
 * <p>Group 1: Many {@link Road} object, where the {@link Road#getRoadType()} will indicate the entry point or exit point.</p>
 * <p>Group 2: {@link #paths}, {@link #startPoints}, {@link #endPoints}, where is a abstract way to describe the path on the map.</p>
 * 
 * <h6>Group 1</h6>
 * <p>When user edit the map via the {@link EditingScene}, they only manipulate the Group 1.</p>
 * <p>When validator check the map, they only check information in Group 1.</p>
 * 
 * <h6>Group 2</h6>
 * <p>When saving a map, only information in Group 2 will be saved.</p>
 * <p>When reading a map file, the map will be recovered with only the information in Group 2.</p>
 * 
 * <h6>Bridge between Group 1 and Group 2</h6>
 * <p>So, before saving a map, someone needs to transform the information in Group 1 to Group 2.</p>
 * <p>As well, after reading a map file, someone needs to transform the data in Group 2 to Group 1.</p>
 * 
 * <h5>To transform between Group 1 and Group 2</h5>
 * <p>There is a {@link PathManager} object in the gridMap to handle the transform.</p>
 * <p>Call {@link #getPathManager()} to get the path manager.</p>
 * <p>Then, call {@link PathManager#generatePathsFromRoadItems ()} to transform Group 1 to Group 2.</p>
 * <p>Or, call {@link PathManager#generateRoadItemsFromPaths ()} to transform Group 2 to Group 1.</p>
 * 
 * <h5>To validate the map</h5>
 * <p>{@link PathManager} object will also handle the path validation.</p>
 * <p>Call {@link #getPathManager()} to get the path manager.</p>
 * <p>Then, call {@link PathManager#validate()} to validate the map.</p>
 * <p><strong>Attention:</strong> Group 1 information will be needed to execute the validation.</p>
 * 
 * <h5>To get start points or end points</h5>
 * <p>The type of the {@link #startPoints #endPoints} are array, though, the app does not support multi-entry or exits now.</p>
 * <p>Call {@link #getStartPoints()} or {@link #getEndPoints()} then get the first item in the list.</p>
 * 
 * <h5>To get a path starting from a point</h5>
 * <p>Call {@link #pathFrom(MapPoint)}.</p>
 * 
 * 
 * 
 * 
 * @author Zhe Zhao
 * @author Mohammad Ali
 *
 * @version $Revision: 1.0 $
 */
public class GridMap implements IArchive {
	
	/*
	 * Mark - Context - Properties
	 */
	 
	private Play play;
	
	/*
	 * Mark - Context - Getters & Setters
	 */
	
	/**
	 * Method getPlay.
	 * @return Play
	 */
	public Play getPlay() {
		return play;
	}

	/**
	 * Method setPlay.
	 * @param play Play
	 */
	public void setPlay(Play play) {
		this.play = play;
	}
	
	/*
	 * Mark - Constructors
	 */

	public GridMap() {
		this.critters = new ArrayList<Critter>();
		
		this.pathManager = new PathManager();
		this.pathManager.setMap(this);
		
//		this.itemsListeners = new ArrayList<GridMapItemsListener>();
	}
	
	/*
	 * Mark - Grided Item Management - Properties
	 */
	
	private int width = 2;
	private int height = 2;
	private MapItem[][] items; 
	
	/*
	 * Mark - Grided Item Management - Methods
	 */
	/**
	 * @param item		The item
	 */
	public void setItem(MapItem item){ 
		items[item.getLocation().getGridedY()][item.getLocation().getGridedX()] = item;
		item.setMap(this);
		item.setPlay(play);
		
		if (item instanceof Tower) {
			Tower tower = (Tower)item;
			play.registeRunner(tower);
			
		} else if (item instanceof Road) {
			Road road = (Road)item;
			road.rediscoverConnections(true);
		}
		
		if (itemsListener != null) {
			itemsListener.gridMapDidAddItem(item);
		}
	}
	
	/**
	 * Set a grided map item on to the map.
	 * This method should be used only if the item will be place on grid.
	 * The connection between <code>item</code> and <code>location</code> will be setup in this method.
	 * 
	 * @param item		The item
	 * @param location	An grided location where put the item
	 */
	public void setItem(MapItem item, MapPoint location){
		item.setLocation(location);
		this.setItem(item);
		
	}
	
	/**
	 * Get a grided map item on the map
	 * 
	 * @param location An grided location you want to check
	* @return Return the item if there is, or return null if there isn't, 
	 * or return null if the location is out of the boundary.
     */
	public MapItem getItem(MapPoint location){
		int x = location.getGridedX();
		int y = location.getGridedY();
		
		// boundary check
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return null;
		} else {
			// get the one
			return items[y][x];
		}
	}
	
	/**
	 * Remove a grided map item from the map
	 * Before calling this method, you should call <code>getItem</code> first.
	 * 
	 * @param item An grided item which you want to remove.
	 *
     * @see #getItem(MapPoint)    
     */
	public void removeItem(MapItem item){
		MapPoint location = item.getLocation();
		items[location.getGridedY()][location.getGridedX()] = null;
		
		if (item instanceof Tower) {
			Tower tower = (Tower)item;
			play.resignRunner(tower);
		} else if (item instanceof Road) {
			Road road = (Road)item;
			road.rediscoverConnections(true);
		}
		
		if (itemsListener != null) {
			itemsListener.gridMapDidRemoveItem(item);
		}
	}
	
	/*
	 * Mark - Grided Item Management - Getters & Setters
	 */

	/**
	 * Method getWidth.
     * @return int  
     */
	public int getWidth() {
		return width;
	}

	/**
	 * Method setWidth.
	 * @param width int
	 */
	public void setWidth(int width) {
		this.width = width;
		regenerateGrid();
	}

	/**
	 * Method getHeight.
     * @return int   
     */
	public int getHeight() {
		return height;
	}

	/**
	 * Method setHeight.
	 * @param height int
	 */
	public void setHeight(int height) {
		this.height = height;
		regenerateGrid();
	}
	
	private void regenerateGrid(){
		items = new MapItem[this.height][this.width];
	}
	
	/*
	 * Mark - Non-grided Item Management - Properties
	 */
	
	private List<Critter> critters;
	
	/*
	 * Mark - Non-grided Item Management - Methods
	 */

	/**
	 * Method addCritter.
	 * @param critter Critter
	 */
	public void addCritter(Critter critter) {
		critters.add(critter);
		critter.setMap(this);
		critter.setPlay(play);
		play.registeRunner(critter);
		if (itemsListener != null) {
			itemsListener.gridMapDidAddCritter(critter);
		}
	}
	
	/**
	 * Method addCritter.
	 * @param critter Critter
	 * @param location MapPoint
	 */
	public void addCritter(Critter critter, MapPoint location){
		critter.setLocation(location);
		this.addCritter(critter);
	}
	
	/**
	 * Method removeCritter.
	 * @param critter Critter
	 */
	public void removeCritter(Critter critter) {
		if (critters.contains(critter)) {
			critters.remove(critter);
			play.resignRunner(critter);
			if (itemsListener != null) {
				itemsListener.gridMapDidRemoveCritter(critter);
			}
		}
	}
	
	/*
	 * Mark - Non-grided Item Management - Getters & Setters
	 */
	 
	/**
	 * Method getCritters.
	 * @return List<Critter>
	 */
	public List<Critter> getCritters() {
		return critters;
	}
	
	
	/*
	 * Mark - Item Event - Properties
	 */
	 
	private GridMapItemsListener itemsListener;
	
	/*
	 * Mark - Item Event - Methods
	 */
	
	/**
	 * Method getItemsListener.
	 * @return GridMapItemsListener
	 */
	public GridMapItemsListener getItemsListener() {
		return itemsListener;
	}

	/**
	 * Method setItemsListener.
	 * @param itemsListener GridMapItemsListener
	 */
	public void setItemsListener(GridMapItemsListener itemsListener) {
		this.itemsListener = itemsListener;
	}

	 
	/*
	 * Mark - Item Searching - Methods
	 */
	
	/**
	 * Method getAllItems.
	 * @return List<MapItem>  
     */
	public List<MapItem> getAllItems() {
		List<MapItem> itemList = new ArrayList<MapItem>();
		for (int x = 0; x < width; x ++){
			for (int y = 0; y < height; y++){
				MapItem item = this.getItem(new MapPoint(x, y));
				if (item != null){
					itemList.add(item);
				}
			}
		}
		itemList.addAll(critters);
		return itemList;
	}

	/**
	 * Method getItemSelector.
	 * @return MapItemSelector
	 */
	public MapItemSelector getItemSelector(){
		MapItemSelector mapItemSelector = new MapItemSelector();
		mapItemSelector.setMap(this);
		mapItemSelector.setItems(getAllItems());
		return mapItemSelector;
	}
	 
	/*
	 * Mark - Selection - Properties
	 */
	
	private MapPoint selectedPoint;

	/*
	 * Mark - Selection - Method
	 */
	
	/**
	 * Method getSelectedItem.
	 * @return MapItem  
     */
	public MapItem getSelectedItem() {
		return this.getItem(selectedPoint);
	}

	/*
	 * Mark - Selection - Getters & Setters
	 */
	
	/**
	 * Method getSelectedPoint.
	 * @return MapPoint 
     */
	public MapPoint getSelectedPoint() {
		return selectedPoint;
	}

	/**
	 * Method setSelectedPoint.
	 * @param selectedPoint MapPoint
	 */
	public void setSelectedPoint(MapPoint selectedPoint) {
		this.selectedPoint = selectedPoint;
	}
	
	
	/*
	 * Mark - Path - Properties
	 */
	

	private List<MapPoint> startPoints;
	private List<MapPoint> endPoints;
	private List<MapPath> paths;
	private PathManager pathManager;

	/*
	 * Mark - Path - Methods
	 */
	

	/**
	 * Get any path which start from a specific point
	 * If there is no branches in the game, it will give you only one path
	 * 
	 * @param point The location
 	 * @return It will return all the paths which start from the point. 
	 * If there is no path start from the point, it will return an list object but no object in it.   
     */
	public List<MapPath> pathFrom(MapPoint point){
		List<MapPath> resultPaths = new ArrayList<MapPath>();
		for(MapPath path : paths){
			if (path.getLocations().get(0).equals(point)){
				resultPaths.add(path);
			}
		}
		return resultPaths;
	}
	
	/**
	 * Method moveOnPath.
	 * @param item MapItem
	 * @param amount double
	 * @return double
	 */
	public double moveOnPath(MapItem item, double amount) {
		MapPath path = paths.get(0);
		return path.goAlong(item.getLocation(), amount);
	}
	
	/**
	 * Method pathDistanceToEndPoints.
	 * @param point MapPoint
	 * @return double
	 */
	public double pathDistanceToEndPoints(MapPoint point) {
		MapPath path = paths.get(0);
		return path.distanceToLastLocation(point);
	}
	

	/*
	 * Mark - Path - Getters & Setters
	 */

	/**
	 * Method getStartPoints.
     * @return List<MapPoint>   
     */
	public List<MapPoint> getStartPoints() {
		return startPoints;
	}

	/**
	 * Method setStartPoints.
	 * @param startPoints List<MapPoint>
	 */
	public void setStartPoints(List<MapPoint> startPoints) {
		this.startPoints = startPoints;
	}

	/**
	 * Method getEndPoints.
     * @return List<MapPoint>  
     */
	public List<MapPoint> getEndPoints() {
		return endPoints;
	}

	/**
	 * Method setEndPoints.
	 * @param endPoints List<MapPoint>
	 */
	public void setEndPoints(List<MapPoint> endPoints) {
		this.endPoints = endPoints;
	}

	/**
	 * Method getPaths.
     * @return List<MapPath>   
     */
	public List<MapPath> getPaths() {
		return paths;
	}

	/**
	 * Method setPaths.
	 * @param paths List<MapPath>
	 */
	public void setPaths(List<MapPath> paths) {
		this.paths = paths;
	}

	/**
	 * Method getPathManager.
	 * @return PathManager
     */
	public PathManager getPathManager() {
		return pathManager;
	}
	
	/*
	 * Mark - Log - Properties
	 */
	
	private Logger logger = new Logger();

	/*
	 * Mark - Log - Getters & Setters
	 */
	
	/**
	 * Method getLogger.
	 * @return Logger
	 */
	public Logger getLogger() {
		return logger;
	}

	/**
	 * Method setLogger.
	 * @param logger Logger
	 */
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	
	/*
	 * Mark - Archive - Methods
	 */
	
	/**
     * @author Zhe Zhao
	 * @version $Revision: 1.0 $
	 */
	public class NameForArchiving {
		public static final String Class = "GridMap";
		public static final String Width = "width";
		public static final String Height = "height";
		public static final String Items = "items";
		public static final String Logger = "logger";
	}
	
	/**
	 * Method decode.
	 * @param element Element
	 * @see com.soen6441.core.IArchive#decode(Element)
	 */
	@Override
	public void decode(Element element) {

		// reading width
		Node widthNode = element.selectSingleNode(NameForArchiving.Width);
		this.setWidth(Integer.parseInt(widthNode.getText()));

		// reading height
		Node heightNode = element.selectSingleNode(NameForArchiving.Height);
		this.setHeight(Integer.parseInt(heightNode.getText()));
		
		Element itemsElement = element.element(NameForArchiving.Items);
		@SuppressWarnings("unchecked")
		List<Element> itemElements = itemsElement.elements();
		for (Element itemElement : itemElements) {
			MapItem item = (MapItem) ArchiveCenter.decodeElement(itemElement);
			this.setItem(item);
		}

		Element loggerElement = element.element(NameForArchiving.Logger).element(Logger.NameForArchiving.Class);
		Logger logger = new Logger();
		logger.decode(loggerElement);
		this.logger = logger;
	}

	
	/**
	 * Method encode.
	 * @return Element
	 * @see com.soen6441.core.IArchive#encode()
	 */
	@Override
	public Element encode() {
		Element element = new DefaultElement(NameForArchiving.Class);
		
		//adding width Node in xml and assigning Value
		Element widthElement = element.addElement(NameForArchiving.Width);
		widthElement.addText(String.valueOf(this.width));

		//adding height Node in xml and assigning Value
		Element heightElement = element.addElement(NameForArchiving.Height);
		heightElement.addText(String.valueOf(this.height));
		
		List<MapItem> items = this.getAllItems();
		Element itemsElement = element.addElement(NameForArchiving.Items);
		for (MapItem item : items) {
			itemsElement.add(item.encode());
		}

		element.addElement(NameForArchiving.Logger).add(logger.encode());

		return element;
	}
}
