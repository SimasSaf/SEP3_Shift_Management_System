package group3.model;

import java.io.Serializable;


public class Employee extends User implements Serializable
{
    private static final long serialVersionUID = 6529685098267757690L;

    private String address;

    private double hours;

    public Employee() {}

    public Employee(long id) {
        super(id);
    }

    public Employee(long id, String username, String password, String firstName, String lastName,
                    String email, String phoneNumber, String authLevel, String address, double hours) {
        super(id, username, password, firstName, lastName, email, phoneNumber, authLevel);
        this.address = address;
        this.hours = hours;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }
}
