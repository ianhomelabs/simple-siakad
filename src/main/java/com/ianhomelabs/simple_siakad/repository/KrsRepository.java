package com.ianhomelabs.simple_siakad.repository;

import com.ianhomelabs.simple_siakad.model.Krs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KrsRepository extends JpaRepository<Krs, UUID> {

}
