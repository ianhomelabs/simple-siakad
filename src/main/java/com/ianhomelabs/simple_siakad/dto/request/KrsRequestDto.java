package com.ianhomelabs.simple_siakad.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KrsRequestDto {
    @NotNull(message = "Mahasiswa id tidak boleh kosong")
    private UUID mahasiswaId;

    @NotNull(message = "Semester tidak boleh kosong")
    private Integer semester;
}
