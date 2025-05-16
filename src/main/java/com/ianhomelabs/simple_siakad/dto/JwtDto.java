package com.ianhomelabs.simple_siakad.dto;


import lombok.*;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtDto {
    private UUID id;
    private String email;
    private String role;
    private String issuer;
    private Date exp;
    private Date iat;
}
