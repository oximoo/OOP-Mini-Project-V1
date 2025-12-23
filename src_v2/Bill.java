import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * ENCAPSULATION: Private fields with getters/setters
 * POLYMORPHISM: Override toString() method
 */
public class Bill {
    // ENCAPSULATION: Private fields
    private String billID;
    private String tenantID;
    private String billType;
    private double amount;
    private Date dueDate;
    private boolean paid;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    static {
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));
    }

    public Bill(String billID, String tenantID, String billType, double amount, Date dueDate) {
        this.billID = billID;
        this.tenantID = tenantID;
        this.billType = billType;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paid = false;
    }

    // ENCAPSULATION: Getters and Setters
    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getTenantID() {
        return tenantID;
    }

    public void setTenantID(String tenantID) {
        this.tenantID = tenantID;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    // POLYMORPHISM: Override toString() from Object class
    @Override
    public String toString() {
        return "Bill [ID=" + billID + ", Type=" + billType + ", Amount=" + amount + ", Paid=" + paid + "]";
    }

    // To link a bill to a specific tenant
    public void assignBillToTenant(Tenant t) {
        t.getOutstandingBills().add(this);
        System.out.println("Bill " + this.billID + " assigned to " + t.getName());
    }

    // To update the status of the bill
    public void markAsPaid() {
        this.paid = true;
        System.out.println("Bill " + this.billID + " marked as paid.\n");
    }

    // Get formatted due date
    public String getFormattedDueDate() {
        return dateFormat.format(this.dueDate);
    }
}
