package com.nhlstenden.kingdomsandquests;

import java.util.ArrayList;
import java.util.List;

public class SpecialQuest extends Quest
{
    private List<Item> items;

    public SpecialQuest(int offeredXp, Character opponent, int difficulty)
    {
        super(offeredXp, opponent, difficulty);
        this.setItems(new ArrayList<>());
    }

    public List<Item> getItems()
    {
        return this.items;
    }

    public void setItems(List<Item> items)
    {
        if (items == null)
        {
            throw new IllegalArgumentException("Items cannot be null.");
        }

        if (items.contains(null))
        {
            throw new IllegalArgumentException("Items list cannot contain null elements.");
        }

        this.items = items;
    }
}