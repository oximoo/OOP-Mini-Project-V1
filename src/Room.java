import java.util.List;
import java.util.ArrayList;

public class Room {
    String roomID;
    String roomNumber;
    double monthlyRent;
    boolean isOccupied;
    List<String> facilities;
    String assignedTenantID;

    public Room(String roomID, String roomNumber, double monthlyRent) {
        this.roomID = roomID;
        this.roomNumber = roomNumber;
        this.monthlyRent = monthlyRent;
        this.isOccupied = false;
        this.facilities = new ArrayList<>();
        this.assignedTenantID = null;
    }

    public void addFacility(String facility) {
        this.facilities.add(facility);
    }

    public void assignTenant(String tenantID) {
        this.assignedTenantID = tenantID;
        this.isOccupied = true;
    }

    public void vacateRoom() {
        this.assignedTenantID = null;
        this.isOccupied = false;
    }

    public void displayDetails() {
        System.out.println("Room: " + roomNumber + " | Rent: Rs. " + monthlyRent + " | Status: "
                + (isOccupied ? "Occupied" : "Available"));
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
}
