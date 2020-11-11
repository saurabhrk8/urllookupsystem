package com.cisco.urllookupsystem.resource;

import com.cisco.urllookupsystem.entity.Url;
import com.cisco.urllookupsystem.model.ErrorResponse;
import com.cisco.urllookupsystem.model.UrlLookUpResponse;
import com.cisco.urllookupsystem.service.UrlLookUpService;
import com.cisco.urllookupsystem.utility.DecisionCode;
import com.cisco.urllookupsystem.utility.UrlConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UrlLookUpControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UrlLookUpService urlLookUpService;

    @Test
    public void findUrlDetailsTest() throws Exception {

        Url url = TestDataHelper.getUrl();
        final String hostName = "www.google.com";
        given(this.urlLookUpService.findUrl(hostName)).willReturn(url);
        given(this.urlLookUpService.makeDescion(url)).willReturn(DecisionCode.ALLOW);
        UrlLookUpResponse response = TestDataHelper.getUrlLookUpResponse(url);
        String resposetJason = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        this.mvc.perform(
                get(UrlConstants.URL_TEMPLATE_WITH_PARAM).contentType(MediaType.APPLICATION_JSON).content(resposetJason))
                .andExpect(status().isOk()).andExpect(content().json(resposetJason));

    }

    @Test
    public void findUrlDetailsSucussTest() throws Exception {

        Url url = TestDataHelper.getUrl();
        final String hostName = "www.google.com";
        given(this.urlLookUpService.findUrl(hostName)).willReturn(url);
        given(this.urlLookUpService.makeDescion(url)).willReturn(DecisionCode.ALLOW);
        UrlLookUpResponse response = TestDataHelper.getUrlLookUpResponse(url);
        String resposetJason = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);

        this.mvc.perform(get(UrlConstants.URL_TEMPLATE)
                .param("hostname", hostName)
                .param("port", "8080")
                .param("originalpath", "/abc/xyz")
                .param("query", "amount=2")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ALLOW"));
    }

    @Test
    public void notFoundUrlDetailsTest() throws Exception {

        Url url = TestDataHelper.getNullUrl();
        given(this.urlLookUpService.findUrl("www.mnc.com")).willReturn(url);
        ErrorResponse errorResponse = TestDataHelper.getErrorResponse();
        String resposetJason = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorResponse);
        this.mvc.perform(
                get(UrlConstants.URL_TEMPLATE_WITH_PARAM).contentType(MediaType.APPLICATION_JSON).content(resposetJason))
                .andExpect(status().isNotFound()).andExpect(content().json(resposetJason));

    }

    @Test
    public void invalidUrlDetailsTest() throws Exception {
        Url url = TestDataHelper.getUrl();
        given(this.urlLookUpService.findUrl("www.google.com")).willReturn(url);
        ErrorResponse errorResponse = TestDataHelper.getInvalidInputErrorResponse();
        String resposetJason = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorResponse);
        this.mvc.perform(
                get(UrlConstants.URL_INVALID_INPUT_PARAM).contentType(MediaType.APPLICATION_JSON).content(resposetJason))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findUrlDetailsFailureTest() throws Exception {

        Url url = TestDataHelper.getHarmFulUrl();
        final String hostName = "www.harmful.com";
        given(this.urlLookUpService.findUrl(hostName)).willReturn(url);
        given(this.urlLookUpService.makeDescion(url)).willReturn(DecisionCode.BLOCK);


        UrlLookUpResponse response = TestDataHelper.getHarmfulUrlLookUpResponse(url);
        String resposetJason = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);

        this.mvc.perform(get(UrlConstants.URL_TEMPLATE)
                .param("hostname", hostName)
                .param("port", "9090")
                .param("originalpath", "/trolent")
                .param("query", "scan=true")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("BLOCK"));
    }


}
