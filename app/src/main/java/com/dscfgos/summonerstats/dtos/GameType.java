package com.dscfgos.summonerstats.dtos;

/**
 * Created by David on 31-03-2017.
 */

public enum GameType
{
    CUSTOM_GAME("CUSTOM_GAME","Custom games"),
    TUTORIAL_GAME("TUTORIAL_GAME","Tutorial games"),
    MATCHED_GAME("MATCHED_GAME","All other games");

    private String name ;
    private String description ;
    GameType(String name, String description)
    {
        this.name 			= name;
        this.description 	= description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
