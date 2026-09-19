package com.nhlstenden.appstores;

import java.util.ArrayList;
import java.util.List;

public class ResearchApp
{
    private List<AppStore> appStores;

    public ResearchApp()
    {
        this.setAppStores(new ArrayList<>());
    }

    public List<AppStore> getAppStores()
    {
        return this.appStores;
    }

    public void setAppStores(List<AppStore> appStores)
    {
        if (appStores == null)
        {
            throw new IllegalArgumentException("AppStores cannot be null.");
        }

        this.appStores = appStores;
    }

    public void addAppStore(AppStore appStore)
    {
        if (appStore == null)
        {
            throw new IllegalArgumentException("AppStore cannot be null");
        }

        if (this.getAppStores().contains(appStore))
        {
            throw new IllegalArgumentException("This appStore already exists in the system.");
        }

        this.getAppStores().add(appStore);
    }

    public int getRevenueInCentsFromApp(AppStore appStore, App app)
    {
        if (app == null || appStore == null)
        {
            throw new IllegalArgumentException("App and AppStore cannot be null.");
        }

        if (!this.getAppStores().contains(appStore))
        {
            throw new IllegalArgumentException("ResearchApp does not contain this AppStore.");
        }

        int revenueInCentsFromApp = 0;

        for (List<App> apps : appStore.getUserPurchases().values())
        {
            for (App purchagedApp : apps)
            {
                if (purchagedApp.equals(app))
                {
                    revenueInCentsFromApp += appStore.getRevenueFromSale(app);
                }
            }
        }

        return revenueInCentsFromApp;
    }

    public int getTotalRevenueInCentsOfAppStore(AppStore appStore)
    {
        if (appStore == null)
        {
            throw new IllegalArgumentException("AppStore cannot be null.");
        }

        if (!this.getAppStores().contains(appStore))
        {
            throw new IllegalArgumentException("This appStore does not exist in our system.");
        }

        int totalRevenueInCents = 0;

        for (List<App> apps : appStore.getUserPurchases().values())
        {
            for (App app : apps)
            {
                totalRevenueInCents += appStore.getRevenueFromSale(app);
            }
        }

        return totalRevenueInCents;
    }
}