import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// Base Product class
public class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDiscountedPrice() {
        return price; // No discount for base products
    }

	public void setQuantity(int quan) {
		this.quantity = quan;
	}
}

// Subclasses for specific product categories
class Food extends Product {
    public Food(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public double getDiscountedPrice() {
        return getPrice() * 0.9; // 10% discount
    }
}

class Clothing extends Product {
    public Clothing(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public double getDiscountedPrice() {
        return getPrice() * 0.8; // 20% discount
    }
}

class Electronics extends Product {
    public Electronics(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public double getDiscountedPrice() {
        return getPrice() * 0.85; // 15% discount
    }
}

// Bill class to aggregate products
class Bill {
    private ArrayList<Product> products;

    public Bill() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getTotalCost() {
        return products.stream().mapToDouble(p -> p.getDiscountedPrice() * p.getQuantity()).sum();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}