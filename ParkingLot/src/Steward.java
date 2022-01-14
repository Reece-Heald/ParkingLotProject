/*
 Final Project
 Ari Froelich, Reece Heald, Nicholas Thoman, Sandeep Amarnath
 Steward class
    extends user, creates coupon map, add/ get coupons, has/get lot, calculate cost per spot, give random coupon, add spot, delete spot
 */
package ParkingLot.src;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Steward extends User
{
    // Coupons is a map of Usernames and coupon percentages
    private Map<String, Integer> coupons = new TreeMap<>();

    public Steward(String userName, String password) {
        super(userName, password);
    }

    /**
     * Adds a coupon to Coupon Map
     * @param username Given Customer
     * @param percentOff Given Number off
     */
    public void addCoupon(String username, Integer percentOff){
        coupons.put(username,percentOff);
    }

    public Map<String, Integer> getCoupons() {
        return coupons;
    }

    /**
     * Checks to see if Steward has been assigned parkinglot
     * @return Boolean
     */
    public boolean hasLot(){
        for (ParkingLot oneLot: ParkingBase.getSetOfLots()){
            if(this.equals(oneLot.getStew()))
                return true;
        }
        return false;

    }

    /**
     * Gets the lot they are assigned to
     * @return ParkingLot
     */
    public ParkingLot getLot(){

        for (ParkingLot oneLot: ParkingBase.getSetOfLots()){
            if(this.equals(oneLot.getStew()))
                return oneLot;
        }
        return null;
    }

    /**
     * will randomly assign a coupon to a car in random spot
     */
    public void giveRandomCoupon(int percentOff, int count)
    {

        int size = getLot().getParkingSpaces().size();

        int pastSize = getCoupons().size();
        int newCount = pastSize + count;
        if (newCount >= getLot().getParkingSpaces().size()){
            newCount = getLot().getParkingSpaces().size();
        }

        while (getCoupons().size() < newCount){
            Iterator iterator = getLot().getParkingSpaces().iterator();
            int luckyNumber = (int) (Math.random()*(size) + 1);
            ParkingSpot luckySpot = null;
            for (int j = 1; j <= luckyNumber; j++){
                luckySpot = (ParkingSpot) iterator.next();
            }


            addCoupon(luckySpot.getCust().getUserName(),percentOff);
            System.out.println(percentOff +"%" + " Coupon Given to " + luckySpot.getCust().getUserName());
        }

    }

    /**
     * will calculate the cost of a specific parking spot
     * @param ps ParkingSpot
     * @return double total cost for spot
     */
    public double calculateSpotCost(ParkingSpot ps)
    {
        double totalCostPerHour = 0;
        double baseCost = getLot().getBaseCostPerHour();
        totalCostPerHour += baseCost;
        double totalCost = 0;
        int timeElapsed = (int) ((System.currentTimeMillis() - ps.getTimeOfPark()) / 3600000);
        boolean wentOvertime = timeElapsed / 3600000 > ParkingBase.getHoursAllowed();
        if(ps.isPremium()){
            totalCostPerHour += baseCost * ((ParkingBase.getPercentPremiumFee() / 100.00) + 1);
        }
        if(ps.isLongTerm()){
            totalCostPerHour += baseCost * ((ParkingBase.getPercentLongTermFee() / 100.00) + 1);
        }
        if(!wentOvertime){
            totalCost = totalCostPerHour * timeElapsed;
        }
        else{
            totalCost = (totalCostPerHour * ParkingBase.getHoursAllowed()) +
                  ((timeElapsed - ParkingBase.getHoursAllowed()) * ParkingBase.getPercentOvertimeFee());
        }

        return totalCost;
    }

    /**
     * will calculate the cost of a specific customer
     * @param c Customer
     * @return double total cost
     */
    public double calculateCost(Customer c)
    {
        double total = 0;
        for (ParkingSpot oneSpot : getLot().getParkingSpaces()){
            if(c.equals(oneSpot.getCust())){
                total += calculateSpotCost(oneSpot);
            }
        }
        return total;
    }

    // spotID Customer row timeOfPark(double) spacesTaken longTerm premium

    /**
     * will add a spot in parking lot - will return false if there is not enough room
     * @param cust Customer
     * @param spacesTaken Int number of Spaces taken
     * @param longTerm Blooean if its longerm
     * @param premium Boolean is it a premium spot
     * @return
     */
    public boolean addSpot(Customer cust, int spacesTaken, boolean longTerm, boolean premium) {
        if(premium && (getLot().getPremiumSpaceTaken() + spacesTaken > getLot().getPremiumSpace()))
            return false;
        boolean done = false;
        int nextrow = -1;
        int i = 0;
        while (!done){
            if(getLot().getRowSpaceTaken()[i] + spacesTaken <= getLot().getRowSize()){
                done = true;
                nextrow = i;
                getLot().getRowSpaceTaken()[i] += spacesTaken;
            }
            if(i >= getLot().getNumberOfRows() - 1)
                done = true;
            i++;
        }
        if (nextrow == -1)
            return false;

        if(premium)
            getLot().setPremiumSpaceTaken(getLot().getPremiumSpaceTaken() + spacesTaken);


        System.out.println(nextrow);
        String spotID = ((char) (65 + nextrow)) + "" + getLot().getRowSpaceTaken()[nextrow];

        getLot().getParkingSpaces().add(new ParkingSpot(spotID,cust,nextrow + 1,
              System.currentTimeMillis(),spacesTaken,longTerm,premium));


        return true;
    }

    /**
     * will delete a spot in parking lot
     * @param ps ParkingSpot
     */
    public void delSpot(ParkingSpot ps) {
        if(ps.isPremium())
            getLot().setPremiumSpaceTaken(getLot().getPremiumSpaceTaken() - ps.getSpaceTaken());

        getLot().getRowSpaceTaken()[ps.getRow()-1] -= ps.getSpaceTaken();

        getLot().getParkingSpaces().remove(ps);
    }

    public static void main(String[] args){
        UserBase.loadData();
        ParkingBase.loadData();
        String testStewardUsername = "testSteward";
        User testUser = null;
        for (User oneUser : UserBase.getSetOfUsers()){
            if(testStewardUsername.equals(oneUser.getUserName()))
                System.out.println("Found");
            testUser = oneUser;
        }



        Steward testSteward = (Steward) testUser;

        String testUsername = "BobCustomer";
        testUser = null;
        for (User oneUser : UserBase.getSetOfUsers()){

            if(testUsername.equals(oneUser.getUserName())) {
                System.out.println(oneUser);
                testUser = oneUser;
            }
        }

        Customer testCustomer = null;

        if(testUser instanceof  Customer)
            testCustomer = (Customer) testUser;

        System.out.println(testUser);
        System.out.println(testCustomer);


        //testSteward.addSpot(testCustomer,1,false,false);

        String testID = "B1";
        ParkingSpot testSpot = null;
        for (ParkingSpot oneSpot : testSteward.getLot().getParkingSpaces()){

            if(testID.equals(oneSpot.getSpotID())) {
                System.out.println(oneSpot);
                testSpot = oneSpot;
            }
        }
        System.out.printf("\nCost = %.2f", testSteward.calculateSpotCost(testSpot));
        //testSteward.delSpot(testSpot);

        UserBase.saveData();
        ParkingBase.saveData();




    }

}
