package com.dscfgos.summonerstats.dtos;

/**
 * Created by David on 05-04-2017.
 */

public class RecentGamesResult extends BaseResult
{
    private RecentGames recentGames = null;
    public RecentGames getRecentGames() {
        return recentGames;
    }

    public void setRecentGames(RecentGames recentGames) {
        this.recentGames = recentGames;
    }
}
