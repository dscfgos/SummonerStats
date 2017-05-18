package com.dscfgos.summonerstats.dtos;

import java.util.List;
import java.util.Map;

/**
 * Created by David on 22-03-2017.
 */

public class LeaguePositionResult extends BaseResult
{
    private List<LeaguePosition> leaguePosition = null;

    public List<LeaguePosition> getLeaguePosition() {
        return leaguePosition;
    }

    public void setLeaguePosition(List<LeaguePosition> leaguePosition) {
        this.leaguePosition = leaguePosition;
    }
}
