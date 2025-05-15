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
        return matakuliahService.mapToDto(
                matakuliahService.create(
                        matakuliahService.mapToEntity(createMatakuliahRequestDto)
                )
        );
    }

    @GetMapping("")
    public List<MatakuliahDetailResponseDto> getAll() {
        List<Matakuliah> matakuliahList = matakuliahService.getAll();

        return matakuliahList.stream()
                .map(matakuliahService::mapToDto)
                .toList();
    }

    @GetMapping("/{id}")
    public MatakuliahDetailResponseDto getById(@PathVariable String id) {
        return matakuliahService.mapToDto(
                matakuliahService.getById(UUID.fromString(id))
        );
    }

    @PutMapping("/{id}")
    public MatakuliahDetailResponseDto create(@PathVariable String id, @RequestBody @Valid CreateMatakuliahRequestDto createMatakuliahRequestDto) {
        return matakuliahService.mapToDto(
                matakuliahService.update(
                        UUID.fromString(id),
                        matakuliahService.mapToEntity(createMatakuliahRequestDto)
                )
        );
    }

    @DeleteMapping("/{id}")
    public MatakuliahDetailResponseDto delete(@PathVariable String id) {
        return matakuliahService.mapToDto(
                matakuliahService.delete(UUID.fromString(id))
        );
    }
}
