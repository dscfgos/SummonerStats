package com.dscfgos.summonerstats.dtos;

import java.util.List;

/**
 * Created by David on 31-03-2017.
 */

public class SpellVars
{
    private List<Double> coeff;
    private String       dyn;
    private String       key;
    private String       link;
    private String       ranksWith;
    public List<Double> getCoeff() {
        return coeff;
    }
    public void setCoeff(List<Double> coeff) {
        this.coeff = coeff;
    }
    public String getDyn() {
        return dyn;
    }
    public void setDyn(String dyn) {
        this.dyn = dyn;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getRanksWith() {
        return ranksWith;
    }
    public void setRanksWith(String ranksWith) {
        this.ranksWith = ranksWith;
    }
}
