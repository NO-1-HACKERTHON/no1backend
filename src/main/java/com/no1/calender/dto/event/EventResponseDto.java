package com.no1.calender.dto.event;

import com.no1.calender.domain.event.Event;
import com.no1.calender.domain.event.EventHashTag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class EventResponseDto {
    private Long eventId;

    private String eventName;

    private String eventContent;

    private String imageUrl;

    private String duration;

    private String startTime;

    private String endTime;

    private String place;

    private List<EventHashTag> eventHashTags;

    public EventResponseDto(Event event) {
        this.eventId = event.getEventId();
        this.eventName = event.getEventName();
        this.eventContent = event.getEventContent();
        this.imageUrl = event.getImageUrl();
        this.duration = event.getDuration();
        this.startTime = event.getStartTime().toString();
        this.endTime = event.getEndTime().toString();
        this.place = event.getPlace();
        this.eventHashTags = event.getEventHashTags();
    }
}
