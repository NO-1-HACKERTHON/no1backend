package com.no1.calender.domain.event;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventHashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventHashTagId;

    private String eventHashTag;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;
}
