package com.vladtam.springboot.controllers;

import com.vladtam.springboot.entities.*;
import com.vladtam.springboot.repos.AdvertisementRepo;
import com.vladtam.springboot.repos.CategoryRepo;
import com.vladtam.springboot.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/advertisements")
public class AdvertisementController {
    private AdvertisementRepo advertisementRepo;
    private UserRepo userRepo;
    private CategoryRepo categoryRepo;

    public AdvertisementController() {}

    @Autowired
    public AdvertisementController(AdvertisementRepo advertisementRepo, UserRepo userRepo, CategoryRepo categoryRepo) {
        this.advertisementRepo = advertisementRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping
    public String advertisementsList(Map<String, Object> model){
        Iterable<Advertisement> advertisements = advertisementRepo.findAll();
        model.put("advertisements", advertisements);
        return "advertisementPages/advertisementsListPage";
    }

    @GetMapping("/{id}")
    public String advertisementInfo(Map<String, Object> model, @PathVariable String id){
        Optional<Advertisement> advertisementOptional = advertisementRepo.findById(Long.parseLong(id));
        if(advertisementOptional.isPresent()){
            model.put("advertisement", advertisementOptional.get());
            return "advertisementPages/advertisementPage";
        }else
            return "errorPages/errorPage";
    }

    @GetMapping("/new")
    public String newAdvertisement(Map<String, Object> model){
        Iterable<User> users = userRepo.findAll();
        model.put("users", users);
        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);
        return "advertisementPages/createAdvertisementPage";
    }

    @PostMapping("/creating")
    public String newUser(@RequestParam String name, @RequestParam String description, @RequestParam String price,
                          @RequestParam String categoryId, @RequestParam String sellerId){
        Advertisement advertisement = new Advertisement(new BaseAdvertisementInfo(name, description, Double.valueOf(price)), false, new HashSet<>());
        Optional<Category> categoryOptional = categoryRepo.findById(Long.parseLong(categoryId));
        Optional<User> userOptional = userRepo.findById(Long.parseLong(sellerId));
        if(categoryOptional.isPresent() && userOptional.isPresent()) {
            advertisement.setCategory(categoryOptional.get());
            advertisement.setVendor(userOptional.get());
            advertisementRepo.save(advertisement);
            return "redirect:/advertisements";
        }else
            return "errorPages/errorPage";
    }

    @PostMapping("/{id}/deleting")
    public String deleteAdvertisement(@PathVariable String id){
        advertisementRepo.deleteById(Long.parseLong(id));
        return "redirect:/advertisements";
    }
}
