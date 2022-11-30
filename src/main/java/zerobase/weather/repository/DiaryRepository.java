package zerobase.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.weather.entity.Diary;

public interface DiaryRepository extends JpaRepository<Diary,Long> {
}
