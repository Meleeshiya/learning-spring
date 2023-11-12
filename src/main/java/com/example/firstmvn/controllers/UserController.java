package com.example.firstmvn.controllers;

import com.example.firstmvn.entities.User;
import com.example.firstmvn.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public static final String SUCCESSFUL_POST_MSG = "Thanks For Posting!!";
    public static final String SUCCESSFUL_UPDATE_MSG = "Thanks for Updating!!";
    public static final String SUCCESSFUL_DELETE_MSG = "Thanks for Deleting!!";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {
        List<User> users = userService.getAll();
        // pick up here wrap in object
        return new ResponseEntity<Object>(users, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Long id) {
        User user = userService.getOne(id);
        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<String> addOne(@RequestBody User user) {
        userService.addOne(user);
        return new ResponseEntity<String>(SUCCESSFUL_POST_MSG, HttpStatus.OK);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<String> updateOne(@RequestBody User user) {
        userService.updateOne(user);
        return new ResponseEntity<String>(SUCCESSFUL_UPDATE_MSG, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteOne(@PathVariable Long id) {
        userService.deleteOne(id);
        return new ResponseEntity<String>(SUCCESSFUL_DELETE_MSG, HttpStatus.OK);
    }
}
