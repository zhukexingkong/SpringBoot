package com.learn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.mappers.UserMapper;
import com.learn.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.List;

@SpringBootTest
class Day01SpringbootMybatisApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;

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
}
