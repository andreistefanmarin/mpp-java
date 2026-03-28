package eu.ase.io.serialization;

public class MyResource implements AutoCloseable {
    public void doSomething() {
        System.out.println("Working...");
    }
    @Override
    public void close()  {
        System.out.println("resource closed");
    }
}
