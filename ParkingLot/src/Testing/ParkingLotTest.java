package ParkingLot.src.Testing;

import ParkingLot.src.Customer;
import ParkingLot.src.ParkingLot;
import ParkingLot.src.ParkingSpot;
import ParkingLot.src.Steward;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ParkingLotTest
{
    ParkingLot lot = new ParkingLot("abc", 3, 5, 3.00,5);
    Steward s = new Steward("a", "b");
    Customer q = new Customer("b", "b");
    Customer o = new Customer("c", "b");


    @Test
    public void test()
    {
        assertEquals(0, lot.getTotalSpaceTaken());
        lot.setStew(s);
        assertEquals("Steward: a", lot.getStew().toString());
        Set<ParkingSpot> ps = new HashSet<>();
        ps.add(new ParkingSpot("A2", q, 1, 45654, 1, false, false));
        ps.add(new ParkingSpot("A3", q, 1, 45654, 1, false, false));
        lot.setParkingSpaces(ps);
        assertEquals("[A2 b takes 1, A3 b takes 1]",lot.getParkingSpaces().toString());
        assertEquals("abc",lot.getLotID());
        lot.setLotID("cba");
        assertEquals("cba", lot.getLotID());
        lot.setBaseCostPerHour(2.50);
        assertEquals(2.50, lot.getBaseCostPerHour(), 0.01);
        lot.setNumberOfRows(11);
        assertEquals(11, lot.getNumberOfRows());
        lot.setRowSize(5);
        assertEquals(5, lot.getRowSize());
        lot.setPremiumSpaceTaken(0);
        assertEquals(0, lot.getPremiumSpaceTaken());
        lot.setPremiumSpace(5);
        assertEquals(5, lot.getPremiumSpace());
        assertEquals(55, lot.getTotalSpace());

    }

}
