package framework.model;

public class Order {
    private String order_id;
    private String order_description;
    private String order_status;
    private String last_updated_timestamp;
    private boolean special_order;
    
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getOrder_description() {
		return order_description;
	}
	public void setOrder_description(String order_description) {
		this.order_description = order_description;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getLast_updated_timestamp() {
		return last_updated_timestamp;
	}
	public void setLast_updated_timestamp(String last_updated_timestamp) {
		this.last_updated_timestamp = last_updated_timestamp;
	}
	public boolean isSpecial_order() {
		return special_order;
	}
	public void setSpecial_order(boolean special_order) {
		this.special_order = special_order;
	}
	public Order(String order_id, String order_description, String order_status, String last_updated_timestamp,
			boolean special_order) {
		super();
		this.order_id = order_id;
		this.order_description = order_description;
		this.order_status = order_status;
		this.last_updated_timestamp = last_updated_timestamp;
		this.special_order = false;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
    

}
