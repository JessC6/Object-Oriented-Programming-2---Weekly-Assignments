package com.nhlstenden.kingdomsandquests;

public class Attribute
{
    private String name;
    private int value;

    public Attribute(String name, int value)
    {
        this.setName(name);
        this.setValue(value);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }

        this.name = name;
    }

    public int getValue()
    {
        return this.value;
    }

    public void setValue(int value)
    {
        if (value < 0)
        {
            throw new IllegalArgumentException("Value cannot be negative.");
        }

        this.value = value;
    }
}