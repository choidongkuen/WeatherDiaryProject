package zerobase.weather.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="date_weather")

// 일기 작성과 상관 없이 매일 정해진 시각(1AM) 전날 날씨 저장을 위한 엔티티
public class DateWeather {

    @Id
    private LocalDate date;

    @Column
    private String weather;

    @Column
    private String icon;

    @Column
    private Double temperature;


}
