import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Payment {
    String paymentID;
    String tenantID;
    double amount;
    Date paymentDate;
    String paymentType;
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

    public void recordPayment() {
        System.out.println("Payment " + this.paymentID + " recorded.\n");
    }

    public String getFormattedDate() {
        return dateFormat.format(this.paymentDate);
    }
}
