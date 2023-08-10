package cn.itcast.eshop.common.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * JSON工具类
 * 处理和JSON相关的内容
 */
public class JSONUtil {
    /**
     * 把对象转化成JSON格式的字符串
     *
     * @param entity 指定对象
     * @return 返回JSON格式的字符串
     */
    public static String entityToJSON(Object entity) {
        return JSON.toJSONString(entity);
    }

    /**
     * 把对象列表转换成JSON
     *
     * @param entityList
     * @return
     */
    public static String entityListToJSON(List<?> entityList) {
        return JSON.toJSONString(entityList);
    }

    /**
     * 把JSON字符串转换成指定类型的对象
     * ? 泛型的通配符，代表的是未知的任意类型，或者说是Object
     *
     * @param json  要转换的数据
     * @param clazz 指定的类型
     * @return 返回Object对象
     */

    /**
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T JSONToEntity(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 将JSON数组转换成指定类型的对象列表
     *
     * @param json  数据
     * @param clazz 指定的类型对象
     * @param <T>   指定的类型
     * @return 对象列表
     */
    public static <T> List<T> JSONArrayToList(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }
}
