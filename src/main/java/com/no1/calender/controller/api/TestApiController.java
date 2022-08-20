package com.no1.calender.controller.api;

import com.no1.calender.dto.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestApiController {

    @GetMapping
    public ResponseEntity<Test> test() {
        Test test = new Test();
        test.setAbc("모행");
        test.setCef("빨리되거라");

        return ResponseEntity.ok(test);
    }
}
