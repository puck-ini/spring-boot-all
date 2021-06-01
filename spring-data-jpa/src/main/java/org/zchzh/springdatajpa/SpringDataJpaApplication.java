package org.zchzh.springdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.zchzh.springdatajpa.repository.BaseRepoImpl;


@EnableJpaRepositories(repositoryBaseClass = BaseRepoImpl.class)
@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

}
