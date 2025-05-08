package com.ianhomelabs.simple_siakad.dto.request;

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
public class KrsDetailRequestDto {
    @NotNull(message = "Krs id tidak boleh kosong")
    private UUID krsId;

    @NotNull(message = "Matakuliah id tidak boleh kosong")
    private UUID matakuliahId;

    @NotNull(message = "Nilai tidak boleh kosong")
    private Float nilai;
}
