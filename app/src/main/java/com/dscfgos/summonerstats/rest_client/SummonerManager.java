package com.dscfgos.summonerstats.rest_client;

import android.os.AsyncTask;

import com.dscfgos.summonerstats.constants.URIConstants;
import com.dscfgos.summonerstats.dtos.Summoner;
import com.dscfgos.summonerstats.dtos.SummonerResult;
import com.dscfgos.summonerstats.interfaces.SummonerResponseListener;
import com.dscfgos.utils.URLUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by David on 03-03-2017.
 */

public class SummonerManager extends AsyncTask<String, Void, String>
{
    public static final String GET_SUMMONER     = "GET_SUMMONER";
    public static final String UPDATE_SUMMONER  = "UPDATE_SUMMONER";

    private final SummonerResponseListener summonerResponseListener;
    public SummonerManager(SummonerResponseListener summonerResponseListener)
    {
        this.summonerResponseListener = summonerResponseListener;
    }


    @Override
    protected String doInBackground(String... params)
    {
        String result = null ;

        HttpURLConnection urlConnection = null;
        try
        {
            String operationType = params[0];
            String[] urlParams = new String[params.length-1];
            urlParams = Arrays.copyOfRange(params, 1, params.length);

            URL url = null;
            if(operationType.equals(GET_SUMMONER))
            {
                url = URLUtils.getURLWithParams(URIConstants.SUMMONER, urlParams);
            }
            else if(operationType.equals(UPDATE_SUMMONER))
            {
                url = URLUtils.getURLWithParams(URIConstants.SUMMONER_UPDATE, urlParams);
            }

            urlConnection = (HttpURLConnection) url.openConnection();

            int statusCode = urlConnection.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK)
            {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                result = new Scanner(in).useDelimiter("\\A").next();
            }
        }
        catch(Exception ex)
        {

        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s != null && summonerResponseListener != null)
        {
            SummonerResult summonerResult = new Gson().fromJson(s , new TypeToken<SummonerResult>() {}.getType());

            summonerResponseListener.getSummoner(summonerResult);
        }
    }
}
