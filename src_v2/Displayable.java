/**
 * ABSTRACTION: Interface defining display behavior.
 * This demonstrates:
 * - ABSTRACTION: Defines what methods must exist without implementing them
 * - POLYMORPHISM: Different classes implement these methods differently
 */
public interface Displayable {
    // Methods to be implemented by classes
    void displayInfo();

    String getDisplaySummary();
}
