package zerobase.weather.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @NonNull
    private String weather;

    @Column
    @NonNull
    private String icon;

    @Column
    @NonNull
    private Double temperature;

    @Column
    @NonNull
    @Lob
    private String text;

    @Column
    @NonNull
    private LocalDate date; // 날짜가 쓰여지는 날짜

}
