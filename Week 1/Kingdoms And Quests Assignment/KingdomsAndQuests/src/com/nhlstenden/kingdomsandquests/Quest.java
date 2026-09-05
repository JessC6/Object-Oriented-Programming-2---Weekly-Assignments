package com.nhlstenden.kingdomsandquests;

public class Quest
{
    private int offeredXp;
    private Character opponent;
    private int difficulty;

    private static final int REQUIRED_XP_MULTIPLIER = 10;

    public Quest(int offeredXp, Character opponent, int difficulty)
    {
        this.setOfferedXp(offeredXp);
        this.setOpponent(opponent);
        this.setDifficulty(difficulty);
    }

    public int getOfferedXp()
    {
        return this.offeredXp;
    }

    public void setOfferedXp(int offeredXp)
    {
        if (offeredXp <= 0)
        {
            throw new IllegalArgumentException("OfferedXp cannot be equal or inferior than 0.");
        }

        this.offeredXp = offeredXp;
    }

    public Character getOpponent()
    {
        return this.opponent;
    }

    public void setOpponent(Character opponent)
    {
        if (opponent == null)
        {
            throw new IllegalArgumentException("Opponent cannot be null.");
        }

        this.opponent = opponent;
    }

    public int getDifficulty()
    {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty)
    {
        if (difficulty < 0)
        {
            throw new IllegalArgumentException("Difficulty cannot be negative.");
        }

        this.difficulty = difficulty;
    }

    public int getRequiredXp()
    {
        return this.getDifficulty() * REQUIRED_XP_MULTIPLIER;
    }
}