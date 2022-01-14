/*
 Final Project
 Ari Froelich, Reece Heald, Nicholas Thoman, Sandeep Amarnath
 Steward driver
    menu for steward.... uses all functions in Steward.java
 */
package ParkingLot.src;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class StewardDriver {

    Scanner sc = new Scanner(System.in);
    private Steward steward;

    public StewardDriver(Steward steward){
        this.steward = steward;
    }
    /**
     * manages GUI and launches functions for the user
     */
    public void menu() {
        if(steward.hasLot()) {

            String input = "";
            int ammount = 00;
            int numOfCoupons = 0;
            while (!input.equals("Q")) {
                System.out.println("Welcome To Steward Menu");
                System.out.println("Type R to give a random customer a coupon, I to Inspect your parking lot, or Q to quit");
                input = sc.next().toUpperCase();
                boolean valid;
                if (input.equals("R")) {
                    System.out.println("Enter the amount of the discount:");
                    valid = false;
                    while (!valid) {
                        try {
                            input = sc.next();
                            ammount = Integer.parseInt(input);
                            valid = true;


                        } catch (NumberFormatException ex) {
                            System.out.println("Not a number !");
                        }
                    }

                    System.out.println("Enter how many you would like to give out");
                    valid = false;
                    while (!valid) {
                        try {
                            input = sc.next();
                            //System.out.println("hat");
                            numOfCoupons = Integer.parseInt(input);
                            valid = true;


                        } catch (NumberFormatException ex) {
                            System.out.println("Not a number !");
                        }
                    }
                    // steward.addCoupon();
                    steward.giveRandomCoupon(ammount, numOfCoupons);

                }
                if (input.equals("I")) {
                    while (!input.toUpperCase().equals("B")) {
                        // ParkingBase.getSetOfLots().
                        for (ParkingLot lots : ParkingBase.getSetOfLots()) {
                            if(lots.getStew() != null) {
                                if (lots.getStew().getUserName().equals(steward.getUserName())) {
                                    System.out.println("Select a parking spot to inspect, or Type B to go back to Steward Menu");
                                    for (ParkingSpot spaces : lots.getParkingSpaces()) {
                                        System.out.println(spaces.getSpotID() + " Occupied by " + spaces.getCust());
                                    }
                                    input = sc.next();
                                    for (ParkingSpot spaces : lots.getParkingSpaces()) {
                                        if (input.equals(spaces.getSpotID())) {
                                            System.out.println("Customer: " + spaces.getCust());
                                            System.out.println("Spaces Taken: " + spaces.getSpaceTaken());
                                            if (spaces.isLongTerm()) {
                                                System.out.println("Long/Short Term :  Long Term");
                                            }
                                            if (!spaces.isLongTerm()) {
                                                System.out.println("Long/Short Term : Short Term");
                                            }
                                            if (spaces.isPremium()) {
                                                System.out.println("Regular/Premium: Premium");
                                            }
                                            if (!spaces.isPremium()) {
                                                System.out.println("Regular/Premium: Regular");
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        else {
            System.out.println("You have no lot Assigned to you." +
                  "\n If you think this is a mistake, please contact an administrator");
            String input = "";
            while (!input.toUpperCase().equals("Q")){
                System.out.println("Please press Q to logout.");
                input = sc.next();
            }

        }

    }

}

