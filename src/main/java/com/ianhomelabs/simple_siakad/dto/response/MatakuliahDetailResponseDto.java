package com.ianhomelabs.simple_siakad.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatakuliahDetailResponseDto {
    private UUID id;

    private String kode;

    private String nama;

    private Integer sks;

    private UUID dosenId;
}
