/**
 * 
 */
package test.soen6441.core.effect;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.soen6441.core.critter.Critter;
import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.effect.SlowEffect;

/**
 * This class tests two main method of SlowEffect class.
 * @author Haiyang Sun
 *
 * @version $Revision: 1.0 $
 */
public class SlowEffectTest {
	
	private static final double DELTA = 0;
	private static SlowEffect slowEffect1 = new SlowEffect();
	private static SlowEffect slowEffect2 = new SlowEffect();
	private static Critter critter = new Critter();
	
	/**
	 * @throws Exception
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		slowEffect1.setSlowRate(0.5);
		slowEffect2.setSlowRate(0.8);
		slowEffect1.setType("FreezingEffect");
		slowEffect2.setType("FreezingEffect");
		
		AffectableValue speed = new AffectableValue(100.0);
		critter.setSpeed(speed);
		
	}

	
	/**
	 * Test method for {@link com.soen6441.core.effect.SlowEffect#strongerThan(com.soen6441.core.effect.Effect)}.
	 */
	@Test
	public void testStrongerThan() {
		
		boolean result = slowEffect1.strongerThan(slowEffect2);
		assertTrue(result);
		
	}

	/**
	 * Test method for {@link com.soen6441.core.effect.SlowEffect#affect()}.
	 */
	@Test
	public void testAffect() {
		critter.addEffect(slowEffect1);
		double expected = critter.getSpeed().getOriginalValue () * slowEffect1.getSlowRate();
		double result = critter.getSpeed().getAffectedValue();
		assertEquals(expected, result,DELTA);
	}

}
