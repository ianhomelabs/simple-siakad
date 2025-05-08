package com.ianhomelabs.simple_siakad.service;

import com.ianhomelabs.simple_siakad.model.KrsDetail;

import java.util.List;
import java.util.UUID;

public interface KrsDetailService {
    KrsDetail create(KrsDetail krsDetail);

    List<KrsDetail> getAll();

    KrsDetail getById(UUID id);

    KrsDetail update(UUID id, KrsDetail krsDetail);

    KrsDetail delete(UUID id);

}
