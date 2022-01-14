/*
 Final Project
 Ari Froelich, Reece Heald, Nicholas Thoman, Sandeep Amarnath
 Parking Base class
    getter for lots in a set, get/set for premium and long term fees, get and set for hours allowed(max time)
    loads and saves data
 */
package ParkingLot.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Used as a set of parking lots
 */
abstract class ParkingBase
{
    //TODO STORE INSTANCE VARIABLES AND LET ADMIN CHANGE THEM
    private static Set<ParkingLot> setOfLots = new TreeSet<>();
    private static final String PARKING_DATA = "ParkingData.txt";
    private static int percentPremiumFee = 40;
    private static int percentLongTermFee = 30;
    private static int hoursAllowed = 30;
    private static int percentOvertimeFee = 200;


    public static Set<ParkingLot> getSetOfLots() {
        return setOfLots;
    }

    public static int getPercentPremiumFee() {
        return percentPremiumFee;
    }

    public static void setPercentPremiumFee(int percentPremiumFee) {
        ParkingBase.percentPremiumFee = percentPremiumFee;
    }

    public static int getPercentLongTermFee() {
        return percentLongTermFee;
    }

    /**
     * sets percent of long term Fee
     * @param percentLongTermFee Int
     */
    public static void setPercentLongTermFee(int percentLongTermFee) {
        ParkingBase.percentLongTermFee = percentLongTermFee;
    }

    public static int getHoursAllowed() {
        return hoursAllowed;
    }

    public static void setHoursAllowed(int hoursAllowed) {
        ParkingBase.hoursAllowed = hoursAllowed;
    }

    public static int getPercentOvertimeFee() {
        return percentOvertimeFee;
    }

    public static void setPercentOvertimeFee(int overtimeFee) {
        ParkingBase.percentOvertimeFee = overtimeFee;
    }


    /**
     * will scan txt file and load data into system
     */
    static void loadData()
    {
        try {
            File userData = new File(PARKING_DATA);
            Scanner scan = new Scanner(userData);

            String nextScan = "";
            String nextScan2 = "";

            String nextLotID;
            String nextStewardUsername;
            double nextBaseCostPerHour;
            int nextNumberOfRows;
            int nextRowSize;
            int[] nextRowSpaceTaken;
            int nextPremiumSpace;
            int nextPremiumSpaceTaken;
            Set<ParkingSpot> nextParkingSpaces;

            // percentPremiumFee percentLongTermFee hoursAllowed percentOvertimeFee
            if(scan.hasNextInt())
                percentPremiumFee = scan.nextInt();

            if(scan.hasNextInt())
                percentLongTermFee = scan.nextInt();

            if(scan.hasNextInt())
                hoursAllowed = scan.nextInt();

            if(scan.hasNextInt())
                percentOvertimeFee = scan.nextInt();

            while (scan.hasNext()){
                // lotID stew baseCostPerHour numberOfRows rowSize rowSpaceTaken* premiumSpace premiumSpaceTaken parkingSpaces**
                nextLotID = scan.next();
                nextStewardUsername = scan.next();
                Steward nextSteward = null;
                for (User oneUser : UserBase.getSetOfUsers()){
                    if(nextStewardUsername.equals(oneUser.getUserName()))
                        nextSteward = (Steward) oneUser;
                }
                nextBaseCostPerHour = scan.nextDouble();
                nextNumberOfRows = scan.nextInt();
                nextRowSize = scan.nextInt();
                nextRowSpaceTaken = new int[nextNumberOfRows];
                for(int i = 0 ; i < nextNumberOfRows ; i++){
                    nextRowSpaceTaken[i] = scan.nextInt();
                }
                nextPremiumSpace = scan.nextInt();
                nextPremiumSpaceTaken = scan.nextInt();

                ParkingLot nextParkingLot = new ParkingLot(nextLotID,nextNumberOfRows,
                      nextRowSize,nextBaseCostPerHour,nextPremiumSpace);

                nextParkingLot.setRowSpaceTaken(nextRowSpaceTaken);
                nextParkingLot.setPremiumSpaceTaken(nextPremiumSpaceTaken);

                for (User oneUser : UserBase.getSetOfUsers()){
                    if(nextStewardUsername.equals(oneUser.getUserName()))
                        nextSteward = (Steward) oneUser;
                }
                if(nextSteward != null){
                    nextParkingLot.setStew(nextSteward);
                }

                nextParkingSpaces = new TreeSet<>();


                // spotID Customer row timeOfPark(double) spaceTaken longTerm premium
                nextScan = scan.next();
                while (!nextScan.equals("out")) {
                    nextScan2 = scan.next();
                    Customer nextCustomer = null;
                    for (User oneUser : UserBase.getSetOfUsers()) {
                        if (nextScan2.equals(oneUser.getUserName()))
                            nextCustomer = (Customer) oneUser;
                    }
                    //String spotID, Customer cust, int row, double timeOfPark,  spacesTaken, boolean longTerm, boolean premium
                    if(nextCustomer != null) {
                        nextParkingSpaces.add(new ParkingSpot(nextScan, nextCustomer, scan.nextInt(),
                        scan.nextLong(), scan.nextInt(), scan.nextBoolean(), scan.nextBoolean()));
                    }
                    nextScan = scan.next();
                }
                nextParkingLot.setParkingSpaces(nextParkingSpaces);
                setOfLots.add(nextParkingLot);

            }


            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("ParkingData.txt was not found by ParkingData.java");
            e.printStackTrace();
        }
    }

    //ONE Popeye5 5 2 5 1 2 4 1 A1 NickT 1 3.57 1 false true B1 ChloeKat 2 190429.24 2 true false out
    /**
     * saves data onto a text file
     */
    static void saveData()
    {
        try {
            PrintWriter out = new PrintWriter(PARKING_DATA);
            // percentPremiumFee percentLongTermFee hoursAllowed percentOvertimeFee
            out.println( percentPremiumFee + " " + getPercentLongTermFee() + " " + getHoursAllowed() +
                  " " + percentOvertimeFee);


            // lotID stew baseCostPerHour numberOfRows rowSize rowSpaceTaken* premiumSpace premiumSpaceTaken parkingSpaces**
            for(ParkingLot oneLot : setOfLots){
                String output = "";
                String outputList = " ";
                String outputList2 = " ";
                output = output + oneLot.getLotID() + " ";
                if(oneLot.getStew() == null)
                    output = output + "null ";
                else
                    output = output + oneLot.getStew().getUserName() + " ";
                output = output + oneLot.getBaseCostPerHour() + " ";
                output = output + oneLot.getNumberOfRows() + " ";
                output = output + oneLot.getRowSize() + " ";

                for (int oneRow : oneLot.getRowSpaceTaken()){
                    outputList = outputList + oneRow + " ";
                }

                output = output + outputList + " ";

                output = output + oneLot.getPremiumSpace() + " ";
                output = output + oneLot.getPremiumSpaceTaken() + " ";


                out.print(output);
                // spotID Customer row timeOfPark(double) spaceTaken longTerm premium


                for (ParkingSpot oneSpot : oneLot.getParkingSpaces()){
                    outputList2 = "";
                    outputList2 = outputList2 + oneSpot.getSpotID() + " ";
                    outputList2 = outputList2 + oneSpot.getCust().getUserName() + " ";
                    outputList2 = outputList2 + oneSpot.getRow() + " ";
                    outputList2 = outputList2 + oneSpot.getTimeOfPark() + " ";
                    outputList2 = outputList2 + oneSpot.getSpaceTaken() + " ";
                    outputList2 = outputList2 + oneSpot.isLongTerm() + " ";
                    outputList2 = outputList2 + oneSpot.isPremium() + " ";
                    out.print("\n   " + outputList2);
                }

                out.print("out\n");
            }

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args){

        /*
        Admin James231 3dsf4
Customer BobCustomer mYPass false A1 B2 out
Customer BillCustomer doggy false A1 B2 out
Steward Popeye5 Secret4 BobCustomer 20 BillCustomer 30 out
         */


        // percentPremiumFee percentLongTermFee hoursAllowed percentOvertimeFee
        UserBase.loadData();
        ParkingBase.loadData();
        System.out.println(ParkingBase.getPercentPremiumFee());
        System.out.println(ParkingBase.getPercentLongTermFee());
        System.out.println(ParkingBase.getHoursAllowed());
        System.out.println(ParkingBase.getPercentOvertimeFee());
        setHoursAllowed(45);
        ParkingBase.saveData();

    }

}
