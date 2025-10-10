package IMS;

public class Demo {
    public static void main(String[] args) {
        InventoryManager ShopManagerYESSS = new InventoryManager();

        PerishableItem milk = new PerishableItem("P001", "Milk", 3.99, 7);
        ElectronicItem laptop = new ElectronicItem("E001", "Laptop", 999.99, 24);
        ClothingItem tshirt = new ClothingItem("C001", "T-Shirt", 19.99, "M", "Cotton");

        milk.receive(50);
        laptop.receive(10);
        tshirt.receive(100);

        milk.issue(10);
        laptop.applyDiscount(12.99);

        ShopManagerYESSS.addItem(milk);
        ShopManagerYESSS.addItem(laptop);
        ShopManagerYESSS.addItem(tshirt);

        System.out.println(milk.toString());
        System.out.println(laptop.toString());
        System.out.println(tshirt.toString());

        System.out.println("Total Value:" + ShopManagerYESSS.totalValue());

        System.out.println("Perishable: " + ShopManagerYESSS.totalQuantityByCategory("Perishable"));
        System.out.println("Electronic: " + ShopManagerYESSS.totalQuantityByCategory("Electronic"));
        System.out.println("Clothing: " + ShopManagerYESSS.totalQuantityByCategory("Clothing"));

        System.out.println("Issue P001: " + ShopManagerYESSS.issue("P001", 5));
    }
}