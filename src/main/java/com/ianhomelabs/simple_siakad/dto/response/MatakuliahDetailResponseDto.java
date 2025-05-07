package com.ianhomelabs.simple_siakad.dto.response;

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
