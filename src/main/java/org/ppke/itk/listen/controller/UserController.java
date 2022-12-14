package org.ppke.itk.listen.controller;

import org.ppke.itk.listen.domain.user.data.User;
import org.ppke.itk.listen.domain.user.repository.UserRepository;
import org.ppke.itk.listen.domain.usertrack.data.UserTrack;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<User> getUsers() {
        log.info("Calling GET /users endpoint.");
        
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping(value ="/{id}", produces = "application/json")
    public User getUserById(@PathVariable("id") Long id) {
        log.info("Calling GET /users/{id} endpoint.");

        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + User.class.getSimpleName() + " with the id: " + id));
    }

    @GetMapping(value ="/name/{name}", produces = "application/json")
    public User getUserByUserName(@PathVariable("name") String name) {
        log.info("Calling GET /users/name/{name} endpoint.");

        return userRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + User.class.getSimpleName() + " with the name: " + name));
    }

    @GetMapping(value ="/{id}/favorites", produces = "application/json")
    public List<UserTrack> getUserFavorites(@PathVariable("id") Long id) {
        log.info("Calling GET /users/{id}/favorites endpoint.");

        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + User.class.getSimpleName() + " with the id: " + id));

        return foundUser.getFavoritedTracks();
    }

    @GetMapping(value ="/{id}/followers", produces = "application/json")
    public List<User> getUserFollowers(@PathVariable("id") Long id) {
        log.info("Calling GET /users/{id}/followers endpoint.");

        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + User.class.getSimpleName() + " with the id: " + id));

        return foundUser.getFollowerUsers();
    }

    @GetMapping(value ="/{id}/following", produces = "application/json")
    public List<User> getUserFollowing(@PathVariable("id") Long id) {
        log.info("Calling GET /users/{id}/following endpoint.");

        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + User.class.getSimpleName() + " with the id: " + id));

        return foundUser.getFollowedUsers();
    }

    @GetMapping(value ="/{id}/tracks", produces = "application/json")
    public List<UserTrack> getUserTracks(@PathVariable("id") Long id) {
        log.info("Calling GET /users/{id}/tracks endpoint.");

        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + User.class.getSimpleName() + " with the id: " + id));

        return foundUser.getUserTracks();
    }
}
