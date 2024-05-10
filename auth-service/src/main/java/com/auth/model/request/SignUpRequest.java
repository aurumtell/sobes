package com.auth.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String email;
    private String password;
    private String username;
}