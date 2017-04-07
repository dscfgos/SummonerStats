package com.dscfgos.summonerstats.dtos;

/**
 * Created by David on 22-03-2017.
 */

public class CurrentGameResult extends BaseResult
{
    private CurrentGameInfo currentGame = null;
    public CurrentGameInfo getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(CurrentGameInfo currentGame) {
        this.currentGame = currentGame;
    }
}
