package com.nhlstenden.kingdomsandquests;

public class Warrior extends Character
{
    private static final int WARRIOR_BONUS_SPECIAL_ABILITY = 1;

    public Warrior(int attackPower, int defensePower)
    {
        super(attackPower, defensePower);
    }

    @Override
    public void useSpecialAbility()
    {
        // This is my interpretation of Warrior's special ability
        this.setAttackPower(this.getAttackPower() + WARRIOR_BONUS_SPECIAL_ABILITY);
        this.attack();

        // resets after an attack example
        this.setAttackPower(this.getAttackPower() - WARRIOR_BONUS_SPECIAL_ABILITY);
    }
}