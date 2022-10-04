package com.studyCafe.domain;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    private String id;
    private String pw;
    private String email;
    private String tiket;

    @Column(name="remainingtime")
    private long remainingTime;


    public long getRemainingTime() {
        return remainingTime;
    }

    public String getTiket() {
        return tiket;
    }

    public void setTiket(String tiket) {

        if(tiket == "defaultHour"){
            this.remainingTime = Tiket.defaultHour.getHour();
        }else if(tiket == "twoHour"){
            this.remainingTime = Tiket.twoHour.getHour();
        }else if(tiket == "fourHour"){
            this.remainingTime = Tiket.fourHour.getHour();
        }

        this.tiket = tiket;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", email='" + email + '\'' +
                ", tiket='" + tiket + '\'' +
                ", remainingTime=" + remainingTime +
                '}';
    }
}
