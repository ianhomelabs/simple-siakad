package com.ianhomelabs.simple_siakad.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MahasiswaDetailResponseDto {
    private UUID id;

    private String nama;

    private String nim;

    private String email;

    private List<KrsOnMahasiswaResponseDto> daftarKrs;
}
