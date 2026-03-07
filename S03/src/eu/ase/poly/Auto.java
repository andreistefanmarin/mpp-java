package eu.ase.poly;
//can only extend one class in Java
public class Auto extends Vehicle {
    private int doorsNo;

    public Auto() {
        super();
    }
    public Auto(int weight, int doorsNo) throws Exception{
        super(weight); //super class is the initial class (Vehicle)
        if(doorsNo < 0) {
            throw new Exception("Doors number can not be negative");
        }
        this.doorsNo = doorsNo;
    }

    public int getDoorsNo() {
        return this.doorsNo;
    }

    public void setDoorsNo(int doorsNo) throws Exception {
        if(doorsNo < 0) {
            throw new Exception("Doors number can not be negative");
        }
        this.doorsNo = doorsNo;
    }

    @Override
    public String display() {
        return new String("Auto - weight: " + this.getWeight() + ", doorsNo: " + this.getDoorsNo());
    }

} //end of Auto class
