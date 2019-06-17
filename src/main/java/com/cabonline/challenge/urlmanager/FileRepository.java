package com.cabonline.challenge.urlmanager;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FileRepository implements Repository {

    private final Map<String, String> abbreviationToUrl;
    private final File file;

    public FileRepository(File file) throws IOException {
        this.file = file;
        this.abbreviationToUrl = new TreeMap<>();
        if (this.file.exists()) {
            readFile();
        }
        else {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
    }

    private void readFile() throws IOException {
        FileUtils.readLines(file, "UTF-8").stream()
                .map(String::trim)
                .filter(line -> !line.startsWith("#"))
                .filter(line -> line.contains("="))
                .map(line -> line.split("="))
                .forEach(pair->
                        this.abbreviationToUrl.put(
                                pair[0].trim(),
                                pair[1]));
    }

    private void writeFile() throws IOException {
        List<String> lines = abbreviationToUrl.entrySet().stream()
                .map(entry->entry.getKey()+"="+entry.getValue())
                .collect(Collectors.toList());
        FileUtils.writeLines(file, lines);
    }

    Map.Entry<String, String> getEntry(String url) {
        return abbreviationToUrl.entrySet().stream()
                .filter(entry->entry.getValue().equals(url))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Map<String, String> getAll() {
        return this.abbreviationToUrl;
    }

    @Override
    public String add(String abbreviation, String url) throws IOException {
        Map.Entry<String, String> entry = getEntry(url);
        if (entry != null) {
            throw new RuntimeException(entry.getKey()+" is already mapped to "+entry.getValue());
        }
        if (Objects.equals(abbreviation, url)) {
            return null;
        }
        String prevUrl = this.abbreviationToUrl.put(abbreviation, url);
        writeFile();
        return prevUrl;
    }

    @Override
    public String getUrl(String abbreviation) {
        return this.abbreviationToUrl.get(abbreviation);
    }

    @Override
    public String getAbbreviation(String url) {
        Map.Entry<String, String> entry = getEntry(url);
        return entry == null ? null : entry.getKey();
    }

    @Override
    public String removeAbbreviation(String abbreviation) throws IOException {
        String url = this.abbreviationToUrl.remove(abbreviation);
        writeFile();
        return url;
    }

    @Override
    public String removeUrl(String url) throws IOException {
        Map.Entry<String, String> entry = getEntry(url);
        if (entry == null) {
            return null;
        }
        this.abbreviationToUrl.remove(entry.getKey());
        writeFile();
        return entry.getKey();
    }

}
