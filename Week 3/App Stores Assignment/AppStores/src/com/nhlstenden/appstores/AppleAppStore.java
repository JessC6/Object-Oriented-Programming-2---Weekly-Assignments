package com.nhlstenden.appstores;

import java.util.Iterator;

public class AppleAppStore extends AppStore
{
    private static final int PERCENTAGE_TAKEN_FROM_SALES = 70;

    public AppleAppStore(Currency currency)
    {
        super(currency);
        removeAllNudityApps();
    }

    private void removeAllNudityApps()
    {
        Iterator<App> it = this.getApps().iterator();

        while (it.hasNext())
        {
            App app = it.next();

            if (app.isNudityIncluded())
            {
                it.remove();
            }
        }
    }

    @Override
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

        if (app.isNudityIncluded())
        {
            throw new IllegalArgumentException("This app contains nudity and therefore cannot be added to the AppleAppStore.");
        }

        this.getApps().add(app);
    }

    @Override
    public int getRevenueFromSale(App app)
    {
        return app.getPriceInCents() * PERCENTAGE_TAKEN_FROM_SALES / 100;
    }
}
