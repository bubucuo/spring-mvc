package org.example.springmvc.service;

import org.example.springmvc.model.PageModel;
import org.example.springmvc.model.PageRequest;
import org.example.springmvc.model.User;

public interface UserService {

    PageModel<User> list(PageRequest pageRequest);

    User add(User user);
    User update(User user);

    Object delete(User user);

    User findByName(String name);

    User login(User user);

    void logout(String token);

    User findByToken(String token);
}
