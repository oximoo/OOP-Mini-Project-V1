import java.util.List;
import java.util.ArrayList;

public class Property {
    String propertyID;
    String address;
    String propertyType;
    String description;
    List<Tenant> tenantList;
    List<Room> rooms;
    List<InventoryItem> inventoryList;
    List<String> facilities;
    String landlordID;

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

    public void setDescription(String description) {
        this.description = description;
    }

    public void addFacility(String facility) {
        this.facilities.add(facility);
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void addTenant(Tenant t) {
        this.tenantList.add(t);
        System.out.println("Tenant " + t.name + " added to " + this.address);
    }

    public void removeTenant(Tenant t) {
        this.tenantList.remove(t);
        for (Room room : rooms) {
            if (room.assignedTenantID != null && room.assignedTenantID.equals(t.tenantID)) {
                room.vacateRoom();
            }
        }
        System.out.println("Tenant " + t.name + " removed from " + this.address);
    }

    public void assignTenantToRoom(Tenant t, String roomID) {
        for (Room room : rooms) {
            if (room.roomID.equals(roomID) && !room.isOccupied) {
                room.assignTenant(t.tenantID);
                t.assignedRoomID = roomID;
                System.out.println("Tenant " + t.name + " assigned to room " + room.roomNumber);
                return;
            }
        }
        System.out.println("Room not found or already occupied.");
    }

    public int getAvailableRoomCount() {
        int count = 0;
        for (Room room : rooms) {
            if (!room.isOccupied)
                count++;
        }
        return count;
    }

    public void displayAvailableRooms() {
        System.out.println("\nAvailable Rooms at " + address + ":");
        boolean found = false;
        for (Room room : rooms) {
            if (!room.isOccupied) {
                room.displayDetails();
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
                room.displayDetails();
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
            if (room.isOccupied)
                occupiedRooms++;
        }
        int availableRooms = totalRooms - occupiedRooms;
        System.out.println("Total Rooms: " + totalRooms);
        System.out.println("Occupied Rooms: " + occupiedRooms);
        System.out.println("Available Rooms: " + availableRooms);
    }

    public void addInventoryItem(InventoryItem i) {
        this.inventoryList.add(i);
        System.out.println("Item " + i.itemName + " added to inventory.\n");
    }

    public void displayDetails() {
        System.out.println("Property: " + address + " (" + propertyType + ")");
        System.out.println("  Total Rooms: " + rooms.size() + " | Available: " + getAvailableRoomCount());
        if (!description.isEmpty())
            System.out.println("  Description: " + description);
    }
}
