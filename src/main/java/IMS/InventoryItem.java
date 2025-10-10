package IMS;


abstract class InventoryItem implements StockTrackable {
    private String sku;
    private String name;
    private int quantity;
    private double unitPrice;

    public InventoryItem(String sku, String name, double unitPrice) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative");
        }

        this.sku = sku;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = 0;
    }

    public String getSku() {return sku;}

    public String getName() {return name;}

    public int getQuantity() {return quantity;}

    public double getUnitPrice() {return unitPrice;}

    protected void setQuantity(int q) {
        if (q < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = q;
    }

    public final double value() {return quantity * unitPrice;}

    public abstract String category();

    @Override
    public void receive(int qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Receive quantity must be positive");
        }
        this.quantity += qty;
    }

//    @Override
    public boolean issue(int qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Issue quantity must be +p");
        }
        if (this.quantity < qty) {
            return false;
        }
        this.quantity -= qty;
        return true;
    }

//    @Override
    public String toString() {
        return String.format("%s/%s/%s/qty=%d/unitPrice=%.2f/value=%.2f",
                sku, name, category(), quantity, unitPrice, value());
    }
}
