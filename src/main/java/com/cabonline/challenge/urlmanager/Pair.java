package com.cabonline.challenge.urlmanager;

import java.io.Serializable;

public class Pair implements Serializable {

    private String abbreviation;
    private String url;

    public Pair(String abbreviation, String url) {
        this.abbreviation = abbreviation;
        this.url = url;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return abbreviation+"="+url;
    }

}
