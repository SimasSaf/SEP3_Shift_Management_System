using System.Text;
using System.Text.Json;
using PoCLayer1.Model;

namespace PoCLayer1.Http;

public class EmployeeHttpClientImpl : IEmployeeHttpClient
{
    
    //Serializes, Encodes, Sends, Gets response, Checks if connected, returns what it had sent
    public async Task<Employee> AddEmployeeAsync(Employee employee)
    {
        using HttpClient client = new();

        string employeeToJson = JsonSerializer.Serialize(employee);
        
        
        StringContent content = new(employeeToJson, Encoding.UTF8, "application/json");
        

        HttpResponseMessage response = await client.PostAsync("http://localhost:8081/employees/add", content);
        
        string responseContent = await response.Content.ReadAsStringAsync();
    
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception($"Error: {response.StatusCode}, {responseContent}");
        }
    
        Employee returned = JsonSerializer.Deserialize<Employee>(responseContent, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;

        return returned;
    }

    public async Task<Employee> GetEmployeeByIdAsync(long id)
    {
        using HttpClient client = new();
        
        UriBuilder builder = new UriBuilder($"http://localhost:8081/employees/get/{id}");
        
        HttpResponseMessage response = await client.GetAsync(builder.Uri);
        

        string content = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode) {
            throw new Exception($"Error: {response.StatusCode}, {content}");
        }
        
        Employee returned = JsonSerializer.Deserialize<Employee>(content, new JsonSerializerOptions {
            PropertyNameCaseInsensitive = true
        })!;
  
        return returned;
    }

    public async Task<ICollection<Employee>> GetAllEmployeesAsync()
    {
        using HttpClient client = new HttpClient();
        
        HttpResponseMessage response = await client.GetAsync("http://localhost:8081/employees/getAll");
        
        string responseContent = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception($"Error: {response.StatusCode}, {responseContent}");
        }

        ICollection<Employee> returned = JsonSerializer.Deserialize<ICollection<Employee>>(responseContent, new JsonSerializerOptions {
            PropertyNameCaseInsensitive = true
        })!;
        
        return returned;
    }

    public async Task<Employee> DeleteEmployeeByIdAsync(long id)
    {
        using HttpClient client = new HttpClient();
        
        UriBuilder builder = new UriBuilder($"http://localhost:8081/employees/delete/{id}");
        
        HttpResponseMessage response = await client.DeleteAsync(builder.Uri);
        
        string responseContent = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode) {
            throw new Exception($"Error: {response.StatusCode}, {responseContent}");
        }
        
        Employee returned = JsonSerializer.Deserialize<Employee>(responseContent, new JsonSerializerOptions {
            PropertyNameCaseInsensitive = true
        })!;
  
        return returned;
    }

    public async Task<Employee> UpdateEmployeeAsync(long id, Employee employee)
    {
            using HttpClient client = new HttpClient();

            string employeeToJson = JsonSerializer.Serialize(employee);
        
            StringContent content = new(employeeToJson, Encoding.UTF8, "application/json");
        
            UriBuilder builder = new UriBuilder($"http://localhost:8081/employees/update/{id}");
        
            HttpResponseMessage response = await client.PutAsync(builder.Uri, content);

            string responseContent = await response.Content.ReadAsStringAsync();
        
            if (!response.IsSuccessStatusCode) {
                throw new Exception($"Error: {response.StatusCode}, {content}");
            }
        
            Employee returned = JsonSerializer.Deserialize<Employee>(responseContent, new JsonSerializerOptions {
                PropertyNameCaseInsensitive = true
            })!;

            return returned;
        
    }
    

    }