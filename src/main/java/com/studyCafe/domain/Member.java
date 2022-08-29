package com.studyCafe.domain;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    private String id;
    private String pw;
    @Column(name="SEATNUMBER")
    private long seatNumber;

    public long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(long seatNumber) {
        this.seatNumber = seatNumber;
    }

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
