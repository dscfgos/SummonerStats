package com.dscfgos.summonerstats.dtos;

import java.util.List;

/**
 * Created by David on 31-03-2017.
 */

public class Block
{
    private List<BlockItem> items;
    private boolean         recMath;
    private String          type;
    public List<BlockItem> getItems() {
        return items;
    }
    public void setItems(List<BlockItem> items) {
        this.items = items;
    }
    public boolean isRecMath() {
        return recMath;
    }
    public void setRecMath(boolean recMath) {
        this.recMath = recMath;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
