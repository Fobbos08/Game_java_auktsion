package game.bonuses;

/**
 * Created by Эмиль on 18.05.2015.
 */
public final class BonusFactory {
    public static IBuyHelper createHelper(Bonus bonus)
    {
        switch (bonus)
        {
            case Joker: return new Joker();
            case Whiskey: return new Whiskey();
            default: return null;
        }
    }
}
