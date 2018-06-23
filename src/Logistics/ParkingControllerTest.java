package Logistics;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Actors.Customer;
import Actors.Employee;
import Actors.Manager;

class ParkingControllerTest {

	ParkingController park;

	public ParkingControllerTest() {}

	@BeforeEach
	void setUp() throws Exception {
		park = ParkingController.getInstance();
		park.addParkingLot(27, 44,12);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testCheckIfparkingLotExist() {
		// check a parking lot that exists
		assertTrue(park.checkIfparkingLotExist(27));
		// check a parking lot thats not exist
		assertFalse(park.checkIfparkingLotExist(2));
		// check if parking lot is full
		assertTrue(park.isParkingFull(27)==false);
	}

	@Test
	void testAddOrder() {
		
		requestOrder(123);
		Order order = park.getOrder(123);
		assertNotSame(order,null);
	}
	@Test
	void testNotExistOrder()
	{
		Order order = requestOrder(123);
		assertEquals(order,null);
	}
	
	
	@Test
	void testExitOrder()
	{
		Order order = requestOrder(123);
		park.exitParking(27, 123);
		assertEquals(order,null);
	}
	
	@Test
	void testCancelOrder()
	{
		Order order = requestOrder(123);
		park.cancelParking(27, 123);
		assertEquals(order,null);
		
	}
	
	public Order requestOrder(int numId)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dateArrive="24/06/2018 10:00";
		String dateEnd="24/06/2018 17:00";
		Order order = null;
		Date arrive;
		try {
			arrive = dateFormat.parse(dateArrive);
			Date end = dateFormat.parse(dateEnd);
			Location loc = park.orderParking(27,new Customer("Noa",7,arrive),"9839878",numId, 1,"noa@gmail.com" ,arrive, end);
			park.addOrderToParkingLot(27,new Order(new Customer("Noa",7,arrive),"9839878",numId,"noa@gmail.com",arrive,end,loc,27));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

}
