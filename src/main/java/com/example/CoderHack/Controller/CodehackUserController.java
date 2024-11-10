package com.example.CoderHack.Controller;


import com.example.CoderHack.Service.UserServices;
import com.example.CoderHack.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class CodehackUserController {

    @Autowired
    private UserServices userServices;

    @GetMapping()
    public ResponseEntity<List<User>> getalluser(){

        return ResponseEntity.ok(userServices.getalluser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getSpecificUser(@PathVariable String id){
        Optional<User> userOptional=userServices.getSpecificUser(id);

        if(userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<String> CreateUser(@RequestBody User user){
        String result= userServices.CreateUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserScore(@PathVariable String id, @RequestBody User user) {
        // Fetch the user by ID
        Optional<User> userOptional = userServices.getSpecificUser(id);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();

            // Update fields only if provided in the request body
            if (user.getScore() != 0) {
                existingUser.setScore(user.getScore());  // Update score
            }

            if (user.getUsername() != null) {
                existingUser.setUsername(user.getUsername());  // Update username if provided
            }

            // Call the service to save and update the user
            User updatedUser = userServices.saveUser(existingUser);  // Save the updated user

            return ResponseEntity.ok(updatedUser);  // Return the updated user
        } else {
            return ResponseEntity.notFound().build();  // Return 404 if user not found
        }
    }


    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteByid(@PathVariable String id){
        Optional<User> userOptional=userServices.getSpecificUser(id);

        if(userOptional.isPresent()){
            User user=userOptional.get();
            userServices.deleteUserById(id);
            return  ResponseEntity.noContent().build();
        }
          return  ResponseEntity.notFound().build();
    }

}
