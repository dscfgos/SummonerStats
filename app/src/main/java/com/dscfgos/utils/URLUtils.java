package com.dscfgos.utils;

import java.net.URL;

/**
 * Created by David on 07-03-2017.
 */

public class URLUtils
{
    public static String getURLStringWithParams(String url, String[] params)
    {
        String result = null;

        if(params != null && params.length > 0 && url != null)
        {
            result = StringUtils.substitute(url,params);
        }

        return result;
    }

    public static URL getURLWithParams(String url, String[] params)
    {
        URL result = null;

        if(params != null && params.length > 0 && url != null)
        {
            String urlString = StringUtils.substitute(url,params);
            try
            {
                result = new URL(urlString);
            }catch(Exception ex){}

        }

        return result;
    }
}
