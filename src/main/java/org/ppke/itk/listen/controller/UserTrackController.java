package org.ppke.itk.listen.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.ppke.itk.listen.domain.user.data.User;
import org.ppke.itk.listen.domain.user.repository.UserRepository;
import org.ppke.itk.listen.domain.usertrack.data.UserTrack;
import org.ppke.itk.listen.domain.usertrack.repository.UserTrackRepository;
import org.ppke.itk.listen.model.NewUserTrack;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-tracks")
public class UserTrackController {

    private final UserTrackRepository userTrackRepository;
    private final UserRepository userRepository;

    @GetMapping(value ="/{id}", produces = "application/json")
    public UserTrack getTrackById(@PathVariable("id") Long id) {
        log.info("Calling GET /user-tracks/{id} endpoint.");

        return userTrackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + UserTrack.class.getSimpleName() + " with the id: " + id));
    }

    @GetMapping(value ="/newest", produces = "application/json")
    public List<UserTrack> getNewestTracks() {
        log.info("Calling GET /user-tracks/newest endpoint.");

        List<UserTrack> tracks = userTrackRepository.findAll();

        if (tracks.size() > 10) return tracks.subList(0, 10);

        return tracks;

        // TODO: List tracks sorted by createdAt attribute + add limit & pagination logic
    }

    @PostMapping
    public UserTrack saveNewTrack(@RequestBody NewUserTrack userTrack) {
        log.info("Calling POST /user-tracks endpoint.");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        log.info("User authenticated");

        User authenticatedUser = userRepository.findByName(currentPrincipalName)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + User.class.getSimpleName() + " with the name: " + currentPrincipalName));
        log.info("User entity found");

        final UserTrack newTrack = UserTrack.builder()
            .name(userTrack.getName())
            .audioUrl(userTrack.getAudioUrl())
            .pictureUrl(userTrack.getPictureUrl())
            .ownerUser(authenticatedUser)
            .build();
        log.info("New UserTrack created");

        return userTrackRepository.save(newTrack);
    }

    @PutMapping(value ="/{id}")
    public UserTrack updateTrack(@RequestBody NewUserTrack userTrack, @PathVariable("id") Long id) {
        log.info("Calling PUT /user-tracks/{id} endpoint.");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        boolean hasAdminRole = authentication.getAuthorities().stream()
          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        log.info("User authenticated");

        User authenticatedUser = userRepository.findByName(currentPrincipalName)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + User.class.getSimpleName() + " with the name: " + currentPrincipalName));
        log.info("User entity found");

        UserTrack trackToUpdate = userTrackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + UserTrack.class.getSimpleName() + " with the id: " + id));
        log.info("UserTrack is found");

        if(authenticatedUser != trackToUpdate.getOwnerUser() & !hasAdminRole) {
            throw new AccessDeniedException("Authenticated user is not authorized to edit UserTrack");
        }

        trackToUpdate.setName(userTrack.getName());
        trackToUpdate.setAudioUrl(userTrack.getAudioUrl());
        trackToUpdate.setPictureUrl(userTrack.getPictureUrl());
        log.info("UserTrack updated");

        return userTrackRepository.save(trackToUpdate);
    }

    @DeleteMapping(value ="/{id}")
    public void deleteTrack(@PathVariable("id") Long id) {
        log.info("Calling DELETE /user-tracks/{id} endpoint.");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        boolean hasAdminRole = authentication.getAuthorities().stream()
          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        
        log.info("User authenticated");

        UserTrack trackToDelete = userTrackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + UserTrack.class.getSimpleName() + " with the id: " + id));
        log.info("UserTrack is found");

        User authenticatedUser = userRepository.findByName(currentPrincipalName)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + User.class.getSimpleName() + " with the name: " + currentPrincipalName));
        log.info("User entity found");

        if(authenticatedUser != trackToDelete.getOwnerUser() & !hasAdminRole) {
            throw new AccessDeniedException("Authenticated user is not authorized to delete UserTrack");
        }

        userTrackRepository.delete(trackToDelete);
    }
}
