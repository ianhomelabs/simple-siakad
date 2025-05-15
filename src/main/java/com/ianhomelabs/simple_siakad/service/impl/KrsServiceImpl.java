package com.ianhomelabs.simple_siakad.service.impl;

import com.ianhomelabs.simple_siakad.dto.request.KrsRequestDto;
import com.ianhomelabs.simple_siakad.dto.response.KrsResponseDto;
import com.ianhomelabs.simple_siakad.exception.DataNotFoundException;
import com.ianhomelabs.simple_siakad.model.Krs;
import com.ianhomelabs.simple_siakad.model.Mahasiswa;
import com.ianhomelabs.simple_siakad.repository.KrsRepository;
import com.ianhomelabs.simple_siakad.service.KrsService;
import com.ianhomelabs.simple_siakad.service.MahasiswaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KrsServiceImpl implements KrsService {

    private final KrsRepository krsRepository;
    private final MahasiswaService mahasiswaService;

    public KrsServiceImpl(KrsRepository krsRepository, MahasiswaService mahasiswaService) {
        this.krsRepository = krsRepository;
        this.mahasiswaService = mahasiswaService;
    }


    @Override
    public Krs create(Krs krs) {
        // Validate if mahasiswa is exists
        mahasiswaService.getById(krs.getMahasiswa().getId());

        krsRepository.save(krs);
        return krs;
    }

    @Override
    public List<Krs> getAll() {
        return krsRepository.findAll();
    }

    @Override
    public Krs getById(UUID id) {
        return krsRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Krs tidak ditemukan"));
    }

    @Override
    public Krs update(UUID id, Krs krs) {
        // Validate if krs exist
        Krs existingKrs = getById(id);

        if (!existingKrs.getMahasiswa().getId().equals(krs.getMahasiswa().getId())) {
            // Validate if mahasiswa is exists
            mahasiswaService.getById(krs.getMahasiswa().getId());
            existingKrs.getMahasiswa().setId(krs.getMahasiswa().getId());
        }
        if (!existingKrs.getSemester().equals(krs.getSemester())) {
            existingKrs.setSemester(krs.getSemester());
        }

        krsRepository.save(krs);
        return existingKrs;
    }

    @Override
    public Krs delete(UUID id) {
        // Validate if krs exist
        Krs existingKrs = getById(id);

        krsRepository.delete(existingKrs);
        return existingKrs;
    }

    @Override
    public KrsResponseDto mapToDto(Krs krs) {
        return KrsResponseDto.builder()
                .id(krs.getId())
                .mahasiswaId(krs.getMahasiswa().getId())
                .mahasiswaNama(krs.getMahasiswa().getNama())
                .semester(krs.getSemester())
                .build();
    }

    @Override
    public Krs mapToEntity(KrsRequestDto krsRequestDto) {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setId(krsRequestDto.getMahasiswaId());

        return Krs.builder()
                .mahasiswa(mahasiswa)
                .semester(krsRequestDto.getSemester())
                .build();
    }
}
