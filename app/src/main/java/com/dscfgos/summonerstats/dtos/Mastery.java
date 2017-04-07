package com.dscfgos.summonerstats.dtos;

/**
 * Created by David on 31-03-2017.
 */

public class Mastery
{
    private long masteryId ; 	// - The ID of the mastery
    private int rank ; 			// - The number of points put into this mastery by the user

    public long getMasteryId() {
        return masteryId;
    }
    public void setMasteryId(long masteryId) {
        this.masteryId = masteryId;
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
}
