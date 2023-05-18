package com.user.service.service;

import com.user.service.entities.User;

import java.util.List;

public interface UserService {

    // user operation

    //Create
    User saveUser(User user);

    //get  all user

    List<User> getAllUser();

    //get single user of given userID

    User getUser(String userId);

    // todo: delete
    //todo: updatet
}
