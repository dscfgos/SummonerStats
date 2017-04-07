package com.dscfgos.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.dscfgos.summonerstats.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by David on 16-03-2017.
 */

public class SharedPreferenceManager<T>
{
    private static SharedPreferenceManager instance = null;

    public static SharedPreferenceManager getInstance()
    {
        if(instance == null)
        {
            instance = new SharedPreferenceManager();
        }

        return instance;
    }
    private SharedPreferenceManager()
    {

    }
    public void saveToShared(Context context, String itemKeyName, Object item, Class<T> type)
    {
        SharedPreferences mPrefs        = context.getSharedPreferences(context.getApplicationInfo().name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        Gson gson = new Gson();
        editor.putString(itemKeyName, gson.toJson(item,type));
        editor.commit();
    }

    public T loadFromShared(Context context, String itemKeyName, Class<T> type)
    {
        SharedPreferences mPrefs        = context.getSharedPreferences(context.getApplicationInfo().name, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        JsonParser parser=new JsonParser();
        String strObject = mPrefs.getString(itemKeyName, null);
        if(strObject != null)
        {
            JsonObject object =parser.parse(strObject).getAsJsonObject();
            return gson.fromJson(object,type);
        }
        else
        {
            return null ;
        }
    }
}
