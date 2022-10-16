import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AppTest {

	@Test
	public void testApp() {
		App testApp = new App();
		MyData data1 = App.initializeData("2017-06-21 Thursday");
		MyData data2 = App.initializeData("29/01/2019 Thursday");
		MyData data3 = App.initializeData("Thursday, January 29, 2019");
		MyData data4 = App.initializeData("2019-01-28 Thursday");
		MyData data5 = App.initializeData("February 15, 2009 Monday");
		MyData data6 = App.initializeData("Saturday 29/11/2019");
		
		assertEquals(21, data1.getDay());
		assertEquals(6, data1.getMonth());
		assertEquals(2017, data1.getYear());
		assertEquals("Thursday", data1.getWeekday());
		
		assertEquals(29, data2.getDay());
		assertEquals(1, data2.getMonth());
		assertEquals(2019, data2.getYear());
		assertEquals("Thursday", data2.getWeekday());
		
		assertEquals(29, data3.getDay());
		assertEquals(1, data3.getMonth());
		assertEquals(2019, data3.getYear());
		assertEquals("Thursday", data3.getWeekday());
		
		assertEquals(28, data4.getDay());
		assertEquals(1, data4.getMonth());
		assertEquals(2019, data4.getYear());
		assertEquals("Thursday", data4.getWeekday());
		
		assertEquals(15, data5.getDay());
		assertEquals(2, data5.getMonth());
		assertEquals(2009, data5.getYear());
		assertEquals("Monday", data5.getWeekday());
		
		assertEquals(29, data6.getDay());
		assertEquals(11, data6.getMonth());
		assertEquals(2019, data6.getYear());
		assertEquals("Saturday", data6.getWeekday());
	}

}
