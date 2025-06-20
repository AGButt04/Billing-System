import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BillingSystem {
    private static final String[] PRODUCT_COLUMNS = {"Name", "Price", "Quantity", "Category"};
    private static final String[] BILL_COLUMNS = {"Name", "Price", "Quantity", "Discounted Price"};

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BillingSystem::createAndShowUI);
    }

    private static void createAndShowUI() {
        ArrayList<Product> productList = loadProducts();
        Bill bill = new Bill();

        JFrame frame = new JFrame("Billing System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Product table
        DefaultTableModel productModel = new DefaultTableModel(PRODUCT_COLUMNS, 0);
        JTable productTable = new JTable(productModel);
        populateProductTable(productList, productModel, "All");

        // Bill table
        DefaultTableModel billModel = new DefaultTableModel(BILL_COLUMNS, 0);
        JTable billTable = new JTable(billModel);
        JLabel totalCostLabel = new JLabel("Total Cost: $0.00");

        // Filter dropdown
        String[] categories = {"All", "Food", "Clothing", "Electronics"};
        JComboBox<String> categoryBox = new JComboBox<>(categories);
        categoryBox.addActionListener(e -> {
            String selected = (String) categoryBox.getSelectedItem();
            populateProductTable(productList, productModel, selected);
        });

        // Add to Bill button
        JButton addToBillButton = new JButton("Add to Bill");
        addToBillButton.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a product.");
                return;
            }

            String name = (String) productModel.getValueAt(selectedRow, 0);
            double price = (Double) productModel.getValueAt(selectedRow, 1);
            int stockQuantity = (Integer) productModel.getValueAt(selectedRow, 2);
            String category = (String) productModel.getValueAt(selectedRow, 3);

            // Prompt for quantity
            String qtyStr = JOptionPane.showInputDialog(frame, "Enter quantity to add to bill (Available: " + stockQuantity + "):", "1");
            if (qtyStr == null) return; // Cancelled
            int requestedQty;
            try {
                requestedQty = Integer.parseInt(qtyStr);
                if (requestedQty <= 0 || requestedQty > stockQuantity) {
                    JOptionPane.showMessageDialog(frame, "Invalid quantity entered.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                return;
            }

            // Find the original product and update stock
            for (Product p : productList) {
                if (p.getName().equals(name) && p.getClass().getSimpleName().equals(category)) {
                    p.setQuantity(p.getQuantity() - requestedQty);  // Update product's stock
                    Product billedProduct = createProduct(category, name, price, requestedQty);

                    bill.addProduct(billedProduct);
                    billModel.addRow(new Object[]{
                            billedProduct.getName(),
                            billedProduct.getPrice(),
                            billedProduct.getQuantity(),
                            billedProduct.getDiscountedPrice()
                    });

                    // Update total cost
                    totalCostLabel.setText("Total Cost: $" + String.format("%.2f", bill.getTotalCost()));
                    break;
                }
            }

            // Refresh the product table to show updated stock
            String selectedCategory = (String) categoryBox.getSelectedItem();
            populateProductTable(productList, productModel, selectedCategory);
        });

        // Layout setup
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JScrollPane(productTable), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.add(new JLabel("Filter by Category:"));
        controlPanel.add(categoryBox);
        controlPanel.add(addToBillButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.CENTER);
        frame.add(new JScrollPane(billTable), BorderLayout.SOUTH);
        frame.add(totalCostLabel, BorderLayout.PAGE_END);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static ArrayList<Product> loadProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Food("Apple", 2.99, 10));
        products.add(new Clothing("T-shirt", 19.99, 5));
        products.add(new Electronics("Headphones", 59.99, 3));
        products.add(new Food("Bread", 1.99, 15));
        return products;
    }

    private static void populateProductTable(ArrayList<Product> products, DefaultTableModel model, String filter) {
        model.setRowCount(0);
        for (Product p : products) {
            if (filter.equals("All") || p.getClass().getSimpleName().equals(filter)) {
                model.addRow(new Object[]{
                        p.getName(), p.getPrice(), p.getQuantity(), p.getClass().getSimpleName()
                });
            }
        }
    }

    private static Product createProduct(String category, String name, double price, int quantity) {
        switch (category) {
            case "Food":
                return new Food(name, price, quantity);
            case "Clothing":
                return new Clothing(name, price, quantity);
            case "Electronics":
                return new Electronics(name, price, quantity);
            default:
                return null;
        }
    }
}
