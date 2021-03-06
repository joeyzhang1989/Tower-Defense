package com.soen6441.core.map;

import java.util.List;

import org.dom4j.Element;

import com.soen6441.core.ArchiveCenter;


/**
 * This class will represent Road which is a MapItem. It extends MapItem and also defines additional methods 
 * specific to it. 
 * 
 * @author Zhe Zhao
 * @author Mohammad Ali
 * @version $Revision: 1.0 $
 */

public class Road extends MapItem {
	
	/*
	 * Mark - Constructors
	 */
	
	public Road() {
		this(RoadType.NORMAL);
	}
	
	/**
	 * Constructor for Road.
	 * @param roadType Type
	 */
	public Road(RoadType roadType) {
		super();
		this.roadType = roadType;
		
		this.setName("");
		this.setCellImageName("");
	}
	
	
	/*
	 * Mark - Basic - Properties
	 */
	
	/**
     * @author Mohammad Ali
	 * @version $Revision: 1.0 $
	 */
	public enum RoadType{
		NORMAL, START, END
	}
	
	private RoadType roadType;

	/*
	 * Mark - Basic - Observerable
	 */
	
	public static String OBSERVABLE_EVENT_PROPERTY_CONNECTIONS_DID_CHANGE = "ObservableEvent_PropertyTypeDidChange";
	
	/*
	 * Mark - Basic - Getters & Setters
	 */
	
	/**
	 * Method getType.
	 * @return Type
     */
	public RoadType getRoadType() {
		return roadType;
	}

	/**
	 * Method setType.
	 * @param roadType Type
	 */
	public void setRoadType(RoadType roadType) {
		this.roadType = roadType;
	}
	
	/*
	 * Mark - Display - Methods
	 */
	
	/**
	 * Method getName.
	 * @return String
     */
	@Override
	public String getName() {
		switch (roadType) {
		case NORMAL:
			return "Road";
		case START:
			return "Entry Point";
		case END:
			return "Exit Point";
		default:
			return null;
		}
	}
 
	/**
	 * Method getCellImageName.
	 * @return String
     */
	@Override
	public String getCellImageName() {

		switch (roadType) {
		case NORMAL:
			return "road_cell_normal.png";
		case START:
			return "road_cell_start.png";
		case END:
			return "road_cell_end.png";
		default:
			return null;
		}
	}
	
	private boolean[] connections;
	

	/**
	 * Method rediscoverConnections.
	 * @param center boolean
	 */
	public void rediscoverConnections(boolean center){
		boolean[] connections = new boolean[4];
		
		List<MapPoint> directions = MapPoint.crossDirections();
		for (int i = 0; i < directions.size(); i++) {
			MapPoint direction = directions.get(i);
			MapPoint checkPoint = this.getLocation().add(direction);
			MapItem item = map.getItem(checkPoint);
			if (item != null && item instanceof Road) {
				connections[i] = true;
				if (center) {
					((Road)item).rediscoverConnections(false);
				}
			} else {
				connections[i] = false;
			}
		}
		
		this.setConnections(connections);
		
	}

	/**
	 * Method getConnections.
	 * @return boolean[]
	 */
	public boolean[] getConnections() {
		return connections;
	}

	/**
	 * Method setConnections.
	 * @param connections boolean[]
	 */
	public void setConnections(boolean[] connections) {
		this.connections = connections;
		
		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_CONNECTIONS_DID_CHANGE);
	}
	

	/*
	 * Mark - Archive - Methods
	 */
	
	/**
	 * Method roadTypeToString.
	 * @return String
	 */
	private String roadTypeToString() {

		switch (roadType) {
		case NORMAL:
			return "Normal";
		case START:
			return "Start";
		case END:
			return "End";
		default:
			return null;
		}
	}
	
	/**
	 * Method stringToRoadType.
	 * @param value String
	 * @return RoadType
	 */
	private RoadType stringToRoadType(String value) {
		if (value.equals("Normal")) {
			return RoadType.NORMAL;
		}
		if (value.equals("Start")) {
			return RoadType.START;
		}
		if (value.equals("End")) {
			return RoadType.END;
		}
		return null;
	}
	
	public static void registeToArchiveCenter() {
		ArchiveCenter.registeClass(Road.class, NameForArchiving.Class);
	}
	
	/**
	 * @ author Zhe Zhao
	 */
	public class NameForArchiving{
		public static final String Class = "Road";
		private static final String RoadType = "roadType";
	}
	
	/**
	 * Method decode.
	 * @param element Element
	 * @see com.soen6441.core.IArchive#decode(Element)
	 */
	@Override
	public void decode(Element element) {
		String value = element.elementText(NameForArchiving.RoadType);
		this.roadType = stringToRoadType(value);
		
		super.decode(element);
	}

	/**
	 * Method encode.
	 * @return Element
	 * @see com.soen6441.core.IArchive#encode()
	 */
	@Override
	public Element encode() {
		Element element = super.encode();
		element.setName(NameForArchiving.Class);
		element.addElement(NameForArchiving.RoadType).addText(roadTypeToString());
		
		return element;
	}
	
}
