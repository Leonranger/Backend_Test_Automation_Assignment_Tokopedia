import framework.service.OrderProfileService;
import framework.utils.exceptions.AutomationException;
import framework.utils.initializers.TestInit;
import framework.utils.reportManagement.extent.ExtentTestManager;
import org.testng.annotations.Test;


public class TC_GetAllOrdersAPI extends TestInit {

    /**
     * Test Case: TC01_GetAllOrders
     * Description: To verify that Order is able to get all Orders in system through API
     *
     * @throws AutomationException
     */
    @Test
    public void TC01_GetAllOrders() throws AutomationException {

        ExtentTestManager.startTest("Get All Orders", "To verify that Order is able to get all Orders in system through API");

        OrderProfileService
                .init()
                .getOrderProfiles();

    }

}
