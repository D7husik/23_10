package IMS;


class ElectronicItem extends InventoryItem implements PriceAdjustable {
    private int warrantyMonths;

    public ElectronicItem(String sku, String name, double unitPrice, int warrantyMonths) {
        super(sku, name, unitPrice);
        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Warranty months cannot be negative CHange to positive");
        }
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {return warrantyMonths;}

    @Override
    public String category() {return "Electronic";}

    @Override
    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100");
        }
        double newPrice = getUnitPrice() * (1-percent/100);
        try {
            var field = InventoryItem.class.getDeclaredField("unitPrice");
            field.setAccessible(true);
            field.set(this, newPrice);
        } catch (Exception e) {
            throw new RuntimeException("Failed to apply discount", e);
        }
    }

    @Override
    public void applySurcharge(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Surcharge percent must be between 0 and 100");
        }
        double newPrice = getUnitPrice() * (1 + percent / 100);
        try {
            var field = InventoryItem.class.getDeclaredField("unitPrice");
            field.setAccessible(true);
            field.set(this, newPrice);
        } catch (Exception e) {
            throw new RuntimeException("Failed to apply surcharge", e);
        }
    }
}
