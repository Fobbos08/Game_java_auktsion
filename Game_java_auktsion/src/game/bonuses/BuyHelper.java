package game.bonuses;

/**
 * Created by Эмиль on 18.05.2015.
 */
public abstract class BuyHelper implements IBuyHelper{

    protected int cost;

    @Override
    public int getCost() {
        return cost;
    }

}
