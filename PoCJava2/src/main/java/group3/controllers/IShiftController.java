package group3.controllers;

import group3.model.Shift;

import java.io.IOException;
import java.util.List;


public interface IShiftController {
    public List<Shift> getAllShifts() throws IOException, ClassNotFoundException;
    public Shift addShift(Shift shift) throws IOException, InterruptedException, ClassNotFoundException;
    public Shift deleteShift(Long shiftId) throws IOException, ClassNotFoundException;
    public Shift enrollToShift(Long shiftId, Long employeeId, Shift shift) throws IOException, ClassNotFoundException;
}

