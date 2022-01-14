/*
 Final Project
 Ari Froelich, Reece Heald, Nicholas Thoman, Sandeep Amarnath
 CustomerDriver
    menu(display for customer),view spots(shows all possible spots to park), add or retrieve cars( allows customer to add their vehicle to a lot, and specify spaces)
 */
package ParkingLot.src;

import java.util.Scanner;

public class CustomerDriver
{
    private  Customer c;
    Scanner sc = new Scanner(System.in);

    public CustomerDriver(Customer c) {
        this.c = c;
    }

    /**
     * creates a menu for the customer
     */
    public void menu()
    {
        String input = "";

        System.out.println("Welcome to the Customer Menu!");
        while (!input.toUpperCase().equals("Q")) {
            System.out.println("Customer Menu:");
            if(c.isBlackListed()){
                System.out.println("NOTICE: You are blacklisted and will not be able to successfully add" +
                      "any new cars to any parking lots.");
            }
            System.out.println("Type VIEW to display parking lot information, LOT to add/receive cars from a lot, or Q to quit");
            input = sc.next().toUpperCase();
            if(input.toUpperCase().equals("VIEW")){
                viewSpots();
            }
            else if(input.toUpperCase().equals("LOT")){
                addOrRetrieve();
            }
            else if(!input.toUpperCase().equals("Q")){
                System.out.println("Sorry, we couldn't recognize that.");
            }
        }
    }

    /**
     * shows customer available spots
     */
    public void viewSpots()
    {
        System.out.print("\n" );
        for(ParkingLot oneLot: ParkingBase.getSetOfLots()){
            System.out.print("Lot: " + oneLot.getLotID());
            if(oneLot.getStew() != null){
                System.out.print("  " + oneLot.getStew() + "\n");
            }
            else {
                System.out.print("   [THIS LOT HAS NO STEWARD] Try again later to add/receive cars \n");
            }
            for(ParkingSpot oneSpot : oneLot.getParkingSpaces()){
                if(oneSpot.getCust().equals(c))
                    System.out.println("\t" + oneSpot);
            }
        }
    }

    /**
     * adds or retrieves cars for customers
     */
    public void addOrRetrieve()
    {
        String input;

        System.out.println("Type a lot name (lotID) or Q to go back:");

        ParkingLot parkingLot = null;

        input = sc.next().toUpperCase();
        boolean validLot = false;
        while (!input.toUpperCase().equals("Q") && !validLot) {
            for(ParkingLot oneLot : ParkingBase.getSetOfLots()) {
                if (input.toUpperCase().equals(oneLot.getLotID())) {
                    if(oneLot.getStew() == null){
                        System.out.println(" [THIS LOT HAS NO STEWARD] Try again later to add/receive cars");
                    }
                    else {
                        validLot = true;
                        parkingLot = oneLot;
                    }
                }
            }
            if(!validLot){
                System.out.println("Invalid lot. A list of your lot names and spots can be seen with the " +
                      "VIEW command in the Customer Menu.   \nTry again or Q to go back to the Customer Menu:     ");
                input = sc.next().toUpperCase();
            }
        }

        if(validLot) {
            System.out.println("Displaying your Spot(s) in Parking Lot : " + parkingLot.getLotID());
            System.out.printf("The base cost per hour: $%.2f \n", parkingLot.getBaseCostPerHour());
            for(ParkingSpot oneSpot: parkingLot.getParkingSpaces()){
                if(oneSpot.getCust().equals(c)) {
                    System.out.println("\t" + oneSpot);
                }
            }

            while (!input.toUpperCase().equals("Q")) {
                System.out.println("Type ADD to add a vehicle to the parking lot, RECEIVE to receive from the lot, " +
                      "or Q to go back");
                input = sc.next().toUpperCase();

                if(input.toUpperCase().equals("ADD")){
                    if(c.isBlackListed()){
                        System.out.println("Sorry, but you are currently blacklisted and cannot add any new cars");
                    }
                    else{
                        System.out.println("How many spaces will your vehicle take?");
                        int spacesTaken = sc.nextInt();
                        boolean longterm = false;
                        boolean premium = false;
                        boolean longtermWork = false;
                        boolean premiumWork = false;
                        while (!longtermWork){
                            System.out.println("Do you want a \"Long-Term\" spot? \n " +
                                  "(This is recommended if you plan to be parked for " + ParkingBase.getHoursAllowed() +
                                  " or more hours) \n Type \"Y\" for yes, or \"N\" for no");
                            input = sc.next();
                            if(input.toUpperCase().equals("Y")){
                                longterm = true;
                                longtermWork = true;
                            }
                            if(input.toUpperCase().equals("N")){
                                longterm = false;
                                longtermWork = true;
                            }
                            else {
                                System.out.println("Invalid response, Type \"Y\" for yes, or \"N\" for no");
                            }
                        }
                        while (!premiumWork){
                            System.out.println("Do you want a \"Premium\" spot? \n " +
                                  "Type \"Y\" for yes, or \"N\" for no");
                            input = sc.next();
                            if(input.toUpperCase().equals("Y")){
                                premium = true;
                                premiumWork = true;
                            }
                            if(input.toUpperCase().equals("N")){
                                premium = false;
                                premiumWork = true;
                            }
                            else {
                                System.out.println("Invalid response, Type \"Y\" for yes, or \"N\" for no");
                            }
                        }
                        c.getLots().add(parkingLot.getLotID());
                        System.out.println("Car Added Successfully");
                        if(!parkingLot.getStew().addSpot(c,spacesTaken, longterm, premium)){
                            System.out.println("There is not enough room for your vehicle in this lot.");
                        }
                    }
                    input = "Q";
                }

                else if(input.toUpperCase().equals("RECEIVE")){
                    System.out.println("Type a ParkingSpot ID:   (ex. B2)               or Q to go back");
                    boolean valid = false;
                    ParkingSpot parkingSpot = null;
                    input = sc.next();
                    while (!input.toUpperCase().equals("Q") && !valid){
                        for (ParkingSpot oneSpot : parkingLot.getParkingSpaces()){
                            if(oneSpot.getSpotID().equals(input.toUpperCase()) &&
                                oneSpot.getCust().equals(c)){
                                parkingSpot = oneSpot;
                                valid = true;
                            }
                        }
                        if(!valid){
                            System.out.println("Invaid Spot. Type a ParkingSpot ID (ex. B2) or Q to go back");
                            input = sc.next();
                        }
                    }

                    if(valid){
                        System.out.println("Receiving Spot " + parkingSpot.getSpotID()
                              + " in lot " + parkingLot.getLotID());
                        System.out.printf("\nCost = $%.2f \n", parkingLot.getStew().calculateSpotCost(parkingSpot));

                        boolean hasCoupon = false;

                        if(parkingLot.getStew().getCoupons().containsKey(c.getUserName())){
                            System.out.println("You have a gift coupon that you can redeem! \n" +
                                  "Enter C to pay with cash, \n" +
                                  "N to enter a Credit/Debit card number, \n  " +
                                  "G to redeem you gift coupon, or Q to go back");
                            hasCoupon = true;
                        }
                        else {
                            System.out.println("Enter C to pay with cash, \n" +
                                  "N to enter a Credit/Debit card number, or Q to go back");
                        }
                        input = sc.next();

                        while (!input.toUpperCase().equals("Q")){
                            if(input.toUpperCase().equals("C")){
                                System.out.println("You payed with cash. Thank you for your business!");
                                parkingLot.getStew().delSpot(parkingSpot);
                                input = "Q";
                                boolean anotherSpot = false;
                                for(ParkingSpot oneSpot : parkingLot.getParkingSpaces()){
                                    if (oneSpot.getCust().equals(c)) {
                                        anotherSpot = true;
                                        break;
                                    }
                                }
                                if(!anotherSpot){
                                    c.getLots().remove(parkingLot.getLotID());
                                }
                            }
                            else if(input.toUpperCase().equals("N")) {
                                System.out.println("You payed with Card. Thank you for your business!");
                                parkingLot.getStew().delSpot(parkingSpot);
                                boolean anotherSpot = false;
                                for(ParkingSpot oneSpot : parkingLot.getParkingSpaces()){
                                    if (oneSpot.getCust().equals(c)) {
                                        anotherSpot = true;
                                        break;
                                    }
                                }
                                if(!anotherSpot){
                                    c.getLots().remove(parkingLot.getLotID());
                                }

                                input = "Q";
                            }
                            else if(input.toUpperCase().equals("G") && hasCoupon){
                                System.out.println("You used your coupon!");
                                System.out.println("New Cost = " +
                                      (parkingLot.getStew().calculateSpotCost(parkingSpot) *
                                            (1 - parkingLot.getStew().getCoupons().get(c.getUserName()))));
                                parkingLot.getStew().getCoupons().remove(c.getUserName());

                                System.out.println("Gift coupon only valid if you buy right now.");
                                System.out.println("Enter C to pay with cash, \n" +
                                      "N to enter a Credit/Debit card number, or Q to go back");
                            }
                            else {
                                System.out.println("Invalid entry.");
                                if(parkingLot.getStew().getCoupons().containsKey(c.getUserName())){
                                    System.out.println("You have a gift coupon that you can redeem! \n" +
                                          "Enter C to pay with cash, \n" +
                                          "N to enter a Credit/Debit card number, \n  " +
                                          "G to redeem you gift coupon, or Q to go back");
                                }
                                else {
                                    System.out.println("Enter C to pay with cash, \n" +
                                          "N to enter a Credit/Debit card number, or Q to go back");
                                }
                                input = sc.next();
                            }

                        }

                    }

                    input = "Q";

                }
                else {
                    System.out.println("Sorry, we couldn't recognize that.");
                }
            }



        }
    }
}
