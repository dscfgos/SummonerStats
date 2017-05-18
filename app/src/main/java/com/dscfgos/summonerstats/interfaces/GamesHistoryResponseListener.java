package com.dscfgos.summonerstats.interfaces;

import com.dscfgos.summonerstats.dtos.GamesHistoryResult;

/**
 * Created by David on 03-03-2017.
 */

public interface GamesHistoryResponseListener
{
    public void getGamesHistory(GamesHistoryResult gamesHistoryResult);
}
