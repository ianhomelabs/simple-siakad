package com.ianhomelabs.simple_siakad.repository;

import com.ianhomelabs.simple_siakad.model.Mahasiswa;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, UUID> {

    Optional<Mahasiswa> findByNama(@NotBlank(message = "Nama tidak boleh kosong") String nama);
}
