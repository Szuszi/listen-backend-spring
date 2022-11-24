package org.ppke.itk.listen.controller;

import java.util.NoSuchElementException;

import org.ppke.itk.listen.domain.user.data.User;
import org.ppke.itk.listen.domain.user.repository.UserRepository;
import org.ppke.itk.listen.domain.usertrack.data.UserTrack;
import org.ppke.itk.listen.domain.usertrack.repository.UserTrackRepository;
import org.ppke.itk.listen.model.NewUserTrack;
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
    public UserTrack getTeamById(@PathVariable("id") Long id) {
        log.info("Calling GET /user-tracks/{id} endpoint.");

        return userTrackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + UserTrack.class.getSimpleName() + " with the id: " + id));
    }

    @PostMapping
    public UserTrack saveNewTrack(@RequestBody NewUserTrack userTrack) {
        User ownerUser = userRepository.findById(userTrack.getOwnerUserId())
                .orElseThrow(() -> new NoSuchElementException("Could not find " + User.class.getSimpleName() + " with the id: " + userTrack.getOwnerUserId()));

        final UserTrack newTrack = UserTrack.builder()
            .name(userTrack.getName())
            .audioUrl(userTrack.getAudioUrl())
            .pictureUrl(userTrack.getPictureUrl())
            .ownerUser(ownerUser)
            .build();

        return userTrackRepository.save(newTrack);

        // TODO: Only allow track upload for authenticated user for their ids
    }

    @PutMapping(value ="/{id}")
    public UserTrack updateUserTrack(@RequestBody NewUserTrack userTrack, @PathVariable("id") Long id) {
        UserTrack trackToUpdate = userTrackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + UserTrack.class.getSimpleName() + " with the id: " + id));

        trackToUpdate.setName(userTrack.getName());
        trackToUpdate.setAudioUrl(userTrack.getAudioUrl());
        trackToUpdate.setPictureUrl(userTrack.getPictureUrl());

        return userTrackRepository.save(trackToUpdate);

        // TODO: Only allow track update for authenticated user for their ids
    }

    @DeleteMapping(value ="/{id}")
    public void deleteTrack(@PathVariable("id") Long id) {
        userTrackRepository.deleteById(id);

        // TODO: Handle not found (It's 500 now)
        // TODO: Only allow track delete for authenticated user for their ids
    }
}
