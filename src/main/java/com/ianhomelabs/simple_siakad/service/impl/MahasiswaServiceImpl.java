package com.ianhomelabs.simple_siakad.service.impl;

import com.ianhomelabs.simple_siakad.dto.request.CreateMahasiswaRequestDto;
import com.ianhomelabs.simple_siakad.dto.response.KrsOnMahasiswaResponseDto;
import com.ianhomelabs.simple_siakad.dto.response.KrsResponseDto;
import com.ianhomelabs.simple_siakad.dto.response.MahasiswaDetailResponseDto;
import com.ianhomelabs.simple_siakad.dto.response.MatakuliahOnKrsResponseDto;
import com.ianhomelabs.simple_siakad.exception.BadRequestException;
import com.ianhomelabs.simple_siakad.exception.DataNotFoundException;
import com.ianhomelabs.simple_siakad.model.Krs;
import com.ianhomelabs.simple_siakad.model.KrsDetail;
import com.ianhomelabs.simple_siakad.model.Mahasiswa;
import com.ianhomelabs.simple_siakad.model.Matakuliah;
import com.ianhomelabs.simple_siakad.repository.MahasiswaRepository;
import com.ianhomelabs.simple_siakad.service.KrsService;
import com.ianhomelabs.simple_siakad.service.MahasiswaService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MahasiswaServiceImpl implements MahasiswaService {

    private final MahasiswaRepository mahasiswaRepository;

    public MahasiswaServiceImpl(MahasiswaRepository mahasiswaRepository) {
        this.mahasiswaRepository = mahasiswaRepository;
    }

    @Override
    public Mahasiswa create(Mahasiswa mahasiswa) {
        Optional<Mahasiswa> existingMahasiswaByNama = mahasiswaRepository.findByNama(mahasiswa.getNama());
        if (existingMahasiswaByNama.isPresent()) {
            throw new BadRequestException("Nama mahasiswa sudah ada");
        }

        mahasiswa.setPassword(BCrypt.hashpw(mahasiswa.getPassword(), BCrypt.gensalt()));
        return mahasiswaRepository.save(mahasiswa);
    }

    @Override
    public List<Mahasiswa> getAll() {
        return mahasiswaRepository.findAll();
    }

    @Override
    public Mahasiswa getById(UUID id) {
        return mahasiswaRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Data mahasiswa tidak ditemukan"));
    }

    @Override
    public Mahasiswa update(UUID id, Mahasiswa mahasiswa) {
        // Get existing mahasiswa
        Mahasiswa existingMahasiswa = getById(id);

        // Update the value
        if (!existingMahasiswa.getNama().equals(mahasiswa.getNama())) {
            Optional<Mahasiswa> byNama = mahasiswaRepository.findByNama(mahasiswa.getNama());
            if (byNama.isPresent() && !byNama.get().getId().equals(existingMahasiswa.getId())){
                throw new BadRequestException("Nama mahasiswa sudah digunakan");
            }
            existingMahasiswa.setNama(mahasiswa.getNama());
        }
        if (!existingMahasiswa.getEmail().equals(mahasiswa.getEmail())) {
            existingMahasiswa.setEmail(mahasiswa.getEmail());
        }
        if (!existingMahasiswa.getNim().equals(mahasiswa.getNim())) {
            existingMahasiswa.setNim(mahasiswa.getNim());
        }
        if (!existingMahasiswa.getPassword().equals(mahasiswa.getPassword())) {
            existingMahasiswa.setPassword(BCrypt.hashpw(mahasiswa.getPassword(), BCrypt.gensalt()));
        }

        // Save updated mahasiswa
        mahasiswaRepository.save(existingMahasiswa);

        return existingMahasiswa;
    }

    @Override
    public Mahasiswa delete(UUID id) {
        // Get existing mahasiswa
        Mahasiswa existingMahasiswa = getById(id);

        // Delete mahasiswa
        mahasiswaRepository.delete(existingMahasiswa);
        return existingMahasiswa;
    }

    @Override
    public MahasiswaDetailResponseDto mapToDto(Mahasiswa mahasiswa) {
        return MahasiswaDetailResponseDto.builder()
                .id(mahasiswa.getId())
                .email(mahasiswa.getEmail())
                .nim(mahasiswa.getNim())
                .nama(mahasiswa.getNama())
                .daftarKrs(mahasiswa.getDaftarKrs().stream().map(krs -> KrsOnMahasiswaResponseDto.builder()
                        .semester(krs.getSemester())
                        .matakuliahList(
                                krs.getKrsDetails().stream().map(krsDetail -> MatakuliahOnKrsResponseDto.builder()
                                        .kode(krsDetail.getMatakuliah().getKode())
                                        .nama(krsDetail.getMatakuliah().getNama())
                                        .sks(krsDetail.getMatakuliah().getSks())
                                        .build()).toList()
                        )
                        .build()).toList())
                .build();
    }

    @Override
    public Mahasiswa mapToEntity(CreateMahasiswaRequestDto mahasiswaRequestDto) {
        return Mahasiswa.builder()
                .password(mahasiswaRequestDto.getPassword())
                .nim(mahasiswaRequestDto.getNim())
                .email(mahasiswaRequestDto.getEmail())
                .nama(mahasiswaRequestDto.getNama())
                .build();
    }
}
