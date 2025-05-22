package com.ianhomelabs.simple_siakad.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KrsOnMahasiswaResponseDto {
    private Integer semester;

    private List<MatakuliahOnKrsResponseDto> matakuliahList;
}
