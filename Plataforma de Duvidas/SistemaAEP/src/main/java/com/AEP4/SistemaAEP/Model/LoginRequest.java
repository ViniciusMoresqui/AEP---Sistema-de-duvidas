package com.AEP4.SistemaAEP.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String senha;
}