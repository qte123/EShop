package cn.itcast.eshop.cart.action;

import cn.itcast.eshop.cart.entity.CartGoods;
import cn.itcast.eshop.common.action.BaseAction;
import cn.itcast.eshop.common.entity.Msg;
import cn.itcast.eshop.common.util.JSONUtil;
import cn.itcast.eshop.goods.entity.Goods;
import cn.itcast.eshop.goods.service.GoodsService;
import cn.itcast.eshop.goods.service.impl.GoodsServiceImpl;

import java.util.*;


public class CartAction extends BaseAction {

    private Goods goods;

    // 商品服务层对象
    private GoodsService goodsService;

    /**
     * 购物车对象
     * key：     商品ID
     * value：   商品数量
     */
    Map<String, Integer> cart = new HashMap<>();

    public CartAction() {
        goodsService = new GoodsServiceImpl();
    }

    /**
     * 添加购物车
     *
     * 把数据存放到cart中
     * 1.如果购物车中已存在该商品，把数量 +1
     * 2.如果不存在，则放进去数字1
     * @return 返回成功或者失败的消息
     */
    public String addCart() {
        Msg msg = new Msg();
        try {
            Integer num = cart.get(goods.getId());
            if(num != null && num > 0) {
                cart.put(goods.getId(), num + 1);
            } else {
                cart.put(goods.getId(), 1);
            }
            msg.setType(Msg.SUCCESS);
        } catch (Exception e) {
            msg.setType(Msg.FAIL);
            msg.setMsg("服务器异常");
        }
        return JSONUtil.entityToJSON(msg);
    }

    /**
     * 展示购物车
     * 1.获取购物车所有商品IDs，并转成字符串，用逗号隔开
     * 2.通过GoodsService对象获取对应的商品列表
     * 3.将Goods对象转成CartGoods对象
     * 4.封装到Msg对象并返回
     * @return
     */
    public String showCart() {
        Msg msg = new Msg();
        try {
            // 购物车中所有的商品IDs
            String ids = Arrays.toString(cart.keySet().toArray()); // [id1, id2, id3]
            List<Goods> goodsList = goodsService.getGoodsList(ids);
            List<CartGoods> cgList = new ArrayList<>();
            for (Goods g: goodsList) {
                CartGoods cg = new CartGoods();
                cg.setId(g.getId());                // id
                cg.setGoodsNum(cart.get(g.getId()));// 数量
                cg.setName(g.getName());            // 名称
                cg.setPrice(g.getPrice());          // 单价
                cgList.add(cg);
            }
            msg.setType(Msg.SUCCESS);
            msg.setObj(cgList);
        } catch (Exception e) {
            msg.setType(Msg.FAIL);
            msg.setMsg(e.getMessage());
        }
        return JSONUtil.entityToJSON(msg);
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
