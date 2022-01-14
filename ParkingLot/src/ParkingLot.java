/*
 Final Project
 Ari Froelich, Reece Heald, Nicholas Thoman, Sandeep Amarnath
 Parking lot Class
    implements comparable, getters and setters: steward, lotID, parking spaces, base cost per hour,
    number of rows, row size, row space taken, premium?, premium take, and to string()
 */
package ParkingLot.src;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ParkingLot implements Comparable
{

    // lotID stew baseCostPerHour numberOfRows rowSize rowSpaceTaken*** premiumSpace premiumSpaceTaken parkingSpaces**
    private String lotID;
    private Steward stew;
    private Set<ParkingSpot> parkingSpaces = new TreeSet<>();
    private double baseCostPerHour;
    private int numberOfRows;
    private int rowSize;
    private int[] rowSpaceTaken;
    private int premiumSpace;
    private int premiumSpaceTaken;


    /**
     * constructor for creating a paring lot
     */
    public ParkingLot(String lotID, int numberOfRows, int rowSize, double baseCostPerHour, int premiumSpace) {
        this.lotID = lotID;
        this.numberOfRows = numberOfRows;
        this.rowSize = rowSize;
        this.baseCostPerHour = baseCostPerHour;
        this.premiumSpace = premiumSpace;
        this.rowSpaceTaken = new int[numberOfRows];
    }


    public Steward getStew() {
        return stew;
    }

    public void setStew(Steward stew) {
        this.stew = stew;
    }

    public Set<ParkingSpot> getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(Set<ParkingSpot> parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public String getLotID() {
        return lotID;
    }

    public void setLotID(String lotID) {
        this.lotID = lotID;
    }

    public void setBaseCostPerHour(double baseCostPerHour) {
        this.baseCostPerHour = baseCostPerHour;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
        int[] newArray = new int[numberOfRows];
        int cieling = Math.min(rowSpaceTaken.length,numberOfRows);

        for(int i = 0 ; i < cieling ; i++){
            newArray[i] = rowSpaceTaken[i];
        }

        setRowSpaceTaken(newArray);


    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public void setRowSpaceTaken(int[] rowSpaceTaken) {
        this.rowSpaceTaken = rowSpaceTaken;
    }

    public int[] getRowSpaceTaken() {
        return rowSpaceTaken;
    }

    public int getPremiumSpace() {
        return premiumSpace;
    }

    public void setPremiumSpace(int premiumSpace) {
        this.premiumSpace = premiumSpace;
    }

    public void setPremiumSpaceTaken(int premiumSpaceTaken) {
        this.premiumSpaceTaken = premiumSpaceTaken;
    }

    @Override
    public int compareTo(Object o)
    {
        return lotID.compareTo(((ParkingLot) o).lotID);
    }


    /**
     * will return the total space in a specific parking lot
     *
     * @return Integar Total Spaces
     */
    public int getTotalSpace()
    {
        return rowSize*numberOfRows;
    }

    /**
     * will return the spaces or number of spaces taken
     * @return Integer Total space taken
     */
    public int getTotalSpaceTaken()
    {
        int output = 0;
        for (int row: rowSpaceTaken){
            output += row;
        }
        return output;
    }

    /**
     * will return the premium spaces or number of premium spaces taken
     */
    public int getPremiumSpaceTaken() {
        return premiumSpaceTaken;
    }

    /**
     * will return the base cost per hour of a specific car
     */
    public double getBaseCostPerHour() {
        return baseCostPerHour;
    }

    // lotID stew baseCostPerHour numberOfRows rowSize rowSpaceTaken*** premiumSpace premiumSpaceTaken parkingSpaces**

    /**
     * Sends to String
     * @return String
     */
    public String toString(){

        String output = lotID;
        if(stew == null)
            output = output + " null";
        else
            output = output + " " + stew;

        output = output + "  $:" + baseCostPerHour + "  TotalSpace: " + getTotalSpace()
              + "  TotalSpaceTaken: " + getTotalSpaceTaken()
              + "  PSpace: " + premiumSpace + "  PSpaceTaken: " + premiumSpaceTaken +
              "  Spots: " + parkingSpaces;

        return output;
    }
}
