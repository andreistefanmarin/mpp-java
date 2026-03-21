package eu.ase.io;

import java.io.*;

public class Invoice {
    private String[] descriptions;
    private int[] units;
    private double[] prices;

    public Invoice(String[] descriptions, int[] units, double[] prices) {
        this.units = units;
        this.descriptions = descriptions;
        this.prices = prices;
    }
    public void saveInvoiceToFile(String fileName) {
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            for(int i=0; i<units.length;i++) {
                out.writeUTF(descriptions[i]);
                out.writeInt(units[i]);
                out.writeDouble(prices[i]);
            }
            out.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public double readInvoiceFromFile(String fileName) {
        double total = 0.0f;
        try {
            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            String desc;
            int unit;
            double price;

            try {
                while (true) {
                    desc = in.readUTF();
                    unit = in.readInt();
                    price = in.readDouble();
                    total += price * unit;
                }
            } catch(EOFException e) {
                in.close();
                System.out.println(total);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return total;
    }
}
