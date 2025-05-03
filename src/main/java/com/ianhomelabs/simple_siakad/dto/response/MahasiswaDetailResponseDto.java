package com.ianhomelabs.simple_siakad.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MahasiswaDetailResponseDto {
    private String nama;
    private String jurusan;
    private String nim;
}
