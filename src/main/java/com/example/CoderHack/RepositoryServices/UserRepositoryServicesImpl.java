package com.example.CoderHack.RepositoryServices;

import com.example.CoderHack.Repository.UserRepository;
import com.example.CoderHack.SortingUSerList.SortUserList;
import com.example.CoderHack.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserRepositoryServicesImpl implements UserRepositoryServices {

    @Autowired
    private UserRepository userRepository;



    @Override
    public List<User> getSortedUserList() {
        List<User> userList = userRepository.findAll();
        Collections.sort(userList,new SortUserList());
        return userList; // Return the sorted list
    }
}


