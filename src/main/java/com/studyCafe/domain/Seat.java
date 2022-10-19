package com.studyCafe.domain;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
public class Seat {
    @Id
    private String id;
    @Column(name="SEATNUMBER")
    private long seatNumber;
    @Column(name="STARTTIME")
    private Timestamp startTime;
    @Column(name="ENDTIME")
    private Timestamp endTime;

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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id='" + id + '\'' +
                ", seatNumber=" + seatNumber +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
