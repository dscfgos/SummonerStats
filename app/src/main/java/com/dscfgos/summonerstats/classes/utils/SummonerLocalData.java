package com.dscfgos.summonerstats.classes.utils;

import com.dscfgos.summonerstats.dtos.Summoner;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by David on 16-03-2017.
 */

public class SummonerLocalData implements Serializable
{
    public SummonerLocalData(List<Summoner> summonerList, Date lastUpdated)
    {
        this.summonerList = summonerList;
        this.lastUpdated = lastUpdated;
    }
    private List<Summoner> summonerList ;
    private Date lastUpdated ;

    public List<Summoner> getSummonerList() {
        return summonerList;
    }

    public void setSummonerList(List<Summoner> summonerList) {
        this.summonerList = summonerList;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
