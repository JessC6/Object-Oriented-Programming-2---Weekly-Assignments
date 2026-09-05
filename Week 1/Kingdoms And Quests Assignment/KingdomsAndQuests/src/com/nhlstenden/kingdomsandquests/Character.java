package com.nhlstenden.kingdomsandquests;

import java.util.ArrayList;
import java.util.List;

public abstract class Character
{
    private List<Attribute> attributes;
    private int xp;
    private int attackPower;
    private int defensePower;

    private static final int REQUIRED_XP_TO_LEVEL_UP = 200;

    public Character(int attackPower, int defensePower)
    {
        this.setAttributes(new ArrayList<>());
        this.setXp(0);
        this.setAttackPower(attackPower);
        this.setDefensePower(defensePower);
    }

    public List<Attribute> getAttributes()
    {
        return this.attributes;
    }

    public void setAttributes(List<Attribute> attributes)
    {
        if (attributes == null)
        {
            throw new IllegalArgumentException("Attributes cannot be null.");
        }

        if (attributes.contains(null))
        {
            throw new IllegalArgumentException("Attributes list cannot contain any null elements.");
        }

        this.attributes = attributes;
    }

    public int getXp()
    {
        return this.xp;
    }

    public void setXp(int xp)
    {
        if (xp < 0)
        {
            throw new IllegalArgumentException("Xp cannot be negative.");
        }

        this.xp = xp;
    }

    public int getAttackPower()
    {
        return this.attackPower;
    }

    public void setAttackPower(int attackPower)
    {
        if (attackPower < 0)
        {
            throw new IllegalArgumentException("AttackPower cannot be negative.");
        }

        this.attackPower = attackPower;
    }

    public int getDefensePower()
    {
        return this.defensePower;
    }

    public void setDefensePower(int defensePower)
    {
        if (defensePower < 0)
        {
            throw new IllegalArgumentException("DefensePower cannot be negative.");
        }

        this.defensePower = defensePower;
    }

    public int getLevel()
    {
        return this.getXp() / REQUIRED_XP_TO_LEVEL_UP;
    }

    public void attack()
    {
        // Not enough information to do this method
    }

    public void defend()
    {
        // Not enough information to do this method
    }

    public abstract void useSpecialAbility();
}