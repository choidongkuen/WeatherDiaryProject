package zerobase.weather.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    // Post방식으로 create/diary path로 요청을 보낼 때
    // 쿼리스트링으로 날짜, RequestBody에 일기 내용을 보내겠다.
    @PostMapping("/create/diary")
    void createDiardy(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date
    , @RequestBody String text){

        diaryService.createDiardy(date,text);
    }
}
