package com.cabonline.challenge.urlmanager;

//import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UrlController {

    private final AbbreviationService abbreviationService;

    @Autowired
    public UrlController(AbbreviationService abbreviationService) {
        this.abbreviationService = abbreviationService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Pair> list = abbreviationService.getAll().entrySet()
                .stream()
                .map(entry->new Pair(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        model.addAttribute("pairs", list);
        return "index";
    }

    boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    @GetMapping("/add")
    public String add(Model model,
                      @RequestParam(required = false) String abbreviation,
                      @RequestParam String url) throws IOException {
        if (isBlank(abbreviation)) {
            this.abbreviationService.getOrCreateAbbreviation(url);
        }
        else {
            this.abbreviationService.add(abbreviation, url);
        }
        return home(model);
    }

    @GetMapping("/getAbbreviation")
    public String getAbbreviation(Model model,
                                  @RequestParam String url) throws IOException {
        this.abbreviationService.getOrCreateAbbreviation(url);
        return home(model);
    }

    @GetMapping("/getUrl")
    public String getUrl(Model model,
                         @RequestParam String abbreviation) {
        model.addAttribute("redirect", this.abbreviationService.getUrl(abbreviation));
        return "redirect";
    }

    @GetMapping("/removeAbbreviation")
    public String removeAbbreviation(Model model, @RequestParam String abbreviation) throws IOException {
        this.abbreviationService.removeAbbreviation(abbreviation);
        return home(model);
    }

    @GetMapping("/removeUrl")
    public String removeUrl(Model model,
                            @RequestParam String url) throws IOException {
        this.abbreviationService.removeUrl(url);
        return home(model);
    }

}
