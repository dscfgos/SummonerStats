package com.dscfgos.summonerstats.dtos;

/**
 * Created by David on 05-04-2017.
 */

public class Player
{
    private int championId	; 	// - Champion id associated with player.
    private long summonerId; 	// - Summoner id associated with player.
    private int teamId	; 		// - Team id associated with player.

    public int getChampionId() {
        return championId;
    }
    public void setChampionId(int championId) {
        this.championId = championId;
    }
    public long getSummonerId() {
        return summonerId;
    }
    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }
    public int getTeamId() {
        return teamId;
    }
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
