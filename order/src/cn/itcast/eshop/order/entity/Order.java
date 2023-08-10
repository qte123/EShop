package cn.itcast.eshop.order.entity;


import cn.itcast.eshop.common.entity.Entity;

public class Order extends Entity {

    /* 订单状态 待支付 */
    public static final String WAITING_PAY = "WAITING_PAY";
    /* 订单状态 待发货 */
    public static final String WAITING_SEND = "WAITING_SEND";
    /* 订单状态 待收货 */
    public static final String WAITING_RECEIVE = "WAITING_RECEIVE";
    /* 订单状态 已完成 */
    public static final String COMPLETED = "COMPLETED";
    /* 订单状态 已取消 */
    public static final String CANCELLED = "CANCELLED";

    /* 订单序列号 根据当前系统日期生成 */
    private String serialNumber;
    /* 收货人 */
    private String consignee;
    /* 收货地址 */
    private String consigneeAddress;
    /* 联系电话 */
    private String phone;
    /* 订单金额 */
    private String amount;
    /* 订单状态：
     * 	待付款：订单生成之后，立即进入待付款状态
     * 		支付完成之后，进入支付完成状态，之后需要商家发货
     * 	待收货：商家发货之后，进入发货状态
     * 	已完成：客户查收商品，进入已完成状态
     * 	已取消：订单生成后，任意环节都有可能取消该订单（也可以超时后，系统自动取消）
     */
    private String state;

    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getConsignee() {
        return consignee;
    }
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }
    public String getConsigneeAddress() {
        return consigneeAddress;
    }
    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
