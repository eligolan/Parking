package Logistics;

public enum OrderType {
	
	Order(1),
	PreOrder(2),
	MembershipOrder(3);
	private final int order_type;
    
	/**
	 * sets order type
	 * @param order_type integer 
	 */
    private OrderType(int order_type) {
        this.order_type = order_type;
    }
     /**
      * gets order type
      * @return integer
      */
    public int getOrderType() {
        return this.order_type;
    }
}
