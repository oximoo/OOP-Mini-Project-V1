import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.TimeZone;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Landlord> landlords = new ArrayList<>();
    private static List<Property> properties = new ArrayList<>();
    private static List<Tenant> tenants = new ArrayList<>();
    private static List<Bill> bills = new ArrayList<>();
    private static List<Payment> payments = new ArrayList<>();
    private static List<InventoryItem> inventoryItems = new ArrayList<>();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private static Landlord currentLandlord = null;
    private static Tenant currentTenant = null;

    static {
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));
    }

    public static void main(String[] args) {
        initializeDefaultData();

        System.out.println("\n========================================");
        System.out.println("     Property Management System         ");
        System.out.println("========================================\n");

        boolean running = true;
        while (running) {
            showMainMenu();
            String choice = getStringInput("Enter your choice: ");
            switch (choice) {
                case "1":
                    loginLandlord();
                    break;
                case "2":
                    loginTenant();
                    break;
                case "3":
                    registerLandlord();
                    break;
                case "4":
                    registerTenant();
                    break;
                case "0":
                    running = false;
                    System.out.println("\n--- System End ---\n");
                    break;
                default:
                    System.out.println("Invalid choice.\n");
            }
        }
        scanner.close();
    }

    private static void initializeDefaultData() {
        // Create default landlord
        Landlord defaultLandlord = new Landlord("L001", "Kamal Perera", "077-1234567", "admin", "admin123");
        landlords.add(defaultLandlord);

        // Create Property 1: Sunrise Apartments
        Property prop1 = new Property("P001", "Sunrise Apartments, Colombo 05", "Apartment");
        prop1.setDescription("Modern apartments in heart of Colombo");
        prop1.addFacility("24/7 Security");
        prop1.addFacility("Parking");
        prop1.addFacility("CCTV");
        prop1.addFacility("Generator Backup");

        Room r1 = new Room("R001", "101", 25000);
        r1.addFacility("AC");
        r1.addFacility("WiFi");
        r1.addFacility("Hot Water");
        Room r2 = new Room("R002", "102", 20000);
        r2.addFacility("Fan");
        r2.addFacility("WiFi");
        Room r3 = new Room("R003", "103", 30000);
        r3.addFacility("AC");
        r3.addFacility("WiFi");
        r3.addFacility("Hot Water");
        r3.addFacility("Furnished");
        Room r4 = new Room("R004", "201", 22000);
        r4.addFacility("AC");
        r4.addFacility("WiFi");
        Room r5 = new Room("R005", "202", 18000);
        r5.addFacility("Fan");
        r5.addFacility("Attached Bathroom");

        prop1.addRoom(r1);
        prop1.addRoom(r2);
        prop1.addRoom(r3);
        prop1.addRoom(r4);
        prop1.addRoom(r5);
        properties.add(prop1);
        defaultLandlord.addProperty(prop1);

        // Create Property 2: Green Valley Villa
        Property prop2 = new Property("P002", "Green Valley Villa, Kandy", "Villa");
        prop2.setDescription("Scenic villa with garden view");
        prop2.addFacility("Garden");
        prop2.addFacility("Parking");
        prop2.addFacility("Security");

        Room r6 = new Room("R006", "A1", 35000);
        r6.addFacility("AC");
        r6.addFacility("WiFi");
        r6.addFacility("Furnished");
        r6.addFacility("Balcony");
        Room r7 = new Room("R007", "A2", 32000);
        r7.addFacility("AC");
        r7.addFacility("WiFi");
        r7.addFacility("Hot Water");
        Room r8 = new Room("R008", "B1", 28000);
        r8.addFacility("AC");
        r8.addFacility("WiFi");

        prop2.addRoom(r6);
        prop2.addRoom(r7);
        prop2.addRoom(r8);
        properties.add(prop2);
        defaultLandlord.addProperty(prop2);

        // Create Property 3: Beach Side Resort
        Property prop3 = new Property("P003", "Beach Side Resort, Galle", "Resort");
        prop3.setDescription("Beach front rooms with ocean view");
        prop3.addFacility("Beach Access");
        prop3.addFacility("Swimming Pool");
        prop3.addFacility("Restaurant");
        prop3.addFacility("24/7 Security");

        Room r9 = new Room("R009", "Sea-01", 45000);
        r9.addFacility("AC");
        r9.addFacility("WiFi");
        r9.addFacility("Ocean View");
        r9.addFacility("Mini Bar");
        Room r10 = new Room("R010", "Sea-02", 42000);
        r10.addFacility("AC");
        r10.addFacility("WiFi");
        r10.addFacility("Ocean View");

        Room r11 = new Room("R011", "Garden-01", 35000);
        r11.addFacility("AC");
        r11.addFacility("WiFi");
        r11.addFacility("Garden View");
        Room r12 = new Room("R012", "Garden-02", 32000);
        r12.addFacility("AC");
        r12.addFacility("WiFi");

        prop3.addRoom(r9);
        prop3.addRoom(r10);
        prop3.addRoom(r11);
        prop3.addRoom(r12);
        properties.add(prop3);
        defaultLandlord.addProperty(prop3);

        // Create default tenant
        Tenant defaultTenant = new Tenant("T001", "Nimal Silva", "076-9876543", "tenant1", "tenant123");
        tenants.add(defaultTenant);

        System.out.println("Default data loaded successfully!\n");
    }

    private static void showMainMenu() {
        System.out.println("============================================");
        System.out.println("               MAIN MENU                    ");
        System.out.println("============================================");
        System.out.println("  1. Login as Landlord");
        System.out.println("  2. Login as Tenant");
        System.out.println("  3. Register as Landlord");
        System.out.println("  4. Register as Tenant");
        System.out.println("  0. Exit\n");
    }

    // ==================== LOGIN/REGISTER ====================

    private static void loginLandlord() {
        System.out.println("\n--- Landlord Login ---");
        String username = getStringInput("Username: ");
        String password = getStringInput("Password: ");

        for (Landlord l : landlords) {
            // POLYMORPHISM: Using inherited login() method from Person class
            if (l.login(username, password)) {
                currentLandlord = l;
                System.out.println("Login successful! Welcome, " + l.getName() + "\n");
                landlordDashboard();
                return;
            }
        }
        System.out.println("Invalid username or password.\n");
    }

    private static void loginTenant() {
        System.out.println("\n--- Tenant Login ---");
        String username = getStringInput("Username: ");
        String password = getStringInput("Password: ");

        for (Tenant t : tenants) {
            // POLYMORPHISM: Using inherited login() method from Person class
            if (t.login(username, password)) {
                currentTenant = t;
                System.out.println("Login successful! Welcome, " + t.getName() + "\n");
                tenantDashboard();
                return;
            }
        }
        System.out.println("Invalid username or password.\n");
    }

    private static void registerLandlord() {
        System.out.println("\n--- Landlord Registration ---");
        String id = "L" + String.format("%03d", landlords.size() + 1);
        String name = getStringInput("Enter Name: ");
        String contact = getStringInput("Enter Contact: ");
        String username = getStringInput("Enter Username: ");
        String password = getStringInput("Enter Password: ");

        // Check if username exists - using ENCAPSULATION (getUsername())
        for (Landlord l : landlords) {
            if (l.getUsername() != null && l.getUsername().equals(username)) {
                System.out.println("Username already exists.\n");
                return;
            }
        }

        Landlord newLandlord = new Landlord(id, name, contact, username, password);
        landlords.add(newLandlord);
        System.out.println("Registration successful! You can now login.\n");
    }

    private static void registerTenant() {
        System.out.println("\n--- Tenant Registration ---");
        String id = "T" + String.format("%03d", tenants.size() + 1);
        String name = getStringInput("Enter Name: ");
        String contact = getStringInput("Enter Contact: ");
        String username = getStringInput("Enter Username: ");
        String password = getStringInput("Enter Password: ");

        // Check if username exists - using ENCAPSULATION (getUsername())
        for (Tenant t : tenants) {
            if (t.getUsername() != null && t.getUsername().equals(username)) {
                System.out.println("Username already exists.\n");
                return;
            }
        }

        Tenant newTenant = new Tenant(id, name, contact, username, password);
        tenants.add(newTenant);
        System.out.println("Registration successful! You can now login.\n");
    }

    // ==================== LANDLORD DASHBOARD ====================

    private static void landlordDashboard() {
        boolean running = true;
        while (running) {
            showLandlordMenu();
            String choice = getStringInput("Enter your choice: ");
            switch (choice) {
                case "1.1":
                    currentLandlord.viewAllLands();
                    break;
                case "1.2":
                    currentLandlord.viewAvailableRooms();
                    break;
                case "1.3":
                    currentLandlord.viewTenantPayments(payments);
                    break;
                case "1.4":
                    currentLandlord.viewTenantBalances();
                    break;
                case "1.5":
                    addNewProperty();
                    break;
                case "1.6":
                    addNewRoom();
                    break;
                case "1.7":
                    assignTenantToProperty();
                    break;
                case "1.8":
                    createBill();
                    break;
                case "1.9":
                    viewAllTenants();
                    break;
                case "0":
                    running = false;
                    currentLandlord = null;
                    System.out.println("Logged out.\n");
                    break;
                default:
                    System.out.println("Invalid choice.\n");
            }
        }
    }

    private static void showLandlordMenu() {
        System.out.println("============================================");
        System.out.println("           LANDLORD DASHBOARD               ");
        System.out.println("============================================");
        System.out.println("--- View Reports ---");
        System.out.println("  1.1 View All Lands");
        System.out.println("  1.2 View Available Rooms");
        System.out.println("  1.3 View Tenant Monthly Payments");
        System.out.println("  1.4 View Tenant Remaining Balances");
        System.out.println("--- Management ---");
        System.out.println("  1.5 Add New Property");
        System.out.println("  1.6 Add New Room");
        System.out.println("  1.7 Assign Tenant to Room");
        System.out.println("  1.8 Create Bill for Tenant");
        System.out.println("  1.9 View All Tenants");
        System.out.println("--- Exit ---");
        System.out.println("  0   Logout\n");
    }

    private static void addNewProperty() {
        System.out.println("\n--- Add New Property ---");
        String id = "P" + String.format("%03d", properties.size() + 1);
        String address = getStringInput("Enter Address: ");
        String type = getStringInput("Enter Type (Apartment/Villa/Resort): ");
        String description = getStringInput("Enter Description: ");

        Property prop = new Property(id, address, type);
        prop.setDescription(description);

        String addFacility = getStringInput("Add facilities? (y/n): ");
        while (addFacility.equalsIgnoreCase("y")) {
            String facility = getStringInput("Enter Facility: ");
            prop.addFacility(facility);
            addFacility = getStringInput("Add more? (y/n): ");
        }

        properties.add(prop);
        currentLandlord.addProperty(prop);
        System.out.println("Property created successfully!\n");
    }

    private static void addNewRoom() {
        // ENCAPSULATION: Using getter method
        if (currentLandlord.getPropertyList().isEmpty()) {
            System.out.println("No properties available. Add a property first.\n");
            return;
        }

        System.out.println("\n--- Add New Room ---");
        int pIdx = selectProperty(currentLandlord.getPropertyList()) - 1;
        if (pIdx < 0 || pIdx >= currentLandlord.getPropertyList().size())
            return;

        Property prop = currentLandlord.getPropertyList().get(pIdx);
        String id = "R" + String.format("%03d", getTotalRoomCount() + 1);
        String roomNumber = getStringInput("Enter Room Number: ");
        double rent = getDoubleInput("Enter Monthly Rent (Rs.): ");

        Room room = new Room(id, roomNumber, rent);

        String addFacility = getStringInput("Add facilities? (y/n): ");
        while (addFacility.equalsIgnoreCase("y")) {
            String facility = getStringInput("Enter Facility: ");
            room.addFacility(facility);
            addFacility = getStringInput("Add more? (y/n): ");
        }

        prop.addRoom(room);
        System.out.println("Room added successfully!\n");
    }

    private static void assignTenantToProperty() {
        if (tenants.isEmpty()) {
            System.out.println("No tenants registered.\n");
            return;
        }
        // ENCAPSULATION: Using getter method
        if (currentLandlord.getPropertyList().isEmpty()) {
            System.out.println("No properties available.\n");
            return;
        }

        System.out.println("\n--- Assign Tenant to Room ---");
        int tIdx = selectTenant() - 1;
        if (tIdx < 0 || tIdx >= tenants.size())
            return;

        int pIdx = selectProperty(currentLandlord.getPropertyList()) - 1;
        if (pIdx < 0 || pIdx >= currentLandlord.getPropertyList().size())
            return;

        Property prop = currentLandlord.getPropertyList().get(pIdx);
        prop.displayAvailableRooms();

        String roomID = getStringInput("Enter Room ID to assign: ");
        Tenant tenant = tenants.get(tIdx);

        prop.addTenant(tenant);
        prop.assignTenantToRoom(tenant, roomID);
        System.out.println();
    }

    private static void createBill() {
        if (tenants.isEmpty()) {
            System.out.println("No tenants available.\n");
            return;
        }

        System.out.println("\n--- Create Bill ---");
        int tIdx = selectTenant() - 1;
        if (tIdx < 0 || tIdx >= tenants.size())
            return;

        Tenant t = tenants.get(tIdx);
        String billId = "B" + String.format("%03d", bills.size() + 1);
        String type = getStringInput("Enter Bill Type (Rent/Electricity/Water): ");
        double amount = getDoubleInput("Enter Amount (Rs.): ");
        Date dueDate = getDateInput("Enter Due Date (dd-MM-yyyy) or Enter for today: ");

        // ENCAPSULATION: Using getId() getter
        Bill bill = new Bill(billId, t.getId(), type, amount, dueDate);
        bill.assignBillToTenant(t);
        bills.add(bill);
        System.out.println("Bill created successfully!\n");
    }

    private static void viewAllTenants() {
        System.out.println("\n--- All Registered Tenants ---");
        if (tenants.isEmpty()) {
            System.out.println("No tenants found.");
        } else {
            for (int i = 0; i < tenants.size(); i++) {
                Tenant t = tenants.get(i);
                // ENCAPSULATION: Using getter methods
                System.out.println((i + 1) + ". [" + t.getId() + "] " + t.getName() + " | Contact: " + t.getContactInfo() + " | Room: "
                        + (t.getAssignedRoomID() != null ? t.getAssignedRoomID() : "Not Assigned"));
            }
        }
        System.out.println();
    }

    // ==================== TENANT DASHBOARD ====================

    private static void tenantDashboard() {
        boolean running = true;
        while (running) {
            showTenantMenu();
            String choice = getStringInput("Enter your choice: ");
            switch (choice) {
                case "1.1":
                    Tenant.viewAvailableLands(properties);
                    break;
                case "1.2":
                    Tenant.viewAvailableRooms(properties);
                    break;
                case "1.3":
                    Tenant.viewFacilities(properties);
                    break;
                case "1.4":
                    currentTenant.viewBills();
                    System.out.println();
                    break;
                case "1.5":
                    makePayment();
                    break;
                case "1.6":
                    currentTenant.viewPaymentHistory();
                    System.out.println();
                    break;
                case "0":
                    running = false;
                    currentTenant = null;
                    System.out.println("Logged out.\n");
                    break;
                default:
                    System.out.println("Invalid choice.\n");
            }
        }
    }

    private static void showTenantMenu() {
        System.out.println("============================================");
        System.out.println("            TENANT DASHBOARD                ");
        System.out.println("============================================");
        System.out.println("--- Browse Properties ---");
        System.out.println("  1.1 View Available Lands");
        System.out.println("  1.2 View Available Rooms & Prices");
        System.out.println("  1.3 View Facilities");
        System.out.println("--- My Account ---");
        System.out.println("  1.4 View My Bills");
        System.out.println("  1.5 Make Payment");
        System.out.println("  1.6 View Payment History");
        System.out.println("--- Exit ---");
        System.out.println("  0   Logout\n");
    }

    private static void makePayment() {
        // ENCAPSULATION: Using getter method
        if (currentTenant.getOutstandingBills().isEmpty()) {
            System.out.println("No outstanding bills.\n");
            return;
        }

        System.out.println("\n--- Make Payment ---");
        currentTenant.viewBills();

        String paymentID = "PAY" + String.format("%03d", payments.size() + 1);
        double amount = getDoubleInput("Enter Payment Amount (Rs.): ");
        String type = getStringInput("Enter Payment Type (Cash/Card/Bank Transfer): ");

        Payment p = currentTenant.makePayment(paymentID, amount, type);
        payments.add(p);

        // Link to bill
        if (getStringInput("Link to specific bill? (y/n): ").equalsIgnoreCase("y")) {
            System.out.println("\nSelect bill to link:");
            // ENCAPSULATION: Using getter method
            for (int i = 0; i < currentTenant.getOutstandingBills().size(); i++) {
                Bill b = currentTenant.getOutstandingBills().get(i);
                if (!b.isPaid()) {
                    System.out.println(
                            (i + 1) + ". " + b.getBillID() + " - Rs. " + b.getAmount() + " (" + b.getBillType() + ")");
                }
            }
            int bIdx = getIntInput("Enter bill number: ") - 1;
            if (bIdx >= 0 && bIdx < currentTenant.getOutstandingBills().size()) {
                Bill bill = currentTenant.getOutstandingBills().get(bIdx);
                if (amount >= bill.getAmount()) {
                    bill.markAsPaid();
                }
            }
        }
        System.out.println();
    }

    // ==================== HELPER METHODS ====================

    private static int getTotalRoomCount() {
        int count = 0;
        for (Property p : properties) {
            // ENCAPSULATION: Using getter method
            count += p.getRooms().size();
        }
        return count;
    }

    private static int selectProperty(List<Property> propList) {
        System.out.println("\nSelect Property:");
        for (int i = 0; i < propList.size(); i++) {
            // ENCAPSULATION: Using getter method
            System.out.println((i + 1) + ". " + propList.get(i).getAddress());
        }
        return getIntInput("Enter number: ");
    }

    private static int selectTenant() {
        System.out.println("\nSelect Tenant:");
        for (int i = 0; i < tenants.size(); i++) {
            // ENCAPSULATION: Using getter method
            System.out.println((i + 1) + ". [" + tenants.get(i).getId() + "] " + tenants.get(i).getName());
        }
        return getIntInput("Enter number: ");
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return getIntInput(prompt);
        }
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return getDoubleInput(prompt);
        }
    }

    private static Date getDateInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (input.isEmpty())
            return new Date();
        try {
            return dateFormat.parse(input);
        } catch (ParseException e) {
            System.out.println("Invalid date. Using today.");
            return new Date();
        }
    }
}