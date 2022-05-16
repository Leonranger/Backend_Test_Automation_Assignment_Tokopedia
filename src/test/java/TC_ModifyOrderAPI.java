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

public class TC_ModifyOrderAPI extends TestInit {

    /**
     * Test Case: TC07_ModifyOrderProfile
     * Test Type: Positive
     * Description: To verify that Order is able to modify existing Order in system through API
     *
     * @throws AutomationException
     */
    @Test
    public void TC07_ModifyOrderProfile() throws AutomationException {

        ExtentTestManager.startTest("Modify Order Profile", "To verify that Order is able to modify existing Order in system through API");

        List<Order> OrderList = (List<Order>) OrderProfileService
                .init()
                .getOrderProfiles()
                .getResponse();

        Order OrderToModify = OrderList.get(1);
        OrderToModify.setFirstname("ModifiedFirstName" + Randomizer.randomNumeric(5));

        Order createdOrder = (Order) OrderProfileService
                .init()
                .modifyOrderProfiles(OrderToModify)
                .getResponse();

        assertThat(createdOrder).usingRecursiveComparison().isEqualTo(OrderToModify);

    }

    /**
     * Test Case: TC08_ModifyOrderProfile
     * Test Type: Negative
     * Description: To verify that Order is able to modify Order in system if Orderid provided does not exist through API
     *
     * @throws AutomationException
     */
    @Test
    public void TC08_ModifyOrderProfile() throws AutomationException {

        ExtentTestManager.startTest("Modify Order Profile", "To verify that Order is able to modify Order in system if Orderid provided does not exist through API");

        List<Order> OrderList = (List<Order>) OrderProfileService
                .init()
                .getOrderProfiles()
                .getResponse();

        Order OrderToModify = OrderList.get(1);
        OrderToModify.setOrderid("SampleInvalidOrderID");

        OrderProfileService
                .init()
                .isNegativeTest(HttpStatus.INTERNAL_SERVER_ERROR)
                .modifyOrderProfiles(OrderToModify);

    }

}
