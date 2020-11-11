package com.cisco.urllookupsystem.resource;

import com.cisco.urllookupsystem.entity.Url;
import com.cisco.urllookupsystem.entity.UrlProfile;
import com.cisco.urllookupsystem.model.DomainDetails;
import com.cisco.urllookupsystem.model.ErrorResponse;
import com.cisco.urllookupsystem.model.UrlLookUpResponse;
import com.cisco.urllookupsystem.utility.DecisionCode;
import com.cisco.urllookupsystem.utility.UrlConstants;

import java.util.Arrays;
import java.util.List;

public class TestDataHelper {

    public static Url getUrl(){

        UrlProfile googleProfile1 = new UrlProfile("8080", "/abc/xyz", "amount=2", "ALLOW","https");
        UrlProfile googleProfile2 = new UrlProfile("8080", "/abc/xyz", "amount=2", "ALLOW","https");
        List<UrlProfile> urlProfileList= Arrays.asList(googleProfile1,googleProfile2);
        Url googleUrl=new Url("www.google.com",urlProfileList);

        return  googleUrl;
    }

    public static Url getHarmFulUrl(){

        UrlProfile harmfulUrlProfile1 = new UrlProfile("9090", "/trolent", "scan=true", "BLOCK","https");
        UrlProfile harmfulUrlProfile2 = new UrlProfile("9090", "/trolent", "scan=true", "BLOCK","https");
        List<UrlProfile> urlProfileList= Arrays.asList(harmfulUrlProfile1,harmfulUrlProfile2);
        Url harmfulUrl=new Url("www.harmful.com",urlProfileList);

        return  harmfulUrl;
    }

    public static UrlLookUpResponse getUrlLookUpResponse(Url url) {
        DomainDetails domainDetails = new DomainDetails();
        DomainDetails.setServiceUrlList(domainDetails, url.getUrlProfileList());
        UrlLookUpResponse response = new UrlLookUpResponse.Builder().domainName(url.getHostName()).status(DecisionCode.ALLOW).domainDetails(domainDetails).build();
        return response;
    }

    public static UrlLookUpResponse getHarmfulUrlLookUpResponse(Url url) {
        DomainDetails domainDetails = new DomainDetails();
        DomainDetails.setServiceUrlList(domainDetails, url.getUrlProfileList());
        UrlLookUpResponse response = new UrlLookUpResponse.Builder().domainName(url.getHostName()).status(DecisionCode.BLOCK).domainDetails(domainDetails).build();
        return response;
    }

    public static Url getNullUrl(){
        Url googleUrl=null;
        return  googleUrl;
    }

    public static ErrorResponse getErrorResponse(){
        ErrorResponse errorResponse = new ErrorResponse(UrlConstants.ERROR_RESPONSE_MESSAGE, UrlConstants.ERROR_RESPONSE_DETAILS);
        return errorResponse;
    }

    public static ErrorResponse getInvalidInputErrorResponse(){
        ErrorResponse errorResponse = new ErrorResponse(UrlConstants.ERROR_RESPONSE_INVALID_MESSAGE,UrlConstants.ERROR_RESPONSE_INVALID_DETAILS);
        return errorResponse;
    }

}
