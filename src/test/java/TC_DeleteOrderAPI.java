import framework.model.Order;
import framework.service.OrderProfileService;
import framework.utils.common.Randomizer;
import framework.utils.exceptions.AutomationException;
import framework.utils.globalConstants.HttpStatus;
import framework.utils.initializers.TestInit;
import framework.utils.reportManagement.extent.ExtentTestManager;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class TC_DeleteOrderAPI extends TestInit {

    /**
     * Test Case: TC09_DeleteOrderProfile
     * Test Type: Positive
     * Description: To verify that Order is able to delete existing Order in system through API
     *
     * @throws AutomationException
     */
    @Test
    public void TC09_DeleteOrderProfile() throws AutomationException {

        ExtentTestManager.startTest("Delete Order Profile", "To verify that Order is able to delete existing Order in system through API");

        Order createdOrder = (Order) OrderProfileService
                .init()
                .addOrderProfiles(new Order(Randomizer.randomNumberWithoutZero(5)))
                .getResponse();

        OrderProfileService
                .init()
                .deleteOrderProfiles(createdOrder.getOrderid());

    }

    /**
     * Test Case: TC10_DeleteOrderProfile
     * Test Type: Negative
     * Description: To verify that Order is able to delete Order in system if Orderid provided does not exist through API
     *
     * @throws AutomationException
     */
    @Test
    public void TC10_DeleteOrderProfile() throws AutomationException {

        ExtentTestManager.startTest("Delete Order Profile", "To verify that Order is able to delete Order in system if Orderid provided does not exist through API");

        List<Order> OrderList = (List<Order>) OrderProfileService
                .init()
                .getOrderProfiles()
                .getResponse();

        Order OrderToDelete = OrderList.get(1);
        OrderToDelete.setOrderid("SampleInvalidOrderID");

        OrderProfileService
                .init()
                .isNegativeTest(HttpStatus.INTERNAL_SERVER_ERROR)
                .modifyOrderProfiles(OrderToDelete);

    }

}
