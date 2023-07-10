package cn.knightzz.train.member.member.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 王天赐
 */
@SpringBootApplication
@ComponentScan("cn.knightzz")
public class TrainMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainMemberApplication.class, args);
    }

}
