package com.nhlstenden.appstores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppStore
{
    private static final int VIOLENCE_AGE_RESTRICTION = 16;
    private static final int NUDITY_AGE_RESTRICTION = 18;

    private Currency currency;
    private List<App> apps;
    private List<User> users;
    private Map<User, List<App>> userPurchases;

    public AppStore(Currency currency)
    {
        this.setCurrency(currency);
        this.setApps(new ArrayList<>());
        this.setUsers(new ArrayList<>());
        this.setUserPurchases(new HashMap<>());
    }

    public Currency getCurrency()
    {
        return this.currency;
    }

    public void setCurrency(Currency currency)
    {
        if (currency == null)
        {
            throw new IllegalArgumentException("Currency cannot be null.");
        }

        this.currency = currency;
    }

    public List<App> getApps()
    {
        return this.apps;
    }

    public void setApps(List<App> apps)
    {
        if (apps == null)
        {
            throw new IllegalArgumentException("Apps cannot be null.");
        }

        this.apps = apps;
    }

    public void addApp(App app)
    {
        if (app == null)
        {
            throw new IllegalArgumentException("App cannot be null.");
        }

        for (App existingApp : this.getApps())
        {
            if (existingApp.getName().equalsIgnoreCase(app.getName()))
            {
                throw new IllegalArgumentException("This app already exists in the system.");
            }
        }

        this.getApps().add(app);
    }

    public List<User> getUsers()
    {
        return this.users;
    }

    public void setUsers(List<User> users)
    {
        if (users == null)
        {
            throw new IllegalArgumentException("Users cannot be null.");
        }

        this.users = users;
    }

    public void addUser(User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("User cannot be null.");
        }

        for (User existingUser : this.getUsers())
        {
            if (existingUser.getName().equalsIgnoreCase(user.getName()) && existingUser.getEmail().equalsIgnoreCase(user.getEmail()))
            {
                throw new IllegalArgumentException("This user already exists in the system.");
            }
        }

        this.getUsers().add(user);
    }

    public Map<User, List<App>> getUserPurchases()
    {
        return this.userPurchases;
    }

    public void setUserPurchases(Map<User, List<App>> userPurchases)
    {
        if (userPurchases == null)
        {
            throw new IllegalArgumentException("UserPurchase cannot be null.");
        }

        this.userPurchases = userPurchases;
    }

    private boolean isUserOfAgeForApp(User user, App app)
    {
        if (app.isNudityIncluded() && user.getAge() < NUDITY_AGE_RESTRICTION)
        {
            return false;
        }

        if (app.isViolenceIncluded() && user.getAge() < VIOLENCE_AGE_RESTRICTION)
        {
            return false;
        }

        return true;
    }

    public void addUserPurchase(User user, App app) throws DownloadNotAllowedException
    {
        if (user == null || app == null)
        {
            throw new IllegalArgumentException("User and app cannot be null.");
        }

        if (!this.getUsers().contains(user))
        {
            throw new IllegalArgumentException("This user does not exist in our system.");
        }

        if (!this.getApps().contains(app))
        {
            throw new IllegalArgumentException("This app does not exist in our system.");
        }

        if (!this.isUserOfAgeForApp(user, app))
        {
            throw new DownloadNotAllowedException("This user does not meet the requirements for the purchase of this app.");
        }

        if (this.getUserPurchases().containsKey(user))
        {
            this.getUserPurchases().get(user).add(app);
        }
        else
        {
            this.getUserPurchases().put(user, new ArrayList<>(List.of(app)));
        }
    }

    public int getRevenueFromSale(App app)
    {
        return app.getPriceInCents();
    }
}