package com.ianhomelabs.simple_siakad.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
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

    @OneToMany(mappedBy = "matakuliah", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<KrsDetail> krsDetails;

    @ManyToOne
    @JoinColumn(name = "dosen_id", referencedColumnName = "id")
    private Dosen dosen;
}
