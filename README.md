# 💰 Java Billing System

A desktop point-of-sale application built with Java Swing that manages product inventory and generates bills with category-based discounts. Features a dual-table interface for product browsing and bill management with real-time total calculation.

## 🎯 What I Built
- **Product Management**: Inventory system with multiple product categories (Food, Clothing, Electronics)
- **Bill Generation**: Dynamic billing with automatic discount application
- **Category Filtering**: Filter products by type for easier browsing
- **Real-time Calculations**: Live total cost updates as items are added
- **Dual-Table Interface**: Separate views for available products and current bill

## ✨ Key Features
- 🏪 **Multi-Category Inventory**: Pre-loaded products across Food, Clothing, and Electronics
- 🎫 **Smart Billing**: Add products to bill with automatic discount calculation
- 🔍 **Category Filter**: Dropdown to filter products by category or view all
- 💲 **Dynamic Pricing**: Real-time total cost calculation with formatted currency display
- 🖥️ **Professional UI**: Clean table-based interface with scroll functionality
- 📊 **Bill Tracking**: Separate table showing selected items with discounted prices

## 🏗️ Project Architecture
```
src/
├── BillingSystem.java    # Main application with GUI and event handling
├── Product.java          # Abstract base class for all products
├── Food.java             # Food category with specific discount logic
├── Clothing.java         # Clothing category with specific discount logic
├── Electronics.java      # Electronics category with specific discount logic
├── Bill.java             # Bill management and total calculation
└── README.md            # Project documentation
```

## 🔧 Implementation Details
- **Object-Oriented Design**: Inheritance hierarchy with Product base class and category-specific subclasses
- **GUI Architecture**: JTable components for data display with DefaultTableModel for dynamic updates
- **Event Handling**: ActionListener implementations for button clicks and dropdown selection
- **Data Management**: ArrayList for product storage with real-time filtering capabilities

## 🚀 Getting Started
```bash
# Clone the repository
git clone https://github.com/AGButt04/Billing-System.git

# Navigate to project directory
cd Billing-System

# Compile the Java files
javac *.java

# Run the application
java BillingSystem
```

**IDE Setup:**
1. Import project into your Java IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Ensure JDK 8+ is configured
3. Run `BillingSystem.java` as the main class

## 🔧 Technologies & Concepts
- **Java SE** - Core programming language
- **Swing Framework** - Desktop GUI components (JTable, JComboBox, JButton)
- **Object-Oriented Programming** - Inheritance, polymorphism, and encapsulation
- **Event-Driven Architecture** - ActionListener pattern for user interactions
- **Data Structures** - ArrayList for dynamic product management
- **MVC Pattern** - Separation of data (models) and presentation (views)

## 💡 Technical Highlights
- **Polymorphic Design**: Different product categories with category-specific discount calculations
- **Dynamic Table Management**: Real-time filtering and updating of JTable components
- **Currency Formatting**: Proper decimal formatting for financial calculations
- **User Input Validation**: Error handling for invalid product selections
- **Memory Efficient**: Lightweight data structures for small-scale inventory management

## 📖 Learning Outcomes
This project demonstrates proficiency in:
- **Desktop Application Development**: Building functional business applications with Java Swing
- **Object-Oriented Design Patterns**: Implementing inheritance hierarchies and polymorphism
- **GUI Event Handling**: Managing user interactions and real-time data updates
- **Business Logic Implementation**: Discount calculation and billing system workflows
- **Data Management**: Filtering, selection, and display of structured data

---
**Part of my programming portfolio** | [Github Profile](https://github.com/AGButt04) | [LinkedIn](https://www.linkedin.com/in/abdul-ghani-butt-290056338/)
