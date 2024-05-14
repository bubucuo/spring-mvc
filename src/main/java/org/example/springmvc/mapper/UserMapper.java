package org.example.springmvc.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.springmvc.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> find(Integer pageNo, Integer pageSize);

    User findByName(String username);

    void add(User user);

    void update(User user);

    void delete(User user);

    @Select("select count(*) from `user`")
    Long count();
}
