package com.dscfgos.utils;

/**
 * Created by David on 07-03-2017.
 */

public class StringUtils
{
    public static String substitute(String source, Object... values)
    {
        if(values != null && values.length > 0)
        {
            for (int i = 0; i < values.length; i++)
            {
                if(values[i] == null)
                    values[i] = "N/A";
                String oldChar = "\\{"+i+"\\}";
                source = source.replaceAll(oldChar, values[i].toString());
            }
        }
        return source;
    }
}
