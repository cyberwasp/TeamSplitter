package ru.cyberwasp.teamsplitter.test;

import junit.framework.TestCase;
import ru.cyberwasp.teamsplitter.algorithms.Utils;

public class UtilsTestCase extends TestCase {

	public void testCalcAvg() {
		double data[] = {10, 5, 5, 15, 90};
		assertEquals(25.0, Utils.calcAvg(data));
	}

	public void testCalcSquareOfStandartDeviation() {
		double data[] = {10, 5, 5, 15, 90};
		assertEquals(1070.0, Utils.calcSquareOfStandartDeviation(data));
	}
	
}
