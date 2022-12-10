package org.ppke.itk.listen.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    
    @GetMapping(value ="/me", produces = "application/json")
    public Principal getAuthenticatedUser(Principal user) {
        log.info("Calling GET /auth/me endpoint.");

        return user;
    }
}
