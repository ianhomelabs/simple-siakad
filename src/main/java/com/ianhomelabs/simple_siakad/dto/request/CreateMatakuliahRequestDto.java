package com.ianhomelabs.simple_siakad.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMatakuliahRequestDto {
    @NotBlank(message = "Kode matakuliah tidak boleh kosong")
    private String kode;

    @NotBlank(message = "Nama matakuliah tidak boleh kosong")
    private String nama;

    @NotNull(message = "SKS matakuliah tidak boleh kosong")
    private Integer sks;

    @NotNull(message = "Dosen matakuliah tidak boleh kosong")
    private UUID dosenId;
}
