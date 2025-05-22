package com.ianhomelabs.simple_siakad.service;

import com.ianhomelabs.simple_siakad.dto.request.CreateMahasiswaRequestDto;
import com.ianhomelabs.simple_siakad.dto.response.MahasiswaDetailResponseDto;
import com.ianhomelabs.simple_siakad.model.Mahasiswa;

import java.util.List;
import java.util.UUID;

public interface MahasiswaService {
    Mahasiswa create(Mahasiswa mahasiswa);

    List<Mahasiswa> getAll();

    Mahasiswa getById(UUID id);

    Mahasiswa update(UUID id, Mahasiswa mahasiswa);

    Mahasiswa delete(UUID id);

    MahasiswaDetailResponseDto mapToDto(Mahasiswa mahasiswa);

    Mahasiswa mapToEntity(CreateMahasiswaRequestDto mahasiswaRequestDto);
}
