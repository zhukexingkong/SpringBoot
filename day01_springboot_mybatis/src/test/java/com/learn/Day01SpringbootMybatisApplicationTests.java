package com.learn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.mappers.UserMapper;
import com.learn.bean.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class Day01SpringbootMybatisApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
    }
    @Test
    public void testRedis() throws JsonProcessingException {
        String users = (String) redisTemplate.boundValueOps("user.findAll").get();
        if (StringUtils.isEmpty(users)) {
            List<User> userList = userMapper.findAll();
            ObjectMapper jsonFormat = new ObjectMapper();
            users = jsonFormat.writeValueAsString(userList);
            redisTemplate.boundValueOps("user.findAll").set(users);
            System.out.println("==============从数据库中获取用户数据 ===================");
        }else {
            System.out.println("==============从Redis缓存中获取用户数据 ===================");
        }
        System.out.println(users);
    }

    @Test
    public void testREST() {
        String url = "https://www.baidu.com";
        String json = restTemplate.getForObject(url, String.class);
        System.out.println(json);
    }
}
