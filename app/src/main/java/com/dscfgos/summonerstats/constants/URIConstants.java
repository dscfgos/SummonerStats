package com.dscfgos.summonerstats.constants;

/**
 * Created by David on 03-03-2017.
 */

public class URIConstants 
{
    //private static String BASE_URL = "http://localhost:8080/LoLWS/rest/";
    private static String BASE_URL = "http://api-loldsu.rhcloud.com/LoLWS/rest/";

    //Summoner
    public static String SUMMONER   = BASE_URL + "summoner/users/{0}";
    //Stats
    public static String SHARDS     = BASE_URL + "status/shards";
}
