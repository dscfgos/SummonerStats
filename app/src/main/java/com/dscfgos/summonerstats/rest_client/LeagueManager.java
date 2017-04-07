package com.dscfgos.summonerstats.rest_client;

import android.os.AsyncTask;
import android.widget.Toast;

import com.dscfgos.summonerstats.constants.URIConstants;
import com.dscfgos.summonerstats.dtos.LeagueResult;
import com.dscfgos.summonerstats.dtos.SummonerResult;
import com.dscfgos.summonerstats.interfaces.LeagueEntryResponseListener;
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

public class LeagueManager extends AsyncTask<String, Void, String>
{
    public static final String GET_LEAGUE_ENTRY     = "GET_LEAGUE_ENTRY";

    private final LeagueEntryResponseListener leagueEntryResponseListener;
    public LeagueManager(LeagueEntryResponseListener leagueEntryResponseListener)
    {
        this.leagueEntryResponseListener = leagueEntryResponseListener;
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
            if(operationType.equals(GET_LEAGUE_ENTRY))
            {
                url = URLUtils.getURLWithParams(URIConstants.LEAGUE_ENTRY, urlParams);
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
        if(s != null && leagueEntryResponseListener != null)
        {
            LeagueResult leagueResult = new Gson().fromJson(s , new TypeToken<LeagueResult>() {}.getType());

            leagueEntryResponseListener.getLeagueEntry(leagueResult);
        }
        else
        {
            LeagueResult leagueResult = new LeagueResult();
            leagueResult.setResultCode("-1");
            leagueEntryResponseListener.getLeagueEntry(leagueResult);
        }
    }
}
