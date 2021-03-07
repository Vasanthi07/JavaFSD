package ibm.com.vasanthi.training;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyFirstJunit {

	@Test
	void testGetMessage() {
		Starter s = new Starter();
		String actual = s.getMessage(" ibm");
		assertEquals("hi ibm",actual);
	}

}
