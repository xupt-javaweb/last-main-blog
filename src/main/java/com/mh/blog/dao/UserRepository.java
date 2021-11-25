package com.mh.blog.dao;

import com.mh.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
                                                        /*Long是User中的主键类型*/
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);

    User findByUsernameAndNickname(String username,String nickname);

    User findByUsernameAndEmail(String username,String email);
}
