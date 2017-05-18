package com.dscfgos.summonerstats.rest_client;

import android.os.AsyncTask;

import com.dscfgos.summonerstats.constants.URIConstants;
import com.dscfgos.summonerstats.dtos.GamesHistoryResult;
import com.dscfgos.summonerstats.dtos.RecentGamesResult;
import com.dscfgos.summonerstats.interfaces.GamesHistoryResponseListener;
import com.dscfgos.utils.URLUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by David on 05-04-2017.
 */

public class GamesHistoryManager extends AsyncTask<String, Void, String>
{
    public static final String GET_RECENT_GAMES     = "GET_RECENT_GAMES";

    private final GamesHistoryResponseListener gamesHistoryResponseListener;
    public GamesHistoryManager(GamesHistoryResponseListener gamesHistoryResponseListener)
    {
        this.gamesHistoryResponseListener = gamesHistoryResponseListener;
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
            if(operationType.equals(GET_RECENT_GAMES))
            {
                url = URLUtils.getURLWithParams(URIConstants.RECENT_GAMES, urlParams);
            }
            urlConnection = (HttpURLConnection) url.openConnection();

            int statusCode = urlConnection.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK)
            {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                result = new Scanner(in).useDelimiter("\\A").next();
            }
            else
            {
                result = null;
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s != null && gamesHistoryResponseListener != null)
        {
            GamesHistoryResult gamesHistoryResult = new Gson().fromJson(s , new TypeToken<GamesHistoryResult>() {}.getType());

            gamesHistoryResponseListener.getGamesHistory(gamesHistoryResult);
        }
        else
        {
            GamesHistoryResult gamesHistoryResult = new GamesHistoryResult();
            gamesHistoryResult.setResultCode("-1");
            gamesHistoryResponseListener.getGamesHistory(gamesHistoryResult);
        }
    }
}
