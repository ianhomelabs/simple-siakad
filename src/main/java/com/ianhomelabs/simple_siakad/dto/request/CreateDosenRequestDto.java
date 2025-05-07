package com.ianhomelabs.simple_siakad.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDosenRequestDto {
    @NotBlank(message = "Nama tidak boleh kosong")
    private String nama;

    @NotBlank(message = "Nip tidak boleh kosong")
    private String nip;

    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Email tidak valid")
    private String email;

    @NotBlank(message = "Password tidak boleh kosong")
    private String password;
}
