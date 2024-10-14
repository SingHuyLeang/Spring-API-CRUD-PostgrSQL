package etec.app.api.controller;

import etec.app.api.entity.User;
import etec.app.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        User requestUser = userService.saveUser(user);
        if (requestUser != null) {
            System.out.println(requestUser);
            return requestUser;
        } else {
            System.err.println("Failed to save user");
        }
        return null;
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        try {
            List<User> users = userService.findAllUser();
            if (!users.isEmpty()) {
                return users;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @GetMapping("/findById")
    public User findById(@RequestParam int id) {
        try {
            User user = userService.findUserById(id);
            if (user != null) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        try {
            User updatedUser = userService.updateUser(user);
            if (updatedUser != null) {
                return updatedUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/deleteById")
    public void deleteUser(@RequestParam int id){
        userService.deleteUser(id);
    }
}
