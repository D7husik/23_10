package IMS;
import java.util.ArrayList;
import java.util.List;

final class InventoryManager {
    private List<InventoryItem> inventory;

    public InventoryManager() {this.inventory = new ArrayList<>();}

    public void addItem(InventoryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        inventory.add(item);
    }

    public InventoryItem findBySku(String sku) {
        if (sku == null) {
            return null;
        }
        for (InventoryItem item : inventory) {
            if (item.getSku().equals(sku)) {
                return item;
            }
        }
        return null;
    }

    public double totalValue() {
        double total = 0;
        for (InventoryItem item : inventory) {
            total += item.value();
        }
        return total;
    }

    public int totalQuantityByCategory(String category) {
        if (category == null) {
            return 0;
        }
        int total = 0;
        for (InventoryItem item : inventory) {
            if (item.category().equalsIgnoreCase(category)) {
                total += item.getQuantity();
            }
        }
        return total;
    }

    public boolean issue(String sku, int qty) {
        InventoryItem item = findBySku(sku);
        if (item == null) {
            return false;
        }
        return item.issue(qty);
    }

    public boolean issue(InventoryItem item, int qty) {
        if (item == null) {
            return false;
        }
        return item.issue(qty);
    }
}
