package zerobase.weather;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zerobase.weather.domain.Memo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


// DataBase Test 때문에 실제 DB 내용의 변경을 막기 위한 어노테이션 => Transactional
@SpringBootTest
//@Transactional
public class JdbcMemoRepositoryTest {


    @Autowired
    JdbcMemoRepository jdbcMemoRepository;


    @Test
    void insertMemoTest(){
        // given
        Memo newMemo = new Memo(2, "This is new memo2");
        // when
        jdbcMemoRepository.save(newMemo);
        // then
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(), "This is new memo2");
     }


     @Test
     void findAllMemoTest(){
         // given
         List<Memo> memoList = jdbcMemoRepository.findAll();
         // when

         // then
         assertNotNull(memoList);
      }



}
