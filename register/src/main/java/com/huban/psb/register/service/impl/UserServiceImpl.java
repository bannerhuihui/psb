package com.huban.psb.register.service.impl;


import com.github.pagehelper.PageHelper;
import com.huban.psb.register.mapper.MyUserMapper;
import com.huban.psb.register.mapper.UserMapper;
import com.huban.psb.register.model.MyUser;
import com.huban.psb.register.model.User;
import com.huban.psb.register.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响

    @Autowired
    private MyUserMapper myMapper;

    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.offsetPage(pageNum,pageSize);
        return userMapper.selectAllUser();
    }

    @Override
    public void userRegister(MyUser user) {
        myMapper.insert(user);
    }

    @Override
    public MyUser selectOneUserByName(String userNameInput) {
        List<MyUser> myUsers = myMapper.selectByUserName(userNameInput);
        if(myUsers.size()>0){
            return myUsers.get(0);
        }
        return null;
    }

    @Override
    public String selectAsaltByName(String name) {
        List<MyUser> users = myMapper.selectByUserName(name);
        if(users.size()>0){
            return users.get(0).getSalt();
        }
        return null;
    }


}
