package com.dscfgos.summonerstats.dtos;

/**
 * Created by David on 31-03-2017.
 */

public class BannedChampion
{
    private int   championId;	// - Banned champion ID
    private Champion champion;
    private int   pickTurn;	// - Turn during which the champion was banned
    public int getChampionId() {
        return championId;
    }
    public void setChampionId(int championId) {
        this.championId = championId;
    }
    public int getPickTurn() {
        return pickTurn;
    }
    public void setPickTurn(int pickTurn) {
        this.pickTurn = pickTurn;
    }
    public Champion getChampion() {
        return champion;
    }
    public void setChampion(Champion champion) {
        this.champion = champion;
    }
}
