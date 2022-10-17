using PoCLayer1.Model;

namespace PoCLayer1.Http;

public interface IUserHttpClient
{
    public Task<User> AddUserAsync(User user);
    public Task<User> GetUserByIdAsync(long id);
    public Task<ICollection<User>> GetAllUsersAsync();
    public Task<User> DeleteUserByIdAsync(long id);
    public Task<User> UpdateUserAsync(long id, User user);
    public Task<User> GetUser(User user);
}