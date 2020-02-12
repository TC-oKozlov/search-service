package com.tigerconnect.searchservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "account")
public class Account {
    @Id
    private String id;

    private String accountType;
    private String status;
    private String organization;
    private String consumer;
    private Boolean verified;
    private String name;
    private String displayName;
    private String token;

    private String type;

    public Account(String status, String organization, String name, String token, String type) {
        this.status = status;
        this.organization = organization;
        this.name = name;
        this.token = token;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", accountType='" + accountType + '\'' +
                ", status='" + status + '\'' +
                ", organization='" + organization + '\'' +
                ", consumer='" + consumer + '\'' +
                ", verified=" + verified +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", token='" + token + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
