package group3.model;

import java.io.Serializable;

public class Shift implements Serializable {

    private static final long serialVersionUID = -7065683873804696266L;


    private Long employee_id;
    private Long id;
    private String description;
    private String address;
    private String time;
    private String date;
    private int hands_req;

    public Shift() {
    }

    public Shift(Long id)
    {
        this.id = id;
    }

    public Shift(Long id, Long employee_id, String description, String address, String time, String date, int hands_req)
    {
        this.id = id;
        this.employee_id = employee_id;
        this.description = description;
        this.address = address;
        this.time = time;
        this.date = date;
        this.hands_req = hands_req;
    }

    public Shift(Long id, String description, String address, String time, String date, int hands_req) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.time = time;
        this.date = date;
        this.hands_req = hands_req;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHands_req() {
        return hands_req;
    }

    public void setHands_req(int hands_req) {
        this.hands_req = hands_req;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

}
