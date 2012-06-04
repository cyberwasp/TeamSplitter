package ru.cyberwasp.teamsplitter.test;

import junit.framework.TestCase;
import ru.cyberwasp.teamsplitter.Utils;

public class UtilsTestCase extends TestCase {

	public void testCalcMediane() {
		double data[] = {10, 5, 5, 15, 90};
		assertEquals(25.0, Utils.calcMediane(data));
	}

	public void testCalcSquareOfStandartDeviation() {
		double data[] = {10, 5, 5, 15, 90};
		assertEquals(1070.0, Utils.calcSquareOfStandartDeviation(data));
	}
	
}
