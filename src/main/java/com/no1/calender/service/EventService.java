package com.no1.calender.service;

import com.no1.calender.domain.event.Event;
import com.no1.calender.domain.event.EventHashTag;
import com.no1.calender.dto.event.EventResponseDto;
import com.no1.calender.dto.search.SearchCondition;
import com.no1.calender.repository.EventCustomRepository;
import com.no1.calender.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventCustomRepository eventCustomRepository;

    @Transactional
    public List<EventResponseDto> findMainEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public EventResponseDto findEvent(Long eventId) {
        return eventRepository.findByEventId(eventId);
    }

    public List<EventResponseDto> search1(SearchCondition searchCondition) {
        List<Event> searchSubject = eventCustomRepository.search(searchCondition);
        return searchSubject.stream().map(EventResponseDto::new).collect(Collectors.toList());
    }
}
