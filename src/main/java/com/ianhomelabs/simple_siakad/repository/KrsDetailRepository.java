package com.ianhomelabs.simple_siakad.repository;

import com.ianhomelabs.simple_siakad.model.KrsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KrsDetailRepository extends JpaRepository<KrsDetail, UUID> {

}
