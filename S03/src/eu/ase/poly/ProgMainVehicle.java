package eu.ase.poly;

public class ProgMainVehicle {
    public static void main(String[] args) {
        Vehicle v = null;
        Auto a = null;
        try {
            a = new Auto(1500,5);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(a.display());
        Plane p = new Plane(15000,12,2);
        v = a; //upcasting is automatically done in Java
        System.out.println(v.display());
        v = p;
        System.out.println(p.display()); //upcasting - nu trebuie sa scriem explicit cast

        Vehicle v0 = null;
        p = (Plane)v0;
        //p = (Plane)a - nu se poate

        Movement m0 = null;
        try {
            m0 = new Auto(2900,4);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //m0.display(); -- won't work
        m0.startEngine();

    } //end of main

} //end of ProgMainVehicle class
