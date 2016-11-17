package com.example.mahe.voyage;

public class Login {

    private String u_name;
    private String u_pass;

    public Login() {
    }

    public Login(String u_name, String u_pass) {
        this.u_name = u_name;
        this.u_pass = u_pass;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public void setU_pass(String u_pass) {
        this.u_pass = u_pass;
    }

    public String getU_name() {
        return u_name;
    }

    public String getU_pass() {
        return u_pass;
    }
}
