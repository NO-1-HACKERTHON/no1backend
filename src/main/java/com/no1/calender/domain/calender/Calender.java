package com.no1.calender.domain.calender;

import com.no1.calender.domain.Event;
import com.no1.calender.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calenderId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date likeCalender;

    @OneToOne(mappedBy = "calender")
    private Member member;

    @OneToMany(mappedBy = "calender", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Event> eventList;
}
