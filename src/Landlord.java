import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Landlord {
    String landlordID;
    String name;
    String contactInfo;
    String username;
    String password;
    List<Property> propertyList;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    static {
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));
    }

    public Landlord(String landlordID, String name, String contactInfo) {
        this.landlordID = landlordID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.propertyList = new ArrayList<>();
    }

    public Landlord(String landlordID, String name, String contactInfo, String username, String password) {
        this.landlordID = landlordID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.username = username;
        this.password = password;
        this.propertyList = new ArrayList<>();
    }

    public boolean login(String username, String password) {
        return this.username != null && this.username.equals(username) && this.password.equals(password);
    }

    public void addProperty(Property p) {
        this.propertyList.add(p);
        p.landlordID = this.landlordID;
        System.out.println("Property " + p.address + " added.\n");
    }

    public void removeProperty(Property p) {
        this.propertyList.remove(p);
        System.out.println("Property " + p.address + " removed.\n");
    }

    public void viewAllLands() {
        System.out.println("\n========================================");
        System.out.println("         ALL PROPERTIES (LANDS)         ");
        System.out.println("========================================");
        if (propertyList.isEmpty()) {
            System.out.println("No properties found.");
        } else {
            for (int i = 0; i < propertyList.size(); i++) {
                System.out.println((i + 1) + ". " + propertyList.get(i).address);
                System.out.println("   Type: " + propertyList.get(i).propertyType);
                System.out.println("   Total Rooms: " + propertyList.get(i).rooms.size());
                System.out.println("   Available Rooms: " + propertyList.get(i).getAvailableRoomCount());
                System.out.println("   Tenants: " + propertyList.get(i).tenantList.size());
            }
        }
        System.out.println();
    }

    public void viewAvailableRooms() {
        System.out.println("\n========================================");
        System.out.println("           AVAILABLE ROOMS              ");
        System.out.println("========================================");
        boolean found = false;
        for (Property prop : propertyList) {
            for (Room room : prop.rooms) {
                if (!room.isOccupied) {
                    System.out.println("Property: " + prop.address);
                    room.displayDetails();
                    System.out.println();
                    found = true;
                }
            }
        }
        if (!found)
            System.out.println("No available rooms.\n");
    }

    public void viewTenantPayments(List<Payment> allPayments) {
        System.out.println("\n========================================");
        System.out.println("       TENANT MONTHLY PAYMENTS          ");
        System.out.println("========================================");
        boolean found = false;
        for (Property prop : propertyList) {
            for (Tenant tenant : prop.tenantList) {
                System.out.println("Tenant: " + tenant.name + " (Room: "
                        + (tenant.assignedRoomID != null ? tenant.assignedRoomID : "N/A") + ")");
                double totalPaid = 0;
                for (Payment p : tenant.paymentHistory) {
                    System.out.println(
                            "  - " + dateFormat.format(p.paymentDate) + " | Rs. " + p.amount + " | " + p.paymentType);
                    totalPaid += p.amount;
                }
                System.out.println("  Total Paid: Rs. " + String.format("%.2f", totalPaid));
                System.out.println();
                found = true;
            }
        }
        if (!found)
            System.out.println("No tenants found.\n");
    }

    public void viewTenantBalances() {
        System.out.println("\n========================================");
        System.out.println("       TENANT REMAINING BALANCES        ");
        System.out.println("========================================");
        boolean found = false;
        for (Property prop : propertyList) {
            for (Tenant tenant : prop.tenantList) {
                double totalBills = 0;
                double totalPaid = 0;
                for (Bill bill : tenant.outstandingBills) {
                    totalBills += bill.amount;
                    if (bill.isPaid)
                        totalPaid += bill.amount;
                }
                double balance = totalBills - totalPaid;
                System.out.println("Tenant: " + tenant.name);
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
            for (Tenant tenant : prop.tenantList) {
                for (Payment p : tenant.paymentHistory) {
                    totalIncome += p.amount;
                }
            }
        }
        System.out.println("Total Income: Rs. " + String.format("%.2f", totalIncome));
    }
}
