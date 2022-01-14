package ParkingLot.src.Testing;

import ParkingLot.src.*;
import ParkingLot.src.ParkingSpot;
import ParkingLot.src.Steward;

import org.junit.Test;

import static org.junit.Assert.*;

public class StewardTest
{
    Steward s = new Steward("a" ,"b");

    @Test
    public void hasLot()
    {
        assertEquals(false, s.hasLot());
    }

    @Test
    public void getLot()
    {
        assertEquals(null, s.getLot());
    }

    @Test
    public void addCoupon()
    {
        Steward s = new Steward("a" ,"b");
        s.addCoupon("a", 10);
        s.addCoupon("b", 15);
        s.addCoupon("c", 15);
        assertEquals("{a=10, b=15, c=15}", s.getCoupons().toString());
    }

    @Test
    public void getCoupon()
    {
        Steward s = new Steward("a" ,"b");
        s.addCoupon("a", 10);
        s.addCoupon("b", 15);
        s.addCoupon("c", 15);
        assertEquals("{a=10, b=15, c=15}", s.getCoupons().toString());
    }


    @Test
    public void calculateSpotCost()
    {
    }

    @Test
    public void calculateCost()
    {
    }

    @Test
    public void addSpot()
    {
    }

    @Test
    public void delSpot()
    {
    }

}