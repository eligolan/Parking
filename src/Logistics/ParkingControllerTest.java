//package Logistics;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import Actors.Employee;
//import Actors.Manager;
//
//class ParkingControllerTest {
//
//	ParkingController park;
//	
//	public ParkingControllerTest() {}
//	
//	@BeforeEach
//	void setUp() throws Exception {
//		park = ParkingController.getInstance();
//		park.SetUp(1, "Tel Hai", new Manager("Shlomi", 305606899), "Tel Hai Parking", 10);
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}
//	
//	@Test
//	public void testCheckIfparkingLotExist() {
//		// check a parking lot that exists
//		assertTrue(park.checkIfparkingLotExist(1));
//	}
//
//	@Test
//	void test() {
//		// check a parking lot thats not exist
//		assertFalse(park.checkIfparkingLotExist(2));
//		// check if parking lot is full
//		assertTrue(park.isParkingFull(1)==false);
//	}
//
//}
