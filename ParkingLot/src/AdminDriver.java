
package ParkingLot.src;

import ParkingLot.src.*;

import java.util.Scanner;

public class AdminDriver {

    private Admin admin;
    Scanner sc = new Scanner(System.in);

    public AdminDriver(Admin admin) {
        this.admin = admin;
    }

    /**
     * manages GUI and launches functions for the admin menu CHANGES MADE HERE
     */
    public void menu() {

        String input = "";

        System.out.println("Welcome To Admin Menu");
        while (!input.equals("Q")) {
            System.out.println("Admin Menu: Type LOT to display parking lot information, STAFF to display/manage staff information, " +
                  "CUSTOMER to display Customer information,  or Q to quit");
            input = sc.next().toUpperCase();
            if (input.equals("LOT")) {
                manageLot();
            }
            if (input.equals("STAFF")) {
                manageStaff();
            }
            if (input.equals("CUSTOMER")) {
                manageCustomer();
            }
        }
        //System.out.println( "Thank you for using the Parking Lot System!");
    }

    /**
     * manages GUI and launches functions for the user
     */
    public void manageLot() {
        viewLots();
        String input = "";

        System.out.println("Managing Lots Menu");

        while (!input.toUpperCase().equals("B")) {


            System.out.println("Type L to add/delete ParkingLots, C to increase/decrease Lot capacity " +
                  "\n  S set set new fee values, or B to go back to the Admin Menu");
            input = sc.next();

            if(input.toUpperCase().equals("B"))
                break;

            if (input.toUpperCase().equals("L")) {
                while (!input.toUpperCase().equals("B")) {
                    System.out.println("Type A to add a Parking Lot, D to delete one, or B to go back.");
                    input = sc.next();
                    if(input.toUpperCase().equals("B"))
                        break;
                    if (input.toUpperCase().equals("D")){
                        boolean valid = false;
                        ParkingLot selectedParkingLot = null;
                        System.out.println("Type the name of a lot that you want to delete or B to go back.");
                        while (!valid) {
                            input = sc.next();
                            valid = false;
                            boolean found = false;
                            if (input.toUpperCase().equals("B")) {
                                break;
                            }
                            for (ParkingLot oneLot : ParkingBase.getSetOfLots()) {
                                if (oneLot.getLotID().equals(input)){
                                    found = true;
                                    selectedParkingLot = oneLot;

                                    if (oneLot.getParkingSpaces().isEmpty()){
                                        valid = true;
                                    }
                                    else
                                        System.out.println("You cannot delete a Parking lot with occupied spots.");
                                    break;
                                }
                            }

                            if (!found) {
                                System.out.println("Could not find entered parking lot.");
                            }

                            if (!valid) {
                                System.out.println("Try again or press Q to go back:");
                            }
                        }

                        if(valid){
                            ParkingBase.getSetOfLots().remove(selectedParkingLot);
                            System.out.println("ParkingLot Successfully Removed!");
                        }

                    }

                    if (input.toUpperCase().equals("A")) {

                        boolean valid = false;

                        String lotID = "";
                        System.out.println("Enter a name/ID for the new lot or B to go go back");
                        while (!valid) {
                            input = sc.next();
                            boolean found = false;
                            valid = true;
                            if (input.toUpperCase().equals("B")) {
                                valid = false;
                                break;
                            }

                            for (ParkingLot oneLot : ParkingBase.getSetOfLots()) {
                                if(input.equals(oneLot.getLotID())){
                                    found = true;
                                }
                            }

                            if (found) {
                                System.out.println("A lot already exists with that name.");
                                valid = false;
                            }

                            if (!valid) {
                                System.out.println("Try again or press B to go back:");
                            }
                        }

                        if(valid) {
                            // String lotID, int numberOfRows, int rowSize, double baseCostPerHour, int premiumSpace
                            lotID = input;
                            int numberOfRows = 0;
                            int rowSize = 0;
                            double baseCostPerHour = 0;
                            int premiumSpace = 0;

                            System.out.println("How many rows are in the parking lot?");
                            valid = false;
                            while (!valid) {
                                try {
                                    input = sc.next();
                                    numberOfRows = Integer.parseInt(input);
                                    valid = true;


                                } catch (NumberFormatException ex) {
                                    System.out.println("Not a number !");
                                }
                            }

                            System.out.println("How big is each row?");
                            valid = false;
                            while (!valid) {
                                try {
                                    input = sc.next();
                                    rowSize = Integer.parseInt(input);
                                    valid = true;


                                } catch (NumberFormatException ex) {
                                    System.out.println("Not a number !");
                                }
                            }

                            System.out.println("What is going to be the base cost per hour?");
                            valid = false;
                            while (!valid) {
                                try {
                                    input = sc.next();

                                    baseCostPerHour = Double.parseDouble(input);
                                    valid = true;


                                } catch (NumberFormatException ex) {
                                    System.out.println("Not a number !");
                                }
                            }

                            System.out.println("How much premium space is there going top be?");
                            valid = false;
                            while (!valid) {
                                try {
                                    input = sc.next();
                                    premiumSpace = Integer.parseInt(input);
                                    valid = true;


                                } catch (NumberFormatException ex) {
                                    System.out.println("Not a number !");
                                }
                            }

                            ParkingBase.getSetOfLots().add(new ParkingLot(lotID, numberOfRows, rowSize,
                                  baseCostPerHour, premiumSpace));
                            System.out.println("Lot successfully added!");
                        }
                        input = "V";
                    }
                }
            }

            else if (input.toUpperCase().equals("C")) {
                viewLots();
                while (!input.toUpperCase().equals("B")){


                    ParkingLot selectedlot = null;
                    boolean valid = false;
                    System.out.println("Enter a name/ID for the new lot or B to go go back");
                    while (!valid) {
                        input = sc.next();
                        if (input.toUpperCase().equals("B")) {
                            valid = false;
                            break;
                        }

                        for (ParkingLot oneLot : ParkingBase.getSetOfLots()) {
                            if (input.equals(oneLot.getLotID())) {
                                valid = true;
                                selectedlot = oneLot;
                                break;
                            }
                        }

                        if (!valid) {
                            System.out.println("Lot not found. Try again or press B to go back:");
                        }
                    }
                    if(valid) {
                        System.out.println("Current capacity total capacity: " + selectedlot.getTotalSpace());
                        System.out.println("Number of rows = " + selectedlot.getNumberOfRows() +
                              "    Spaces per row = " + selectedlot.getRowSize() +
                              "   Premium space = " + selectedlot.getPremiumSpace());

                        while (!input.toUpperCase().equals("B")) {
                            System.out.println("Type R to change the number of rows, S to change row size, " +
                                  "\n P to change premium capacity, or B to go back.");
                            input = sc.next();
                            if(input.toUpperCase().equals("B"))
                                break;
                            if (input.toUpperCase().equals("R")){
                                System.out.println("How many rows are in the parking lot?");
                                valid = false;
                                int numberOfRows = 0;
                                while (!valid) {
                                    try {
                                        input = sc.next();
                                        if (input.toUpperCase().equals("B")) {
                                            valid = false;
                                            break;
                                        }
                                        numberOfRows = Integer.parseInt(input);
                                        valid = true;
                                        if(numberOfRows < selectedlot.getRowSpaceTaken().length){
                                            for(int i = numberOfRows - 1;
                                                i < selectedlot.getRowSpaceTaken().length ; i ++){
                                                if(selectedlot.getRowSpaceTaken()[i] != 0){
                                                    valid = false;
                                                    System.out.println("Cannot delete rows when final row is occupied");
                                                }

                                            }
                                        }

                                    } catch (NumberFormatException ex) {
                                        System.out.println("Not a number !");
                                    }

                                    if(!valid){
                                        System.out.println("Try again or press B to go back");
                                    }

                                }

                                if(valid) {
                                    selectedlot.setNumberOfRows(numberOfRows);
                                    System.out.println("Successfully changed rows to: " + numberOfRows);
                                }

                            }

                            if (input.toUpperCase().equals("S")) {
                                System.out.println("How many spaces are in each row?");
                                valid = false;
                                int rowSize = 0;
                                while (!valid) {
                                    try {
                                        input = sc.next();
                                        if (input.toUpperCase().equals("B")) {
                                            valid = false;
                                            break;
                                        }
                                        rowSize = Integer.parseInt(input);
                                        valid = true;

                                        for (int i : selectedlot.getRowSpaceTaken()){
                                            if(i > rowSize){
                                                valid = false;
                                                System.out.println("Cannot decrease capacity as rows are over-occupied");
                                                break;
                                            }
                                        }


                                    } catch (NumberFormatException ex) {
                                        System.out.println("Not a number !");
                                    }

                                    if(!valid){
                                        System.out.println("Try again or press B to go back");
                                    }

                                }

                                if(valid) {
                                    selectedlot.setRowSize(rowSize);
                                    System.out.println("Successfully changed rows size to: " + rowSize);
                                }
                            }

                            if (input.toUpperCase().equals("P")) {
                                System.out.println("How many Premium spaces are in the lot?");
                                valid = false;
                                int premiumSize = 0;
                                while (!valid) {
                                    try {
                                        input = sc.next();
                                        if (input.toUpperCase().equals("B")) {
                                            valid = false;
                                            break;
                                        }
                                        premiumSize = Integer.parseInt(input);
                                        valid = true;

                                        if(premiumSize > selectedlot.getTotalSpace()){
                                            valid = false;
                                            System.out.println("Premium spots cannot be more than total capacity.");
                                        }


                                    } catch (NumberFormatException ex) {
                                        System.out.println("Not a number !");
                                    }

                                    if(!valid){
                                        System.out.println("Try again or press B to go back");
                                    }

                                }

                                if(valid) {
                                    selectedlot.setPremiumSpace(premiumSize);
                                    System.out.println("Successfully changed premium capacity size to: " + premiumSize);
                                }
                            }
                        }


                    }
                }

            }

            else if (input.toUpperCase().equals("S")) {

                // percentPremiumFee percentLongTermFee hoursAllowed percentOvertimeFee
                System.out.println("Premium fee = " + ParkingBase.getPercentPremiumFee() + "%" +
                      "\nLong-Term fee = " + ParkingBase.getPercentLongTermFee() + "%" +
                      "\nHours before overtime = " + ParkingBase.getHoursAllowed() +
                      "\nOvertime fee = " + ParkingBase.getPercentOvertimeFee() + "%");

                while (!input.toUpperCase().equals("B")) {
                    System.out.println("Type P to change Premium fee, L to change Long-Term fee, " +
                          "\n H to change Hours before overtime, O to change Overtime fee, or B to go back.");
                    input = sc.next();
                    if(input.toUpperCase().equals("B"))
                        break;
                    if (input.toUpperCase().equals("P")){
                        System.out.println("Whats the new fee?");
                        boolean valid = false;
                        int fee = 0;
                        while (!valid) {
                            try {
                                input = sc.next();
                                if (input.toUpperCase().equals("B")) {
                                    valid = false;
                                    break;
                                }
                                fee = Integer.parseInt(input);
                                valid = true;


                            } catch (NumberFormatException ex) {
                                System.out.println("Not a number !");
                            }

                            if(!valid){
                                System.out.println("Try again or press B to go back");
                            }

                        }

                        if(valid) {
                            ParkingBase.setPercentPremiumFee(fee);
                            System.out.println("Successfully changed Premium fee to: " + fee + "%");
                        }
                    }

                    if (input.toUpperCase().equals("L")) {
                        System.out.println("Whats the new fee?");
                        boolean valid = false;
                        int fee = 0;
                        while (!valid) {
                            try {
                                input = sc.next();
                                if (input.toUpperCase().equals("B")) {
                                    valid = false;
                                    break;
                                }
                                fee = Integer.parseInt(input);
                                valid = true;


                            } catch (NumberFormatException ex) {
                                System.out.println("Not a number !");
                            }

                            if(!valid){
                                System.out.println("Try again or press B to go back");
                            }

                        }

                        if(valid) {
                            ParkingBase.setPercentLongTermFee(fee);
                            System.out.println("Successfully changed Long-Term fee to: " + fee + "%");
                        }
                    }

                    if (input.toUpperCase().equals("H")) {
                        System.out.println("How many hours?");
                        boolean valid = false;
                        int hours = 0;
                        while (!valid) {
                            try {
                                input = sc.next();
                                if (input.toUpperCase().equals("B")) {
                                    valid = false;
                                    break;
                                }
                                hours = Integer.parseInt(input);
                                valid = true;


                            } catch (NumberFormatException ex) {
                                System.out.println("Not a number !");
                            }

                            if(!valid){
                                System.out.println("Try again or press B to go back");
                            }

                        }

                        if(valid) {
                            ParkingBase.setHoursAllowed(hours);
                            System.out.println("Successfully changed hours to: " + hours);
                        }
                    }

                    if (input.toUpperCase().equals("O")){
                        System.out.println("Whats the new fee?");
                        boolean valid = false;
                        int fee = 0;
                        while (!valid) {
                            try {
                                input = sc.next();
                                if (input.toUpperCase().equals("B")) {
                                    valid = false;
                                    break;
                                }
                                fee = Integer.parseInt(input);
                                valid = true;


                            } catch (NumberFormatException ex) {
                                System.out.println("Not a number !");
                            }

                            if(!valid){
                                System.out.println("Try again or press B to go back");
                            }

                        }

                        if(valid) {
                            ParkingBase.setPercentOvertimeFee(fee);
                            System.out.println("Successfully changed Overtime fee to: " + fee + "%");
                        }
                    }
                }
            }
        }

    }


    /**
     * Displays info of selected Parkinglot
     */
    public void viewLots() {
        System.out.print("\n");
        for (ParkingLot oneLot : ParkingBase.getSetOfLots()) {
            System.out.print("Lot: " + oneLot.getLotID());
            if (oneLot.getStew() != null) {
                System.out.print("  " + oneLot.getStew() + "\n");
            } else {
                System.out.print("   [THIS LOT HAS NO STEWARD] \n");
            }
            for (ParkingSpot oneSpot : oneLot.getParkingSpaces()) {
                System.out.println("\t" + oneSpot);
            }

        }
    }


    /**
     * manages GUI and launches functions for the user
     */
    public void manageStaff() {
        String input = "";


        while (!input.toUpperCase().equals("B")) {
            System.out.println("Managing Staff Menu");
            Steward selectedSteward = null;
            ParkingLot selectedLot = null;
            System.out.println("Type A to add a Steward to a parking lot, D to remove a Steward from its " +
                  "parking lot, \n H to Hire/Fire Stewards from the system, or B to go back to the Admin Menu");
            input = sc.next();

            if (input.toUpperCase().equals("A")) {
                for (User stews : UserBase.getSetOfUsers()) {
                    if (stews instanceof Steward) {
                        System.out.println(stews);
                    }
                }
                System.out.println("Which one would you like to add");
                boolean valid = false;
                while (!valid) {
                    input = sc.next();
                    boolean found = false;
                    if (input.toUpperCase().equals("Q")) {
                        break;
                    }
                    for (User stew : UserBase.getSetOfUsers()) {
                        if (input.equals(stew.getUserName()) && stew instanceof Steward) {
                            selectedSteward = (Steward) stew;
                            valid = true;
                            found = true;
                            if (selectedSteward.hasLot()) {
                                valid = false;
                                System.out.println("Selected Steward is already in a lot.");
                            }
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Entered Steward not found. ");
                    }

                    if (!valid) {
                        System.out.println("Try again or press Q to go back:");
                    }
                }


                if (valid) {
                    valid = false;
                    viewLots();
                    System.out.println("Which lot would you like to add them to");
                    while (!valid) {
                        input = sc.next();
                        if (input.toUpperCase().equals("Q")) {
                            break;
                        }
                        boolean found = false;
                        for (ParkingLot lots : ParkingBase.getSetOfLots()) {
                            if (input.equals(lots.getLotID())) {
                                selectedLot = lots;
                                found = true;
                                valid = true;

                                if (lots.getStew() != null) {
                                    valid = false;
                                    System.out.println("Selected Lot already has a Steward.");
                                }
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("Entered Lot not found. ");
                        }

                        if(!valid){
                            System.out.println("Try again or press Q to go back:");
                        }

                    }
                    if (valid) {
                        admin.addSteward(selectedLot, selectedSteward);
                        System.out.println("Successfully added " + selectedSteward.getUserName() + " to lot " +
                              selectedLot.getLotID());
                    }
                }
                input = "V";
            }
            else if (input.toUpperCase().equals("D")) {
                viewLots();
                System.out.println("Which one would you like to remove");
                boolean valid = false;
                while (!valid) {
                    boolean found = false;
                    input = sc.next();
                    if (input.toUpperCase().equals("Q")) {
                        break;
                    }
                    for (User stew : UserBase.getSetOfUsers()) {
                        if (input.equals(stew.getUserName()) && stew instanceof Steward) {
                            selectedSteward = (Steward) stew;
                            valid = true;
                            found = true;
                            if (!selectedSteward.hasLot()) {
                                valid = false;
                                System.out.println("Entered Steward is not currently assigned a lot.");
                            }

                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Entered Steward not found. ");
                    }

                    if (!valid) {
                        System.out.println("Try again or press Q to go back:");
                    }
                }

                if (valid) {
                    for (ParkingLot oneLot : ParkingBase.getSetOfLots()){
                        if(oneLot.getStew() != null){
                            if(oneLot.getStew().equals(selectedSteward)){
                                selectedLot = oneLot;
                            }
                        }
                    }



                    admin.delStewards(selectedLot);
                    System.out.println("Successfully removed " + selectedSteward.getUserName() + " from lot " +
                          selectedLot.getLotID());

                }
            }
            else if (input.toUpperCase().equals("H")) {
                mangeStewards();
                input = "";
            }
        }


    }


    /**
     * manages GUI and launches functions for the user
     */
    public void manageCustomer() {
        String input = "";
        System.out.println("Managing Customer Menu");
        ///System.out.println(UserBase.getSetOfUsers());
        for (User user : UserBase.getSetOfUsers()) {
            if (user instanceof Customer) {
                System.out.println(user);
            }
        }
        while (!input.equals("B")) {
            System.out.println("Type in C to blacklist, U to un-blacklist, or B to go back to the Admin Menu");
            input = sc.next();
            if (input.equals("C")) {
                System.out.println("Type the Customers Username you would like to blacklist");
                input = sc.next();
                for (User user : UserBase.getSetOfUsers()) {
                    if (user instanceof Customer) {
                        if (user.getUserName().equals(input)) {
                            ((Customer) user).setBlackListed(true);
                        }
                    }
                }

            }
            if (input.equals("U")) {
                System.out.println("Type the Customers Username you would like to unblacklist from the list below: ");
                for (User user : UserBase.getSetOfUsers()) {
                    if (user instanceof Customer && ((Customer) user).isBlackListed() == true) {
                        System.out.println(user);
                    }
                }
                input = sc.next();
                for (User user : UserBase.getSetOfUsers()) {
                    if (user instanceof Customer) {
                        if (user.getUserName().equals(input)) {
                            ((Customer) user).setBlackListed(false);
                        }
                    }
                }
            }
        }
        menu();
    }

    /**
     * Displays Menu to manage Stewards, Either Higher/Firer a steward
     */
    public void mangeStewards() {
        String input = "";
        System.out.println("Hire/Fire Staff Menu");
        while (!input.toUpperCase().equals("B")) {
            System.out.println("Type H to hire a new steward or F to fire an existing steward, or B to go back:");
            input = sc.next();
            if (input.toUpperCase().equals("H")) {
                System.out.println("Create a new Steward to hire");
                System.out.println("Enter Username:");
                String userName = sc.next();
                System.out.println("Enter Password:");
                String password = sc.next();
                User dummyUser = new User(userName, "");
                if (UserBase.getSetOfUsers().contains(dummyUser)) {
                    System.out.println("Username Already taken.");
                } else {
                    UserBase.getSetOfUsers().add(new Steward(userName, password));
                    System.out.println("Steward successfully added to system!");
                }
            }
            else if (input.toUpperCase().equals("F")) {
                System.out.println("Current staff:");
                for (User stews : UserBase.getSetOfUsers()) {
                    if (stews instanceof Steward) {
                        System.out.println(stews);
                    }
                }
                Steward selectedSteward = null;
                System.out.println("Type in which steward you would like to fire.");
                boolean valid = false;
                while (!valid) {
                    boolean found = false;
                    input = sc.next();
                    if (input.toUpperCase().equals("Q")) {
                        break;
                    }
                    for (User stew : UserBase.getSetOfUsers()) {
                        if (input.equals(stew.getUserName()) && stew instanceof Steward) {
                            selectedSteward = (Steward) stew;
                            valid = true;
                            found = true;
                            if (selectedSteward.hasLot()) {
                                valid = false;
                                System.out.println("Entered Steward currently assigned a lot, " + selectedSteward +
                                      " must be unassigned from a lot before being fired.");
                            }

                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Entered Steward not found. ");
                    }

                    if (!valid) {
                        System.out.println("Try again or press Q to go back:");
                    }
                }

                if (valid) {
                    admin.fireSteward(selectedSteward.getUserName());
                    System.out.println("Successfully removed " + selectedSteward.getUserName() + " from the System");

                }
            }
        }
        menu();
    }
}
