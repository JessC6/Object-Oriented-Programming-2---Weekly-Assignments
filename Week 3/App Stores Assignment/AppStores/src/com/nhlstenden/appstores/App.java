package com.nhlstenden.appstores;

public class App
{
    private String name;
    private int priceInCents;
    private boolean isViolenceIncluded;
    private boolean isNudityIncluded;

    public App(String name, int priceInCents, boolean isViolenceIncluded, boolean isNudityIncluded)
    {
        this.setName(name);
        this.setPriceInCents(priceInCents);
        this.setViolenceIncluded(isViolenceIncluded);
        this.setNudityIncluded(isNudityIncluded);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        this.name = name;
    }

    public int getPriceInCents()
    {
        return this.priceInCents;
    }

    public void setPriceInCents(int priceInCents)
    {
        if (priceInCents < 0)
        {
            throw new IllegalArgumentException("PriceInCents cannot be negative.");
        }

        this.priceInCents = priceInCents;
    }

    public boolean isViolenceIncluded()
    {
        return this.isViolenceIncluded;
    }

    public void setViolenceIncluded(boolean violenceIncluded)
    {
        this.isViolenceIncluded = violenceIncluded;
    }

    public boolean isNudityIncluded()
    {
        return this.isNudityIncluded;
    }

    public void setNudityIncluded(boolean nudityIncluded)
    {
        this.isNudityIncluded = nudityIncluded;
    }
}