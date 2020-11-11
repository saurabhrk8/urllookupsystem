package com.cisco.urllookupsystem.model;

import com.cisco.urllookupsystem.utility.DecisionCode;

import java.util.Objects;

public class UrlLookUpResponse {

    private String domainName;

    private DecisionCode status;

    private DomainDetails domainDetails;

    //constructor
    public UrlLookUpResponse(Builder builder) {
        this.domainName = builder.domainName;
        this.status = builder.status;
        this.domainDetails = builder.domainDetails;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public DomainDetails getDomainDetails() {
        return domainDetails;
    }

    public void setDomainDetails(DomainDetails domainDetails) {
        this.domainDetails = domainDetails;
    }

    public DecisionCode getStatus() {
        return status;
    }

    public void setStatus(DecisionCode status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UrlLookUpResponse{" +
                "domainName='" + domainName + '\'' +
                ", status=" + status +
                ", domainDetails=" + domainDetails +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlLookUpResponse that = (UrlLookUpResponse) o;
        return Objects.equals(domainName, that.domainName) &&
                status == that.status &&
                Objects.equals(domainDetails, that.domainDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domainName, status, domainDetails);
    }

    public static class Builder {
        private String domainName;
        private DecisionCode status;
        private DomainDetails domainDetails;

        public Builder() {
        }

        public Builder domainName(final String domainName) {
            this.domainName = domainName;
            return this;
        }

        public Builder status(final DecisionCode status) {
            this.status = status;
            return this;
        }

        public Builder domainDetails(final DomainDetails domainDetails) {
            this.domainDetails = domainDetails;
            return this;
        }

        public UrlLookUpResponse build() {
            return new UrlLookUpResponse(this);
        }
    }


}
