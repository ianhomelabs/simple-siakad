package com.ianhomelabs.simple_siakad.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_krs_detail")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KrsDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Krs id tidak boleh kosong")
    private UUID krsId;

    @NotNull(message = "Matakuliah id tidak boleh kosong")
    private UUID matakuliahId;

    @NotNull(message = "Nilai tidak boleh kosong")
    private Float nilai;
}