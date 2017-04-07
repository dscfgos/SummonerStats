package com.dscfgos.summonerstats.dtos;

import java.util.List;

/**
 * Created by David on 31-03-2017.
 */

public class LevelTip
{
    private List<String> effect;
    private List<String> label;
    public List<String> getEffect() {
        return effect;
    }
    public void setEffect(List<String> effect) {
        this.effect = effect;
    }
    public List<String> getLabel() {
        return label;
    }
    public void setLabel(List<String> label) {
        this.label = label;
    }
}
