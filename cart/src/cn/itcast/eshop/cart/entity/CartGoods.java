package cn.itcast.eshop.cart.entity;

import cn.itcast.eshop.goods.entity.Goods;

public class CartGoods extends Goods {
    //商品数量
    private int goodsNum;

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }
}
