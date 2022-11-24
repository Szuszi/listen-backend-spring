package org.ppke.itk.listen.controller;

import java.util.NoSuchElementException;

import org.ppke.itk.listen.domain.usertrack.data.UserTrack;
import org.ppke.itk.listen.domain.usertrack.repository.UserTrackRepository;
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

    @GetMapping(value ="/{id}", produces = "application/json")
    public UserTrack getTeamById(@PathVariable("id") Long id) {
        log.info("Calling GET /user-tracks/{id} endpoint.");

        return userTrackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + UserTrack.class.getSimpleName() + " with the id: " + id));
    }

    @PostMapping
    public UserTrack saveNewTrack(@RequestBody UserTrack userTrack) {
        UserTrack track = userTrackRepository.save(userTrack);
        return track;

        // TODO: Don't allow update (Solution: Dto)
    }

    @PutMapping(value ="/{id}")
    public UserTrack updateUserTrack(@RequestBody UserTrack userTrack, @PathVariable("id") Long id) {
        UserTrack trackToUpdate = userTrackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + UserTrack.class.getSimpleName() + " with the id: " + id));

        trackToUpdate.setName(userTrack.getName());
        trackToUpdate.setAudioUrl(userTrack.getAudioUrl());
        trackToUpdate.setPictureUrl(userTrack.getPictureUrl());

        return userTrackRepository.save(userTrack);

        // TODO: Fails if input userTrack has an invalid userId inside ownerUser
        // TODO: Weird things when path ID and userTrack.id doesn't match
    }

    @DeleteMapping(value ="/{id}")
    public void deleteTrack(@PathVariable("id") Long id) {
        userTrackRepository.deleteById(id);

        // TODO: Handle not found (It's 500 now)
    }
}
