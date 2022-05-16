import framework.model.Order;
import framework.service.OrderProfileService;
import framework.utils.common.Randomizer;
import framework.utils.exceptions.AutomationException;
import framework.utils.globalConstants.HttpStatus;
import framework.utils.initializers.TestInit;
import framework.utils.reportManagement.extent.ExtentTestManager;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class TC_AddOrderAPI extends TestInit {

    /**
     * Test Case: TC02_AddOrderProfile
     * Test Type: Positive
     * Description: To verify that Order is able to add new Order in system through API
     *
     * @throws AutomationException
     */
    @Test
    public void TC02_AddOrderProfile() throws AutomationException {

        ExtentTestManager.startTest("Add Order Profile", "To verify that Order is able to add new Orders in system through API");

        Order OrderToCreate = new Order(Randomizer.randomNumberWithoutZero(5));
        Order createdOrder = (Order) OrderProfileService
                .init()
                .addOrderProfiles(OrderToCreate)
                .getResponse();

        assertThat(createdOrder).usingRecursiveComparison().ignoringFields("id").isEqualTo(OrderToCreate);

    }

    /**
     * Test Case: TC03_AddOrderProfileExistingOrderId
     * Test Type: Negative
     * Description: To verify that Order is not able to add new Order in system through API if provided OrderID already exists in system
     *
     * @throws AutomationException
     */
    @Test
    public void TC03_AddOrderProfileExistingOrderId() throws AutomationException {

        ExtentTestManager.startTest("Add Order Profile", "To verify that Order is not able to add new Order in system through API if provided OrderID already exists in system");

        Order OrderToCreate = new Order(Randomizer.randomNumberWithoutZero(5));
        OrderToCreate.setOrderid("KRISHAN001");

        OrderProfileService
                .init()
                .isNegativeTest(HttpStatus.INTERNAL_SERVER_ERROR)
                .addOrderProfiles(OrderToCreate);

    }

    /**
     * Test Case: TC04_AddOrderProfileAlphabeticStatus
     * Test Type: Negative
     * Description: To verify that Order is not able to add new Order in system through API if provided status is not numeric
     *
     * @throws AutomationException
     */
    @Test
    public void TC04_AddOrderProfileAlphabeticStatus() throws AutomationException {

        ExtentTestManager.startTest("Add Order Profile", "To verify that Order is not able to add new Order in system through API if provided status is not numeric");

        Order OrderToCreate = new Order(Randomizer.randomNumberWithoutZero(5));
        OrderToCreate.setStatus("STATUS");

        OrderProfileService
                .init()
                .isNegativeTest(HttpStatus.BAD_REQUEST)
                .addOrderProfiles(OrderToCreate);

    }

}
