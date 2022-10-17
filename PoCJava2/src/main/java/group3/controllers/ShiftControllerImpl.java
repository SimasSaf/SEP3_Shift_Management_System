package group3.controllers;

import group3.InitializeConnection;
import group3.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/shifts")
public class ShiftControllerImpl implements IShiftController {

    @Autowired
    InitializeConnection initializeConnection;

    @GetMapping("/all")
    public List<Shift> getAllShifts() throws IOException, ClassNotFoundException {
        System.out.println("Getting All");
        Shift shift = new Shift();
        return (List<Shift>) initializeConnection.sendTransferObject("all", shift);
    }

    @GetMapping("/{id}")
    public Shift getShiftById(@PathVariable("id") Long shiftId) throws IOException, ClassNotFoundException {
        Shift Shift = new Shift();
        Shift = (Shift) initializeConnection.sendTransferObject("getShiftById", new Shift(shiftId));
        return Shift;

    }

    @PostMapping("/add")
    public Shift addShift(@RequestBody Shift shift) throws IOException, ClassNotFoundException {
        System.out.println("Posting...");
        Shift newShift = (Shift) initializeConnection.sendTransferObject("post", shift);
        System.out.println(shift.getDescription() + " layer2 shift controller impl:  " + shift.getAddress());
        return newShift;
    }

    @DeleteMapping("/{id}")
    public Shift deleteShift(@PathVariable("id") Long shiftId) throws IOException, ClassNotFoundException {
        System.out.println("Deleting...");
        Shift shift = (Shift) initializeConnection.sendTransferObject("delete", new Shift(shiftId));
        return shift;
    }

    @PostMapping("{shiftId}/{employeeId}")
    public Shift enrollToShift(@PathVariable Long shiftId, @PathVariable Long employeeId, @RequestBody Shift shift) throws IOException, ClassNotFoundException {
        System.out.println("Enrolling to the shift...");
        return (Shift) initializeConnection.sendTransferObject("EnrollToShift",
                new Shift(shiftId, employeeId, shift.getDescription(), shift.getAddress(), shift.getTime(), shift.getDate(), shift.getHands_req()));
    }

}
