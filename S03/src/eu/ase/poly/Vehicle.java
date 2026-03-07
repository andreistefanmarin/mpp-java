package eu.ase.poly;

public class Vehicle implements Movement {
    private int weight;

    public Vehicle() {

    }

    Vehicle(int weight) {
        this.weight = weight;
    }

    public void startEngine() {
        System.out.println("Vehicle::startEngine()");
    }

    public void stopEngine() {
        System.out.println("Vehicle::stopEngine");
    }

    public int getWeight() {
        return this.weight;
    }

    public String display() {
        return new String("Vehicle - weight: " + this.weight);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Vehicle) super.clone();
    }

} //end of Vehicle class
