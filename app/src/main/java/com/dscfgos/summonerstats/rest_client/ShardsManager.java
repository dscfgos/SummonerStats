package com.dscfgos.summonerstats.rest_client;

import android.os.AsyncTask;

import com.dscfgos.summonerstats.constants.URIConstants;
import com.dscfgos.summonerstats.dtos.Shard;
import com.dscfgos.summonerstats.interfaces.ShardsResponseListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created by David on 03-03-2017.
 */

public class ShardsManager extends AsyncTask<String, Void, String>
{
    private final ShardsResponseListener shardsResponseListener;
    public ShardsManager(ShardsResponseListener shardsResponseListener)
    {
        this.shardsResponseListener = shardsResponseListener;
    }


    @Override
    protected String doInBackground(String... params)
    {
        String result = null ;

        HttpURLConnection urlConnection = null;
        try
        {
            URL url = new URL(URIConstants.SHARDS);
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
        if(s != null && shardsResponseListener != null)
        {
            List<Shard> shards = new Gson().fromJson(s , new TypeToken<List<Shard>>() {}.getType());

            shardsResponseListener.getAllShards(shards);
        }
    }
}
