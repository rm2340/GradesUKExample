import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MarksTest {

	// Interface-based input domain partitions (P1..P6):
	
	@Test
	void testGradesAverageInterfaceP1() throws InvalidMarkException {
		Grade g = Marks.marksAverage(1,99,99);
		assertEquals(g.getPercentage(), 66);
	}
	@Test
	void testGradesAverageInterfaceP3() throws InvalidMarkException {
		Grade g = Marks.marksAverage(99,1,99);
		assertEquals(g.getPercentage(), 66);
	}
	@Test
	void testGradesAverageInterfaceP5() throws InvalidMarkException {
		Grade g = Marks.marksAverage(99,99,1);
		assertEquals(g.getPercentage(), 66);
	}

	@Test
	void testGradesAverageInterfaceP2() throws InvalidMarkException {
		assertThrows(InvalidMarkException.class, ()->{ Marks.marksAverage(-1,99,99); });
	}
	@Test
	void testGradesAverageInterfaceP4() throws InvalidMarkException {
		assertThrows(InvalidMarkException.class, ()->{ Marks.marksAverage(1,101,99); });
	}
	@Test
	void testGradesAverageInterfaceP6() throws InvalidMarkException {
		assertThrows(InvalidMarkException.class, ()->{ Marks.marksAverage(1,99,-100); });
	}

	
	// same tests, but now using parametrisation
	
	@ParameterizedTest
	@MethodSource("provideGradesValid")
	void testGradesAverageInterfaceP135(int m1, int m2, int m3, int expected) throws InvalidMarkException {
		Grade g = Marks.marksAverage(m1, m2, m3);
		assertEquals(g.getPercentage(), expected);
	}
	
	@ParameterizedTest
	@MethodSource("provideGradesInvalid")
	void testGradesAverageInterfaceP246(int m1, int m2, int m3) {
		assertThrows(InvalidMarkException.class, ()->{ Marks.marksAverage(m1, m2, m3); });
	}
	
	private static Stream<Arguments> provideGradesValid() {
	    return Stream.of(
 	      Arguments.of(1, 99, 99, 66), // p1
 	      Arguments.of(99, 1, 99, 66), // p3
 	      Arguments.of(99, 99, 1, 66)  // p5
	    );
	}
	private static Stream<Arguments> provideGradesInvalid() {
		return Stream.of(
				Arguments.of(-1, 99, 99), // p2
				Arguments.of(1, 101, 99), // p4 
				Arguments.of(1, 99, -100) // p6
				);
	}
	
	
	// Functionality-based input domain partitions (P1..P6):
	
	@ParameterizedTest
	@MethodSource("provideGradesValidF")
	void testGradesAverageFunctionalityP12345(int m1, int m2, int m3, int expected) throws InvalidMarkException {
		Grade g = Marks.marksAverage(m1, m2, m3);
		assertEquals(g.getPercentage(), expected);
	}
	
	@ParameterizedTest
	@MethodSource("provideGradesInvalidF")
	void testGradesAverageFunctionalityP6(int m1, int m2, int m3) {
		assertThrows(InvalidMarkException.class, ()->{ Marks.marksAverage(m1, m2, m3); });
	}
	
	private static Stream<Arguments> provideGradesValidF() {
	    return Stream.of(
 	      Arguments.of(75, 80, 85, 80), // p1 (First)
 	      Arguments.of(68, 56, 64, 63), // p2 (2nd Upper)
 	      Arguments.of(55, 55, 55, 55), // p3 (2nd Lower)
 	      Arguments.of(45, 40, 45, 43), // p4 (3rd)
 	      Arguments.of(20, 20, 20, 20)  // p5 (Fail)
	    );
	}
	private static Stream<Arguments> provideGradesInvalidF() {
		return Stream.of(
				Arguments.of(-1, 50, 50) // p6 INVALID
				);
	}

	
}
