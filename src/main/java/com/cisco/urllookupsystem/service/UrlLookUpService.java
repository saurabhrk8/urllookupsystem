package com.cisco.urllookupsystem.service;

import com.cisco.urllookupsystem.entity.Url;
import com.cisco.urllookupsystem.entity.UrlProfile;
import com.cisco.urllookupsystem.utility.DecisionCode;

public interface UrlLookUpService {

    public Url findUrl(String hostName);

    public UrlProfile retriveurlprofile(Url url, String orginalPath, String portNumber);

    public DecisionCode makeDescion(Url url);

}
