package com.ianhomelabs.simple_siakad.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_krs")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Krs {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Mahasiswa id tidak boleh kosong")
    @Column(name = "mahasiswa_id")
    private UUID mahasiswaId;

    @NotNull(message = "Semester tidak boleh kosong")
    private Integer semester;
}
