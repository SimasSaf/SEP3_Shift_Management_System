namespace PoCLayer1.Model;

public class Employee : User
{
    private static long serialVersionUID = 6529685098267757690L;
    public Employee()
    {
    }
    
    public Employee(long id) : base(id)
    {
    }

    public Employee(string username, string password) : base(username, password)
    {
    }

    public Employee(long id, string username, string password, string firstName, string lastName,
        string email, string phoneNumber, string authLevel, string address, double hours) 
        : base(id, username, password, firstName, lastName, email, phoneNumber, authLevel)
    {
        this.address = address;
        this.hours = hours;
    }


    public string address { get; set; }
    public double hours { get; set; }
    
}