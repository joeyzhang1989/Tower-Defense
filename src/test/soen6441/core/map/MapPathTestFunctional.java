package test.soen6441.core.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.soen6441.core.map.MapPath;
import com.soen6441.core.map.MapPoint;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class MapPathTestFunctional {
	
	private MapPath mapPath;
	
	@Before
	public void setUp() {
		mapPath = new MapPath();
		mapPath.addLocation(new MapPoint(1, 1));
		mapPath.addLocation(new MapPoint(4, 1));
		mapPath.addLocation(new MapPoint(4, 4));
		mapPath.addLocation(new MapPoint(1, 4));
		mapPath.addLocation(new MapPoint(1, 3));
	}

	@Test
	public void testGoAlong() {
		
		double amount;
		
		MapPoint point;
		
		point = new MapPoint(2, 1);
		amount = mapPath.goAlong(point, 0.5);
		assertTrue(amount == 0);
		assertTrue(point.equals(new MapPoint(2.5, 1)));
		
		point = new MapPoint(2, 1);
		amount = mapPath.goAlong(point, 2);
		assertTrue(amount == 0);
		assertTrue(point.equals(new MapPoint(4, 1)));
		
		point = new MapPoint(2, 1);
		amount = mapPath.goAlong(point, 2.5);
		assertTrue(amount == 0);
		assertTrue(point.equals(new MapPoint(4, 1.5)));
		
		point = new MapPoint(2, 1);
		amount = mapPath.goAlong(point, 6);
		assertTrue(amount == 0);
		assertTrue(point.equals(new MapPoint(3, 4)));
		
		point = new MapPoint(2, 1);
		amount = mapPath.goAlong(point, 8.5);
		assertTrue(amount == 0);
		assertTrue(point.equals(new MapPoint(1, 3.5)));

		point = new MapPoint(2, 1);
		amount = mapPath.goAlong(point, 9);
		assertTrue(amount == 0);
		assertTrue(point.equals(new MapPoint(1, 3)));
		
		point = new MapPoint(2, 1);
		amount = mapPath.goAlong(point, 10);
		assertTrue(amount == 1);
		assertTrue(point.equals(new MapPoint(2, 1)));
		
		point = new MapPoint(1, 3);
		amount = mapPath.goAlong(point, 1);
		assertTrue(amount == 1);
		assertTrue(point.equals(new MapPoint(1, 3)));
		
	}
	
	@Test
	public void testPathDistanceToEndPoint (){

		double amount;
		
		MapPoint point;
		
		point = new MapPoint(2, 1);
		amount = mapPath.distanceToLastLocation (point);
		System.out.println(amount);
		assertTrue(amount == 9);
	}

}
