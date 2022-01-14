/*
 Final Project
 Ari Froelich, Reece Heald, Nicholas Thoman, Sandeep Amarnath
 Customer class
    extends user, customer super(username and password), checks if blacklisted and setter for admin to reference,
    get lots, add to lots(cars) based on lot id
 */
package ParkingLot.src;

import java.util.HashSet;
import java.util.Set;

public class Customer extends User
{

    private boolean blackListed;

    private Set<String> lots = new HashSet<>();

    /**
     * the constructor for the customer class
     */
    public Customer(String userName, String password)
    {
        super(userName, password);

        this.blackListed = false;
    }

    public boolean isBlackListed() {
        return blackListed;
    }

    public void setBlackListed(boolean blackListed) {
        this.blackListed = blackListed;
    }

    public Set<String> getLots() {
        return lots;
    }

    public void addToLot(String lotID)
    {
        lots.add(lotID);
    }

    public static void main(String[] args)
    {
        Customer c = new Customer("a", "b");
        c.addToLot("s");
        System.out.println(c.getLots().toString());
    }
}
