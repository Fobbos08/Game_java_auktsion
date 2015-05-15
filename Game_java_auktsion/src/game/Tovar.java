package game;

import models.Goods;

/**
 * Created by Эмиль on 02.05.2015.
 */
public class Tovar {
    private int cost;
    private int currentCost;
    private Goods goods;

    public Tovar(Goods goods, int cost) {
        this.setGoods(goods);
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public int getId()
    {
        return getGoods().getId();
    }

    public int getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(int currentCost) {
        this.currentCost = currentCost;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
