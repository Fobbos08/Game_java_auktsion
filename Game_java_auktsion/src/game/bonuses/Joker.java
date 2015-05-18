package game.bonuses;

import game.bonuses.IBuyHelper;

public class Joker extends BuyHelper {
    @Override
    public int getNewCost(int cost) {
        return 0;
    }
}
