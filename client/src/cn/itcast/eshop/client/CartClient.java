package cn.itcast.eshop.client;

import cn.itcast.eshop.cart.action.CartAction;
import cn.itcast.eshop.cart.entity.CartGoods;
import cn.itcast.eshop.common.entity.Msg;
import cn.itcast.eshop.common.util.JSONUtil;
import cn.itcast.eshop.goods.entity.Goods;

import java.util.*;

public class CartClient extends Client {

    /**
     * 购物车控制器类
     */
    CartAction cartAction = new CartAction();

    // 购物车总金额
    private static String cartAmount;

    /**
     * 数字编号和商品ID映射表 Map（key：数字编号；value：商品ID）
     */
    private Map<Integer, Goods> codeToGoods;


    public String index() {
        // 展示购物车商品列表
        String result = showCart();
        while (true) {
            if (result.equals(LOGIN)) { // 全局操作 登录
                // 直接返回到上层Client
                return LOGIN;
            }
            if (result.equals(EXIT)) {
                return EXIT;
            }
            if (result.equals(INDEX)) {
                return INDEX;
            }

            if (result.equals(ORDER)) { // 去结算
                return ORDER;
            } else {
                System.out.println("输入错误，请重新输入");
                result = userOperate("请根据序号选择操作", "I去凑单 ", "L登录 ", "O去结算 ");
            }
        }
    }


    /**
     * 添加购物车
     * <p>
     * 把当前正在操作的商品对象添加到购物车中
     * 1.把currentGoods对象发送请求到Aciton
     * 2.接收Action响应消息
     *
     * @return
     */
    public String addCart() {
        // 1.把currentGoods对象发送请求到Aciton
        cartAction.setGoods(currentGoods);
        // 2.接收Action响应消息
        String msgJson = cartAction.addCart();
        Msg msg = JSONUtil.JSONToEntity(msgJson, Msg.class);
        if (msg.getType().equals(Msg.SUCCESS)) {
            System.out.println("添加购物车成功");
        } else {
            System.out.println(msg.getMsg());
        }
        return userOperate("请选择操作", "I继续浏览", "C购物车", "L登录");
    }

    /**
     * 展示购物车功能
     * 1.从服务器获取购物车数据List<CartGoods>
     * 2.展示商品列表
     * 3.展示购物车总金额
     *
     * @return
     */
    public String showCart() {
        System.out.println("\n【我的购物车】");
        System.out.println("编号\t商品名称\t\t单价\t\t数量");
        System.out.println("----------------------------------------------");

        String result = cartAction.showCart();
        Msg msg = JSONUtil.JSONToEntity(result, Msg.class);
        List<?> cartGoodsList = (List<?>) msg.getObj(); // 购物车商品列表
        int i = 1; // 序号
        double sum = 0; // 总金额
        codeToGoods = new HashMap<>(); // 将展示编号和数据ID关联起来

        for (Object obj : cartGoodsList) {
            String json = obj.toString();
            CartGoods cartGoods = JSONUtil.JSONToEntity(json, CartGoods.class);
            int num = cartGoods.getGoodsNum();      // 商品数量
            String name = cartGoods.getName();      // 商品名称
            double price = cartGoods.getPrice();    // 商品单价
            System.out.println(i + "." + "\t" + name + "\t\t\t"
                    + price + "\t\t" + num);
            sum += price * num; // BigDecimal
            codeToGoods.put(i++, cartGoods);
        }
        this.setCartAmount(sum + "");
        System.out.println("总金额：" + sum);

        return userOperate("请根据序号选择操作", "I去凑单 ", "L登录 ", "O去结算 ");
    }

    public String getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(String cartAmount) {
        this.cartAmount = cartAmount;
    }

    /*
     * GoodsClient:
     *   currentGoods 肯定是有值的
     *   这里的赋值操作，仅仅是对GoodsClient对象的属性currentGoods赋值
     *   CartClient里面的属性currentGoods并没有被赋值，
     * CartClient：
     *   currentGoods 没有值
     *   CartClient在创建对象的时候，currentGoods是null
     *
     * 解决方案：
     *   currentGoods 用static修饰
     *   两个对象GoodsClient和CartClient共享同一个currentGoods对象
     */
}
