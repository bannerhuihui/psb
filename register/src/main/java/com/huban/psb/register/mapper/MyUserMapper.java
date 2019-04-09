package com.huban.psb.register.mapper;


import com.huban.psb.register.model.MyUser;

import java.util.List;

public interface MyUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyUser record);

    int insertSelective(MyUser record);

    MyUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyUser record);

    int updateByPrimaryKey(MyUser record);

    List<MyUser> selectByUserName(String name);

}