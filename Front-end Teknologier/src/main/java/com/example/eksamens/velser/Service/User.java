package com.example.eksamens.velser.Service;


//Bruges til at demonstrere thymeleaf
public class User {

    private String firstnameAndLastname;
    private String role;
    private String comment;

    public User() {
    }

    public User(String firstnameAndLastname, String role, String comment) {
        this.firstnameAndLastname = firstnameAndLastname;
        this.role = role;
        this.comment = comment;
    }

    public String getFirstnameAndLastname() {
        return firstnameAndLastname;
    }

    public void setFirstnameAndLastname(String firstnameAndLastname) {
        this.firstnameAndLastname = firstnameAndLastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
