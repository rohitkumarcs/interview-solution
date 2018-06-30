package interview.solution;

import org.junit.Assert;
import org.junit.Test;

public class ConveyorBeltTest {

    private ConveyorBelt belt = new ConveyorBelt();

    @Test
    public void verifyConveyorBelt(){
        int totalProducts = belt.totalProducts(3, 6);
        Assert.assertEquals(3, totalProducts);
        Assert.assertEquals(180, belt.timeConsumedToClearAllProductsInSec(totalProducts, 60));

        totalProducts = belt.totalProducts(6, 12);
        Assert.assertEquals(6, totalProducts);
        Assert.assertEquals(360, belt.timeConsumedToClearAllProductsInSec(totalProducts, 60));

    }
}
