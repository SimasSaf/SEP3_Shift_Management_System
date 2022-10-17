package group3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "employee")
public class Employee extends User implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledEmployees")
    private List<Shift> shifts = new ArrayList<>();

    @Column(name = "address", nullable = false)
    @Size(max = 50)
    private String address;

    @Column(name = "hours", nullable = false)
    private double hours;


    public Employee() {}

    public Employee(long id)
    {
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

    public List<Shift> getShifts() {
        return shifts;
    }
}
