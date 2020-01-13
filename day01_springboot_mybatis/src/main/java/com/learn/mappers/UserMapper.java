package com.learn.mappers;

import com.learn.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    void save(User user);
    void delete(Integer id);
    void update(User user);
    List<User> findAll();
    User findById(Integer id);
}
