package eu.ase.jcf.classes;

public class Country {
    private int id;
    private String name;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void print() {
        System.out.println("Country - id: " + this.id + ", name: " + this.name);
    }
}
