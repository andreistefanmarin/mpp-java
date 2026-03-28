package eu.ase.io.serialization;

import javax.imageio.stream.FileImageInputStream;
import javax.sound.midi.SysexMessage;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;

public class ObjectsRestore {
    public static void main(String[] args) {
        ObjectsGraph og = null;
//        try {
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test4.txt"));
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test4.txt")))
        {
            og = (ObjectsGraph)  ois.readObject();
            System.out.println("og read = " + og);

            URL o3 = (URL) ois.readObject();
            System.out.println("o3 read = " + o3);

            boolean exp = ((og.o1 == o3) && (og.o1 == og.o2));
            System.out.println("== " +exp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try(MyResource r = new MyResource()) {
            r.doSomething(); //Working...
        }

        try {
            int x = 10 / 0;
        } catch (Exception e) {
            System.out.println("catch " + e.getMessage());
        } finally {
            System.out.println("dupa");
        }
    }
}
