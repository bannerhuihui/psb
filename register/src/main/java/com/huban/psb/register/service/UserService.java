package com.huban.psb.register.service;






import com.huban.psb.register.model.MyUser;
import com.huban.psb.register.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUser(int pageNum, int pageSize);

    void userRegister(MyUser user);

    MyUser selectOneUserByName(String userNameInput);

    String selectAsaltByName(String name);
}
