package com.ianhomelabs.simple_siakad.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMahasiswaRequestDto {
    private String nama;
    private String jurusan;
    private String nim;
}
