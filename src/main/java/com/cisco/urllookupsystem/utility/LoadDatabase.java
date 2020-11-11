package com.cisco.urllookupsystem.utility;

import com.cisco.urllookupsystem.dao.UrlProfileRepository;
import com.cisco.urllookupsystem.dao.UrlRepository;
import com.cisco.urllookupsystem.entity.Url;
import com.cisco.urllookupsystem.entity.UrlProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UrlRepository urlRepository, UrlProfileRepository urlProfileRepository) {

        return args -> {

            UrlProfile googleProfile1 = new UrlProfile("8080", "/abc/xyz", "amount=2", "ACCEPT","https");
            UrlProfile googleProfile2 = new UrlProfile("8080", "/abc/xyz", "amount=2", "ACCEPT","https");
            List<UrlProfile> urlProfileList= Arrays.asList(googleProfile1,googleProfile2);
            Url googleUrl=new Url("www.google.com",urlProfileList);

            log.info("Preloading " + urlRepository.save(googleUrl));

            UrlProfile ciscoProfile1 = new UrlProfile("6080", "/cisco/umbrella", "scan=true", "ACCEPT","https");
            UrlProfile ciscoProfile2 = new UrlProfile("9050", "/cisco/router", "routerId=2678", "ACCEPT","https");
            urlProfileList= Arrays.asList(ciscoProfile1,ciscoProfile2);
            Url ciscoeUrl=new Url("www.cisco.com",urlProfileList);

            log.info("Preloading " + urlRepository.save(ciscoeUrl));


            UrlProfile exampleMalwareProfile1 = new UrlProfile("43000", "/troleynt", "password=true", "BLOCK","http");
            UrlProfile exampleMalwareProfile2 = new UrlProfile("43000", "/virus", "cvc=3678", "BLOCK","http");
            urlProfileList= Arrays.asList(exampleMalwareProfile1,exampleMalwareProfile2);
            Url exampleMalwareUrl=new Url("www.examplemalware.com",urlProfileList);

            log.info("Preloading " + urlRepository.save(exampleMalwareUrl));


            UrlProfile fraudProfile1 = new UrlProfile("9095", "/fraud/money", "password=true", "BLOCK","http");
            UrlProfile fraudProfile2 = new UrlProfile("9050", "/credit/card", "cvc=3678", "BLOCK","http");
            urlProfileList= Arrays.asList(fraudProfile1,fraudProfile2);
            Url fraudUrl=new Url("www.xyzfraud.com",urlProfileList);

            log.info("Preloading " + urlRepository.save(fraudUrl));

        };
    }


}