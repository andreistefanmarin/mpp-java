package eu.ase.arrays;

public class ProgMainBidimensionalArrays {
    static void main(String[] args) {
        int studentNo = 2; //lines
        int lectNo = 3;    //columns

        short[][] studMarks = new short[][] { {5,5,9}, {9,10,9} };
        //line 0 -> student 0 -> {5,5,9}
        //line 1 -> student 1 -> {9,10,9}

        float[] avgMarks = new float[studentNo];

        for(int i= 0; i < studentNo; i++) {
            avgMarks[i] = 0;
            //parcurgem disciplinele
            for(int j=0; j < lectNo ; j++) {
                avgMarks[i] = avgMarks[i] + studMarks[i][j];
                //avgMarks[i] += studMarks[i][j];
            }
            avgMarks[i] = avgMarks[i] / lectNo;
            //avgMarks[i] =/ lectNo;
        }

        for(int i=0;i<studentNo; i++) {
            System.out.println("Student " + i + " avg " + avgMarks[i]);
        }
    }

}
