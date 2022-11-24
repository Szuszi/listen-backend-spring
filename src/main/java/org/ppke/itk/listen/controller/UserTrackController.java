package org.ppke.itk.listen.controller;

import java.util.NoSuchElementException;

import org.ppke.itk.listen.domain.usertrack.data.UserTrack;
import org.ppke.itk.listen.domain.usertrack.repository.UserTrackRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        log.info("Calling GET /users/{id} endpoint.");

        return userTrackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find " + UserTrack.class.getSimpleName() + " with the id: " + id));
    }
}
