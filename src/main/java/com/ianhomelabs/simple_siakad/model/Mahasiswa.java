package com.ianhomelabs.simple_siakad.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_mahasiswa")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mahasiswa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nama tidak boleh kosong")
    private String nama;

    @NotBlank(message = "Nim tidak boleh kosong")
    private String nim;

    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Email tidak valid")
    private String email;

    @NotBlank(message = "Password tidak boleh kosong")
    private String password;

    @OneToMany(mappedBy = "mahasiswa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Krs> daftarKrs;
}
