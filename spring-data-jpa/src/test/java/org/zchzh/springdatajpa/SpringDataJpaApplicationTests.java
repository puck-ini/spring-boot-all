package org.zchzh.springdatajpa;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zchzh.springdatajpa.dto.UserDTO;
import org.zchzh.springdatajpa.repository.UserRepo;

import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class SpringDataJpaApplicationTests {

    @Autowired
    private UserRepo userRepo;

    @Test
    void contextLoads() {

        List<Map<String, Object>> dto = userRepo.findByUserId(1L);
        log.info(dto.size() + "");
    }

}
