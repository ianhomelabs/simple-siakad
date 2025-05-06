package com.ianhomelabs.simple_siakad.controller;

import com.ianhomelabs.simple_siakad.dto.request.CreateMahasiswaRequestDto;
import com.ianhomelabs.simple_siakad.dto.response.MahasiswaDetailResponseDto;
import com.ianhomelabs.simple_siakad.model.Mahasiswa;
import com.ianhomelabs.simple_siakad.service.MahasiswaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mahasiswa")
public class MahasiswaController {

    private final MahasiswaService mahasiswaService;

    public MahasiswaController(MahasiswaService mahasiswaService) {
        this.mahasiswaService = mahasiswaService;
    }


    @PostMapping("")
    public MahasiswaDetailResponseDto create(@RequestBody @Valid CreateMahasiswaRequestDto mahasiswaRequestDto) {
        Mahasiswa createdMahasiswa = mahasiswaService.create(Mahasiswa.builder()
                        .nama(mahasiswaRequestDto.getNama())
                        .email(mahasiswaRequestDto.getEmail())
                        .nim(mahasiswaRequestDto.getNim())
                        .password(mahasiswaRequestDto.getPassword())
                .build());

        return MahasiswaDetailResponseDto.builder()
                .id(createdMahasiswa.getId())
                .nama(createdMahasiswa.getNama())
                .nim(createdMahasiswa.getNim())
                .email(createdMahasiswa.getEmail())
                .build();
    }

    @GetMapping("/{id}")
    public MahasiswaDetailResponseDto getById(@PathVariable String id) {
        Mahasiswa mahasiswa = mahasiswaService.getById(UUID.fromString(id));
        return MahasiswaDetailResponseDto.builder()
                .id(mahasiswa.getId())
                .nama(mahasiswa.getNama())
                .nim(mahasiswa.getNim())
                .email(mahasiswa.getEmail())
                .build();
    }

    @GetMapping("")
    public List<MahasiswaDetailResponseDto> getAll() {
        List<Mahasiswa> mahasiswaList = mahasiswaService.getAll();

        return mahasiswaList.stream().map(mahasiswa -> MahasiswaDetailResponseDto.builder()
                .id(mahasiswa.getId())
                .nama(mahasiswa.getNama())
                .nim(mahasiswa.getNim())
                .email(mahasiswa.getEmail())
                .build()).toList();
    }

    @PutMapping("/{id}")
    public MahasiswaDetailResponseDto update(@PathVariable String id, @RequestBody @Valid CreateMahasiswaRequestDto mahasiswaRequestDto) {
        Mahasiswa updatedMahasiswa = mahasiswaService.update(UUID.fromString(id), Mahasiswa.builder()
                .nama(mahasiswaRequestDto.getNama())
                .email(mahasiswaRequestDto.getEmail())
                .nim(mahasiswaRequestDto.getNim())
                .password(mahasiswaRequestDto.getPassword())
                .build());

        return MahasiswaDetailResponseDto.builder()
                .id(updatedMahasiswa.getId())
                .nama(updatedMahasiswa.getNama())
                .nim(updatedMahasiswa.getNim())
                .email(updatedMahasiswa.getEmail())
                .build();
    }

    @DeleteMapping("/{id}")
    public MahasiswaDetailResponseDto delete(@PathVariable String id) {
        Mahasiswa deletedMahasiswa = mahasiswaService.delete(UUID.fromString(id));
        return MahasiswaDetailResponseDto.builder()
                .id(deletedMahasiswa.getId())
                .nama(deletedMahasiswa.getNama())
                .nim(deletedMahasiswa.getNim())
                .email(deletedMahasiswa.getEmail())
                .build();
    }
}
