/*
 Final Project
 Ari Froelich, Reece Heald, Nicholas Thoman, Sandeep Amarnath
 User class
    parent code for all users (admin, steward, customer), implements comparable, getters and setters:
    username/password, compareTO(), toString(), and equals().
 */
package ParkingLot.src;

public class User implements Comparable
{
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int compareTo(Object o)
    {
        return userName.compareTo(((User) o).userName);
    }

    /**
     * Send to String
     * @return String
     */
    public String toString(){
        String output = userName;
        if(this instanceof Admin)
            output = "Admin: " + output;
        if(this instanceof Steward)
            output = "Steward: " + output;
        return output;
    }

    public boolean equals(Object obj){
        if (!(obj instanceof User)){
            return false;
        }
        User other = (User) obj;
        return this.getUserName().equals(other.getUserName()) && this.getPassword().equals(other.getPassword());
    }
}
