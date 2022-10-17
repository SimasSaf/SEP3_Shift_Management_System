using PoCLayer1.Model;

namespace PoCLayer1.Http;

public interface IShiftHttpClient
{
    public Task<ICollection<Shift>> GetAllShiftsAsync();
    public Task<Shift> GetShiftByIdAsync(long id);
    public Task<Shift> AddShiftAsync(Shift shift);
    public Task<Shift> DeleteShiftByIdAsync(long id);
    public Task<Shift> EnrollToShift(long shiftId, long employeeId, Shift shift);
}