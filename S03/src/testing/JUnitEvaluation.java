package testing;

import eu.ase.poly.Auto;
import org.junit.Assert;
import org.junit.Test;

public class JUnitEvaluation {
    @Test
    public void testAutoSetDoorsNoLt0 () throws Exception {
        Auto auto = new Auto();
        try {
            auto.setDoorsNo(-5);
            Assert.fail("setDoorsNo accepts negative values!");
        } catch (Exception e) {
            //OK!
        }
    }

    @Test
    public void testSetDoorsNo() {
        Auto auto = new Auto();
        try {
            auto.setDoorsNo(4);
            Assert.assertEquals(4,auto.getDoorsNo()); //if 5, fails
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //jar = java archive

} //end of class
