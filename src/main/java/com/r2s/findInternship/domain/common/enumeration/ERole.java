package com.r2s.findInternship.domain.common.enumeration;

public enum ERole {
    Admin("Role_Admin"),
    Candidate("Role_Candidate"),
    HR("Role_HR"),
    Partner("Role_Partner");

    private final String NAME;

    ERole(String NAME) {
        this.NAME = NAME;
    }

    @Override
    public String toString() {
        return NAME;
    }
}