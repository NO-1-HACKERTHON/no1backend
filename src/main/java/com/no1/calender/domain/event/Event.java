package com.no1.calender.domain.event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.no1.calender.domain.calender.Calender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String eventName;

    private String eventContent;

    private String imageUrl;

    private String duration;

    @Temporal(TemporalType.DATE)
    private Date startTime;

    @Temporal(TemporalType.DATE)
    private Date endTime;

    private String place;

    @ManyToOne(fetch = FetchType.LAZY)
    private Calender calender;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<EventHashTag> eventHashTags;
}
