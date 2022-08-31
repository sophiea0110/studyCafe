package com.studyCafe;


import com.studyCafe.repository.JpaMemberRepository;
import com.studyCafe.repository.JpaSeatRepository;
import com.studyCafe.repository.MemberRepository;
import com.studyCafe.repository.SeatRepository;
import com.studyCafe.service.MemberService;
import com.studyCafe.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;
    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberService memberService() { return new MemberService(memberRepository()); }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

    @Bean
    public SeatService seatService() { return new SeatService(seatRepository()); }

    @Bean
    public SeatRepository seatRepository() { return new JpaSeatRepository(em); }

}
