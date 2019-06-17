package com.cabonline.challenge.urlmanager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class AbbreviationServiceTest {

    private File repositoryFile;
    private AbbreviationService service;

    @Before
    public void setUp() throws IOException {
        this.repositoryFile = new File(System.getProperty("java.io.tmpdir"), "repository.txt");
        this.service = new AbbreviationService(new FileRepository(this.repositoryFile));
    }

    @After
    public void tearDown() {
        if (this.repositoryFile.exists()) {
            this.repositoryFile.delete();
        }
    }

    @Test
    public void getAbbreviationBetweenDots_withoutSplit() {
        // Given
        String url = "abc#def\\ghijkl";
        String expected = url;

        // When
        String actual = this.service.createAbbreviationBetweenDots(url);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void getAbbreviationBetweenDots_withSplit() {
        // Given
        String url = "abc-def-ghi_jkl";
        String expected = "adgj";

        // When
        String actual = this.service.createAbbreviationBetweenDots(url);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void getAbbreviation() throws IOException {
        // Given
        String url = "http://www.laughing-out-loud.com/FelisSilvretisCatus.aspx";
        String expected = "http://www.lol.com/FelisSilvretisCatus.aspx";

        // When
        String actual = this.service.getOrCreateAbbreviation(url);

        // Then
        assertEquals(expected, actual);
    }

}
