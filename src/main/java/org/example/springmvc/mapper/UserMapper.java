package org.example.springmvc.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.springmvc.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;


//这里混用了xml和注解配置映射器文件，xml配置的文件在resources/mapper/use.xml中
//@Repository
public interface UserMapper {
    List<User> find(Integer pageNo, Integer pageSize);

    User findByName(String username);

    void add(User user);

    void update(User user);

    void delete(User user);

    @Select("select count(*) from `user`")
    Long count();
}
