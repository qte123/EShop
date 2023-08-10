package cn.itcast.eshop.goods.action;

import cn.itcast.eshop.common.action.BaseAction;
import cn.itcast.eshop.common.entity.Msg;
import cn.itcast.eshop.common.util.JSONUtil;
import cn.itcast.eshop.goods.entity.Goods;
import cn.itcast.eshop.goods.service.GoodsService;
import cn.itcast.eshop.goods.service.impl.GoodsServiceImpl;

import java.util.List;

/**
 * 商品控制类
 * 处理所有商品相关的后台操作，并返回JSON格式的字符串消息
 */
public class GoodsAction extends BaseAction {
    //商品实体对象
    private Goods goods;
    private GoodsService goodsService;
    public GoodsAction(){
        goodsService=new GoodsServiceImpl();
    }
    /**
     * 获取商品列表
     * 1.获取所有的商品的对象列表
     * 2.将商品对象列表转换成字符串并返回
     * 3.异常处理
     * 4.记录日志
     * 5.响应消息到客户端
     * @return
     */
    public String getGoodsList(){
        Msg msg=new Msg();
        //List<Goods>
        //3.异常处理
        try{
            //1.获取所有的商品的对象列表
           List<Goods> goodsList=goodsService.getGoodsList();
           //2.将商品对象列表转换成字符串并返回
            msg.setObj(goodsList);
            msg.setType(Msg.SUCCESS);
            String result=JSONUtil.entityToJSON(msg);
            //4.记录日志
            log.info("获取商品列表");
            return result;
        }catch (Exception e){
            msg.setType(Msg.FAIL);
            msg.setMsg("获取商品列表失败，服务器异常！");
            //4.记录日志
            log.error("获取商品列表失败"+e.getMessage());
        }
        return JSONUtil.entityToJSON(msg);
    }

    /**
     * 获取商品详情
     * 1.根据商品ID获取商品对象
     * 2.把商品对象转换成JSON格式的字符串并返回
     * @return
     */
    public String getGoodsDetail(){
        Msg msg=new Msg();
        return JSONUtil.entityToJSON(msg);
    }
}
