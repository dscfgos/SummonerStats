package com.dscfgos.summonerstats.dtos;

import java.io.Serializable;

/**
 * Created by David on 22-03-2017.
 */

public class BaseResult implements Serializable
{
    private String resultCode = "";
    public String getResultCode() {
        return resultCode;
    }
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
