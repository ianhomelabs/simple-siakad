package com.ianhomelabs.simple_siakad.service;

import com.ianhomelabs.simple_siakad.model.Dosen;
import com.ianhomelabs.simple_siakad.model.Krs;

import java.util.List;
import java.util.UUID;

public interface KrsService {
    Krs create(Krs krs);

    List<Krs> getAll();

    Krs getById(UUID id);

    Krs update(UUID id, Krs krs);

    Krs delete(UUID id);
}
