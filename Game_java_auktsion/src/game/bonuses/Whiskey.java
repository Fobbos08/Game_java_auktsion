package game.bonuses;

import game.bonuses.IBuyHelper;

public class Whiskey extends BuyHelper {

    public Whiskey()
    {
        this.cost = 5;
    }

    @Override
    public int getNewCost(int cost) {
        return cost / 2;
    }
}
