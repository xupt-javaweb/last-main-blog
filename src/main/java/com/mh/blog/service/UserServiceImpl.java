package com.mh.blog.service;

import com.mh.blog.NotFoundException;
import com.mh.blog.dao.UserRepository;
import com.mh.blog.entity.Type;
import com.mh.blog.entity.User;
import com.mh.blog.util.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService{
    /*输入的用户名与密码需要与数据库中的数据进行比对，所以在此创建Dao层*/
   @Autowired
   private UserRepository userRepository;
    /*登录校验用户名密码是否正确*/
    @Override
    public User checkLogUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
    /*注册校验用户名或昵称是否已存在*/
    @Override
    public User checkSignUser(String username, String nickname) {
        User user = userRepository.findByUsernameAndNickname(username,nickname);
        return user;
    }
    /*注册管理员*/
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    /*按夜查询*/
    @Override
    public Page<User> listUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User checkEditUser(String username,String email) {
        User user = userRepository.findByUsernameAndEmail(username,email);
        return user;
    }

    @Override
    public User updateUser(Long id, User user) {
        User u = userRepository.findById(id).orElse(null);
        if(u==null){
            throw new NotFoundException("不存在该用户");
        }
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setUpdateTime(new Date());
        u.setAvatar(user.getAvatar());
        return userRepository.save(u);
    }

    @Override
    public void deletedUser(Long id) {
        userRepository.deleteById(id);
    }
}
