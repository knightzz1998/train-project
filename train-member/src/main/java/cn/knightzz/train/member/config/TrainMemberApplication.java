package cn.knightzz.train.member.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @author 王天赐
 */
@SpringBootApplication
@ComponentScan("cn.knightzz")
@MapperScan("cn.knightzz.train.member.mapper")
public class TrainMemberApplication {

    private static final Logger LOG = LoggerFactory.getLogger(TrainMemberApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TrainMemberApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("测试地址: \thttp://127.0.0.1:{}{}/hello", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
    }
}
