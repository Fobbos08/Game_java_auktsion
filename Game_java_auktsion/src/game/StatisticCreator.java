package game;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Эмиль on 17.05.2015.
 */
public class StatisticCreator {
    public ArrayList<Player> createStatistic(ArrayList<Player> players, ArrayList<Tovar> tovars)
    {
        for(Player p: players)
        {
            ArrayList<Integer> ids = new ArrayList<Integer>();
            for (Tovar t: p.getTovars())
            {
                if(!ids.contains(t.getId()))
                {
                    ids.add(t.getId());
                }
            }
            p.setScore(ids.size()*20 - (tovars.size()-ids.size())*20);
            if (ids.size() == tovars.size()) {
                p.setScore(p.getScore() + p.getCash());
            }
        }

        return players;
    }
}
