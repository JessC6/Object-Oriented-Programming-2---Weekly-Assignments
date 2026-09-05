package com.nhlstenden.kingdomsandquests;

public class Archer extends Character
{
    private static final int ARCHER_BONUS_SPECIAL_ABILITY = 2;

    public Archer(int attackPower, int defensePower)
    {
        super(attackPower, defensePower);
    }

    @Override
    public void useSpecialAbility()
    {
        this.setAttackPower(this.getAttackPower() * ARCHER_BONUS_SPECIAL_ABILITY);
    }
}