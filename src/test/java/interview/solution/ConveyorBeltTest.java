package interview.solution;

import org.junit.Assert;
import org.junit.Test;

public class ConveyorBeltTest {

    private ConveyorBelt belt = new ConveyorBelt();

    /**
     * assumption is to have bolts exactly double of machine
     */
    @Test
    public void verifyConveyorBelt(){
        int totalProducts = belt.totalProducts(3, 6, 10);
        Assert.assertEquals(3, totalProducts);
        Assert.assertEquals(10, belt.getTotalTimeElapsed());
        Assert.assertEquals(180, belt.timeConsumedToClearAllProductsInSec(totalProducts, 60));

        totalProducts = belt.totalProducts(6, 12, 10);
        Assert.assertEquals(6, totalProducts);
        Assert.assertEquals(20, belt.getTotalTimeElapsed());
        Assert.assertEquals(360, belt.timeConsumedToClearAllProductsInSec(totalProducts, 60));

    }
}
