import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * INHERITANCE: Landlord extends Person class
 * POLYMORPHISM: Implements Displayable interface and overrides methods
 * ENCAPSULATION: Private fields with getters/setters
 */
public class Landlord extends Person implements Displayable {
    // ENCAPSULATION: Private field
    private List<Property> propertyList;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    static {
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));
    }

    // Constructor - uses super() for INHERITANCE
    public Landlord(String landlordID, String name, String contactInfo) {
        super(landlordID, name, contactInfo);
        this.propertyList = new ArrayList<>();
    }

    // Constructor with credentials - INHERITANCE using super()
    public Landlord(String landlordID, String name, String contactInfo, String username, String password) {
        super(landlordID, name, contactInfo, username, password);
        this.propertyList = new ArrayList<>();
    }

    // ENCAPSULATION: Getter for property list
    public List<Property> getPropertyList() {
        return propertyList;
    }

    // POLYMORPHISM: Override from Person class - provides Landlord-specific
    // dashboard
    @Override
    public void displayDashboard() {
        System.out.println("============================================");
        System.out.println("           LANDLORD DASHBOARD               ");
        System.out.println("============================================");
        System.out.println("Welcome, " + getName());
        System.out.println("Total Properties: " + propertyList.size());
    }

    // POLYMORPHISM: Implement Displayable interface
    @Override
    public void displayInfo() {
        System.out.println("Landlord: " + getName());
        System.out.println("Contact: " + getContactInfo());
        System.out.println("Properties Owned: " + propertyList.size());
    }

    // POLYMORPHISM: Implement Displayable interface
    @Override
    public String getDisplaySummary() {
        return "Landlord: " + getName() + " | Properties: " + propertyList.size();
    }

    // POLYMORPHISM: Override toString() from Object class
    @Override
    public String toString() {
        return "Landlord [ID=" + getId() + ", Name=" + getName() + ", Properties=" + propertyList.size() + "]";
    }

    public void addProperty(Property p) {
        this.propertyList.add(p);
        p.setLandlordID(getId());
        System.out.println("Property " + p.getAddress() + " added.\n");
    }

    public void removeProperty(Property p) {
        this.propertyList.remove(p);
        System.out.println("Property " + p.getAddress() + " removed.\n");
    }

    // View all lands/properties
    public void viewAllLands() {
        System.out.println("\n========================================");
        System.out.println("         ALL PROPERTIES (LANDS)         ");
        System.out.println("========================================");
        if (propertyList.isEmpty()) {
            System.out.println("No properties found.");
        } else {
            for (int i = 0; i < propertyList.size(); i++) {
                Property prop = propertyList.get(i);
                System.out.println((i + 1) + ". " + prop.getAddress());
                System.out.println("   Type: " + prop.getPropertyType());
                System.out.println("   Total Rooms: " + prop.getRooms().size());
                System.out.println("   Available Rooms: " + prop.getAvailableRoomCount());
                System.out.println("   Tenants: " + prop.getTenantList().size());
            }
        }
        System.out.println();
    }

    // View all available rooms across all properties
    public void viewAvailableRooms() {
        System.out.println("\n========================================");
        System.out.println("           AVAILABLE ROOMS              ");
        System.out.println("========================================");
        boolean found = false;
        for (Property prop : propertyList) {
            for (Room room : prop.getRooms()) {
                if (!room.isOccupied()) {
                    System.out.println("Property: " + prop.getAddress());
                    room.displayInfo();
                    System.out.println();
                    found = true;
                }
            }
        }
        if (!found)
            System.out.println("No available rooms.\n");
    }

    // View each tenant's monthly payments
    public void viewTenantPayments(List<Payment> allPayments) {
        System.out.println("\n========================================");
        System.out.println("       TENANT MONTHLY PAYMENTS          ");
        System.out.println("========================================");
        boolean found = false;
        for (Property prop : propertyList) {
            for (Tenant tenant : prop.getTenantList()) {
                System.out.println("Tenant: [" + tenant.getId() + "] " + tenant.getName() + " (Room: "
                        + (tenant.getAssignedRoomID() != null ? tenant.getAssignedRoomID() : "N/A") + ")");
                double totalPaid = 0;
                for (Payment p : tenant.getPaymentHistory()) {
                    System.out.println(
                            "  - [" + p.getPaymentID() + "] " + dateFormat.format(p.getPaymentDate()) + " | Rs. "
                                    + p.getAmount() + " | "
                                    + p.getPaymentType());
                    totalPaid += p.getAmount();
                }
                System.out.println("  Total Paid: Rs. " + String.format("%.2f", totalPaid));
                System.out.println();
                found = true;
            }
        }
        if (!found)
            System.out.println("No tenants found.\n");
    }

    // View each tenant's remaining balance
    public void viewTenantBalances() {
        System.out.println("\n========================================");
        System.out.println("       TENANT REMAINING BALANCES        ");
        System.out.println("========================================");
        boolean found = false;
        for (Property prop : propertyList) {
            for (Tenant tenant : prop.getTenantList()) {
                double totalBills = 0;
                double totalPaid = 0;
                for (Bill bill : tenant.getOutstandingBills()) {
                    totalBills += bill.getAmount();
                    if (bill.isPaid())
                        totalPaid += bill.getAmount();
                }
                double balance = totalBills - totalPaid;
                System.out.println("Tenant: [" + tenant.getId() + "] " + tenant.getName());
                System.out.println("  Total Bills: Rs. " + String.format("%.2f", totalBills));
                System.out.println("  Total Paid: Rs. " + String.format("%.2f", totalPaid));
                System.out.println("  Remaining Balance: Rs. " + String.format("%.2f", balance));
                System.out.println();
                found = true;
            }
        }
        if (!found)
            System.out.println("No tenants found.\n");
    }

    public void viewFinancialAnalytics() {
        System.out.println("\n--- Financial Analytics ---");
        double totalIncome = 0;
        for (Property prop : propertyList) {
            for (Tenant tenant : prop.getTenantList()) {
                for (Payment p : tenant.getPaymentHistory()) {
                    totalIncome += p.getAmount();
                }
            }
        }
        System.out.println("Total Income: Rs. " + String.format("%.2f", totalIncome));
    }
}
