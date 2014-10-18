package demo.soen6441.core.clone;

import com.soen6441.core.effect.AffectableValue;

/**
 *  @author Zhe Zhao
 */
public class CloneDemo {

	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		AffectableValue av1 = new AffectableValue();
		av1.setOriginalValue (10);
		av1.setEffectedValue(20);
		
		AffectableValue av2 = null;
		try {
			av2 = (AffectableValue) av1.clone();
			av2.setOriginalValue (30);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(av1.getOriginalValue ());
		System.out.println(av2.getOriginalValue ());
	}

}
