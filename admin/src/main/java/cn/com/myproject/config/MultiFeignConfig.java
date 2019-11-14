package cn.com.myproject.config;

import cn.com.myproject.util.FeignSpringFormEncoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * Created by liyang-macbook on 2017/8/4.
 */
//@Configuration
@Deprecated
public class MultiFeignConfig {

//    @Bean
//    public Encoder encoder() {
//        return new FeignSpringFormEncoder();
//    }
    @Bean
//    @Primary
//    @Scope("prototype")
    public Encoder multipartFormEncoder() {
        return new SpringFormEncoder();
    }

    @Bean
    public feign.Logger.Level multipartLoggerLevel() {
        return feign.Logger.Level.FULL;
    }
}
