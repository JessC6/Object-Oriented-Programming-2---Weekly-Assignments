package com.nhlstenden.kingdomsandquests;

public class Mage extends Character
{
    private static final int MAGE_BONUS_SPECIAL_ABILITY = 1;

    public Mage(int attackPower, int defensePower)
    {
        super(attackPower, defensePower);
    }

    @Override
    public void useSpecialAbility()
    {
        this.setDefensePower(this.getDefensePower() + MAGE_BONUS_SPECIAL_ABILITY);
    }
}