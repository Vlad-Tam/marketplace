package com.vladtam.springboot.controllers;

import com.vladtam.springboot.entities.*;
import com.vladtam.springboot.repos.AddressRepo;
import com.vladtam.springboot.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserRepo userRepo;
    private AddressRepo addressRepo;

    public UserController() {}

    @Autowired
    public UserController(UserRepo userRepo, AddressRepo addressRepo) {
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
    }

    @GetMapping
    public String usersList(Map<String, Object> model){
        Iterable<User> users = userRepo.findAll();
        model.put("users", users);
        return "userPages/usersListPage";
    }

    @GetMapping("/{id}")
    public String userInfo(Map<String, Object> model, @PathVariable String id){
        Optional<User> userOptional = userRepo.findById(Long.parseLong(id));
        if(userOptional.isPresent()){
            model.put("user", userOptional.get());
            return "userPages/userPage";
        }else
            return "errorPages/errorPage";
    }

    @GetMapping("/new")
    public String newUser(Map<String, Object> model){
        Iterable<Address> addresses = addressRepo.findAll();
        model.put("addresses", addresses);
        return "userPages/createUserPage";
    }

    @PostMapping("/creating")
    public String newUser(@RequestParam String name, @RequestParam String surname, @RequestParam String phoneNumber,
                          @RequestParam String email, @RequestParam String password, @RequestParam String addressId){
        User user = new User(new BaseUserInfo(name, surname, phoneNumber, email, password), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        Optional<Address> addressOptional = addressRepo.findById(Long.parseLong(addressId));
        if(addressOptional.isPresent()) {
            user.setAddress(addressOptional.get());
            userRepo.save(user);
            return "redirect:/users";
        }else
            return "errorPages/errorPage";
    }

    @PostMapping("/{id}/deleting")
    public String deleteUser(@PathVariable String id){
        userRepo.deleteById(Long.parseLong(id));
        return "redirect:/users";
    }
}
