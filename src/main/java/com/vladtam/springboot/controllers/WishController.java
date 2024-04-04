package com.vladtam.springboot.controllers;

import com.vladtam.springboot.entities.*;
import com.vladtam.springboot.entities.primaryKeys.WishPK;
import com.vladtam.springboot.repos.AdvertisementRepo;
import com.vladtam.springboot.repos.UserRepo;
import com.vladtam.springboot.repos.WishRepo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wishes")
public class WishController {
    @Autowired
    WishRepo wishRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    AdvertisementRepo advertisementRepo;

    @GetMapping
    public String wishesList(Map<String, Object> model){
        Iterable<Wish> wishes = wishRepo.findAll();
        model.put("wishes", wishes);
        return "wishPages/wishesListPage";
    }

    @PostMapping("/new")
    public String newWish(Map<String, Object> model, @RequestParam String originalPage, @RequestParam(required = false) String userId, @RequestParam(required = false) String advertisementId) {
        model.put("originalPage", originalPage);

        if (userId == null) {
            Iterable<User> users = userRepo.findAll();
            model.put("users", users);
        } else {
            model.put("user", userRepo.findById(Long.parseLong(userId)).get());
        }
        if (advertisementId == null) {
            Iterable<Advertisement> advertisements = advertisementRepo.findAll();
            model.put("advertisements", advertisements);
        } else {
            model.put("advertisement", advertisementRepo.findById(Long.parseLong(advertisementId)).get());
        }
        return "wishPages/createWishPage";
    }

    @PostMapping("/creating")
    public String newWish(@RequestParam String userId, @RequestParam String advertisementId, @RequestParam String originalPage){
        Wish wish = new Wish(Integer.parseInt(userId), Integer.parseInt(advertisementId), userRepo.findById(Long.parseLong(userId)).get(), advertisementRepo.findById(Long.parseLong(advertisementId)).get());
        wishRepo.save(wish);
        return "redirect:" + originalPage;
    }

    @PostMapping("/deleting")
    public String deleteWish(@RequestParam String userId, @RequestParam String advertisementId, @RequestParam String originalPage){
        WishPK wishPK = new WishPK(Integer.parseInt(userId), Integer.parseInt(advertisementId));
        wishRepo.deleteById(wishPK);
        return "redirect:" + originalPage;
    }
}
