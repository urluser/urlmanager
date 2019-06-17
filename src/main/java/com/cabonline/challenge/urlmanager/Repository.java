package com.cabonline.challenge.urlmanager;

import java.io.IOException;
import java.util.Map;

public interface Repository {

    Map<String, String> getAll();
    String add(String abbreviation, String url) throws IOException;
    String getUrl(String abbreviation);
    String getAbbreviation(String url);
    String removeAbbreviation(String abbreviation) throws IOException;
    String removeUrl(String url) throws IOException;

}
