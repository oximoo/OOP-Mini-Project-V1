import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Tenant {
    String tenantID;
    String name;
    String contactInfo;
    String username;
    String password;
    String assignedRoomID;
    List<Payment> paymentHistory;
    List<Bill> outstandingBills;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    static {
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));
    }

    public Tenant(String tenantID, String name, String contactInfo) {
        this.tenantID = tenantID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.paymentHistory = new ArrayList<>();
        this.outstandingBills = new ArrayList<>();
    }

    public Tenant(String tenantID, String name, String contactInfo, String username, String password) {
        this.tenantID = tenantID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.username = username;
        this.password = password;
        this.paymentHistory = new ArrayList<>();
        this.outstandingBills = new ArrayList<>();
    }

    public boolean login(String username, String password) {
        return this.username != null && this.username.equals(username) && this.password.equals(password);
    }

    public Payment makePayment(String paymentID, double amount, String type) {
        Payment payment = new Payment(paymentID, this.tenantID, amount, new Date(), type);
        this.paymentHistory.add(payment);
        payment.recordPayment();
        System.out.println("Payment of Rs. " + amount + " for " + type + " received from " + this.name);
        return payment;
    }

    public void viewBills() {
        System.out.println("\n--- Outstanding Bills for " + this.name + " ---");
        if (outstandingBills.isEmpty()) {
            System.out.println("No outstanding bills.");
        } else {
            double totalDue = 0;
            for (Bill bill : outstandingBills) {
                String status = bill.isPaid ? "Paid" : "Unpaid";
                System.out.println("- Bill ID: " + bill.billID +
                        " | Type: " + bill.billType +
                        " | Amount: Rs. " + bill.amount +
                        " | Due: " + dateFormat.format(bill.dueDate) +
                        " | Status: " + status);
                if (!bill.isPaid)
                    totalDue += bill.amount;
            }
            System.out.println("Total Due: Rs. " + String.format("%.2f", totalDue));
        }
    }

    public void viewPaymentHistory() {
        System.out.println("\n--- Payment History for " + this.name + " ---");
        if (paymentHistory.isEmpty()) {
            System.out.println("No payment history found.");
        } else {
            double totalPaid = 0;
            for (Payment payment : paymentHistory) {
                System.out.println("- Payment ID: " + payment.paymentID +
                        " | Amount: Rs. " + payment.amount +
                        " | Type: " + payment.paymentType +
                        " | Date: " + dateFormat.format(payment.paymentDate));
                totalPaid += payment.amount;
            }
            System.out.println("Total Paid: Rs. " + String.format("%.2f", totalPaid));
        }
    }

    public static void viewAvailableLands(List<Property> properties) {
        System.out.println("\n========================================");
        System.out.println("         AVAILABLE PROPERTIES           ");
        System.out.println("========================================");
        if (properties.isEmpty()) {
            System.out.println("No properties available.");
        } else {
            for (int i = 0; i < properties.size(); i++) {
                Property prop = properties.get(i);
                System.out.println((i + 1) + ". " + prop.address);
                System.out.println("   Type: " + prop.propertyType);
                if (!prop.description.isEmpty()) {
                    System.out.println("   Description: " + prop.description);
                }
                System.out.println("   Available Rooms: " + prop.getAvailableRoomCount());
            }
        }
        System.out.println();
    }

    public static void viewAvailableRooms(List<Property> properties) {
        System.out.println("\n========================================");
        System.out.println("     AVAILABLE ROOMS & PRICES           ");
        System.out.println("========================================");
        boolean found = false;
        for (Property prop : properties) {
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

    public static void viewFacilities(List<Property> properties) {
        System.out.println("\n========================================");
        System.out.println("            FACILITIES                  ");
        System.out.println("========================================");
        for (Property prop : properties) {
            System.out.println("Property: " + prop.address);
            if (prop.facilities.isEmpty()) {
                System.out.println("  No facilities listed.");
            } else {
                for (String facility : prop.facilities) {
                    System.out.println("  - " + facility);
                }
            }
            System.out.println();
        }
    }
}
