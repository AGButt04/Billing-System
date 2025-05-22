import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BillingSystem {
    public static void main(String[] args) {
        // Product list
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Food("Apple", 2.99, 10));
        productList.add(new Clothing("T-shirt", 19.99, 5));
        productList.add(new Electronics("Headphones", 59.99, 3));
        productList.add(new Food("Bread", 1.99, 15));

        // Bill instance
        Bill bill = new Bill();

        // Swing UI Components
        JFrame frame = new JFrame("Billing System");
        frame.setLayout(new BorderLayout());

        // Product table
        String[] productColumns = {"Name", "Price", "Quantity", "Category"};
        DefaultTableModel productModel = new DefaultTableModel(productColumns, 0);
        JTable productTable = new JTable(productModel);

        // Populate product table
        for (Product product : productList) {
            productModel.addRow(new Object[]{
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getClass().getSimpleName() // Category
            });
        }

        // Bill table
        String[] billColumns = {"Name", "Price", "Quantity", "Discounted Price"};
        DefaultTableModel billModel = new DefaultTableModel(billColumns, 0);
        JTable billTable = new JTable(billModel);

        // Total cost label
        JLabel totalCostLabel = new JLabel("Total Cost: $0.00");

        // ComboBox for filtering
        String[] categories = {"All", "Food", "Clothing", "Electronics"};
        JComboBox<String> categoryBox = new JComboBox<>(categories);
        categoryBox.addActionListener(e -> {
            String selectedCategory = (String) categoryBox.getSelectedItem();
            productModel.setRowCount(0);

            for (Product product : productList) {
                if (selectedCategory.equals("All") || product.getClass().getSimpleName().equals(selectedCategory)) {
                    productModel.addRow(new Object[]{
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getClass().getSimpleName()
                    });
                }
            }
        });

        // Add to Bill button
        JButton addToBillButton = new JButton("Add to Bill");
        addToBillButton.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                String name = (String) productTable.getValueAt(selectedRow, 0);
                double price = (Double) productTable.getValueAt(selectedRow, 1);
                int quantity = (Integer) productTable.getValueAt(selectedRow, 2);
                String category = (String) productTable.getValueAt(selectedRow, 3);

                Product selectedProduct = null;
                switch (category) {
                    case "Food":
                        selectedProduct = new Food(name, price, quantity);
                        break;
                    case "Clothing":
                        selectedProduct = new Clothing(name, price, quantity);
                        break;
                    case "Electronics":
                        selectedProduct = new Electronics(name, price, quantity);
                        break;
                }

                if (selectedProduct != null) {
                    bill.addProduct(selectedProduct);
                    billModel.addRow(new Object[]{
                        selectedProduct.getName(),
                        selectedProduct.getPrice(),
                        selectedProduct.getQuantity(),
                        selectedProduct.getDiscountedPrice()
                    });

                    // Update total cost
                    totalCostLabel.setText("Total Cost: $" + String.format("%.2f", bill.getTotalCost()));
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a product to add to the bill.");
            }
        });

        // Layout and UI setup
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Filter by Category:"));
        controlPanel.add(categoryBox);
        controlPanel.add(addToBillButton);

        frame.add(new JScrollPane(productTable), BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.CENTER);
        frame.add(new JScrollPane(billTable), BorderLayout.SOUTH);
        frame.add(totalCostLabel, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	    }
	}

