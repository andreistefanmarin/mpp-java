package eu.ase.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceWithItems {
    private List<InvoiceItem> items;

    public InvoiceWithItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public void saveToFile(String fileName) throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        for(InvoiceItem item: items) {
            out.writeUTF(item.getDescription());
            out.writeInt(item.getUnits());
            out.writeDouble(item.getPrice());
        }
        out.close();
    }

    public List<InvoiceItem> readFromFile(String fileName) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
        List<InvoiceItem> itemsList = new ArrayList<>();
        try {
            while(true) {
                String desc = in.readUTF();
                int units = in.readInt();
                double price = in.readDouble();

                InvoiceItem item = new InvoiceItem(desc,units,price);
                itemsList.add(item);
            }
        } catch(EOFException e) {
            in.close();
            //e.printStackTrace();
        }
        return itemsList;
    }
}
