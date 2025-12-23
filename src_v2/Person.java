/**
 * ABSTRACTION + INHERITANCE: Abstract base class for all persons in the system.
 * This demonstrates:
 * - ABSTRACTION: Hiding common implementation details
 * - INHERITANCE: Landlord and Tenant will extend this class
 * - ENCAPSULATION: Private fields with public getters/setters
 * - POLYMORPHISM: Abstract method displayDashboard() to be overridden
 */
public abstract class Person {
    // ENCAPSULATION: Private fields
    private String id;
    private String name;
    private String contactInfo;
    private String username;
    private String password;

    // Constructor
    public Person(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Constructor with login credentials
    public Person(String id, String name, String contactInfo, String username, String password) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.username = username;
        this.password = password;
    }

    // POLYMORPHISM: This method can be overridden by subclasses
    public boolean login(String username, String password) {
        return this.username != null && this.username.equals(username) && this.password.equals(password);
    }

    // ABSTRACTION: Abstract method - subclasses MUST implement this
    public abstract void displayDashboard();

    // ENCAPSULATION: Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    protected String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // POLYMORPHISM: Override toString() method from Object class
    @Override
    public String toString() {
        return "Person [ID=" + id + ", Name=" + name + ", Contact=" + contactInfo + "]";
    }
}
