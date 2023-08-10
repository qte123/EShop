package cn.itcast.eshop.client;

import cn.itcast.eshop.common.entity.Entity;
import cn.itcast.eshop.order.entity.Order;
import cn.itcast.eshop.user.action.UserAction;

import java.util.Date;

public class OrderClient extends Client {

    /** 去支付 */
    public static final String PAY = "P";
    /** 查看详情 */
    public static final String SEE_DETAIL = "SD";

    /* 当前操作订单对象 */
    private Order currentOrder;
    //SimpleDateFormat sdf=new SimpleDateFormat("h:mm a");//12:08 PM
    /**
     * 订单管理页面
     * 生成订单：
     *  条件：
     *      1.购物车中有数据
     *      2.已登录
     *          前提：如果用户已登录，系统要记住用户的登录状态
     *      3.录入收货人信息
     *  结果：
     *      封装Order对象
     *      订单状态为：待支付
     *      清空购物车
     *
     * @return
     */
    public String index() {
        // TODO 登录验证
        UserAction userAction  = new UserAction();
        if(userAction.getLoginUser() != null) {
            String result = generateOrder();
            while(true) {
                if(result.equals(PAY)) { // pay 去支付
                    result = pay();
                } else if(result.equals(SEE_DETAIL)) { // 查看订单详情
                    result = showOrder();
                } else {
                    return result;
                }
            }
        } else {
            return LOGIN;
        }
    }

    /**
     * 订单支付
     * 1.在控制台录入一个数值，若该数值大于等于订单总金额，则支付成功，否则余额不足
     * @return
     */
    public String pay() { // PAY
        String opr = userOperate("请输入支付金额", "SD查看订单详情 ", "I回首页；");
        while(true) {
            try {
                Double num = Double.parseDouble(opr); // 将金额转成double类型
                Double sum = Double.parseDouble(currentOrder.getAmount());
                if(num >= sum) {
                    System.out.println("支付成功！");
                    currentOrder.setState(Order.WAITING_SEND); // 待发货
                    return userOperate("请选择操作",  "SD查看订单详情 ", "I回首页 ");
                } else {
                    System.out.println("余额不足，请重新支付。");
                    opr = userOperate("请输入支付金额");
                }
            } catch(Exception e) {
                return opr; // 未知操作，返回上层
            }
        }
    }

    /**
     * 生成订单
     * 	1.填写订单信息
     * 	2.封装成Order对象
     * 	3.生成订单 --> 待支付状态
     */
    public String generateOrder() {
        CartClient cartClient = new CartClient();
        /* 填写订单信息 */
        System.out.print("------------- 正在生成订单 ------------\n");

        System.out.println("请输入收货人：");
        String consignee = sc.nextLine();
        System.out.println("请输入收货地址：");
        String consigneeAddress = sc.nextLine();
        System.out.println("请输入联系电话：");
        String phone = sc.nextLine();

        /* 生成订单 --> 待支付状态 */
        currentOrder = new Order();
        currentOrder.setId(Entity.getUUID());               // 订单ID
        currentOrder.setCreateTime(sdf.format(new Date())); // 订单生成日期
        currentOrder.setAmount(cartClient.getCartAmount()); // 订单总金额
        currentOrder.setConsigneeAddress(consigneeAddress); // 收货地址
        currentOrder.setConsignee(consignee);               // 收货人
        currentOrder.setPhone(phone);                       // 收货人手机号
        String serialNumber = getSerialNumber();
        currentOrder.setSerialNumber(serialNumber);         // 订单序列号
        currentOrder.setState(Order.WAITING_PAY);           // 待支付

        System.out.println("------------- 订单已生成 -------------\n订单号：\t" + serialNumber
                + " \n总金额：\t" + cartClient.getCartAmount() + "\n状态：\t待支付。");

        return userOperate("请选择操作", "P去支付", "SD查看订单详情 ", "I回首页");
    }

    /**
     * 查看订单详情
     * 1.展示订单信息
     *      将订单中商品展示出来
     * 2.根据订单状态，显示正确的描述信息
     * @return
     */
    public String showOrder() {
        System.out.println("【我的订单】");
        System.out.println("----------------------------------------------");
        String createTime = currentOrder.getCreateTime();
        String amount = currentOrder.getAmount();
        String consigneeAddress = currentOrder.getConsigneeAddress();
        String consignee = currentOrder.getConsignee();
        String phone = currentOrder.getPhone();
        String serialNumber = currentOrder.getSerialNumber();
        String state = currentOrder.getState(); // 订单状态

        System.out.println("订单号：" + serialNumber + "\t\t下单时间：" + createTime
                + "\t\t总金额：" + amount + "\t\t状态："+ getStateDescribtion(state));
        // TODO other infos of order

        String oprs = "I回首页 ";
        if(state.equals(Order.WAITING_PAY)) { // 如果订单未支付，则提供支付功能
            oprs += " P去支付 ";
        }

        return userOperate("请选择操作", oprs);
    }

    /**
     * 获取订单编号，根据当前系统日期生成
     * @return
     */
    public String getSerialNumber() {
        String serialNumber = new Date().getTime() + "";
        int cartHash = this.hashCode();
        return serialNumber + cartHash;
    }

    /**
     * 根据订单状态常量，获取对应的描述
     * @param state 订单状态常量
     * @return
     */
    public String getStateDescribtion(String state) {
        switch(state) {
            case Order.CANCELLED:
                return "已取消";
            case Order.COMPLETED:
                return "已完成";
            case Order.WAITING_PAY:
                return "待支付";
            case Order.WAITING_RECEIVE:
                return "待收货";
            case Order.WAITING_SEND:
                return "待发货";
            default:
                return "";
        }
    }
}
