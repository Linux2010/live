package cn.com.myproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Thibaud Leprêtre
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableRedisHttpSession
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableScheduling
public class AdminApplication extends WebMvcConfigurerAdapter {

	@Bean
	public TaskScheduler taskScheduler() {
		return new ConcurrentTaskScheduler(); //single threaded by default
	}


	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
