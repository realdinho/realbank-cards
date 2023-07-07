package com.realdiv.cards.model;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Property {
    
    private String msg;
    private String buildVersion;
    private Map<String, String> mailDetails;
    private List<String> activeBranches;

    public Property(String msg, String buildVersion, Map<String, String> mailDetails, List<String> activeBranches) {
        this.msg = msg;
        this.buildVersion = buildVersion;
        this.mailDetails = mailDetails;
        this.activeBranches = activeBranches;
    }
}
