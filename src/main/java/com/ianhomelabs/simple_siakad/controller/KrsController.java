package com.ianhomelabs.simple_siakad.controller;

import com.ianhomelabs.simple_siakad.dto.request.KrsRequestDto;
import com.ianhomelabs.simple_siakad.dto.response.KrsResponseDto;
import com.ianhomelabs.simple_siakad.model.Krs;
import com.ianhomelabs.simple_siakad.service.KrsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/krs")
public class KrsController {

    private final KrsService krsService;

    public KrsController(KrsService krsService) {
        this.krsService = krsService;
    }

    @PostMapping("")
    public KrsResponseDto create(@RequestBody @Valid KrsRequestDto krsRequestDto) {
        return krsService.mapToDto(
                krsService.create(
                        krsService.mapToEntity(krsRequestDto)
                )
        );
    }

    @GetMapping("")
    public List<KrsResponseDto> getAll() {
        List<Krs> allKrs = krsService.getAll();
        return allKrs.stream().map(krsService::mapToDto).toList();
    }

    @GetMapping("/{id}")
    public KrsResponseDto getById(@PathVariable String id) {
        return krsService.mapToDto(
                krsService.getById(UUID.fromString(id))
        );
    }

    @PutMapping("/{id}")
    public KrsResponseDto update(@PathVariable String id, @RequestBody @Valid KrsRequestDto krsRequestDto) {
        return krsService.mapToDto(
                krsService.update(UUID.fromString(id), krsService.mapToEntity(krsRequestDto))
        );
    }

    @DeleteMapping("/{id}")
    public KrsResponseDto delete(@PathVariable String id) {
        return krsService.mapToDto(
                krsService.delete(UUID.fromString(id))
        );
    }
}
