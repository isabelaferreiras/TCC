package com.isabela.TCC.domain.usuario.role;

public enum UserRole {

    EMPRESA("empresa"),
    PROFISSIONAL("profissional");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
