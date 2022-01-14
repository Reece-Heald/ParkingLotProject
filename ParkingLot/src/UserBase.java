/*
 Final Project
 Ari Froelich, Reece Heald, Nicholas Thoman, Sandeep Amarnath
 User base class
    set of users created, get/set set of users, load and save data
 */
package ParkingLot.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

abstract class UserBase
{
    private static Set<User> setOfUsers = new TreeSet<User>();
    private static final String USER_DATA = "UserData.txt";


    public static Set<User> getSetOfUsers() {
        return setOfUsers;
    }

    /**
     * will retrieve user data for all users (users include customers, admins, and stewards)
     */
    static void loadData()
    {
        try {
            File userData = new File(USER_DATA);
            Scanner scan = new Scanner(userData);

            // Scans and adds User to the database
            while (scan.hasNext()){

                // Checks for indicator word
                String nextScan = scan.next();
                if (nextScan.equals("Admin")){
                    // Format: "Admin" userName password
                    Admin nextUser = new Admin(scan.next(),scan.next());
                    setOfUsers.add(nextUser);
                }
                if (nextScan.equals("Steward")) {
                    // Format: "Steward" userName password Customer PercentOff Customer PercentOff ... out
                    Steward nextUser = new Steward(scan.next(), scan.next());
                    nextScan = scan.next();
                    while (!nextScan.equals("out")) {
                        nextUser.addCoupon(nextScan, Integer.parseInt(scan.next()));
                        nextScan = scan.next();
                    }
                    setOfUsers.add(nextUser);
                }
                if (nextScan.equals("Customer")) {
                    // Format: "Customer" userName password blacklisted? Lot Lot Lot ... out
                    Customer nextUser = new Customer(scan.next(), scan.next());
                    if (scan.next().equals("true")) {
                        nextUser.setBlackListed(true);
                    }
                    nextScan = scan.next();
                    while (!nextScan.equals("out")) {
                        nextUser.addToLot(nextScan);
                        nextScan = scan.next();
                    }
                    setOfUsers.add(nextUser);
                }


            }


            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("UserData.txt was not found by UserBase.java");
            e.printStackTrace();
        }
    }

    /**
     * will write down data for all users on a txt file
     */
    static void saveData()
    {
        try {
        PrintWriter out = new PrintWriter(USER_DATA);

        for(User oneUser : setOfUsers){
            String outputList = " ";
            if(oneUser instanceof Admin){
                out.println("Admin " + oneUser.getUserName() + " " + oneUser.getPassword());
            }
            if(oneUser instanceof Steward){
                for(String custName: ((Steward) oneUser).getCoupons().keySet()){
                    outputList = outputList + custName + " " + ((Steward) oneUser).getCoupons().get(custName) + " ";
                }
                out.println("Steward " + oneUser.getUserName() + " " + oneUser.getPassword() + outputList + "out");
            }
            if(oneUser instanceof Customer){
                for(String lotID : ((Customer) oneUser).getLots()){
                    outputList = outputList + lotID + " ";
                }
                out.println("Customer " + oneUser.getUserName() + " " + oneUser.getPassword() + " " +
                      ((Customer) oneUser).isBlackListed() + " " + outputList + "out");
            }

        }
        out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        /*
        FOUR testSteward 4.5 3 5  0 2 2  4 1
   B1 ChloeKat 2 1588272782089 2 true false
   C1 NickT 3 1588272782089 1 false true
   C2 BillCustomer 3 1588272782089 1 false false out
ONE SGuy 5.0 2 5  1 2  4 1
   A1 NickT 1 1588272782089 1 false true
   B1 ChloeKat 2 1588272782089 2 true false out
THREE Popeye5 4.5 3 5  1 2 2  4 1
   A1 BobCustomer 1 1588272782089 1 false false
   B1 ChloeKat 2 1588272782089 2 true false
   C1 NickT 3 1588272782089 1 false true
   C2 BillCustomer 3 1588272782089 1 false false out
         */
        UserBase.loadData();
        System.out.println(UserBase.getSetOfUsers());
        UserBase.saveData();
    }
}
