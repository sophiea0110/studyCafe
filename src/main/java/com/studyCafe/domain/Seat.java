package com.studyCafe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Seat {
    @Id
    private String id;
    @Column(name="SEATNUMBER")
    private long seatNumber;
    @Column(name="STARTTIME")
    private Date startTime;
    @Column(name="ENDTIME")
    private Date endTime;
    @Column(name="REMAININGTIME")
    private Date remainingTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(long seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(Date remainingTime) {
        this.remainingTime = remainingTime;
    }
}
