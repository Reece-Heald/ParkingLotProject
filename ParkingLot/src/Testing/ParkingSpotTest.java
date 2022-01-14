package ParkingLot.src.Testing;

import ParkingLot.src.*;
import ParkingLot.src.ParkingSpot;
import ParkingLot.src.Steward;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingSpotTest
{
    Customer c = new Customer("a", "b");
    ParkingSpot ps = new ParkingSpot("A2", c, 1, 45654, 1, false, false);
    @Test
    public void getCust()
    {
           assertEquals("a", ps.getCust().toString());
    }

    @Test
    public void setCust()
    {
        Customer h = new Customer("l", "m");
        ps.setCust(h);
        assertEquals("l", ps.getCust().toString());
    }

    @Test
    public void getSpotID()
    {
        assertEquals("A2", ps.getSpotID());
    }

    @Test
    public void setSpotID()
    {
        ps.setSpotID("D3");
        assertEquals("D3", ps.getSpotID());
    }

    @Test
    public void getRow()
    {
        assertEquals(1, ps.getRow());
    }

    @Test
    public void setRow()
    {
        ps.setRow(2);
        assertEquals(2, ps.getRow());
    }

    @Test
    public void getTimeOfPark()
    {
        assertEquals(45654, ps.getTimeOfPark());
    }

    @Test
    public void setTimeOfPark()
    {
        ps.setTimeOfPark(45665);
        assertEquals(45665, ps.getTimeOfPark());
    }

    @Test
    public void getSpaceTaken()
    {
        assertEquals(1, ps.getSpaceTaken());
    }

    @Test
    public void setSpaceTaken()
    {
        ps.setSpaceTaken(2);
        assertEquals(2,ps.getSpaceTaken());
    }

    @Test
    public void isLongTerm()
    {
        assertEquals(false, ps.isLongTerm());
    }

    @Test
    public void setLongTerm()
    {
        ps.setLongTerm(true);
        assertEquals(true, ps.isLongTerm());
    }

    @Test
    public void isPremium()
    {
        assertEquals(false, ps.isPremium());
    }

    @Test
    public void setPremium()
    {
        ps.setPremium(true);
        assertEquals(true, ps.isPremium());
    }

}
