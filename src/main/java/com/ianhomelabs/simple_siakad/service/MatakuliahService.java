package com.ianhomelabs.simple_siakad.service;

import com.ianhomelabs.simple_siakad.dto.request.CreateMatakuliahRequestDto;
import com.ianhomelabs.simple_siakad.dto.response.MatakuliahDetailResponseDto;
import com.ianhomelabs.simple_siakad.model.Matakuliah;

import java.util.List;
import java.util.UUID;

public interface MatakuliahService {
    Matakuliah create(Matakuliah matakuliah);

    List<Matakuliah> getAll();

    Matakuliah getById(UUID id);

    Matakuliah update(UUID id, Matakuliah matakuliah);

    Matakuliah delete(UUID id);

    MatakuliahDetailResponseDto mapToDto(Matakuliah matakuliah);

    Matakuliah mapToEntity(CreateMatakuliahRequestDto createMatakuliahRequestDto);
}
