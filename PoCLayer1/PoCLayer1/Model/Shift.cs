namespace PoCLayer1.Model;

public class Shift
{
    public Shift()
    {
    }
    
    public Shift(long id)
    {
        this.id = id;
    }
    
    public Shift(long id, long employeeId, string description, string address, string time, string date, int handsReq)
    {
        this.id = id;
        this.employeeId = employeeId;
        this.description = description;
        this.address = address;
        this.time = time;
        this.date = date;
        this.handsReq = handsReq;    }

    public Shift(long id, string description, string address, string time, string date, int handsReq)
    {
        this.id = id;
        this.description = description;
        this.address = address;
        this.time = time;
        this.date = date;
        this.handsReq = handsReq;
    }

    public long id { get; set; }
    private long employeeId { get; set; }
    public string description { get; set; }
    public string address { get; set; }
    public string time { get; set; }
    public string date { get; set; }
    public int handsReq { get; set; }
   
}