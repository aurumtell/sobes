package com.auth.controller;

import com.auth.ProfileService;
import com.auth.model.request.ProfileRequest;
import com.auth.model.response.ProfileResponse;
import com.auth.repo.UserRepository;
import com.auth.security.services.UserDetailsImpl;
import com.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@SecurityRequirement(name = "Authorization")
@Tag(description = "Api to manage users",
        name = "User Resource")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);


    @GetMapping("/user/profile")
    public ProfileResponse getProfile(@AuthenticationPrincipal UserDetailsImpl user) {
        return userService.getProfile(user);
    }

    @GetMapping("/user/profiles")
    public List<ProfileResponse> getProfiles() {
        return userService.getProfiles();
    }

    @Operation(summary = "Create profile",
            description = "Create profile")
    @PostMapping(value = "/user/profile")
    @ResponseBody
    public ProfileResponse createProfile(@RequestBody ProfileRequest profileRequest, @AuthenticationPrincipal UserDetailsImpl user){
        return userService.createProfile(profileRequest, user);
    }

    @Operation(summary = "Update profile",
            description = "Provides new updated user profile")
    @PutMapping(value = "/user/profile")
    @ResponseBody
    public ProfileResponse updateProfile(@RequestBody ProfileRequest profileRequest, @AuthenticationPrincipal UserDetailsImpl user){
        return userService.updateProfile(profileRequest, user);
    }

    @Operation(summary = "Get profile",
            description = "Get profile by user Id")
    @GetMapping(value = "/user/profile/{userId}")
    @ResponseBody
    public ProfileResponse getProfileById(@PathVariable Long userId){
        return userService.getProfileById(userId);
    }

//    @Operation(summary = "Update experience",
//            description = "Provides new updated user profile")
//    @PutMapping(value = "/user/profile/experience")
//    @ResponseBody
//    public ProfileResponse updateProfileExperince(@RequestBody ProfileRequest profileRequest, @AuthenticationPrincipal UserDetailsImpl user){
//        return userService.updateExperience(profileRequest, user);
//    }
//
//    @Operation(summary = "Update professions",
//            description = "Provides new updated user profile")
//    @PutMapping(value = "/user/profile/profession")
//    @ResponseBody
//    public ProfileResponse updateProfileProfession(@RequestBody ProfileRequest profileRequest, @AuthenticationPrincipal UserDetailsImpl user){
//        return userService.updateProfessions(profileRequest, user);
//    }

//    @Operation(summary = "Get wishlist materials", description = "Get article by favorite")
//    @GetMapping(value = "/user/material/favorite")
//    @ResponseBody
//    public List<MaterialResponse> getFavoriteMaterial(@AuthenticationPrincipal UserDetailsImpl user){
//        return userService.getSavedMaterials(user);
//    }
//
//    @PostMapping(value = "/user/save/material/{materialId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public MaterialResponse changeSaveMaterial(@PathVariable Long materialId, @RequestParam("status") Boolean status, @AuthenticationPrincipal UserDetailsImpl user) {
//        return userService.changeSaveMaterial(materialId, status, user);
//    }
//
//    @GetMapping("/user/saved/articles")
//    public List<ArticleResponse> getSavedArticles(@AuthenticationPrincipal UserDetailsImpl user) {
//        return userService.getSavedArticles(user);
//    }

//    @PostMapping(value = "/user/subscribtion/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ProfileResponse changeStatusSubscribtion(@PathVariable Long userId, @RequestParam("status") Boolean status, @AuthenticationPrincipal UserDetailsImpl user) {
//        return userService.changeStatusSubscribtion(userId, status, user);
//    }
//
//    @GetMapping("/user/subscribers")
//    public List<ProfileResponse> getSubscribers(@AuthenticationPrincipal UserDetailsImpl user) {
//        return userService.getSubscribers(user);
//    }
//
//    @GetMapping("/user/subscribtion")
//    public List<ProfileResponse> getSubscribtions(@AuthenticationPrincipal UserDetailsImpl user) {
//        return userService.getSubscribtions(user);
//    }
//
//    @GetMapping("/user/subscribtion/{userId}")
//    public List<ProfileResponse> getSubscribtions(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable Long userId) {
//        return userService.getUserSubscribtions(user, userId);
//    }
//
//    @GetMapping("/user/subscribers/{userId}")
//    public List<ProfileResponse> getUserSubscribers(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable Long userId) {
//        return userService.getUserSubscribers(user, userId);
//    }


}
