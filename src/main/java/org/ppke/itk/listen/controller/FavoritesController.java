package org.ppke.itk.listen.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.ppke.itk.listen.domain.user.data.User;
import org.ppke.itk.listen.domain.user.repository.UserRepository;
import org.ppke.itk.listen.domain.usertrack.data.UserTrack;
import org.ppke.itk.listen.domain.usertrack.repository.UserTrackRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoritesController {

    private final UserTrackRepository userTrackRepository;
    private final UserRepository userRepository;

    @GetMapping(value ="/{id}", produces = "application/json")
    public List<User> getFavoritesByTrackId(@PathVariable("id") Long id) {
        log.info("Calling GET /favorites/{id} endpoint.");

        UserTrack track = userTrackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + UserTrack.class.getSimpleName() + " with the id: " + id));

        return track.getUserFavorites();
    }

    @PostMapping(value ="/{id}", produces = "application/json")
    public UserTrack favoriteTrack(@PathVariable("id") Long id) {
        log.info("Calling POST /favorites/{id} endpoint.");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        log.info("User authenticated");

        User authenticatedUser = userRepository.findByName(currentPrincipalName)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + User.class.getSimpleName() + " with the name: " + currentPrincipalName));
        log.info("User entity found");

        UserTrack trackToFavorite = userTrackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + UserTrack.class.getSimpleName() + " with the id: " + id));
        log.info("UserTrack is found");

        if (trackToFavorite.getUserFavorites().contains(authenticatedUser)) {
            log.info("User are already favorited track");
            return trackToFavorite;
        }

        trackToFavorite.getUserFavorites().add(authenticatedUser);
        log.info("User successfully favorited track");

        return userTrackRepository.save(trackToFavorite);
    }

    @PutMapping(value ="/{id}", produces = "application/json")
    public UserTrack unFavoriteTrack(@PathVariable("id") Long id) {
        log.info("Calling PUT /favorites/{id} endpoint.");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        log.info("User authenticated");

        User authenticatedUser = userRepository.findByName(currentPrincipalName)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + User.class.getSimpleName() + " with the name: " + currentPrincipalName));
        log.info("User entity found");

        UserTrack trackToFavorite = userTrackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + UserTrack.class.getSimpleName() + " with the id: " + id));
        log.info("UserTrack is found");

        if (!trackToFavorite.getUserFavorites().contains(authenticatedUser)) {
            log.info("User are not favoriting the given track");
            return trackToFavorite;
        }

        trackToFavorite.getUserFavorites().remove(authenticatedUser);
        log.info("User successfully unFavorited track");

        return userTrackRepository.save(trackToFavorite);
    }
}
