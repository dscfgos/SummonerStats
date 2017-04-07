package com.dscfgos.summonerstats.dtos;

import java.util.List;

/**
 * Created by David on 31-03-2017.
 */

public class CurrentGameParticipant
{
    private boolean bot ; 				// - Flag indicating whether or not this participant is a bot
    private long championId ; 			// - The ID of the champion played by this participant
    private Champion champion ; 		// - The champion played by this participant
    private List<Mastery> masteries ; 	// - The masteries used by this participant
    private long profileIconId ; 		// - The ID of the profile icon used by this participant
    private List<Rune> runes ;		 	// - The runes used by this participant
    private SummonerSpell spell1 ; 		// - The first summoner spell used by this participant
    private SummonerSpell spell2 ; 		// - The second summoner spell used by this participant
    private long summonerId ; 			// - The summoner ID of this participant
    private String summonerName ; 		// - The summoner name of this participant
    private long teamId ; 				// - The team ID of this participant, indicating the participant's team
    private ChampionMastery championMastery ;
    private int totalSessionsLost;
    private int totalSessionsPlayed;
    private int totalSessionsWon;

    public boolean isBot() {
        return bot;
    }
    public void setBot(boolean bot) {
        this.bot = bot;
    }
    public long getChampionId() {
        return championId;
    }
    public void setChampionId(long championId) {
        this.championId = championId;
    }
    public List<Mastery> getMasteries() {
        return masteries;
    }
    public void setMasteries(List<Mastery> masteries) {
        this.masteries = masteries;
    }
    public long getProfileIconId() {
        return profileIconId;
    }
    public void setProfileIconId(long profileIconId) {
        this.profileIconId = profileIconId;
    }
    public List<Rune> getRunes() {
        return runes;
    }
    public void setRunes(List<Rune> runes) {
        this.runes = runes;
    }
    public SummonerSpell getSpell1() {
        return spell1;
    }
    public void setSpell1(SummonerSpell spell1) {
        this.spell1 = spell1;
    }
    public SummonerSpell getSpell2() {
        return spell2;
    }
    public void setSpell2(SummonerSpell spell2) {
        this.spell2 = spell2;
    }
    public long getSummonerId() {
        return summonerId;
    }
    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }
    public String getSummonerName() {
        return summonerName;
    }
    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }
    public long getTeamId() {
        return teamId;
    }
    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
    public Champion getChampion() {
        return champion;
    }
    public void setChampion(Champion champion) {
        this.champion = champion;
    }
    public ChampionMastery getChampionMastery() {
        return championMastery;
    }

    public void setChampionMastery(ChampionMastery championMastery) {
        this.championMastery = championMastery;
    }

    public int getTotalSessionsLost() {
        return totalSessionsLost;
    }

    public void setTotalSessionsLost(int totalSessionsLost) {
        this.totalSessionsLost = totalSessionsLost;
    }

    public int getTotalSessionsPlayed() {
        return totalSessionsPlayed;
    }

    public void setTotalSessionsPlayed(int totalSessionsPlayed) {
        this.totalSessionsPlayed = totalSessionsPlayed;
    }

    public int getTotalSessionsWon() {
        return totalSessionsWon;
    }

    public void setTotalSessionsWon(int totalSessionsWon) {
        this.totalSessionsWon = totalSessionsWon;
    }
}
