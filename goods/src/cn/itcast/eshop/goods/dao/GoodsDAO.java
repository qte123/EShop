package cn.itcast.eshop.goods.dao;

import cn.itcast.eshop.common.dao.BaseDAO;
import cn.itcast.eshop.goods.entity.Goods;

import java.util.List;

public interface GoodsDAO extends BaseDAO {
    /**
     * 调用dataAccess对象获取数据列表并返回
     * @return
     * @throws Exception
     */
    public List<Goods> getEntityList() throws Exception;
}
