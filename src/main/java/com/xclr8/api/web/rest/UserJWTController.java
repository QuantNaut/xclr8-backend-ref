/**
 * UserJWTController
 *
 * version :1.0
 *
 * Created on 8/2/17.
 *
 * Copyright 2017 XCLR8
 *
 */
package com.xclr8.api.web.rest;

import com.xclr8.api.dto.APIResponseForUserDTO;
import com.xclr8.api.domain.User;
import com.xclr8.api.security.jwt.JWTConfigurer;
import com.xclr8.api.security.jwt.TokenProvider;
import com.xclr8.api.service.UserService;
import com.xclr8.api.web.rest.vm.LoginVM;

import java.util.Optional;

import com.codahale.metrics.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api")
public class UserJWTController {

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private UserService userService;

    @PostMapping("/authenticate")
    @Timed
    public ResponseEntity<APIResponseForUserDTO> authorize(@Valid @RequestBody LoginVM loginVM, HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
        APIResponseForUserDTO apiResponseForUserDTO = new APIResponseForUserDTO();
        List<User> users = new ArrayList();
        Optional<User> optionalUser = null;
        User user = null;
        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
            String jwt = tokenProvider.createToken(authentication, rememberMe);
            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
            apiResponseForUserDTO.setMessage("Authentication Success");
            apiResponseForUserDTO.setCode("200");
            apiResponseForUserDTO.setStatus("Success");
            optionalUser =  userService.getUserWithAuthoritiesByLogin(loginVM.getUsername());
            user = optionalUser.get();
            users.add(user);
            apiResponseForUserDTO.setData(users);
            apiResponseForUserDTO.setToken(new JWTToken(jwt).getIdToken());
            return ResponseEntity.ok().body(apiResponseForUserDTO);
        } catch (AuthenticationException exception) {
            apiResponseForUserDTO.setCode("401");
            apiResponseForUserDTO.setMessage("Authentication Exception");
            apiResponseForUserDTO.setStatus("Failure");
            return new ResponseEntity<>(apiResponseForUserDTO, HttpStatus.UNAUTHORIZED);
        }
    }
}
