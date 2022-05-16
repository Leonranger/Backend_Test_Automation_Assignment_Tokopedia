import framework.service.OrderProfileService;
import framework.utils.exceptions.AutomationException;
import framework.utils.globalConstants.HttpStatus;
import framework.utils.initializers.TestInit;
import framework.utils.reportManagement.extent.ExtentTestManager;
import org.testng.annotations.Test;

public class TC_GetOrderByID extends TestInit {

    /**
     * Test Case: TC05_GetOrderByID
     * Description: To verify that Order is able to get Order details through API
     *
     * @throws AutomationException
     */
    @Test
    public void TC05_GetOrderByID() throws AutomationException {

        ExtentTestManager.startTest("Get Order By ID", "To verify that Order is able to get Order details through API if Orderid is provided");

        OrderProfileService
                .init()
                .getOrderProfileByID("KRISHAN001");

    }

    /**
     * Test Case: TC06_GetOrderByIDInvalidOrderID
     * Test Type: Negative
     * Description: To verify that Order is not able to get Order details through API if Orderid provided does not exist
     *
     * @throws AutomationException
     */
    @Test
    public void TC06_GetOrderByIDInvalidOrderID() throws AutomationException {

        ExtentTestManager.startTest("Get Order By ID", "To verify that Order is not able to get Order details through API if Orderid provided does not exist");

        OrderProfileService
                .init()
                .isNegativeTest(HttpStatus.INTERNAL_SERVER_ERROR)
                .getOrderProfileByID("INVALIDTESTID");

    }

}
