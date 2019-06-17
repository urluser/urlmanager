package com.cabonline.challenge.urlmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.io.File;
import java.io.IOException;

@Configuration
//@EnableWebMvc
//@ComponentScan
public class Config implements WebMvcConfigurer {

    private static final String REPOSITORY_FILE_NAME = "repository.txt";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
/*
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        registry.viewResolver(resolver);
    }
*/
    @Bean
    public Repository getRepository() throws IOException {
        // In a production environment this method would instead return a DataBaseRepository based on a SQL-database
        return new FileRepository(new File(REPOSITORY_FILE_NAME));
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public AbbreviationService getAbbreviationService(Repository repository) {
        return new AbbreviationService(repository);
    }

}
