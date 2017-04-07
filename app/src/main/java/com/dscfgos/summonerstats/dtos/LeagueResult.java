package com.dscfgos.summonerstats.dtos;

import java.util.List;
import java.util.Map;

/**
 * Created by David on 22-03-2017.
 */

public class LeagueResult extends BaseResult
{
    private Map<Long, List<League>> leagues = null;
    public Map<Long, List<League>> getLeagues()
    {
        return leagues;
    }

    public void setLeagues(Map<Long, List<League>> leagues) {
        this.leagues = leagues;
    }
}
