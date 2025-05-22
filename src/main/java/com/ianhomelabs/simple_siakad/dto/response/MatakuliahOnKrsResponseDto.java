package com.ianhomelabs.simple_siakad.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatakuliahOnKrsResponseDto {
    private String kode;

    private String nama;

    private Integer sks;
}
