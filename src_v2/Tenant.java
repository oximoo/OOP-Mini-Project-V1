import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * INHERITANCE: Tenant extends Person class
 * POLYMORPHISM: Implements Displayable interface and overrides methods
 * ENCAPSULATION: Private fields with getters/setters
 */
public class Tenant extends Person implements Displayable {
    // ENCAPSULATION: Private fields
    private String assignedRoomID;
    private List<Payment> paymentHistory;
    private List<Bill> outstandingBills;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    static {
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));
    }

    // Constructor - uses super() for INHERITANCE
    public Tenant(String tenantID, String name, String contactInfo) {
        super(tenantID, name, contactInfo);
        this.paymentHistory = new ArrayList<>();
        this.outstandingBills = new ArrayList<>();
    }

    // Constructor with credentials - INHERITANCE using super()
    public Tenant(String tenantID, String name, String contactInfo, String username, String password) {
        super(tenantID, name, contactInfo, username, password);
        this.paymentHistory = new ArrayList<>();
        this.outstandingBills = new ArrayList<>();
    }

    // ENCAPSULATION: Getters and Setters
    public String getAssignedRoomID() {
        return assignedRoomID;
    }

    public void setAssignedRoomID(String assignedRoomID) {
        this.assignedRoomID = assignedRoomID;
    }

    public List<Payment> getPaymentHistory() {
        return paymentHistory;
    }

    public List<Bill> getOutstandingBills() {
        return outstandingBills;
    }

    // POLYMORPHISM: Override from Person class - provides Tenant-specific dashboard
    @Override
    public void displayDashboard() {
        System.out.println("============================================");
        System.out.println("            TENANT DASHBOARD                ");
        System.out.println("============================================");
        System.out.println("Welcome, " + getName());
        System.out.println("Room: " + (assignedRoomID != null ? assignedRoomID : "Not Assigned"));
    }

    // POLYMORPHISM: Implement Displayable interface
    @Override
    public void displayInfo() {
        System.out.println("Tenant: " + getName());
        System.out.println("Contact: " + getContactInfo());
        System.out.println("Room: " + (assignedRoomID != null ? assignedRoomID : "Not Assigned"));
    }

    // POLYMORPHISM: Implement Displayable interface
    @Override
    public String getDisplaySummary() {
        return "Tenant: " + getName() + " | Room: " + (assignedRoomID != null ? assignedRoomID : "N/A");
    }

    // POLYMORPHISM: Override toString() from Object class
    @Override
    public String toString() {
        return "Tenant [ID=" + getId() + ", Name=" + getName() + ", Room=" + assignedRoomID + "]";
    }

    public Payment makePayment(String paymentID, double amount, String type) {
        Payment payment = new Payment(paymentID, getId(), amount, new Date(), type);
        this.paymentHistory.add(payment);
        payment.recordPayment();
        System.out.println("Payment of Rs. " + amount + " for " + type + " received from " + getName());
        return payment;
    }

    public void viewBills() {
        System.out.println("\n--- Outstanding Bills for [" + getId() + "] " + getName() + " ---");
        if (outstandingBills.isEmpty()) {
            System.out.println("No outstanding bills.");
        } else {
            double totalBills = 0;
            double totalPaidBills = 0;
            for (Bill bill : outstandingBills) {
                String status = bill.isPaid() ? "Paid" : "Unpaid";
                System.out.println("- Bill ID: " + bill.getBillID() +
                        " | Type: " + bill.getBillType() +
                        " | Amount: Rs. " + bill.getAmount() +
                        " | Due: " + dateFormat.format(bill.getDueDate()) +
                        " | Status: " + status);
                totalBills += bill.getAmount();
                if (bill.isPaid())
                    totalPaidBills += bill.getAmount();
            }

            // Calculate total payments made
            double totalPayments = 0;
            for (Payment p : paymentHistory) {
                totalPayments += p.getAmount();
            }

            double remainingBalance = totalBills - totalPayments;
            if (remainingBalance < 0)
                remainingBalance = 0;

            System.out.println("----------------------------------------");
            System.out.println("Total Bills:        Rs. " + String.format("%.2f", totalBills));
            System.out.println("Total Paid:         Rs. " + String.format("%.2f", totalPayments));
            System.out.println("Remaining Balance:  Rs. " + String.format("%.2f", remainingBalance));
        }
    }

    public void viewPaymentHistory() {
        System.out.println("\n--- Payment History for " + getName() + " ---");
        if (paymentHistory.isEmpty()) {
            System.out.println("No payment history found.");
        } else {
            double totalPaid = 0;
            for (Payment payment : paymentHistory) {
                System.out.println("- Payment ID: " + payment.getPaymentID() +
                        " | Amount: Rs. " + payment.getAmount() +
                        " | Type: " + payment.getPaymentType() +
                        " | Date: " + dateFormat.format(payment.getPaymentDate()));
                totalPaid += payment.getAmount();
            }
            System.out.println("Total Paid: Rs. " + String.format("%.2f", totalPaid));
        }
    }

    // View available lands/properties
    public static void viewAvailableLands(List<Property> properties) {
        System.out.println("\n========================================");
        System.out.println("         AVAILABLE PROPERTIES           ");
        System.out.println("========================================");
        if (properties.isEmpty()) {
            System.out.println("No properties available.");
        } else {
            for (int i = 0; i < properties.size(); i++) {
                Property prop = properties.get(i);
                System.out.println((i + 1) + ". " + prop.getAddress());
                System.out.println("   Type: " + prop.getPropertyType());
                if (!prop.getDescription().isEmpty()) {
                    System.out.println("   Description: " + prop.getDescription());
                }
                System.out.println("   Available Rooms: " + prop.getAvailableRoomCount());
            }
        }
        System.out.println();
    }

    // View available rooms with prices
    public static void viewAvailableRooms(List<Property> properties) {
        System.out.println("\n========================================");
        System.out.println("     AVAILABLE ROOMS & PRICES           ");
        System.out.println("========================================");
        boolean found = false;
        for (Property prop : properties) {
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

    // View facilities
    public static void viewFacilities(List<Property> properties) {
        System.out.println("\n========================================");
        System.out.println("            FACILITIES                  ");
        System.out.println("========================================");
        for (Property prop : properties) {
            System.out.println("Property: " + prop.getAddress());
            if (prop.getFacilities().isEmpty()) {
                System.out.println("  No facilities listed.");
            } else {
                for (String facility : prop.getFacilities()) {
                    System.out.println("  - " + facility);
                }
            }
            System.out.println();
        }
    }
}
