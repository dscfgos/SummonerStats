package com.dscfgos.summonerstats.dtos;

/**
 * Created by David on 31-03-2017.
 */

public class Rune
{
    private long runeId ; 		// - The ID of the rune
    private int count ; 		// - The count of this rune used by the participant
    public long getRuneId() {
        return runeId;
    }
    public void setRuneId(long runeId) {
        this.runeId = runeId;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
