package com.ianhomelabs.simple_siakad.dto.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KrsDetailResponseDto {
    private UUID id;

    private UUID krsId;

    private UUID matakuliahId;

    private Float nilai;
}
