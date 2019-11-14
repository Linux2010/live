package cn.com.myproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by liyang-macbook on 2017/6/27.
 */
@SpringBootApplication
@EnableRedisHttpSession
@EnableDiscoveryClient
@EnableFeignClients
public class ApiApplication {

    private static final Logger logger = LoggerFactory.getLogger(ApiApplication.class);


    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler(); //single threaded by default
    }


    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
        logger.info("项目已启动");
        logger.debug("项目已启动");
    }
}
