/*
Final Project
Ari Froelich, Reece Heald, Nicholas Thoman, Sandeep Amarnath
Admin class
    extends user, adds/delete lots, add/delete(hire/fire) steward, add/delete lot cap(max), blacklist/un-blacklist customers
 */
package ParkingLot.src;

import java.util.Iterator;
import java.util.Set;

public class Admin extends User
{

    public Admin(String userName, String password) {
        super(userName, password);
    }

    /**
     * Adds a lot to Parkingbase
     * @param lotID The lot ID
     * @param numberOfRows Number of rows in the lot
     * @param rowSize How many in each row
     * @param baseCostPerHour how much it cost to stay in the lot per hour
     * @param premiumSpace how many premium spaces will it have
     */
    public void addLot(String lotID, int numberOfRows, int rowSize, double baseCostPerHour, int premiumSpace)
    {
        ParkingBase.getSetOfLots().add(new ParkingLot(lotID,numberOfRows,rowSize,baseCostPerHour,premiumSpace));
    }

    /**
     * Deletes a lot from the Parking Base
     * @param lotID Lot ID
     */
    public void delLot(String lotID)
    {
        Set a = ParkingBase.getSetOfLots();
        Iterator i = a.iterator();
        for(int x = 0; x < a.size(); x++)
        {
            while(i.hasNext())
            {
                if(((ParkingLot)i.next()).getLotID().equals(lotID))
                {
                    a.remove(i.next());
                }
            }
        }
    }

    /**
     * Adds a Steward in UserBase
     * @param userName the ussername
     * @param password the password
     */
    public void hireSteward(String userName, String password)
    {
            UserBase.getSetOfUsers().add(new Steward(userName, password));
    }

    /**
     * Deletes a steward from the UserBase
     * @param userName Stewards username
     */
    public void fireSteward(String userName)
    {
        for(User user: UserBase.getSetOfUsers())
        {
            if(user instanceof Steward)
            {
                if(user.getUserName().equals(userName))
                {
                    UserBase.getSetOfUsers().remove(user);
                    break;
                }
            }
        }
    }

    /**
     * Adds steward to given lot
     * @param lot given ParkingLot
     * @param steward given Steward
     */
    public void addSteward(ParkingLot lot, Steward steward)
    {
        if(lot.getStew() == null)
        {
            lot.setStew(steward);
        }
        else
        {
            System.out.println("There is already a steward in the lot.");
        }
    }

    /**
     * Deletes Stewards from given lot
     * @param lot Given Lot
     */
    public void delStewards(ParkingLot lot)
    {
        lot.setStew(null);
    }

    /**
     * adds one spot in ever row to given lot
     * @param lot given Lot
     */
    public void addLotCap(ParkingLot lot)
    {
        int s = lot.getRowSize();
        lot.setRowSize(s + 1);
    }

    /**
     * Deletes a spot in every row of given lot
     * @param lot given ParkingLot
     */
    public void delLotCap(ParkingLot lot)
    {
        int s = lot.getRowSize();
        lot.setRowSize(s - 1);
    }

    /**
     * Sets given customers Blacklist true
     * @param c Customer
     */
    public void blackList(Customer c)
    {
        c.setBlackListed(true);
    }

    /**
     * Will unblacklist a user from a specific parking lot
     * @param c Customer
     */
    public void unBlackList(Customer c)
    {
        c.setBlackListed(false);
    }

    /**
     * Sends to String
     * @return String
     */
    public String toString(){
        return "Admin(" + this.getUserName() + " - " + this.getPassword() + ")";
    }
}
