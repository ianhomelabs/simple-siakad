package com.ianhomelabs.simple_siakad.repository;

import com.ianhomelabs.simple_siakad.model.Dosen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DosenRepository extends JpaRepository<Dosen, UUID> {

    Optional<Dosen> findByNama(String nama);

    Optional<Dosen> findByEmail(String username);
}
