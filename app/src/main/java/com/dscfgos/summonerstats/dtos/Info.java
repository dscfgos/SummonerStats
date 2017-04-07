package com.dscfgos.summonerstats.dtos;

/**
 * Created by David on 31-03-2017.
 */

class Info
{
    private String description;
    private Image  image;
    private String name;
    private String sanitizedDescription;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSanitizedDescription() {
        return sanitizedDescription;
    }
    public void setSanitizedDescription(String sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }
}
