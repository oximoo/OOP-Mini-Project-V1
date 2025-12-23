import java.util.List;
import java.util.ArrayList;

/**
 * POLYMORPHISM: Implements Displayable interface
 * ENCAPSULATION: Private fields with getters/setters
 */
public class Room implements Displayable {
    // ENCAPSULATION: Private fields
    private String roomID;
    private String roomNumber;
    private double monthlyRent;
    private boolean occupied;
    private List<String> facilities;
    private String assignedTenantID;

    public Room(String roomID, String roomNumber, double monthlyRent) {
        this.roomID = roomID;
        this.roomNumber = roomNumber;
        this.monthlyRent = monthlyRent;
        this.occupied = false;
        this.facilities = new ArrayList<>();
        this.assignedTenantID = null;
    }

    // ENCAPSULATION: Getters and Setters
    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public List<String> getFacilities() {
        return facilities;
    }

    public String getAssignedTenantID() {
        return assignedTenantID;
    }

    public void setAssignedTenantID(String assignedTenantID) {
        this.assignedTenantID = assignedTenantID;
    }

    // POLYMORPHISM: Implement Displayable interface
    @Override
    public void displayInfo() {
        System.out
                .println("Room ID: " + roomID + " | Room: " + roomNumber + " | Rent: Rs. " + monthlyRent + " | Status: "
                        + (occupied ? "Occupied" : "Available"));
        if (!facilities.isEmpty()) {
            System.out.print("  Facilities: ");
            for (int i = 0; i < facilities.size(); i++) {
                System.out.print(facilities.get(i));
                if (i < facilities.size() - 1)
                    System.out.print(", ");
            }
            System.out.println();
        }
    }

    // POLYMORPHISM: Implement Displayable interface
    @Override
    public String getDisplaySummary() {
        return "Room " + roomNumber + " | Rs. " + monthlyRent + " | " + (occupied ? "Occupied" : "Available");
    }

    // POLYMORPHISM: Override toString() from Object class
    @Override
    public String toString() {
        return "Room [ID=" + roomID + ", Number=" + roomNumber + ", Rent=" + monthlyRent + ", Occupied=" + occupied
                + "]";
    }

    public void addFacility(String facility) {
        this.facilities.add(facility);
    }

    public void assignTenant(String tenantID) {
        this.assignedTenantID = tenantID;
        this.occupied = true;
    }

    public void vacateRoom() {
        this.assignedTenantID = null;
        this.occupied = false;
    }

    // Legacy method - now calls displayInfo()
    public void displayDetails() {
        displayInfo();
    }
}
