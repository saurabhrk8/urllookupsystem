package com.cisco.urllookupsystem.controller;

import com.cisco.urllookupsystem.entity.Url;
import com.cisco.urllookupsystem.exceptions.InvalidInputException;
import com.cisco.urllookupsystem.exceptions.UrlRecordNotFound;
import com.cisco.urllookupsystem.model.DomainDetails;
import com.cisco.urllookupsystem.model.UrlLookUpResponse;
import com.cisco.urllookupsystem.service.UrlLookUpService;
import com.cisco.urllookupsystem.utility.DecisionCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlinfo")
public class UrlLookUpController {

    private static final Logger log = LoggerFactory.getLogger(UrlLookUpController.class);

    @Autowired
    private UrlLookUpService urlLookUpService;

    @RequestMapping(path = "/1/hostname_and_port/original_path_and_query_string", method = RequestMethod.GET)
    public UrlLookUpResponse findUrlDetails(@RequestParam("hostname") String hostName, @RequestParam("port") String port, @RequestParam("originalpath") String originalpath, @RequestParam("query") String query) {

        log.info("Inside findUrlDetails resounce method");

        //Invalid parameter check
        if (StringUtils.isEmpty(hostName) || StringUtils.isEmpty(port) || StringUtils.isEmpty(originalpath) || StringUtils.isEmpty(query)) {
            log.info("Invalid host name - " + hostName);
            throw new InvalidInputException("Invalid request parameter's");
        }
        //Service call to retrive Url details
        Url url = urlLookUpService.findUrl(hostName);

        if (url == null) {
            log.info("Url records not found for the host name - " + hostName);
            throw new UrlRecordNotFound("No records found");
        }
        //Prepare response
        UrlLookUpResponse response = convertRespose(url, originalpath, port);
        return response;
    }

    private UrlLookUpResponse convertRespose(Url url, String originalPath, String port) {
        log.info("Inside convertResponse method");
        DomainDetails domainDetails = new DomainDetails();
        DomainDetails.setServiceUrlList(domainDetails, url.getUrlProfileList());
        DecisionCode descionStatus = urlLookUpService.makeDescion(url);
        UrlLookUpResponse response = new UrlLookUpResponse.Builder().domainName(url.getHostName()).status(descionStatus).domainDetails(domainDetails).build();
        return response;
    }


}
