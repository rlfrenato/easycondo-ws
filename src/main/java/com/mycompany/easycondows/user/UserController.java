package com.mycompany.easycondows.user;

import com.mycompany.easycondows.condominium.model.Condominium;
import com.mycompany.easycondows.user.model.User;
import com.mycompany.easycondows.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user) {

        User persistedUser = userService.saveUser(user);

        return new ResponseEntity<User>(persistedUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userService.getAllUsers();

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Condominium> getUserById(@PathVariable Long userId) {

        userService.getUserById(userId);

        return new ResponseEntity(HttpStatus.OK);
    }

}
