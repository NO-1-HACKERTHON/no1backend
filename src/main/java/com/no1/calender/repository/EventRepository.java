package com.no1.calender.repository;

import com.no1.calender.domain.event.Event;
import com.no1.calender.dto.event.EventResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    EventResponseDto findByEventId(Long eventId);

    List<EventResponseDto> findAllByEventId(Long eventId);
}
