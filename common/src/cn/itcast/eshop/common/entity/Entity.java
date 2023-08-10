package cn.itcast.eshop.common.entity;

import java.util.UUID;

/**
 * 实体类
 * 所有模块实体类的父类
 * 职责：封装数据
 */
public class Entity {
    /* 数据唯一标识 */
    private String id;

    /* 创建时间 */
    private String createTime;

    /* 删除时间 */
    private String deleteTime;

    /* 删除状态 0已删除，1正常。默认值1 */
    private String idDel = "1";


    /**
     * 通用唯一识别码，是由一组32位数的16进制数字所构成
     * 总共有 2^ 128个数字，大概是3.4 * 10^38个
     * 特点：不会重复
     * 举例：550e8400-e29b-41d4-a716-446655440000
     * @return 返回一个UUID字符串，不含连接符-
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getIdDel() {
        return idDel;
    }

    public void setIdDel(String idDel) {
        this.idDel = idDel;
    }
}
