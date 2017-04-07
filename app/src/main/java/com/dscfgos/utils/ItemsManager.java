package com.dscfgos.utils;

import com.dscfgos.summonerstats.activities.NewAccountActivity;
import com.dscfgos.summonerstats.dtos.Shard;
import com.dscfgos.summonerstats.dtos.Summoner;
import com.dscfgos.summonerstats.interfaces.ShardsResponseListener;
import com.dscfgos.summonerstats.rest_client.ShardsManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by David on 21-03-2017.
 */

public class ItemsManager implements ShardsResponseListener
{
    private ItemsManager(){}
    private static ItemsManager instance = new ItemsManager();
    private static ArrayList<Shard> shardList = null;

    private static Summoner selectedSummoner = null;

    public static ItemsManager getInstance()
    {
        return instance;
    }
    public void updateItems()
    {
        ShardsManager task = new ShardsManager(this);
        task.execute();
    }

    public Shard getShardById(int shardId)
    {
        Shard result = null;
        if(shardList != null && shardList.size() > 0)
        {
            for (Shard shard:shardList)
            {
                if(shard.getId()==shardId)
                {
                    result = shard;
                    break;
                }
            }
        }

        return result;
    }

    public ArrayList<Shard> getAllShards()
    {
        ArrayList<Shard> result = shardList;

        return result;
    }

    @Override
    public void getAllShards(List<Shard> shards)
    {
        shardList = new ArrayList<>(shards);
    }

    public void setSelectedSummoner(Summoner summoner)
    {
        selectedSummoner = summoner;
    }

    public Summoner getSelectedSummoner()
    {
        return selectedSummoner;
    }

    public boolean isSelectedSummoner(long id)
    {
        boolean result = false;

        if(selectedSummoner != null && selectedSummoner.getId()==id)
        {
            result = true;
        }

        return result;
    }
}
