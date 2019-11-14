package cn.com.myproject.external;

import cn.com.myproject.redis.IRedisService;
import cn.com.myproject.sysuser.service.ISecurityService;
import cn.com.myproject.sysuser.service.impl.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyang-macbook on 2017/8/16.
 */
@RestController
@RequestMapping("/refresh")
public class RefreshController {

    @Autowired
    private ISecurityService securityService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/resource")
    public String resource() {
        securityService.init();
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.select(0);
        connection.del(new StringRedisSerializer().serialize("cn.com.myproject.sysuser.mapper.SysResourceMapper"));
        connection.close();
        return "success";
    }
}
