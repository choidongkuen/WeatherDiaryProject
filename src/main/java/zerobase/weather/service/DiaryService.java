package zerobase.weather.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.entity.DateWeather;
import zerobase.weather.entity.Diary;
import zerobase.weather.repository.DateWeatherRepository;
import zerobase.weather.repository.DiaryRepository;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
// Transactional -> DiaryService(DB 작업이 집중된 영역)
public class DiaryService {


    // 왜? 이렇게 가져오냐?
    // 실제 실무에서는 local, 실제 운영환경, Dev 환경 => 환경별로 DB 구분
    // 따라서 유연성을 위해 @Value 어노테이션 사용
    private String apiKey = "813269163e31fcdb7e20a4cb0d5fd313";
    private final DiaryRepository diaryRepository;
    private final DateWeatherRepository dateWeatherRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createDiary(LocalDate date, String text) {


        // 1. openweathermap 데이터 가져오기
        // String weatherData = getWeatherString();

        // 2. 받아온 날씨 json 파싱하기
        // Map<String, Object> parsedWeather = parseWeather(weatherData);

        // 3. 파싱된 데이터 & 일기 값 db에 넣기

        // 다른 방법 => DB or API 하나의 메소드로 묶어버리기
        DateWeather dateWeather = getDateWeather(date);

        Diary nowDiary = new Diary();
        nowDiary.setDateWeather(dateWeather);
        nowDiary.setText(text);
        diaryRepository.save(nowDiary);
    }

    private DateWeather getDateWeather(LocalDate date){

        List<DateWeather> dateWeatherListFromDB = dateWeatherRepository.findAllByDate(date);
        if(dateWeatherListFromDB.size() == 0){
            // DB 존재하지 않는다면 API로부터 가져와야 한다.
            return getWeatherFromApi();
        }
        return dateWeatherListFromDB.get(0);
    }

    // openweathermap 데이터 가져오는 메소드
    private String getWeatherString() {

        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid="
                + apiKey;


        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader br;

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();
            return response.toString();

        } catch (Exception e) {
            return "failed to get response";
        }

    }

    // 가져온 데이터를 파싱하는 메소드
    private Map<String, Object> parseWeather(String jsonString) {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;

        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> resultMap = new HashMap<>();

        JSONObject mainData = (JSONObject) jsonObject.get("main");
        resultMap.put("temp", mainData.get("temp"));

        JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
        JSONObject weatherData = (JSONObject) weatherArray.get(0);

        resultMap.put("main", weatherData.get("main"));
        resultMap.put("icon", weatherData.get("icon"));

        return resultMap;
    }

    // API를 이용하여 데이터를 가져오는 메소드
    private DateWeather getWeatherFromApi() {

        String weatherData = getWeatherString();

        Map<String, Object> parsedWeather = parseWeather(weatherData);

        return DateWeather.builder()
                      .date(LocalDate.now())
                      .weather(parsedWeather.get("main").toString())
                      .icon(parsedWeather.get("icon").toString())
                      .temperature((Double) parsedWeather.get("temp")).build();


    }

    // 해당 날짜에 해당하는 일기 가져오는 메소드
    @Transactional(readOnly = true)
    public List<Diary> readDiary(LocalDate date) {

        return diaryRepository.findAllByDate(date);

    }

    // 날짜의 범위에 해당하는 일기 가져오는 메소드
    public List<Diary> readDiaries(LocalDate startDate, LocalDate endDate) {

        return diaryRepository.findAllByDateBetween(startDate, endDate);

    }

    // 해당 날짜에 해당하는 일기 수정하는 메소드
    public void updateDiary(LocalDate date, String text) {

        Diary nowDiary = diaryRepository.getFirstByDate(date);
        nowDiary.setText(text);

        diaryRepository.save(nowDiary);

    }

    // 해당 날짜에 해당하는 일기 삭제하는 메소드
    public void deleteDiary(LocalDate date) {

        diaryRepository.deleteAllByDate(date);

    }

    // 매일 새벽 1시에 전날 날씨 저장하는 메소드
    @Transactional()
    @Scheduled(cron = "0/5 * * * * *")
    public void saveWeatherDate() {

        dateWeatherRepository.save(getWeatherFromApi());

    }


}


