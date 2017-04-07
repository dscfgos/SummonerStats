package com.dscfgos.summonerstats.interfaces;

import com.dscfgos.summonerstats.dtos.CurrentGameResult;
import com.dscfgos.summonerstats.dtos.LeagueResult;

/**
 * Created by David on 03-03-2017.
 */

public interface CurrentGameResponseListener
{
    public void getCurrentGame(CurrentGameResult currentGameResult);
}
