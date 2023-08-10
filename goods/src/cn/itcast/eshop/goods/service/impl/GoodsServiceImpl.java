package cn.itcast.eshop.goods.service.impl;

import cn.itcast.eshop.goods.dao.GoodsDAO;
import cn.itcast.eshop.goods.dao.impl.GoodsDAOImpl;
import cn.itcast.eshop.goods.entity.Goods;
import cn.itcast.eshop.goods.service.GoodsService;

import java.util.ArrayList;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    private GoodsDAO goodsDAO;

    public GoodsServiceImpl() {
        goodsDAO = new GoodsDAOImpl();
    }

    /**
     * 获取商品列表
     * 1.调用GoodsDAO对象的方法获取数据并返回
     *
     * @return 商品列表
     * @throws Exception
     */
    @Override
    public List<Goods> getGoodsList() throws Exception {
        return goodsDAO.getEntityList();
    }

    /**
     * 根据商品ID获取商品对象列表
     *
     * @param ids 商品id，多个用逗号隔开
     * @return 返回商品列表
     * @throws Exception
     */
    @Override
    public List<Goods> getGoodsList(String ids) throws Exception {
        List<Goods> goodsList = new ArrayList<>();
        List<Goods> allGoodsList = getGoodsList();
        for (Goods goods : allGoodsList) {
            String id = goods.getId();
            if (ids.contains(id)) {
                goodsList.add(goods);
            }
        }
        return goodsList;
    }

    /**
     * 获取商品详情
     * 1.根据商品ID获取商品对象
     * 2.若该对象存在则返回，否则返回null
     *
     * @return Goods对象，或null
     * @throws Exception
     */
    @Override
    public Goods getGoodsDetail() throws Exception {
        return null;
    }
}
