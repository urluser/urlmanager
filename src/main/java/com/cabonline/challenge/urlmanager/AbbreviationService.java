package com.cabonline.challenge.urlmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class AbbreviationService {

    private final Repository repository;

    @Autowired
    public AbbreviationService(Repository repository) {
        this.repository = repository;
    }

    Map<String, String> getAll() {
        return this.repository.getAll();
    }

    String createAbbreviationBetweenDots(String s) {
        String[] array = s.split("[\\-_]");
        if (array.length <= 1) {
            return s;
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (String t : array) {
                sb.append(t.charAt(0));
            }
            return sb.toString();
        }
    }

    public String getOrCreateAbbreviation(String url) throws IOException {
        String abbreviation = repository.getAbbreviation(url);
        if (abbreviation != null) {
            return abbreviation;
        }
        String[] array = url.split("/");
        for (int i=0; i<array.length; ++i) {
            String[] subArray = array[i].split("\\.");
            for (int j=0; j<subArray.length; ++j) {
                abbreviation = repository.getAbbreviation(subArray[j]);
                if (abbreviation == null) {
                    abbreviation = createAbbreviationBetweenDots(subArray[j]);
                    repository.add(abbreviation, subArray[j]);
                }
                subArray[j] = abbreviation;
            }
            array[i] = String.join(".", subArray);
        }
        return String.join("/", array);
    }

    public String getUrl(String abbreviation) {
        String url = repository.getUrl(abbreviation);
        if (url != null) {
            return url;
        }
        String[] array = abbreviation.split("/");
        for (int i=0; i<array.length; ++i) {
            String[] subArray = array[i].split("\\.");
            for (int j=0; j<subArray.length; ++j) {
                url = repository.getUrl(subArray[j]);
                if (url != null) {
                    subArray[j] = url;
                }
            }
            array[i] = String.join(".", subArray);
        }
        return String.join("/", array);
    }

    public String add(String abbreviation, String url) throws IOException {
        return this.repository.add(abbreviation, url);
    }

    public String removeAbbreviation(String abbreviation) throws IOException {
        return this.repository.removeAbbreviation(abbreviation);
    }

    public String removeUrl(String url) throws IOException {
        return this.repository.removeUrl(url);
    }

}
