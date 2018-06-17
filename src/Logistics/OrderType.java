package Logistics;

public enum OrderType {
	
	Order(1),
	PreOrder(2),
	MembershipOrder(3);
	
    private final int order_type;

    private OrderType(int order_type) {
        this.order_type = order_type;
    }
    
    public int getOrderType() {
        return this.order_type;
    }
}
