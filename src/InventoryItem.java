public class InventoryItem {
    String itemID;
    String itemName;
    String assignedRoomID;
    String condition;

    public InventoryItem(String itemID, String itemName, String condition) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.condition = condition;
        this.assignedRoomID = null;
    }

    public void assignItemToRoom(String roomID) {
        this.assignedRoomID = roomID;
        System.out.println("Item " + this.itemName + " assigned to room " + roomID);
    }

    public void updateCondition(String newCondition) {
        this.condition = newCondition;
        System.out.println("Item " + this.itemName + " condition updated to " + newCondition);
    }
}
