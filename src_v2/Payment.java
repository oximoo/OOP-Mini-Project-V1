import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * ENCAPSULATION: Private fields with getters/setters
 * POLYMORPHISM: Override toString() method
 */
public class Payment {
    // ENCAPSULATION: Private fields
    private String paymentID;
    private String tenantID;
    private double amount;
    private Date paymentDate;
    private String paymentType;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    static {
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));
    }

    public Payment(String paymentID, String tenantID, double amount, Date paymentDate, String paymentType) {
        this.paymentID = paymentID;
        this.tenantID = tenantID;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
    }

    // ENCAPSULATION: Getters and Setters
    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getTenantID() {
        return tenantID;
    }

    public void setTenantID(String tenantID) {
        this.tenantID = tenantID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    // POLYMORPHISM: Override toString() from Object class
    @Override
    public String toString() {
        return "Payment [ID=" + paymentID + ", Amount=" + amount + ", Type=" + paymentType + ", Date="
                + getFormattedDate() + "]";
    }

    // To log the payment in the system and associate it with a tenant
    public void recordPayment() {
        System.out.println("Payment " + this.paymentID + " recorded.\n");
    }

    // Get formatted payment date
    public String getFormattedDate() {
        return dateFormat.format(this.paymentDate);
    }
}
