package com.cisco.urllookupsystem.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UrlProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String portNumber;

    private String originalPath;

    private String queryString;

    private String remarks;

    private String protocol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String pornNumber) {
        this.portNumber = pornNumber;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public UrlProfile(){
    }

    public UrlProfile(String portNumber, String originalPath, String queryString, String remarks, String protocol) {
        this.portNumber = portNumber;
        this.originalPath = originalPath;
        this.queryString = queryString;
        this.remarks = remarks;
        this.protocol=protocol;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
