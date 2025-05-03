package com.ianhomelabs.simple_siakad.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMahasiswaRequestDto {
    @NotBlank(message = "Nama tidak boleh kosong")
    private String nama;

    @NotBlank(message = "Jurusan tidak boleh kosong")
    private String jurusan;

    @NotBlank(message = "Nim tidak boleh kosong")
    private String nim;
}
