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
        return mahasiswaService.mapToDto(
                mahasiswaService.create(
                        mahasiswaService.mapToEntity(mahasiswaRequestDto)
                )
        );
    }

    @GetMapping("/{id}")
    public MahasiswaDetailResponseDto getById(@PathVariable String id) {
        return mahasiswaService.mapToDto(
                mahasiswaService.getById(UUID.fromString(id))
        );
    }

    @GetMapping("")
    public List<MahasiswaDetailResponseDto> getAll() {
        List<Mahasiswa> mahasiswaList = mahasiswaService.getAll();

        return mahasiswaList.stream().map(mahasiswaService::mapToDto).toList();
    }

    @PutMapping("/{id}")
    public MahasiswaDetailResponseDto update(@PathVariable String id, @RequestBody @Valid CreateMahasiswaRequestDto mahasiswaRequestDto) {
        return mahasiswaService.mapToDto(
                mahasiswaService.update(
                        UUID.fromString(id), mahasiswaService.mapToEntity(mahasiswaRequestDto)
                )
        );
    }

    @DeleteMapping("/{id}")
    public MahasiswaDetailResponseDto delete(@PathVariable String id) {
        return mahasiswaService.mapToDto(
                mahasiswaService.delete(UUID.fromString(id))
        );    }
}
