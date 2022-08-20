package com.no1.calender.controller.api;

import com.no1.calender.dto.event.EventResponseDto;
import com.no1.calender.dto.search.SearchCondition;
import com.no1.calender.dto.search.SearchType;
import com.no1.calender.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Api(tags = "메인화면 정보 및 검색")
@RestController
@RequestMapping("main")
public class EventApiController {
    private final EventService eventService;

    // 메인화면 행사리스트
    @GetMapping
    @ApiOperation(value = "메인화면 행사리스트")
    public ResponseEntity<List<EventResponseDto>> mainList() {
        return ResponseEntity.ok(eventService.findMainEvents());
    }

    // 메인화면 상세페이지
    @GetMapping("{eventId}")
    @ApiOperation(value = "메인화면 상세페이지")
    public ResponseEntity<EventResponseDto> findOne(@RequestParam Long eventId) {
        return ResponseEntity.ok(eventService.findEvent(eventId));
    }

    // 검색
    @GetMapping("search")
    @ApiOperation(value = "검색")
    public ResponseEntity<List<EventResponseDto>> searchMain(@RequestParam SearchType searchType,
                                                             @RequestParam String keyword) {
        return ResponseEntity.ok(eventService.search1(new SearchCondition(keyword, searchType)));
    }
}
