package com.ianhomelabs.simple_siakad.controller;

import com.ianhomelabs.simple_siakad.dto.request.CreateDosenRequestDto;
import com.ianhomelabs.simple_siakad.dto.response.DosenDetailResponseDto;
import com.ianhomelabs.simple_siakad.model.Dosen;
import com.ianhomelabs.simple_siakad.service.DosenService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dosen")
public class DosenController {

    private final DosenService dosenService;

    public DosenController(DosenService dosenService) {
        this.dosenService = dosenService;
    }

    @PostMapping("")
    public DosenDetailResponseDto create(@RequestBody @Valid CreateDosenRequestDto createDosenRequestDto) {
        Dosen dosen = dosenService.create(Dosen.builder()
                        .nama(createDosenRequestDto.getNama())
                        .email(createDosenRequestDto.getEmail())
                        .nip(createDosenRequestDto.getNip())
                        .password(createDosenRequestDto.getPassword())
                        .build());

        return DosenDetailResponseDto.builder()
                .id(dosen.getId())
                .nama(dosen.getNama())
                .email(dosen.getEmail())
                .nip(dosen.getNip())
                .build();
    }

    @GetMapping("")
    public List<DosenDetailResponseDto> getAll() {
        List<Dosen> allDosen = dosenService.getAll();
        return allDosen.stream().map(dosen -> DosenDetailResponseDto.builder()
                .id(dosen.getId())
                .nama(dosen.getNama())
                .email(dosen.getEmail())
                .nip(dosen.getNip())
                .build()).toList();

    }

    @GetMapping("/{id}")
    public DosenDetailResponseDto getById(@PathVariable String id) {
        Dosen dosen = dosenService.getById(UUID.fromString(id));

        return DosenDetailResponseDto.builder()
                .id(dosen.getId())
                .nama(dosen.getNama())
                .email(dosen.getEmail())
                .nip(dosen.getNip())
                .build();
    }

    @PutMapping("/{id}")
    public DosenDetailResponseDto update(@PathVariable String id, @RequestBody @Valid CreateDosenRequestDto createDosenRequestDto) {
        Dosen dosen = dosenService.update(UUID.fromString(id), Dosen.builder()
                .nama(createDosenRequestDto.getNama())
                .email(createDosenRequestDto.getEmail())
                .nip(createDosenRequestDto.getNip())
                .password(createDosenRequestDto.getPassword())
                .build());

        return DosenDetailResponseDto.builder()
                .id(dosen.getId())
                .nama(dosen.getNama())
                .email(dosen.getEmail())
                .nip(dosen.getNip())
                .build();
    }

    @DeleteMapping("/{id}")
    public DosenDetailResponseDto delete(@PathVariable String id) {
        Dosen dosen = dosenService.delete(UUID.fromString(id));

        return DosenDetailResponseDto.builder()
                .id(dosen.getId())
                .nama(dosen.getNama())
                .email(dosen.getEmail())
                .nip(dosen.getNip())
                .build();
    }

}
