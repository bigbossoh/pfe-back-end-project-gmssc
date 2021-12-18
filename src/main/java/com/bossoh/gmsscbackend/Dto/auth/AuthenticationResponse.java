package com.bossoh.gmsscbackend.Dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String accessToken;
}
