package com.ianhomelabs.simple_siakad.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
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

    @NotNull(message = "Semester tidak boleh kosong")
    private Integer semester;

    @ManyToOne
    @JoinColumn(name = "mahasiswa_id", referencedColumnName = "id")
    private Mahasiswa mahasiswa;

    @OneToMany(mappedBy = "krs", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<KrsDetail> krsDetails;
}
