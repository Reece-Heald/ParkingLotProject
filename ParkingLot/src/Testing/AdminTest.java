package ParkingLot.src.Testing;

import ParkingLot.src.Admin;
import ParkingLot.src.Customer;
import ParkingLot.src.ParkingLot;
import ParkingLot.src.Steward;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class AdminTest
{
    Admin a = new Admin("user", "pass");
    ParkingLot lot = new ParkingLot("abc", 3, 5, 3.00,5);
    Steward s = new Steward("a", "b");
    Customer q = new Customer("b", "b");

    @Test
    public void test()
    {
            a.addSteward(lot, s);
            assertEquals("Steward: a", lot.getStew().toString());
            a.delStewards(lot);
            assertEquals(null, lot.getStew());
            a.addLotCap(lot);
            assertEquals(6,lot.getRowSize());
            a.delLotCap(lot);
            assertEquals(5, lot.getRowSize());
            a.blackList(q);
            assertEquals(true, q.isBlackListed());
            a.unBlackList(q);
            assertEquals(false, q.isBlackListed());

    }
}
