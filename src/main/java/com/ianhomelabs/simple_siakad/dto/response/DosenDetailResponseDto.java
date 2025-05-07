package com.ianhomelabs.simple_siakad.dto.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DosenDetailResponseDto {
    private UUID id;

    private String nama;

    private String nip;

    private String email;
}
