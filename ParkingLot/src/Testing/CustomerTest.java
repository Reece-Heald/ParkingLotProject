package ParkingLot.src.Testing;

import ParkingLot.src.Customer;

import java.util.Set;

import static org.junit.Assert.*;

public class CustomerTest
{

   Customer c = new Customer("a", "b");

   @org.junit.Test
   public void isBlackListed()
   {
      assertEquals(false, c.isBlackListed());
   }

   @org.junit.Test
   public void setBlackListed()
   {
      c.setBlackListed(true);
      assertEquals(true, c.isBlackListed());
   }

   @org.junit.Test
   public void getLots()
   {
      c.addToLot("s");
      assertEquals("[s]",c.getLots().toString());
   }

   @org.junit.Test
   public void addToLot()
   {
      c.addToLot("a");
      assertEquals("[a]",c.getLots().toString());
   }
}