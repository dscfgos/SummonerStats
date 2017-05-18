package com.dscfgos.summonerstats.rest_client;

import android.os.AsyncTask;

import com.dscfgos.summonerstats.constants.URIConstants;
import com.dscfgos.summonerstats.dtos.CurrentGameResult;
import com.dscfgos.summonerstats.interfaces.CurrentGameResponseListener;
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

public class CurrentGameManager extends AsyncTask<String, Void, String>
{
    public static final String GET_CURRENT_GAME     = "GET_CURRENT_GAME";

    private final CurrentGameResponseListener currentGameResponseListener;
    public CurrentGameManager(CurrentGameResponseListener currentGameResponseListener)
    {
        this.currentGameResponseListener = currentGameResponseListener;
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
            if(operationType.equals(GET_CURRENT_GAME))
            {
                url = URLUtils.getURLWithParams(URIConstants.CURRENT_GAME, urlParams);
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
        if(s != null && currentGameResponseListener != null)
        {
            CurrentGameResult currentGameResult = new Gson().fromJson(s , new TypeToken<CurrentGameResult>() {}.getType());

            currentGameResponseListener.getCurrentGame(currentGameResult);
        }
        else
        {
            CurrentGameResult currentGameResult = new CurrentGameResult();
            currentGameResult.setResultCode("-1");
            currentGameResponseListener.getCurrentGame(currentGameResult);
        }
    }
}
