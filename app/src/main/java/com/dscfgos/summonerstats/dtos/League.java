package com.dscfgos.summonerstats.dtos;

import java.util.List;

/**
 * Created by David on 24-03-2017.
 */

public class League
{
    private List<LeaguePosition> entries ; // - The requested league entries.
    private String name ; 				// - This name is an internal place-holder name only. Display and localization of names in the game client are handled client-side.
    private String participantId ; 		// - Specifies the relevant participant that is a member of this league (i.e., a requested summoner ID, a requested team ID, or the ID of a team to which one of the requested summoners belongs). Only present when full league is requested so that participant's entry can be identified. Not present when individual entry is requested.
    private QueueType queue ; 			// - The league's queue type. (Legal values: RANKED_FLEX_SR, RANKED_FLEX_TT, RANKED_SOLO_5x5, RANKED_TEAM_3x3, RANKED_TEAM_5x5)
    private LeagueTier tier ; 			// - The league's tier. (Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE)

    public List<LeaguePosition> getEntries() {
        return entries;
    }
    public void setEntries(List<LeaguePosition> entries) {
        this.entries = entries;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getParticipantId() {
        return participantId;
    }
    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }
    public QueueType getQueue() {
        return queue;
    }
    public void setQueue(QueueType queue) {
        this.queue = queue;
    }
    public LeagueTier getTier() {
        return tier;
    }
    public void setTier(LeagueTier tier) {
        this.tier = tier;
    }
}
