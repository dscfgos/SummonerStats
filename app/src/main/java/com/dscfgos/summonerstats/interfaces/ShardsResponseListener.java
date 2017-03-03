package com.dscfgos.summonerstats.interfaces;

import com.dscfgos.summonerstats.dtos.Shard;

import java.util.List;

/**
 * Created by David on 03-03-2017.
 */

public interface ShardsResponseListener
{
    public void getAllShards(List<Shard> shards);
}
