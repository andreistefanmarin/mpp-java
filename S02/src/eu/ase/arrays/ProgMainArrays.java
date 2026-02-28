package eu.ase.arrays;

public class ProgMainArrays {
    public static void main(String[] args) {
        int[] v;
        v = new int[5];
        int[] n = {10,20,30};
        //0 -> v.length - 1
        //System.out.println(v[5]);
        for(int i=0;i<v.length - 1; i++) {
            System.out.println("index: " + i + " val: " + v[i]);
        }
        v[2] = 50;
        v[3] = 40;
        for(int x: v) {
            System.out.println(x);
        }
        int[] v2 = v;
        v2 = java.util.Arrays.copyOf(v, v.length);
        System.arraycopy(v,0,v2,0,v.length);
        modifyArray(v);
        System.out.println("v dupa modifyArray: " + v);
    }

    public static void modifyArray(int[] n) {
        n[3] = 999;
    }
}
