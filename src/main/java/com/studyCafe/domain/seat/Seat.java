package com.studyCafe.domain.seat;

import javax.persistence.*;

@Entity(name = "Seat")
public class Seat {
    @Id
    @GeneratedValue
    private String memberId;
    private Long seatNumber;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Long seatNumber) {
        this.seatNumber = seatNumber;
    }
}
