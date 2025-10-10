package IMS;

class ClothingItem extends InventoryItem {
    private String size;
    private String material;

    public ClothingItem(String sku, String name, double unitPrice, String size, String material) {
        super(sku, name, unitPrice);
        if (size == null || size.isBlank()) {
            throw new IllegalArgumentException("Size cannot be null or blank");
        }
        if (material == null || material.isBlank()) {
            throw new IllegalArgumentException("Material cannot be null or blank");
        }
        this.size = size;
        this.material = material;
    }

    public String getSize() {return size;}

    public String getMaterial() {return material;}

    @Override
    public String category() {return "Clothing";}
}