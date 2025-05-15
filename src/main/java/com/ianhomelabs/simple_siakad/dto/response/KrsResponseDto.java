package com.ianhomelabs.simple_siakad.dto.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KrsResponseDto {
    private UUID id;

    private UUID mahasiswaId;

    private String mahasiswaNama;

    private Integer semester;
}
