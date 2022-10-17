using System.Security.Claims;
using System.Text.Json;
using System.Web;
using Microsoft.JSInterop;
using PoCLayer1.Http;
using PoCLayer1.Model;

namespace PoCLayer1.Authentication;

public class AuthServiceImpl : IAuthService
{
    public async Task LogoutAsync()
    {
        await ClearUserFromCacheAsync(); // remove the user object from browser cache
        ClaimsPrincipal principal = CreateClaimsPrincipal(null); // create a new ClaimsPrincipal with nothing.
        OnAuthStateChanged?.Invoke(principal); // notify about change in authentication state
    }

    public Action<ClaimsPrincipal> OnAuthStateChanged { get; set; } = null!; // assigning to null! to suppress null warning.
    private readonly IUserHttpClient userHttpClient;
    private readonly IJSRuntime jsRuntime;

    public AuthServiceImpl(IUserHttpClient userHttpClient, IJSRuntime jsRuntime)
    {
        this.userHttpClient = userHttpClient;
        this.jsRuntime = jsRuntime;
    }

    public async Task LoginAsync(string username, string password)
    {
        Console.WriteLine("In LoginAsync");


        User user = new User(username, password);

        User userClaim = await userHttpClient.GetUser(user);

        if (userClaim.authLevel.Equals("Employee") || userClaim.authLevel.Equals("Manager") || userClaim.authLevel.Equals("Admin")) 
        {
                Console.WriteLine("Valid info");
                 await CacheUserAsync(user!); // Cache the user object in the browser 
                 Console.WriteLine("Cached, creating claim...");
                 ClaimsPrincipal principal = CreateClaimsPrincipal(userClaim); // convert user object to ClaimsPrincipal
                 Console.WriteLine("Claim created, incoking change...");
                 OnAuthStateChanged?.Invoke(principal); // notify interested classes in the change of authentication state

        }
            
    }
    
    public async Task<ClaimsPrincipal> GetAuthAsync() // this method is called by the authentication framework, whenever user credentials are reguired
    {
        User? user=  await GetUserFromCacheAsync(); // retrieve cached user, if any

        ClaimsPrincipal principal = CreateClaimsPrincipal(user); // create ClaimsPrincipal

        return principal;
        
    }
    
    private async Task<User?> GetUserFromCacheAsync()
    {
        string userAsJson = await jsRuntime.InvokeAsync<string>("sessionStorage.getItem", "currentUser");
        if (string.IsNullOrEmpty(userAsJson)) return null;
        User user = JsonSerializer.Deserialize<User>(userAsJson)!;
        return user;
    }
    
    private ClaimsPrincipal CreateClaimsPrincipal(User? user)
    {
        if (user != null)
        {
            Console.WriteLine("user not null, converting to claim identity...");
            ClaimsIdentity identity = ConvertUserToClaimsIdentity(user);
            return new ClaimsPrincipal(identity);
        }

        return new ClaimsPrincipal();
    }
    
    private ClaimsIdentity ConvertUserToClaimsIdentity(User user)
    {
        // here we take the information of the User object and convert to Claims
        List<Claim> claims = new()
        {
            new Claim(ClaimTypes.Name, user.username),
            new Claim(ClaimTypes.Role, IsAdmin(user.authLevel)),
            new Claim(ClaimTypes.Role, IsManager(user.authLevel)),
            new Claim(ClaimTypes.Role, IsEmployee(user.authLevel))
        };

        return new ClaimsIdentity(claims, "apiauth_type");
    }
    
    private async Task CacheUserAsync(User user)
    {
        string serialisedData = JsonSerializer.Serialize(user);
        await jsRuntime.InvokeVoidAsync("sessionStorage.setItem", "currentUser", serialisedData);
    }
    
    private async Task ClearUserFromCacheAsync()
    {
        await jsRuntime.InvokeVoidAsync("sessionStorage.setItem", "currentUser", "");
    }

     private string IsEmployee(String authLevel)
     {
         if (authLevel.Equals("Employee"))
         {
             return "Employee";
         }
         return "false";
     }
     
     private string IsAdmin(String authLevel)
     {
         if (authLevel.Equals("Admin"))
         {
             return "Admin";
         }
         return "false";
         
     }
     
     private string IsManager(String authLevel)
     {
         if (authLevel.Equals("Manager"))
         {
             return "Manager";
         }
         return "false";
     }

     public async Task<long> GetCurrentUserId()
     {
         User user = await GetUserFromCacheAsync();
         User userNew = await userHttpClient.GetUser(user);
         
         return userNew.id;
     }
}