package framework.service;

import com.fasterxml.jackson.core.type.TypeReference;
import framework.model.Order;
import framework.model.error.ValidationError;
import framework.utils.common.RestUtil;
import framework.utils.exceptions.AutomationException;
import framework.utils.globalConstants.APIEndPoint;
import framework.utils.globalConstants.HttpStatus;
import framework.utils.reportManagement.extent.ExtentTestManager;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class OrderProfileService {

    private final Logger _logger = LogManager.getLogger(OrderProfileService.class);

    private Order requestPayload;
    private Object responsePayload;
    private boolean isNegativeTest = false;
    private HttpStatus httpStatus = HttpStatus.OK;
    private ContentType responseContentType = ContentType.JSON;

    public static OrderProfileService init() {
        return new OrderProfileService();
    }

    public OrderProfileService isNegativeTest(HttpStatus httpStatus) {
        this.responseContentType = ContentType.JSON;
        this.isNegativeTest = true;
        this.httpStatus = httpStatus;
        return this;
    }

    public OrderProfileService getOrderProfiles() throws AutomationException {

        ExtentTestManager.step(_logger, "Get Order Profiles");

        RestUtil restInstance =
                RestUtil.init()
                        .path(APIEndPoint.Order_PROFILES)
                        .expectedStatusCode(httpStatus)
                        .expectedResponseContentType(responseContentType)
                        .get();

        if (!isNegativeTest) {
            responsePayload = restInstance.responseToPojo(new TypeReference<List<Order>>() {});
        } else {
            responsePayload = restInstance.responseToPojo(ValidationError.class);
        }

        return this;
    }

    public OrderProfileService getOrderProfileByID(String order_id) throws AutomationException {

        ExtentTestManager.step(_logger, "Get Order By ID");

        RestUtil restInstance =
                RestUtil.init()
                        .path(APIEndPoint.Order_PROFILES + "{order_id}")
                        .pathParam("order_id", order_id)
                        .expectedStatusCode(httpStatus)
                        .expectedResponseContentType(responseContentType)
                        .get();

        if (!isNegativeTest) {
            responsePayload = restInstance.responseToPojo(new TypeReference<List<Order>>() {});
        } else {
            responsePayload = restInstance.responseToPojo(ValidationError.class);
        }

        return this;
    }

    public OrderProfileService addOrderProfiles(Order Order) throws AutomationException {

        ExtentTestManager.step(_logger, "Add Order Profile");
        requestPayload = Order;

        RestUtil restInstance =
                RestUtil.init()
                        .path(APIEndPoint.Order_PROFILES + "add")
                        .contentType(ContentType.JSON)
                        .body(Order)
                        .expectedStatusCode(httpStatus)
                        .expectedResponseContentType(responseContentType)
                        .put();

        if (!isNegativeTest) {
            responsePayload = restInstance.responseToPojo(Order.class);
        } else {
            responsePayload = restInstance.responseToPojo(ValidationError.class);
        }

        return this;
    }

    public OrderProfileService modifyOrderProfiles(Order Order) throws AutomationException {

        ExtentTestManager.step(_logger, "Modify Order Profile");
        requestPayload = Order;

        RestUtil restInstance =
                RestUtil.init()
                        .path(APIEndPoint.Order_PROFILES + "update")
                        .contentType(ContentType.JSON)
                        .body(Order)
                        .expectedStatusCode(httpStatus)
                        .expectedResponseContentType(responseContentType)
                        .post();

        if (!isNegativeTest) {
            responsePayload = restInstance.responseToPojo(Order.class);
        } else {
            responsePayload = restInstance.responseToPojo(ValidationError.class);
        }

        return this;
    }

    public OrderProfileService deleteOrderProfiles(String order_id) throws AutomationException {

        ExtentTestManager.step(_logger, "Delete Order Profile");

        RestUtil restInstance =
                RestUtil.init()
                        .path(APIEndPoint.Order_PROFILES + "delete/{order_id}")
                        .pathParam("order_id", order_id)
                        .expectedStatusCode(httpStatus)
                        .expectedResponseContentType(responseContentType)
                        .delete();

        if (!isNegativeTest) {
            responsePayload = restInstance.responseToPojo(Order.class);
        } else {
            responsePayload = restInstance.responseToPojo(ValidationError.class);
        }

        return this;
    }

    public Object getResponse() {
        return responsePayload;
    }

}
