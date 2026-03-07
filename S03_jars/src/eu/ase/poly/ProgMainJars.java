package eu.ase.poly;

public class ProgMainJars {
    public static void main(String[] args) {
        try {
            Vehicle v = new Auto(1200,5);
            System.out.println(v.display());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
