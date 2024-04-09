package com.vladtam.springboot.controllers;

import com.vladtam.springboot.entities.*;
import com.vladtam.springboot.entities.pk.WishPK;
import com.vladtam.springboot.repos.AdvertisementRepo;
import com.vladtam.springboot.repos.UserRepo;
import com.vladtam.springboot.repos.WishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/wishes")
public class WishController {
    private WishRepo wishRepo;
    private UserRepo userRepo;
    private AdvertisementRepo advertisementRepo;
    private static final String ERROR_PATH = "errorPages/errorPage";

    public WishController() {}

    @Autowired
    public WishController(WishRepo wishRepo, UserRepo userRepo, AdvertisementRepo advertisementRepo) {
        this.wishRepo = wishRepo;
        this.userRepo = userRepo;
        this.advertisementRepo = advertisementRepo;
    }

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
            Optional<User> userOptional = userRepo.findById(Long.parseLong(userId));
            if(userOptional.isPresent()) {
                model.put("user", userOptional.get());
            }else
                return ERROR_PATH;
        }
        if (advertisementId == null) {
            Iterable<Advertisement> advertisements = advertisementRepo.findAll();
            model.put("advertisements", advertisements);
        } else {
            Optional<Advertisement> advertisementOptional = advertisementRepo.findById(Long.parseLong(advertisementId));
            if(advertisementOptional.isPresent()) {
                model.put("advertisement", advertisementOptional.get());
            }else
                return ERROR_PATH;
        }
        return "wishPages/createWishPage";
    }

    @PostMapping("/creating")
    public String newWish(@RequestParam String userId, @RequestParam String advertisementId, @RequestParam String originalPage) {
        Optional<User> userOptional = userRepo.findById(Long.parseLong(userId));
        Optional<Advertisement> advertisementOptional = advertisementRepo.findById(Long.parseLong(advertisementId));
        if (userOptional.isPresent() && advertisementOptional.isPresent() && isGoodCreatePath(originalPage)) {
            Wish wish = new Wish(Integer.parseInt(userId), Integer.parseInt(advertisementId), userOptional.get(), advertisementOptional.get());
            wishRepo.save(wish);
            return "redirect:" + originalPage;
        }else
            return ERROR_PATH;
    }

    @PostMapping("/deleting")
    public String deleteWish(@RequestParam String userId, @RequestParam String advertisementId, @RequestParam String originalPage){
        if (isGoodCreatePath(originalPage)) {
            WishPK wishPK = new WishPK(Integer.parseInt(userId), Integer.parseInt(advertisementId));
            wishRepo.deleteById(wishPK);
            return "redirect:" + originalPage;
        }else
            return ERROR_PATH;
    }

    private boolean isGoodCreatePath(String path) {
        if (path.startsWith("/users/")) {
            String idPart = path.substring("/users/".length());
            return idPart.matches("\\d+");
        }else if(path.startsWith("/advertisements/")) {
            String idPart = path.substring("/advertisements/".length());
            return idPart.matches("\\d+");
        }else if(path.startsWith("/wishes")){
            return true;
        }
        return false;
    }
}
