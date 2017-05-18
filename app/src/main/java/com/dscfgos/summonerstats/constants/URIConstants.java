package com.dscfgos.summonerstats.constants;

import com.dscfgos.utils.StringUtils;

/**
 * Created by David on 03-03-2017.
 */

public class URIConstants {
    //private static String BASE_URL = "http://localhost:8080/LoLWS/rest/";
    //private static String BASE_URL = "http://api-loldsu.rhcloud.com/LoLWS/rest/";
    private static String BASE_URL = "http://lol.dscfgos.com/LoLWS/rest/";

    //Summoner
    public static String SUMMONER = BASE_URL + "summoner/users?region={0}&summonerName={1}";
    public static String SUMMONER_UPDATE = BASE_URL + "summoner/update?region={0}&summonerName={1}";

    //Stats
    public static String SHARDS = BASE_URL + "status/shards";

    //League
    public static String LEAGUE_ENTRY = BASE_URL + "league/summoner?region={0}&summonerId={1}";

    //Current Game
    public static String CURRENT_GAME = BASE_URL + "current/game?region={0}&id={1}&locale={2}";

    //Recent Games
    public static String RECENT_GAMES = BASE_URL + "recent/games?region={0}&id={1}&locale={2}";
}
