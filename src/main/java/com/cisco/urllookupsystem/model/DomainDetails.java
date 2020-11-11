package com.cisco.urllookupsystem.model;

import com.cisco.urllookupsystem.entity.UrlProfile;

import java.util.List;
import java.util.Objects;

public class DomainDetails {

    public DomainDetails() {
    }

    public DomainDetails(List<UrlProfile> serviceUrlList) {
        this.serviceUrlList = serviceUrlList;
    }

    private List<UrlProfile>serviceUrlList;

    public List<UrlProfile> getServiceUrlList() {
        return serviceUrlList;
    }

    public static void setServiceUrlList(DomainDetails convertResponse, List<UrlProfile> serviceUrlList) {
        convertResponse.serviceUrlList = serviceUrlList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainDetails that = (DomainDetails) o;
        return Objects.equals(serviceUrlList, that.serviceUrlList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceUrlList);
    }

    @Override
    public String toString() {
        return "DomainDetails{" +
                "serviceUrlList=" + serviceUrlList +
                '}';
    }
}
