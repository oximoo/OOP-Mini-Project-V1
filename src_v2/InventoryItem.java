/**
 * ENCAPSULATION: Private fields with getters/setters
 * POLYMORPHISM: Override toString() method
 */
public class InventoryItem {
    // ENCAPSULATION: Private fields
    private String itemID;
    private String itemName;
    private String assignedRoomID;
    private String condition;

    public InventoryItem(String itemID, String itemName, String condition) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.condition = condition;
        this.assignedRoomID = null;
    }

    // ENCAPSULATION: Getters and Setters
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAssignedRoomID() {
        return assignedRoomID;
    }

    public void setAssignedRoomID(String assignedRoomID) {
        this.assignedRoomID = assignedRoomID;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    // POLYMORPHISM: Override toString() from Object class
    @Override
    public String toString() {
        return "InventoryItem [ID=" + itemID + ", Name=" + itemName + ", Condition=" + condition + "]";
    }

    // To log which room an item belongs to
    public void assignItemToRoom(String roomID) {
        this.assignedRoomID = roomID;
        System.out.println("Item " + this.itemName + " assigned to room " + roomID);
    }

    // To update the item's condition
    public void updateCondition(String newCondition) {
        this.condition = newCondition;
        System.out.println("Item " + this.itemName + " condition updated to " + newCondition);
    }
}
