package eu.ase.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgMainOOPIO {
    public static void main(String[] args) {
        double[] prices = new double[] {19.99,8.76,15.89};
        int[] units = new int[] {12,8,9};
        String[] descriptions = new String[] {"T-Shirt","Mug","Pen"};
        Invoice invoice = new Invoice(descriptions, units, prices);
        invoice.saveInvoiceToFile("test2.txt");
        double total = invoice.readInvoiceFromFile("test2.txt");
        System.out.println("TOTAL FROM MAIN METHOD: " + total);

        // =======================================
        List<InvoiceItem> items = new ArrayList<>();
        items.add(new InvoiceItem("T-Shirt",12,19.99));
        items.add(new InvoiceItem("Mug",8,8.76));

        InvoiceWithItems invoice2 = new InvoiceWithItems(items);

        try {
            invoice2.saveToFile("test_items.txt");
            List<InvoiceItem> returnedItems = invoice2.readFromFile("test_items.txt");
            for (InvoiceItem it : returnedItems) {
                System.out.println(it);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
