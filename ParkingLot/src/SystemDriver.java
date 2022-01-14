/*
 Final Project
 Ari Froelich, Reece Heald, Nicholas Thoman, Sandeep Amarnath
 SystemDriver class
    run(called to start program user interface), login/logout, register(used for new users)
    this is the filed ran to test/use the project
 */
package ParkingLot.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class SystemDriver
{

    Scanner sc = new Scanner(System.in);
    boolean isLoggedin= false;

    /**
     * Runs Program
     */
    public void run(){
        UserBase.loadData();
        ParkingBase.loadData();
        String input = "";
        while(!input.equals("Q")){
            System.out.println( "Welcome to the Parking Lot System!");
            System.out.println( "Press L to Login, R to Register a new User, or Q to quit.");
            input = sc.next().toUpperCase();
            if (input.equals("L"))
                login();
            if(input.equals("R"))
                register();
        }
        UserBase.saveData();
        ParkingBase.saveData();
        System.out.println( "Thank you for using the Parking Lot System!");
    }




    /**
     * manages GUI and launches functions for the logging in
     */
    public void login()
    {
        SystemDriver systemDriver = new SystemDriver();
        boolean done = false;
        while(!done) {
            boolean validLogin = false;
            String enteredUsername = "";
            String enteredPassword = "";
            System.out.println("Please Login: ");
            System.out.println("Enter Username: ");
            enteredUsername = sc.next();
            System.out.println("Enter Password");
            enteredPassword = sc.next();
            User loginDummy = new User(enteredUsername,enteredPassword);
            for(User oneUser: UserBase.getSetOfUsers()){
                if(oneUser.equals(loginDummy)){
                    validLogin = true;
                    loginDummy = oneUser;
                    done = true;
                }
            }
            if (!validLogin){
                System.out.println("Invalid Credentials. Press L to try again or Q to quit");
                String test = sc.next();
                if(test.equals("Q") || test.equals("q") ){
                    done = true;
                }
            }
            else {
                if(loginDummy instanceof Customer){
                    CustomerDriver cd = new CustomerDriver((Customer) loginDummy);
                    cd.menu();
                    System.out.println("Customer Menu Launched!");
                }
                if(loginDummy instanceof Steward){
                    StewardDriver sd = new StewardDriver((Steward) loginDummy);
                    sd.menu();
                }
                if(loginDummy instanceof Admin){
                    AdminDriver ad = new AdminDriver((Admin) loginDummy);
                    ad.menu();
                }
            }
        }
        // BobCustomer


    }

    /**
     * manages GUI and launches functions for logging out
     */
    public void logout()
    {

        System.out.println("Do you want to loggout? yes/no");
        String ans = sc.nextLine().toUpperCase();
        if(ans.equals("YES")){
            isLoggedin = false;
            System.out.println("Successfully Logged out");
        }
    }

    /**
     * manages GUI and launches functions for the registering
     */
    public void register()
    {

        String input = "";
        Boolean done = false;
        System.out.println("Enter New Username: ");
        input = sc.next();
        while (!input.equals("Q") && !input.equals("q") && !done) {
            User dummyUser = new User(input, "");
            if (UserBase.getSetOfUsers().contains(dummyUser)) {
                System.out.println("Username Already taken. Enter another or enter Q to quit.");
                input = sc.next();
            }
            else {
                done = true;
            }
        }
        if(!input.equals("Q")){
            System.out.println("Enter Password: ");
            String newPassword = sc.next();
            UserBase.getSetOfUsers().add(new Customer(input,newPassword));
            System.out.println("Success");
        }
        //newUser(newUsername,newPassword);
        //Customer newCustomer = new Customer();


    }

    public static void main(String[] args){
        SystemDriver sd = new SystemDriver();
        sd.run();
    }

}
