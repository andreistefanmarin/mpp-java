package eu.ase.jcf.main;

import eu.ase.jcf.classes.Country;
import eu.ase.jcf.classes.Plane;

import java.util.*;

public class ProgMainJCF {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("text");
        // list.add(10);
        // String s = (String)list.get(1);
        List<Plane> listPlanes = new ArrayList<Plane>();
        System.out.println("size: " + listPlanes.size());
        for (int i = 0; i < 20; i++) {
            listPlanes.add(new Plane(101 + i, "Airbus " + i, 500.0f));
        }
        System.out.println("size: " + listPlanes.size());
        listPlanes.iterator();
        Plane temp = null;
        for (Iterator<Plane> it = listPlanes.iterator(); it.hasNext(); ) {
            temp = it.next();
            temp.print();
        }

        for (Plane p : listPlanes) {
            p.print();
        }

        Set<Plane> setPlanes = new TreeSet<Plane>();

        Plane p1 = new Plane(1, "Airbus", 300.0f);
        Plane p2 = new Plane(1, "Airbus", 300.0f);

        setPlanes.add(p1);
        setPlanes.add(p2);

        System.out.print(setPlanes.size());

        Map<Plane, Country> treeMap = new TreeMap<>();

        for (int i = 0; i < 7; i++) {
            Plane pk = new Plane(201 + i, "Airbus" + i, 300.0f);
            Country cv = new Country(701 + i, "Country: " + i);

            treeMap.put(pk, cv);
        }

        Set<Plane> s = treeMap.keySet();
        Iterator<Plane> it = s.iterator();
        for (; it.hasNext(); ) {
            Plane pk = it.next();
            Country cv = treeMap.get(pk);
            pk.print();
            cv.print();
        }
        Set<Map.Entry<Plane, Country>> entries = treeMap.entrySet();
        Iterator<Map.Entry<Plane,Country>> itEntry = entries.iterator();

        for(; itEntry.hasNext();) {
            Map.Entry<Plane, Country> entry = itEntry.next();
            Plane pk = entry.getKey();
            Country cv = entry.getValue();
            pk.print();
            cv.print();
        }
    }
}
