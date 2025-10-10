package IMS;

class PerishableItem extends InventoryItem implements PriceAdjustable {
    private int shelfLifeDays;

    public PerishableItem(String sku, String name, double unitPrice, int shelfLifeDays) {
        super(sku, name, unitPrice);
        if (shelfLifeDays <= 0) {
            throw new IllegalArgumentException("Shelf life must be positive or have a value greater");
        }
        this.shelfLifeDays = shelfLifeDays;
    }

    public int getShelfLifeDays() {
        return shelfLifeDays;
    }

    @Override
    public String category() {return "Perishable";}

    @Override
    public String toString() {return super.toString() + String.format("/shelfLifeDays=%d", shelfLifeDays);}

    @Override
    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100");
        }
        double newPrice = getUnitPrice() * (1 - percent / 100);
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
