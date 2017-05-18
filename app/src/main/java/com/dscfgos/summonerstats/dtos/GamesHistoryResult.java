package com.dscfgos.summonerstats.dtos;

import java.util.List;

/**
 * Created by David on 05-04-2017.
 */

public class GamesHistoryResult extends BaseResult
{
    private List<HistoryGameDataDTO> historyGameDataList = null;

    public List<HistoryGameDataDTO> getHistoryGameDataList() {
        return historyGameDataList;
    }

    public void setHistoryGameDataList(List<HistoryGameDataDTO> historyGameDataList) {
        this.historyGameDataList = historyGameDataList;
    }
}
