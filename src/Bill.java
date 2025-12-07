import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Bill {
    String billID;
    String tenantID;
    String billType;
    double amount;
    Date dueDate;
    boolean isPaid;
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
        this.isPaid = false;
    }

    public void assignBillToTenant(Tenant t) {
        t.outstandingBills.add(this);
        System.out.println("Bill " + this.billID + " assigned to " + t.name);
    }

    public void markAsPaid() {
        this.isPaid = true;
        System.out.println("Bill " + this.billID + " marked as paid.\n");
    }

    public String getFormattedDueDate() {
        return dateFormat.format(this.dueDate);
    }
}
