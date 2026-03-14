package eu.ase.jcf.classes;

public class Plane implements Comparable<Plane>, Cloneable{
    private int idPlane;
    private String type;
    private float capacity;

    public Plane() {

    }

    public Plane(int idPlane, String type, float capacity) {
        this.idPlane = idPlane;
        this.type = type;
        this.capacity = capacity;
    }

    public void print() {
        System.out.println("Plane: " + idPlane + ", Type: " + type + ", Capacity: " + capacity);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof  Plane)) {
            return false;
        }
        Plane p = (Plane) o;
        return (this.idPlane == p.idPlane) && (this.capacity == p.capacity) && (this.type.equals(p.type));
    }

    @Override
    public int hashCode() {
        return 31 * 31 * idPlane + 31 * this.hashCode() + (int)capacity;
    }

    @Override
    public int compareTo(Plane p) {
        if(this.idPlane == p.idPlane) {
            return 0;
        }
        else if(this.idPlane > p.idPlane) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
