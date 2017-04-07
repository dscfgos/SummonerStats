package com.dscfgos.summonerstats.classes.utils;

import android.content.Context;

import com.dscfgos.summonerstats.R;
import com.dscfgos.summonerstats.dtos.LeagueTier;

/**
 * Created by David on 07-03-2017.
 */

public class LeagueUtils
{
    public static int getTierResourceId(LeagueTier tier,String division)
    {
        int result = R.drawable.provisional;
        if(tier != null)
        {
            switch (tier)
            {
                case BRONZE:
                    result = getBronzeImage(division);
                    break;
                case SILVER:
                    result = getSilverImage(division);
                    break;
                case GOLD:
                    result = getGoldImage(division);
                    break;
                case PLATINUM:
                    result = getPlatinumImage(division);
                    break;
                case DIAMOND:
                    result = getDiamondImage(division);
                    break;
                case MASTER:
                    result = R.drawable.master;
                    break;
                case CHALLENGER:
                    result = R.drawable.challenger;
                    break;
            }
        }

        return result;
    }
    private static int getBronzeImage(String division)
    {
        int result = -1;
        if (division.equalsIgnoreCase("I"))
        {
            result = R.drawable.bronze_i;
        }
        else if(division.equalsIgnoreCase("II"))
        {
            result = R.drawable.bronze_ii;
        }
        else if(division.equalsIgnoreCase("III"))
        {
            result = R.drawable.bronze_iii;
        }
        else if(division.equalsIgnoreCase("IV"))
        {
            result = R.drawable.bronze_iv;
        }
        else if(division.equalsIgnoreCase("V"))
        {
            result = R.drawable.bronze_v;
        }
        return result;
    }

    private static int getSilverImage(String division)
    {
        int result = -1;
        if (division.equalsIgnoreCase("I"))
        {
            result = R.drawable.silver_i;
        }
        else if(division.equalsIgnoreCase("II"))
        {
            result = R.drawable.silver_ii;
        }
        else if(division.equalsIgnoreCase("III"))
        {
            result = R.drawable.silver_iii;
        }
        else if(division.equalsIgnoreCase("IV"))
        {
            result = R.drawable.silver_iv;
        }
        else if(division.equalsIgnoreCase("V"))
        {
            result = R.drawable.silver_v;
        }
        return result;
    }

    private static int getGoldImage(String division)
    {
        int result = -1;
        if (division.equalsIgnoreCase("I"))
        {
            result = R.drawable.gold_i;
        }
        else if(division.equalsIgnoreCase("II"))
        {
            result = R.drawable.gold_ii;
        }
        else if(division.equalsIgnoreCase("III"))
        {
            result = R.drawable.gold_iii;
        }
        else if(division.equalsIgnoreCase("IV"))
        {
            result = R.drawable.gold_iv;
        }
        else if(division.equalsIgnoreCase("V"))
        {
            result = R.drawable.gold_v;
        }
        return result;
    }

    private static int getPlatinumImage(String division)
    {
        int result = -1;
        if (division.equalsIgnoreCase("I"))
        {
            result = R.drawable.platinum_i;
        }
        else if(division.equalsIgnoreCase("II"))
        {
            result = R.drawable.platinum_ii;
        }
        else if(division.equalsIgnoreCase("III"))
        {
            result = R.drawable.platinum_iii;
        }
        else if(division.equalsIgnoreCase("IV"))
        {
            result = R.drawable.platinum_iv;
        }
        else if(division.equalsIgnoreCase("V"))
        {
            result = R.drawable.platinum_v;
        }
        return result;
    }

    private static int getDiamondImage(String division)
    {
        int result = -1;
        if (division.equalsIgnoreCase("I"))
        {
            result = R.drawable.diamond_i;
        }
        else if(division.equalsIgnoreCase("II"))
        {
            result = R.drawable.diamond_ii;
        }
        else if(division.equalsIgnoreCase("III"))
        {
            result = R.drawable.diamond_iii;
        }
        else if(division.equalsIgnoreCase("IV"))
        {
            result = R.drawable.diamond_iv;
        }
        else if(division.equalsIgnoreCase("V"))
        {
            result = R.drawable.diamond_v;
        }
        return result;
    }

    public static int getTierNameId(LeagueTier tier)
    {
        int result = R.string.str_unranked;
        if(tier != null)
        {
            switch (tier)
            {
                case BRONZE:
                    result = R.string.str_bronze;
                    break;
                case SILVER:
                    result = R.string.str_silver;
                    break;
                case GOLD:
                    result = R.string.str_gold;
                    break;
                case PLATINUM:
                    result = R.string.str_platinum;
                    break;
                case DIAMOND:
                    result = R.string.str_diamond;
                    break;
                case MASTER:
                    result = R.string.str_master;
                    break;
                case CHALLENGER:
                    result = R.string.str_challenger;
                    break;
            }
        }
        return result;
    }


    public static int getDrawableResourceByName(Context context,String resourceName)
    {
        int result = -1;

        result = context.getResources().getIdentifier(resourceName.toLowerCase().replaceAll(" ","").replaceAll(".png",""),"drawable",context.getPackageName());

        return result;
    }

    public static int getItemResourceByName(Context context,int spellid)
    {
        int result = -1;

        result = context.getResources().getIdentifier("i"+spellid,"drawable",context.getPackageName());

        return result;
    }

    public static String getMapNameById(Context context,int id)
    {
        String result = "-";
        switch(id)
        {
            case  8:
                result = context.getResources().getString(R.string.str_map8);
                break;
            case  10:
                result = context.getResources().getString(R.string.str_map10);
                break;
            case  11:
                result = context.getResources().getString(R.string.str_map11);
                break;
            case  12:
                result = context.getResources().getString(R.string.str_map12);
                break;
            case  14:
                result = context.getResources().getString(R.string.str_map14);
                break;
            default:
                result = context.getResources().getString(R.string.str_mapUnknow);
                break;

           }


        return result;
    }



}
