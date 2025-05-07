package com.ianhomelabs.simple_siakad.controller;

import com.ianhomelabs.simple_siakad.dto.request.CreateMatakuliahRequestDto;
import com.ianhomelabs.simple_siakad.dto.response.MatakuliahDetailResponseDto;
import com.ianhomelabs.simple_siakad.model.Matakuliah;
import com.ianhomelabs.simple_siakad.service.MatakuliahService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/matakuliah")
public class MatakuliahController {

    private final MatakuliahService matakuliahService;

    public MatakuliahController(MatakuliahService matakuliahService) {
        this.matakuliahService = matakuliahService;
    }

    @PostMapping("")
    public MatakuliahDetailResponseDto create(@RequestBody @Valid CreateMatakuliahRequestDto createMatakuliahRequestDto) {
        Matakuliah matakuliah = matakuliahService.create(Matakuliah.builder()
                .kode(createMatakuliahRequestDto.getKode())
                .nama(createMatakuliahRequestDto.getNama())
                .sks(createMatakuliahRequestDto.getSks())
                .dosenId(createMatakuliahRequestDto.getDosenId())
                .build());

        return MatakuliahDetailResponseDto.builder()
                .id(matakuliah.getId())
                .nama(matakuliah.getNama())
                .kode(matakuliah.getKode())
                .sks(matakuliah.getSks())
                .dosenId(matakuliah.getDosenId())
                .build();
    }

    @GetMapping("")
    public List<MatakuliahDetailResponseDto> getAll() {
        List<Matakuliah> matakuliah = matakuliahService.getAll();
        return matakuliah.stream().map(matakuliah1 -> MatakuliahDetailResponseDto.builder()
                .id(matakuliah1.getId())
                .kode(matakuliah1.getKode())
                .nama(matakuliah1.getNama())
                .sks(matakuliah1.getSks())
                .dosenId(matakuliah1.getDosenId())
                .build()).toList();
    }

    @GetMapping("/{id}")
    public MatakuliahDetailResponseDto getById(@PathVariable String id) {
        Matakuliah matakuliah = matakuliahService.getById(UUID.fromString(id));

        return MatakuliahDetailResponseDto.builder()
                .id(matakuliah.getId())
                .nama(matakuliah.getNama())
                .kode(matakuliah.getKode())
                .sks(matakuliah.getSks())
                .dosenId(matakuliah.getDosenId())
                .build();
    }

    @PutMapping("/{id}")
    public MatakuliahDetailResponseDto create(@PathVariable String id, @RequestBody @Valid CreateMatakuliahRequestDto createMatakuliahRequestDto) {
        Matakuliah matakuliah = matakuliahService.update(UUID.fromString(id), Matakuliah.builder()
                .kode(createMatakuliahRequestDto.getKode())
                .nama(createMatakuliahRequestDto.getNama())
                .sks(createMatakuliahRequestDto.getSks())
                .dosenId(createMatakuliahRequestDto.getDosenId())
                .build());

        return MatakuliahDetailResponseDto.builder()
                .id(matakuliah.getId())
                .nama(matakuliah.getNama())
                .kode(matakuliah.getKode())
                .sks(matakuliah.getSks())
                .dosenId(matakuliah.getDosenId())
                .build();
    }

    @DeleteMapping("/{id}")
    public MatakuliahDetailResponseDto delete(@PathVariable String id) {
        Matakuliah matakuliah = matakuliahService.delete(UUID.fromString(id));

        return MatakuliahDetailResponseDto.builder()
                .id(matakuliah.getId())
                .nama(matakuliah.getNama())
                .kode(matakuliah.getKode())
                .sks(matakuliah.getSks())
                .dosenId(matakuliah.getDosenId())
                .build();
    }
}
