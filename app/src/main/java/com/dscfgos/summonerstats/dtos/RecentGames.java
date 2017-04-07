package com.dscfgos.summonerstats.dtos;

import java.util.Set;

/**
 * Created by David on 05-04-2017.
 */

public class RecentGames
{
    private Set<GameDTO> games	; // - Collection of recent games played (max 10).
    private long summonerId; // - Summoner ID.

    public Set<GameDTO> getGames() {
        return games;
    }
    public void setGames(Set<GameDTO> games) {
        this.games = games;
    }
    public long getSummonerId() {
        return summonerId;
    }
    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }
}
