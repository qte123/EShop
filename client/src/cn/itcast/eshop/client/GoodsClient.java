package cn.itcast.eshop.client;

import cn.itcast.eshop.common.entity.Msg;
import cn.itcast.eshop.common.util.JSONUtil;
import cn.itcast.eshop.goods.action.GoodsAction;
import cn.itcast.eshop.goods.entity.Goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsClient extends Client {
    Map<String, Goods> codeToGoods = new HashMap<>();

    /**
     * 商品管理首页
     * 商品页面跳转控制器
     * 本模块的操作由此方法控制跳转
     * 其他模块的操作返回上层，由Client控制跳转
     *
     * @return 返回公共操作
     */
    public String index() {
        showGoodsList(); //展示商品列表
        String result = userOperate("请根据序号查看商品详情", "L登录；", "I首页");
        while (true) {
            if (result.equals(LOGIN)) {
                return LOGIN;
            }
            if (result.equals(EXIT)) {
                return EXIT;
            }
            if (result.equals(INDEX)) {
                return INDEX;
            }
            Goods goods = codeToGoods.get(result);//get方法返回结果，数据或null
            if (goods != null) {//查看详情
                currentGoods = goods;
                showGoodsDetail();
                result = userOperate("输入A加入购物车", "L登录；", "I首页");
            } else if (result.equals(ADD)) {//加入购物车
                return ADD;
            } else {
                System.out.println("输入错误，请重新输入！");
                result = userOperate("请根据序号查看商品详情", "L登录；", "I首页");
            }
        }
    }

    /**
     * 展示商品列表
     * 1.向后台发送请求，获取商品数据
     * 2.解析响应消息字符串
     * 3.展示商品列表
     */
    public void showGoodsList() {
        //1.向后台发送请求，获取商品数据
        GoodsAction goodsAction = new GoodsAction();
        String msgJSON = goodsAction.getGoodsList();
        //2.解析响应消息字符串
        Msg msg = JSONUtil.JSONToEntity(msgJSON, Msg.class);
        Object obj = msg.getObj();//数据就存放在obj对象里 [{},{},{}]
        List<?> goodListJson = (List<?>) obj;
        System.out.println("【商品列表】");
        System.out.println("编号\t\t商品名称\t\t单价\t\t库存"); // \t制表符 ，tab \n换行
        System.out.println("--------------------------------------");
        for (int i = 0; i < goodListJson.size(); i++) {
            String goodsJSON = goodListJson.get(i).toString();//这里是goods对象的json字符串
            Goods goods = JSONUtil.JSONToEntity(goodsJSON, Goods.class);
            //3.展示商品列表
            String name = goods.getName();//商品的名称
            String number = goods.getNumber() + "";//库存
            String price = goods.getPrice() + "";//单价
            System.out.println((i + 1) + ".\t\t" + name + "\t\t\t" + price + "\t\t" + number);
            codeToGoods.put((i + 1) + "", goods);
        }
    }

    /**
     * 查看商品详情
     * 1.通过数据ID获取数据，然后进行展示
     * 2.在展示商品的时候，把商品列表存储起来，然后，当选择商品的编号，就把对应的商品展示出来
     * Map<String,Goods> : key->编号 ，value->Goods对象
     */
    public void showGoodsDetail() {
        System.out.println("【商品详情】");
        System.out.println("编号\t\t商品名称\t\t单价\t\t库存\t\t品牌"); // \t制表符 ，tab \n换行
        System.out.println("--------------------------------------");
        String name = currentGoods.getName();//商品的名称
        String price = currentGoods.getPrice() + "";//单价
        String number = currentGoods.getNumber() + "";//库存
        String brand = currentGoods.getBrand();//品牌
        //3.展示商品列表
        System.out.println(1 + ".\t\t" + name + "\t\t\t" + price + "\t\t" + number + "\t\t" + brand);
    }
}
