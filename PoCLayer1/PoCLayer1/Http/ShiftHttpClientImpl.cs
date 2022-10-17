using System.Text;
using System.Text.Json;
using Microsoft.AspNetCore.Mvc;
using PoCLayer1.Model;
namespace PoCLayer1.Http;

public class ShiftHttpClientImpl : IShiftHttpClient
{
    public async Task<ICollection<Shift>> GetAllShiftsAsync()
    {
        using HttpClient client = new HttpClient();
        
        HttpResponseMessage response = await client.GetAsync("http://localhost:8081/shifts/all");
        
        string responseContent = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception($"Error: {response.StatusCode}, {responseContent}");
        }

        ICollection<Shift> returned = JsonSerializer.Deserialize<ICollection<Shift>>(responseContent, new JsonSerializerOptions {
            PropertyNameCaseInsensitive = true
        })!;
        
        return returned;
    }
    
    public async Task<Shift> GetShiftByIdAsync(long id)
    {
        using HttpClient client = new();
        
        UriBuilder builder = new UriBuilder($"http://localhost:8081/shifts/{id}");
        
        HttpResponseMessage response = await client.GetAsync(builder.Uri);
        

        string content = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode) {
            throw new Exception($"Error: {response.StatusCode}, {content}");
        }
        
        Shift returned = JsonSerializer.Deserialize<Shift>(content, new JsonSerializerOptions {
            PropertyNameCaseInsensitive = true
        })!;
  
        return returned;
    }

    public async Task<Shift> AddShiftAsync(Shift shift)
    {
        using HttpClient client = new();

        string shiftToJson = JsonSerializer.Serialize(shift);
        
        
        StringContent content = new(shiftToJson, Encoding.UTF8, "application/json");
        
        Console.WriteLine("TESTING SHIFT HTTP CLIENT IMPL LAYER 1: " + shift.address + " content: " + content);
        
        HttpResponseMessage response = await client.PostAsync("http://localhost:8081/shifts/add", content);
        
        string responseContent = await response.Content.ReadAsStringAsync();
    
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception($"Error: {response.StatusCode}, {responseContent}");
        }
    
        Shift returned = JsonSerializer.Deserialize<Shift>(responseContent, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;

        return returned;
    }

    public async Task<Shift> DeleteShiftByIdAsync(long id)
    {
        using HttpClient client = new HttpClient();
        
        UriBuilder builder = new UriBuilder($"http://localhost:8081/shifts/{id}");
        
        HttpResponseMessage response = await client.DeleteAsync(builder.Uri);
        
        string responseContent = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode) {
            throw new Exception($"Error: {response.StatusCode}, {responseContent}");
        }
        
        Shift returned = JsonSerializer.Deserialize<Shift>(responseContent, new JsonSerializerOptions {
            PropertyNameCaseInsensitive = true
        })!;
  
        return returned;
    }

    public async Task<Shift> EnrollToShift(long shiftId, long employeeId, Shift shift)
    {
        using HttpClient client = new HttpClient();
        // public Shift(long id, long employeeId, string description, string address, string time, string date, int handsReq)
        shift.id = 5;
        

        string enrollToShiftJson = JsonSerializer.Serialize(shift);
        
        StringContent content = new(enrollToShiftJson, Encoding.UTF8, "application/json");
        
        UriBuilder builder = new UriBuilder($"http://localhost:8081/shifts/{shiftId}/{employeeId}");
        
        HttpResponseMessage response = await client.PostAsync(builder.Uri, content);

        string responseContent = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode) {
            throw new Exception($"Error: {response.StatusCode}, {content}");
        }
        
        Shift returned = JsonSerializer.Deserialize<Shift>(responseContent, new JsonSerializerOptions {
            PropertyNameCaseInsensitive = true
        })!;
  
        return returned;
    }
}

