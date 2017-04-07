package com.dscfgos.summonerstats.dtos;

/**
 * Created by David on 31-03-2017.
 */

public class Observer
{
    private String encryptionKey ; // - Key used to decrypt the spectator grid game data for playback

    public String getEncryptionKey() {
        return encryptionKey;
    }
    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }
}
