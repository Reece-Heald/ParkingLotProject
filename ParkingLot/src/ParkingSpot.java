package ParkingLot.src;
/*
 Final Project
 Ari Froelich, Reece Heald, Nicholas Thoman, Sandeep Amarnath
 Parking spot class
    implements comparable, getters and setters: spotID, customer, row, time of park, spaces taken, longterm?, premium?, also compareTo() and toString().
 */
public class ParkingSpot implements Comparable
{

    // spotID Customer row timeOfPark(double) spacesTaken longTerm premium
    private String spotID;
    private Customer cust;
    private int row;
    private long timeOfPark;
    private int spacesTaken;
    private boolean longTerm;
    private boolean premium;

    public ParkingSpot(String spotID, Customer cust, int row,
                       long timeOfPark, int spacesTaken, boolean longTerm, boolean premium) {

        this.spotID = spotID;
        this.cust = cust;
        this.row = row;
        this.timeOfPark = timeOfPark;
        this.spacesTaken = spacesTaken;
        this.longTerm = longTerm;
        this.premium = premium;
    }


    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    public String getSpotID() {
        return spotID;
    }

    public void setSpotID(String spotID) {
        this.spotID = spotID;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public long getTimeOfPark() {
        return timeOfPark;
    }

    public void setTimeOfPark(long timeOfPark) {
        this.timeOfPark = timeOfPark;
    }

    public int getSpaceTaken() {
        return spacesTaken;
    }

    public void setSpaceTaken(int spaceTaken) {
        this.spacesTaken = spaceTaken;
    }

    public boolean isLongTerm() {
        return longTerm;
    }

    public void setLongTerm(boolean longTerm) {
        this.longTerm = longTerm;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    @Override
    public int compareTo(Object o)
    {
        ParkingSpot other = (ParkingSpot) o;
        return this.getSpotID().compareTo(((ParkingSpot) o).getSpotID());
    }

    /**
     * Sends to String
     * @return String
     */
    public String toString(){
        String output = spotID + " " + cust + " takes " + spacesTaken;
        if(premium){
            output = output + " Premium";
        }
        if(longTerm){
            output = output + " Long Term";
        }
        return output;
    }


}
