package eu.ase.io;

public class InvoiceItem {
    private String description;
    private int units;
    private double price;

    public InvoiceItem(String description, int units, double price) {
        this.description = description;
        this.units = units;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public int getUnits() {
        return units;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "InvoiceItem - desc: " + this.description + ", units: " + this.units + ", price: " + this.price;
    }

}
