package com.ianhomelabs.simple_siakad.repository;

import com.ianhomelabs.simple_siakad.model.Matakuliah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MatakuliahRepository extends JpaRepository<Matakuliah, UUID> {

    Optional<Matakuliah> findByNama(String nama);

    Optional<Matakuliah> findByKode(String kode);
}
