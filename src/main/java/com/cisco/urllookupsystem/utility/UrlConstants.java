package com.cisco.urllookupsystem.utility;

public interface UrlConstants {

    public static final String URL_TEMPLATE = "/urlinfo/1/hostname_and_port/original_path_and_query_string?";

    public static final String URL_TEMPLATE_WITH_PARAM = "/urlinfo/1/hostname_and_port/original_path_and_query_string?hostname=www.google.com&port=8080&originalpath=/abc/xyz&query=amount=2";

    public static final String URL_INVALID_INPUT_PARAM = "/urlinfo/1/hostname_and_port/original_path_and_query_string?port=8080&originalpath=/abc/xyz&query=amount=2";

    public static final String ERROR_RESPONSE_DETAILS = "Details for this url not available check the url again";

    public static final String ERROR_RESPONSE_MESSAGE = "for the hostname no records found";

    public static final String ERROR_RESPONSE_INVALID_DETAILS = "Please check request parameter's they are either wrong or missing";

    public static final String ERROR_RESPONSE_INVALID_MESSAGE = "Please check request parameter's";
}
