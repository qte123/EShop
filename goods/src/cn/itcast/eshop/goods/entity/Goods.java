package cn.itcast.eshop.goods.entity;

import cn.itcast.eshop.common.entity.Entity;

public class Goods extends Entity {
    /* 名称 */
    private String name;
    /* 单价 */
    private double price;
    /* 数量 */
    private int number;
    /* 品牌 */
    private String brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
