package com.cisco.urllookupsystem.service;

import com.cisco.urllookupsystem.dao.UrlRepository;
import com.cisco.urllookupsystem.entity.Url;
import com.cisco.urllookupsystem.entity.UrlProfile;
import com.cisco.urllookupsystem.utility.DecisionCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlLookupServiceImpl implements UrlLookUpService {

    private static final Logger log = LoggerFactory.getLogger(UrlLookupServiceImpl.class);

    @Autowired(required = true)
    private UrlRepository urlRepository;

    @Override
    public Url findUrl(String hostName) {
        log.info("Inside service impl method findUrl ");
        Url url = urlRepository.findByHostName(hostName);
        return url;
    }


    @Override
    public UrlProfile retriveurlprofile(Url url, String orginalPath, String portNumber) {
        return null;
    }

    @Override
    public DecisionCode makeDescion(Url url) {
        log.info("Inside service impl method makeDescion");
        DecisionCode decisionCode = DecisionCode.ALLOW;

        for (UrlProfile urlProfile : url.getUrlProfileList()) {
            if ("BLOCK".equalsIgnoreCase(urlProfile.getRemarks())) {
                decisionCode = DecisionCode.BLOCK;
            }
        }
        log.info("the descion made is  - " + decisionCode);
        return decisionCode;
    }
}
