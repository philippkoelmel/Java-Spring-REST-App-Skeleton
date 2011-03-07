package skeleton.skull;

import java.lang.Boolean;

/**
 * This aspect is for testing only. Just to see if AspectJ works.
 */
privileged aspect SkullController_aspectTest {

	/**
	 * Testing method. Should be there else the aspect test will fail.
	 * 
	 * @return always true
	 */
	public Boolean SkullController.aspectTest() {
		return true;
	}

}
