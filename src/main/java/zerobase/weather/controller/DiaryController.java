package zerobase.weather.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.entity.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    // create
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date
    , @RequestBody String text){

        diaryService.createDiary(date,text);
    }

    // read(특정 날짜)
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date){

        // 해당 날짜의 일기 리턴
        return diaryService.readDiary(date);

    }

    // read(날짜 범위: 두개의 날짜 입력)
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate startDate,
                    @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate endDate){


        // startDate ~ endDate 모든 일기 리턴
        return diaryService.readDiaries(startDate,endDate);

    }
}
