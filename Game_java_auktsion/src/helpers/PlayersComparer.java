package helpers;

import game.Player;

import java.util.Comparator;

public class PlayersComparer implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return o1.getScore() >= o2.getScore() ? 1: -1 ;
    }
}
