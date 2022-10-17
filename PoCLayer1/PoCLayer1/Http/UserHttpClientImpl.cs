using System.Text;
using System.Text.Json;
using PoCLayer1.Model;

namespace PoCLayer1.Http;

public class UserHttpClientImpl : IUserHttpClient
{
    //Serializes, Encodes, Sends, Gets response, Checks if connected, returns what it had sent
    public async Task<User> AddUserAsync(User user)
    {
        using HttpClient client = new();

        string userToJson = JsonSerializer.Serialize(user);
        
        
        StringContent content = new(userToJson, Encoding.UTF8, "application/json");
        

        HttpResponseMessage response = await client.PostAsync("http://localhost:8081/users/add", content);
        
        string responseContent = await response.Content.ReadAsStringAsync();
    
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception($"Error: {response.StatusCode}, {responseContent}");
        }
    
        User returned = JsonSerializer.Deserialize<User>(responseContent, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;

        return returned;
    }

    
    public async Task<User> GetUserByIdAsync(long id)
    {
        using HttpClient client = new();
        
        UriBuilder builder = new UriBuilder($"http://localhost:8081/users/{id}");
        
        HttpResponseMessage response = await client.GetAsync(builder.Uri);
        

        string content = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode) {
            throw new Exception($"Error: {response.StatusCode}, {content}");
        }
        
        User returned = JsonSerializer.Deserialize<User>(content, new JsonSerializerOptions {
            PropertyNameCaseInsensitive = true
        })!;
  
        return returned;
    }

    public async Task<ICollection<User>> GetAllUsersAsync()
    {
        using HttpClient client = new HttpClient();
        
        HttpResponseMessage response = await client.GetAsync("http://localhost:8081/users/all");
        
        string responseContent = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception($"Error: {response.StatusCode}, {responseContent}");
        }

        ICollection<User> returned = JsonSerializer.Deserialize<ICollection<User>>(responseContent, new JsonSerializerOptions {
            PropertyNameCaseInsensitive = true
        })!;
        
        return returned;
    }

    public async Task<User> DeleteUserByIdAsync(long id)
    {
        using HttpClient client = new HttpClient();
        
        UriBuilder builder = new UriBuilder($"http://localhost:8081/users/{id}");
        
        HttpResponseMessage response = await client.DeleteAsync(builder.Uri);
        
        string responseContent = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode) {
            throw new Exception($"Error: {response.StatusCode}, {responseContent}");
        }
        
        User returned = JsonSerializer.Deserialize<User>(responseContent, new JsonSerializerOptions {
            PropertyNameCaseInsensitive = true
        })!;
  
        return returned;
    }

    public async Task<User> UpdateUserAsync(long id, User user)
    {
        using HttpClient client = new HttpClient();

        string userToJson = JsonSerializer.Serialize(user);
        
        StringContent content = new(userToJson, Encoding.UTF8, "application/json");
        
        UriBuilder builder = new UriBuilder($"http://localhost:8081/users/{id}");
        
        HttpResponseMessage response = await client.PutAsync(builder.Uri, content);

        string responseContent = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode) {
            throw new Exception($"Error: {response.StatusCode}, {content}");
        }
        
        User returned = JsonSerializer.Deserialize<User>(responseContent, new JsonSerializerOptions {
            PropertyNameCaseInsensitive = true
        })!;
  
        return returned;
    }

    public async Task<User> GetUser(User user)
    {
        using HttpClient client = new();

        string userToJson = JsonSerializer.Serialize(user);

        StringContent content = new(userToJson, Encoding.UTF8, "application/json");
        
        HttpResponseMessage response = await client.PostAsync("http://localhost:8081/users/validate", content);
        
        string responseContent = await response.Content.ReadAsStringAsync();
    
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception($"Error: {response.StatusCode}, {responseContent}");
        }
    
        User returned = JsonSerializer.Deserialize<User>(responseContent, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;

        return returned;
    }
}