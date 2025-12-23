import java.util.List;
import java.util.ArrayList;

/**
 * POLYMORPHISM: Implements Displayable interface
 * ENCAPSULATION: Private fields with getters/setters
 */
public class Property implements Displayable {
    // ENCAPSULATION: Private fields
    private String propertyID;
    private String address;
    private String propertyType;
    private String description;
    private List<Tenant> tenantList;
    private List<Room> rooms;
    private List<InventoryItem> inventoryList;
    private List<String> facilities;
    private String landlordID;

    public Property(String propertyID, String address, String propertyType) {
        this.propertyID = propertyID;
        this.address = address;
        this.propertyType = propertyType;
        this.description = "";
        this.tenantList = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.inventoryList = new ArrayList<>();
        this.facilities = new ArrayList<>();
        this.landlordID = null;
    }

    // ENCAPSULATION: Getters and Setters
    public String getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(String propertyID) {
        this.propertyID = propertyID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tenant> getTenantList() {
        return tenantList;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<InventoryItem> getInventoryList() {
        return inventoryList;
    }

    public List<String> getFacilities() {
        return facilities;
    }

    public String getLandlordID() {
        return landlordID;
    }

    public void setLandlordID(String landlordID) {
        this.landlordID = landlordID;
    }

    // POLYMORPHISM: Implement Displayable interface
    @Override
    public void displayInfo() {
        System.out.println("Property: " + address + " (" + propertyType + ")");
        System.out.println("  Total Rooms: " + rooms.size() + " | Available: " + getAvailableRoomCount());
        if (!description.isEmpty())
            System.out.println("  Description: " + description);
    }

    // POLYMORPHISM: Implement Displayable interface
    @Override
    public String getDisplaySummary() {
        return "Property: " + address + " | Type: " + propertyType + " | Rooms: " + rooms.size();
    }

    // POLYMORPHISM: Override toString() from Object class
    @Override
    public String toString() {
        return "Property [ID=" + propertyID + ", Address=" + address + ", Type=" + propertyType + "]";
    }

    public void addFacility(String facility) {
        this.facilities.add(facility);
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void addTenant(Tenant t) {
        this.tenantList.add(t);
        System.out.println("Tenant " + t.getName() + " added to " + this.address);
    }

    public void removeTenant(Tenant t) {
        this.tenantList.remove(t);
        for (Room room : rooms) {
            if (room.getAssignedTenantID() != null && room.getAssignedTenantID().equals(t.getId())) {
                room.vacateRoom();
            }
        }
        System.out.println("Tenant " + t.getName() + " removed from " + this.address);
    }

    public void assignTenantToRoom(Tenant t, String roomID) {
        for (Room room : rooms) {
            if (room.getRoomID().equals(roomID) && !room.isOccupied()) {
                room.assignTenant(t.getId());
                t.setAssignedRoomID(roomID);
                System.out.println("Tenant " + t.getName() + " assigned to room " + room.getRoomNumber());
                return;
            }
        }
        System.out.println("Room not found or already occupied.");
    }

    public int getAvailableRoomCount() {
        int count = 0;
        for (Room room : rooms) {
            if (!room.isOccupied())
                count++;
        }
        return count;
    }

    public void displayAvailableRooms() {
        System.out.println("\nAvailable Rooms at " + address + ":");
        boolean found = false;
        for (Room room : rooms) {
            if (!room.isOccupied()) {
                room.displayInfo();
                found = true;
            }
        }
        if (!found)
            System.out.println("No available rooms.");
    }

    public void displayAllRooms() {
        System.out.println("\nAll Rooms at " + address + ":");
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            for (Room room : rooms) {
                room.displayInfo();
            }
        }
    }

    public void displayFacilities() {
        System.out.println("\nFacilities at " + address + ":");
        if (facilities.isEmpty()) {
            System.out.println("No facilities listed.");
        } else {
            for (String facility : facilities) {
                System.out.println("  - " + facility);
            }
        }
    }

    public void checkVacancies() {
        System.out.println("Checking vacancies for " + this.address);
        int totalRooms = this.rooms.size();
        int occupiedRooms = 0;
        for (Room room : rooms) {
            if (room.isOccupied())
                occupiedRooms++;
        }
        int availableRooms = totalRooms - occupiedRooms;

        System.out.println("Total Rooms: " + totalRooms);
        System.out.println("Occupied Rooms: " + occupiedRooms);
        System.out.println("Available Rooms: " + availableRooms);
    }

    public void addInventoryItem(InventoryItem i) {
        this.inventoryList.add(i);
        System.out.println("Item " + i.getItemName() + " added to inventory.\n");
    }

    // Legacy method - now calls displayInfo()
    public void displayDetails() {
        displayInfo();
    }
}
