package com.ianhomelabs.simple_siakad.controller;

import com.ianhomelabs.simple_siakad.dto.request.KrsDetailRequestDto;
import com.ianhomelabs.simple_siakad.dto.response.KrsDetailResponseDto;
import com.ianhomelabs.simple_siakad.model.KrsDetail;
import com.ianhomelabs.simple_siakad.service.KrsDetailService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/krs-detail")
public class KrsDetailController {

    private final KrsDetailService krsDetailService;

    public KrsDetailController(KrsDetailService krsDetailService) {
        this.krsDetailService = krsDetailService;
    }

    @PostMapping("")
    public KrsDetailResponseDto create(@RequestBody @Valid KrsDetailRequestDto krsDetailRequestDto) {
        return krsDetailService.mapToDto(
                krsDetailService.create(
                        krsDetailService.mapToEntity(krsDetailRequestDto)
                )
        );
    }

    @GetMapping("")
    public List<KrsDetailResponseDto> getAll() {
        return krsDetailService.getAll().stream().map(
                krsDetailService::mapToDto
        ).toList();
    }

    @GetMapping("/{id}")
    public KrsDetailResponseDto getById(@PathVariable String id) {
        return krsDetailService.mapToDto(krsDetailService.getById(UUID.fromString(id)));
    }

    @PutMapping("/{id}")
    public KrsDetailResponseDto update(@PathVariable String id, @RequestBody @Valid KrsDetailRequestDto krsDetailRequestDto) {
        return krsDetailService.mapToDto(
                krsDetailService.update(UUID.fromString(id), krsDetailService.mapToEntity(krsDetailRequestDto))
        );
    }

    @DeleteMapping("/{id}")
    public KrsDetailResponseDto delete(@PathVariable String id) {
        return krsDetailService.mapToDto(krsDetailService.delete(UUID.fromString(id)));
    }
}
