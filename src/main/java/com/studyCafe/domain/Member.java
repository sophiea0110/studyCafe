package com.studyCafe.domain;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    private String id;
    private String pw;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
