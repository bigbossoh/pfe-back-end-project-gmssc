package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.Dto.auth.AuthenticationRequest;
import com.bossoh.gmsscbackend.Dto.auth.AuthenticationResponse;
import com.bossoh.gmsscbackend.entities.auth.ExtentedUser;
import com.bossoh.gmsscbackend.services.auth.ApplicationUserDetailsService;
import com.bossoh.gmsscbackend.utils.JwtUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bossoh.gmsscbackend.utils.Constants.AUTHENTICATION_ENDPOINT;

@RestController

@RequestMapping(AUTHENTICATION_ENDPOINT)
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final ApplicationUserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")

    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());

        final String jwt = jwtUtil.generateToken((ExtentedUser)  userDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }


}
