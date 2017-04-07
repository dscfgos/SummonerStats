package com.dscfgos.summonerstats.dtos;

/**
 * Created by David on 03-04-2017.
 */

public class ChampionStats
{
    private int             id;		// - Champion ID. Note that champion ID 0 represents the combined stats for all champions. For static information correlating to champion IDs, please refer to the LoL Static Data API.
    private AggregatedStats stats;		// - Aggregated stats associated with the champion.
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public AggregatedStats getStats() {
        return stats;
    }
    public void setStats(AggregatedStats stats) {
        this.stats = stats;
    }
}
