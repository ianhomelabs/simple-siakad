package com.ianhomelabs.simple_siakad.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_matakuliah")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Matakuliah {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Kode matakuliah tidak boleh kosong")
    private String kode;

    @NotBlank(message = "Nama matakuliah tidak boleh kosong")
    private String nama;

    @NotNull(message = "SKS matakuliah tidak boleh kosong")
    private Integer sks;

    @NotNull(message = "Dosen matakuliah tidak boleh kosong")
    @Column(name = "dosen_id")
    private UUID dosenId;
}
