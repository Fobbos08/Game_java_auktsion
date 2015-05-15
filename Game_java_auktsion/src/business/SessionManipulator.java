package business;

import models.User;
import sun.util.calendar.BaseCalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Эмиль on 14.05.2015.
 */
public final class SessionManipulator {
    private static ArrayList<User> users;
    private static ArrayList<Long> lastTime;

    private SessionManipulator()
    {
        users = new ArrayList<User>();
        lastTime = new ArrayList<Long>();
    }

    private static void load() {
        if (users == null)
        {
            users = new ArrayList<User>();
            lastTime = new ArrayList<Long>();
        }
    }

    public static void addUser(User user)
    {
        Date date = new Date();
        load();
        users.add(user);
        lastTime.add(date.getTime());
    }

    public static User getUser(UUID id)
    {
        load();
        for(int i=0; i<users.size(); i++)
        {
            if (users.get(i).getGUID().equals(id))
            {
                return users.get(i);
            }
        }
        return null;
    }

    public static void updateUser(UUID uuid)
    {
        load();
        for(int i=0; i<users.size(); i++)
        {
            if (users.get(i).getGUID().equals(uuid))
            {
                Date date = new Date();
                lastTime.set(i, date.getTime());
            }
        }
    }

}
