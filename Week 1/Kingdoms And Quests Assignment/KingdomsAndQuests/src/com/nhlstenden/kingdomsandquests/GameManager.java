package com.nhlstenden.kingdomsandquests;

import java.util.ArrayList;
import java.util.List;

public class GameManager
{
    private Character playerCharacter;
    private List<Quest> quests;

    public GameManager(Character playerCharacter)
    {
        this.setPlayerCharacter(playerCharacter);
        this.setQuests(new ArrayList<>());
    }

    public Character getPlayerCharacter()
    {
        return this.playerCharacter;
    }

    public void setPlayerCharacter(Character playerCharacter)
    {
        if (playerCharacter == null)
        {
            throw new IllegalArgumentException("PlayerCharacter cannot be null.");
        }

        this.playerCharacter = playerCharacter;
    }

    public List<Quest> getQuests()
    {
        return this.quests;
    }

    public void setQuests(List<Quest> quests)
    {
        if (quests == null)
        {
            throw new IllegalArgumentException("Quests cannot be null.");
        }

        if (quests.contains(null))
        {
            throw new IllegalArgumentException("Quests list cannot contain any null elements.");
        }

        this.quests = quests;
    }

    public List<Quest> getAvailableQuests()
    {
        List<Quest> availableQuests = new ArrayList<>();

        for (Quest quest : this.getQuests())
        {
            if (this.getPlayerCharacter().getXp() >= quest.getRequiredXp())
            {
                availableQuests.add(quest);
            }
        }

        return availableQuests;
    }
}