namespace PoCLayer1.Model;

public class User
{
    public User() {
    }

    public User(string username, string password)
    {
        this.username = username;
        this.password = password;
    }

    public User(long id, string username, string password, string firstName, string lastName, string authLevel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authLevel = authLevel;
    }

    public User(long id, string username, string password, string firstName, string lastName, string email, string phoneNumber, string authLevel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.authLevel = authLevel;
    }
    
    public User(long id) {
        this.id = id;
    }
    
    public User(string firstName, string lastName, string phoneNumber, string email, string password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
    
    public long id { get; set; }
    public string username { get; set; }
    public string password { get; set; }
    public string firstName { get; set; }
    public string lastName { get; set; }
    public string email { get; set; }
    public string phoneNumber { get; set; }
    public string authLevel { get; set; }
    
}