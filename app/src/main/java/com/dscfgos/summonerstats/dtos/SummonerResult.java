package com.dscfgos.summonerstats.dtos;

/**
 * Created by David on 22-03-2017.
 */

public class SummonerResult extends BaseResult
{
    private Summoner summoner = null;
    public Summoner getSummoner() {
        return summoner;
    }
    public void setSummoner(Summoner summoner) {
        this.summoner = summoner;
    }
}
