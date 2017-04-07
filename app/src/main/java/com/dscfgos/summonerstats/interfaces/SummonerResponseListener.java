package com.dscfgos.summonerstats.interfaces;

import com.dscfgos.summonerstats.dtos.SummonerResult;

import java.util.List;

/**
 * Created by David on 03-03-2017.
 */

public interface SummonerResponseListener
{
    public void getSummoner(SummonerResult summonerResult);
}
