package com.ianhomelabs.simple_siakad.service;

import com.ianhomelabs.simple_siakad.model.Dosen;

import java.util.List;
import java.util.UUID;

public interface DosenService {
    Dosen create(Dosen dosen);

    List<Dosen> getAll();

    Dosen getById(UUID id);

    Dosen update(UUID id, Dosen dosen);

    Dosen delete(UUID id);
}
