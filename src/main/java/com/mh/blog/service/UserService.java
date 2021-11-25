package com.mh.blog.service;

import com.mh.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    /*用户名，密码的检验*/
    User checkLogUser(String username,String password);

    User checkSignUser(String username,String nickname);

    void save(User user);

    /*分页查询*/
    Page<User> listUser(Pageable pageable);

    User getUser(Long id);

    User checkEditUser(String username,String email);

    User updateUser(Long id,User user);

    void deletedUser(Long id);
}
