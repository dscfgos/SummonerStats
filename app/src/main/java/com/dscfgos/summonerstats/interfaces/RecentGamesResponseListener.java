package com.dscfgos.summonerstats.interfaces;

import com.dscfgos.summonerstats.dtos.RecentGamesResult;

/**
 * Created by David on 03-03-2017.
 */

public interface RecentGamesResponseListener
{
    public void getRecentGames(RecentGamesResult recentGamesResult);
}
