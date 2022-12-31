package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;
import java.util.List;

public interface UserService {

    User saveUser(User u);

    List<User> getAllUser();

    User getUser(String userId);


    // add delete  and update

}
