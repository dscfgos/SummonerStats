package com.dscfgos.summonerstats.constants;

import com.dscfgos.utils.StringUtils;

/**
 * Created by David on 16-03-2017.
 */

public class ImagesUtils
{
    public static String IMAGE_PROFILE_XSMALL       = "1";
    public static String IMAGE_PROFILE_SMALL        = "2";
    public static String IMAGE_PROFILE_MEDIUM       = "3";

    public static String IMAGE_PROFILE_TYPE        = "pr";
    private static String BASE_IMAGES_URL = "http://api-loldsu.rhcloud.com/LoLWS/lol_img?name={0}&type={1}&size={2}";

    public static String getProfileImageMedium(String image, String type, String size)
    {
        return StringUtils.substitute(BASE_IMAGES_URL,image,type,size);
    }
}
