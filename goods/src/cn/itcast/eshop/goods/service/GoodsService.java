package cn.itcast.eshop.goods.service;

import cn.itcast.eshop.common.service.BaseService;
import cn.itcast.eshop.goods.entity.Goods;

import java.util.List;

public interface GoodsService extends BaseService {
    List<Goods> getGoodsList() throws Exception;
    List<Goods> getGoodsList(String ids) throws Exception;
    Goods getGoodsDetail() throws Exception;
}
