package com.ianhomelabs.simple_siakad.service.impl;

import com.ianhomelabs.simple_siakad.dto.request.CreateMatakuliahRequestDto;
import com.ianhomelabs.simple_siakad.dto.response.MatakuliahDetailResponseDto;
import com.ianhomelabs.simple_siakad.exception.BadRequestException;
import com.ianhomelabs.simple_siakad.exception.DataNotFoundException;
import com.ianhomelabs.simple_siakad.model.Dosen;
import com.ianhomelabs.simple_siakad.model.Matakuliah;
import com.ianhomelabs.simple_siakad.repository.DosenRepository;
import com.ianhomelabs.simple_siakad.repository.MatakuliahRepository;
import com.ianhomelabs.simple_siakad.service.MatakuliahService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MatakuliahServiceImpl implements MatakuliahService {

    private final MatakuliahRepository matakuliahRepository;
    private final DosenRepository dosenRepository;

    public MatakuliahServiceImpl(MatakuliahRepository matakuliahRepository, DosenRepository dosenRepository) {
        this.matakuliahRepository = matakuliahRepository;
        this.dosenRepository = dosenRepository;
    }

    @Override
    public Matakuliah create(Matakuliah matakuliah) {
        // Check duplicate matakuliah
        Optional<Matakuliah> existingMatakuliah = matakuliahRepository.findByNama(matakuliah.getNama());
        if (existingMatakuliah.isPresent()) {
            throw new BadRequestException("Nama matakuliah sudah digunakan");
        }

        Optional<Matakuliah> existingMatakuliahByKode = matakuliahRepository.findByKode(matakuliah.getKode());
        if (existingMatakuliahByKode.isPresent()) {
            throw new BadRequestException("Kode matakuliah sudah digunakan");
        }

        // Validate dosen are exists
        Dosen dosen = dosenRepository.findById(matakuliah.getDosen().getId()).orElseThrow(
                () -> new DataNotFoundException("Data dosen tidak ditemukan")
        );
        matakuliah.setDosen(dosen);

        matakuliahRepository.save(matakuliah);
        return matakuliah;
    }

    @Override
    public List<Matakuliah> getAll() {
        return matakuliahRepository.findAll();
    }

    @Override
    public Matakuliah getById(UUID id) {
        return matakuliahRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Data matakuliah tidak ditemukan"));
    }

    @Override
    public Matakuliah update(UUID id, Matakuliah matakuliah) {
        // Check existing matakuliah
        Matakuliah existingMatakuliah = getById(id);

        // Update changed value
        if (!existingMatakuliah.getNama().equals(matakuliah.getNama())) {
            existingMatakuliah.setNama(matakuliah.getNama());
        }
        if (!existingMatakuliah.getKode().equals(matakuliah.getKode())) {
            existingMatakuliah.setKode(matakuliah.getKode());
        }
        if (!existingMatakuliah.getSks().equals(matakuliah.getSks())) {
            existingMatakuliah.setSks(matakuliah.getSks());
        }
        if (!existingMatakuliah.getDosen().getId().equals(matakuliah.getDosen().getId())) {
            // Validate dosen are exists
            Dosen dosen = dosenRepository.findById(matakuliah.getDosen().getId()).orElseThrow(
                    () -> new DataNotFoundException("Data dosen tidak ditemukan")
            );

            existingMatakuliah.setDosen(dosen);
        }

        matakuliahRepository.save(existingMatakuliah);
        return existingMatakuliah;
    }

    @Override
    public Matakuliah delete(UUID id) {
        // Check existing matakuliah
        Matakuliah existingMatakuliah = getById(id);

        matakuliahRepository.delete(existingMatakuliah);
        return existingMatakuliah;
    }

    @Override
    public MatakuliahDetailResponseDto mapToDto(Matakuliah matakuliah) {
        return MatakuliahDetailResponseDto.builder()
                .id(matakuliah.getId())
                .kode(matakuliah.getKode())
                .nama(matakuliah.getNama())
                .sks(matakuliah.getSks())
                .dosenId(matakuliah.getDosen().getId())
                .dosenNama(matakuliah.getDosen().getNama())
                .build();
    }

    @Override
    public Matakuliah mapToEntity(CreateMatakuliahRequestDto createMatakuliahRequestDto) {
        return Matakuliah.builder()
                .kode(createMatakuliahRequestDto.getKode())
                .nama(createMatakuliahRequestDto.getNama())
                .sks(createMatakuliahRequestDto.getSks())
                .dosen(Dosen.builder().id(createMatakuliahRequestDto.getDosenId()).build())
                .build();
    }
}
