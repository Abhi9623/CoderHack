package com.example.CoderHack.Service;

import com.example.CoderHack.Repository.UserRepository;
import com.example.CoderHack.RepositoryServices.UserRepositoryServices;
import com.example.CoderHack.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepositoryServices userRepositoryServices;

    @Autowired
    private UserRepository userRepository;

    public List<User> getalluser(){
          List<User> userList=userRepositoryServices.getSortedUserList();
        return userList;
    }



    public Optional<User> getSpecificUser(String id) {
        return userRepository.findById(id); // Directly use the String ID
    }



    public String CreateUser(User user){


        user.setScore(0);
        user.setBadges(new ArrayList<>());

        User savedUser=userRepository.save(user);

        return savedUser.getId();
    }
    public User saveUser(User user) {
        List<String> badges = new ArrayList<>();

        if (user.getScore() <= 30) {
            badges.add("Code Ninja");
        } else if (user.getScore() > 30 && user.getScore() <= 60) {
            badges.add("Code Ninja");
            badges.add("Code Champ");
        } else {
            badges.add("Code Ninja");
            badges.add("Code Champ");
            badges.add("Code Master");
        }

        user.setBadges(badges);  // Set the badges
        return userRepository.save(user);  // Save and return the updated user
    }


    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }


}
